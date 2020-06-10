package com.zzt.rxjavasamples.dialog;


import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

public class SuperDialog extends AppCompatDialog {

    private OnFlowableDismissListener onFlowableDismissListener;

    public SuperDialog(Context context) {
        super(context);
    }

    public SuperDialog(Context context, int theme) {
        super(context, theme);
    }

    protected SuperDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnFlowableDismissListener(OnFlowableDismissListener onFlowableDismissListener) {
        this.onFlowableDismissListener = onFlowableDismissListener;
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        if (onFlowableDismissListener != null) {
            onFlowableDismissListener.onDismiss(this);
        }
        super.setOnDismissListener(listener);
    }

    public interface OnFlowableDismissListener {
        void onDismiss(DialogInterface dialog);
    }
}
