package com.zzt.rxjavasamples;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.zzt.rxjavasamples.dialog.SuperAlertDialog;
import com.zzt.rxjavasamples.dialog.SuperDialog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author: zeting
 * @date: 2020/5/20
 */
public class DialogFlowableUtil {

    public static final String TAG = DialogFlowableUtil.class.getSimpleName();

    Context context;
    // 数据发送
    FlowableEmitter<DialogData> mFlowableEmitter;
    // 数据接收
    Subscription mSubscription;
    // 当前显示dialog
    Dialog showDialog;

    private volatile static DialogFlowableUtil dialogFlowableUtil;

    public static DialogFlowableUtil getInstance() {
        if (dialogFlowableUtil == null) {
            //多线程并发解决保证原子性
            synchronized (DialogFlowableUtil.class) {
                if (dialogFlowableUtil == null) {
                    dialogFlowableUtil = new DialogFlowableUtil();
                }
            }
        }
        return dialogFlowableUtil;
    }

    private DialogFlowableUtil() {
        createFlowable();
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
    }

    /**
     * 创建观察组
     */
    public void createFlowable() {
        Flowable<DialogData> flowable = Flowable.create(new FlowableOnSubscribe<DialogData>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<DialogData> emitter) throws Throwable {
                mFlowableEmitter = emitter;
            }
        }, BackpressureStrategy.LATEST);

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DialogData>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "------onSubscribe:");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(DialogData dialogData) {
                        Log.d(TAG, "------onNext: " + dialogData.toString());
//                        showDialog(dialogData);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "------onError:");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "------onComplete:");
                    }
                });
    }

    public void showDialog(DialogData dialogData) {
        if (context != null) {
            if (dialogData.getDialogType() == DialogData.DIALOG_TYPE_SINGLE) {
                showDialog = DialogUtil.shwoDialogSingle(context, null);
            } else if (dialogData.getDialogType() == DialogData.DIALOG_TYPE_LIST) {
                showDialog = DialogUtil.shwoDialogList(context, null);
            } else {
                showDialog = DialogUtil.shwoDialog(context, null);
            }
//            if (showDialog instanceof SuperDialog) {
//                ((SuperDialog) showDialog).setOnFlowableDismissListener(dialog -> {
//                    requestFlowable(1);
//                });
//            } else if (showDialog instanceof SuperAlertDialog) {
//                ((SuperAlertDialog) showDialog).setOnFlowableDismissListener(dialog -> {
//                    requestFlowable(1);
//                });
//            }

            showDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    requestFlowable(1);
                }
            });
        }
    }


    /**
     * 测试方法
     */
    public void testFun() {
        Log.e(TAG, "----------------------测试数据----------------------");
        Log.e(TAG, "测试方法：" + mFlowableEmitter.serialize().requested());
        Log.e(TAG, "测试方法：" + mFlowableEmitter.requested());
        Log.e(TAG, "----------------------测试数据----------------------");
    }

    /**
     * 清空数据
     */
    public void clearData() {
        if (mFlowableEmitter.isCancelled()) {
            mFlowableEmitter.onComplete();
        }
//       mFlowableEmitter.serialize();
//       mFlowableEmitter.requested();
    }

    /**
     * 取消订阅所有数据
     */
    public void cancleAllData() {
        if (mSubscription != null) {
            mSubscription.cancel();
        }
    }

    /**
     * 发送数据
     *
     * @param data
     */
    public void putDataFlowable(DialogData data) {
        if (mFlowableEmitter != null && !mFlowableEmitter.isCancelled()) {
            Log.e(TAG, "上游未被请求的数量：" + mFlowableEmitter.requested());
            mFlowableEmitter.onNext(data);
            if (showDialog == null) {
                requestFlowable(1);
            }
            if (showDialog != null) {
                if (showDialog instanceof SuperDialog) {
                    if (!((SuperDialog) showDialog).isShowing()) {
                        requestFlowable(1);
                    }
                } else if (showDialog instanceof SuperAlertDialog) {
                    if (!((SuperAlertDialog) showDialog).isShowing()) {
                        requestFlowable(1);
                    }
                }
            }
        }
    }


    /**
     * 请求下一条数据
     *
     * @param n
     */
    public void requestFlowable(int n) {
        if (mSubscription != null) {
            mSubscription.request(n);
        }
    }


}
