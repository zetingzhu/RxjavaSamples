package com.zzt.rxjavasamples;

import android.content.Context;
import android.content.DialogInterface;

import com.zzt.rxjavasamples.dialog.SuperAlertDialog;
import com.zzt.rxjavasamples.dialog.SuperDialog;

/**
 * @author: zeting
 * @date: 2019/12/27
 */
public class DialogUtil {


    public static SuperDialog shwoDialog(Context context, final MyQueueCallback queueCallback) {
        SuperAlertDialog.Builder builder = new SuperAlertDialog.Builder(context);
        builder.setTitle("类型为   1  确定");
        builder.setMessage("我说我是一个对话框，你说是吗？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (queueCallback != null) {
                    queueCallback.callQueue(true);
                }
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("不是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (queueCallback != null) {
                    queueCallback.callQueue(true);
                }
                dialog.dismiss();
            }
        });
        return   builder.show();
    }


    public static SuperDialog shwoDialogList(Context context, final MyQueueCallback queueCallback) {
        final String[] gender = new String[]{"好像是哦", "没看出来"};
        SuperAlertDialog.Builder builder1 = new SuperAlertDialog.Builder(context);
        builder1.setTitle("类型为   2  列表");
        builder1.setItems(gender, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (queueCallback != null) {
                    queueCallback.callQueue(true);
                }
                dialog.dismiss();
            }
        });
        return builder1.show();
    }



    public static SuperDialog  shwoDialogSingle(Context context, final MyQueueCallback queueCallback) {
        SuperAlertDialog.Builder builder2 = new SuperAlertDialog.Builder(context);
        builder2.setTitle("类型为   3  单选");
        final String[] dish = new String[]{"flutter", "java", "android", "kotlin"};
        builder2.setSingleChoiceItems(dish, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (queueCallback != null) {
                    queueCallback.callQueue(true);
                }
                dialog.dismiss();
            }
        });
        SuperAlertDialog superAlertDialog = new SuperAlertDialog(context) ;
        superAlertDialog.setTitle("类型为   3  单选");

        return   builder2.show();
    }
}
