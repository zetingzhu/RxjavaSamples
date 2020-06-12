package com.zzt.rxjavasamples;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * @author: zeting
 * @date: 2019/12/27
 */
public class DialogUtil {


    public static Dialog shwoDialog(Context context, final MyQueueCallback queueCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        return builder.show();
    }


    public static Dialog shwoDialogList(Context context, final MyQueueCallback queueCallback) {
        final String[] gender = new String[]{"好像是哦", "没看出来"};
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
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


    public static Dialog shwoDialogSingle(Context context, final MyQueueCallback queueCallback) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
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
        AlertDialog AlertDialog = builder2.create();
        AlertDialog.setTitle("类型为   3  单选");

        return builder2.show();
    }
}
