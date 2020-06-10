package com.zzt.rxjavasamples;

/**
 * @author: zeting
 * @date: 2020/5/20
 */
public class DialogData {

    public static final int DIALOG_TYPE_DEFAULT = 0;
    public static final int DIALOG_TYPE_LIST = 1;
    public static final int DIALOG_TYPE_SINGLE = 2;

    private int dialogType;

    public int getDialogType() {
        return dialogType;
    }

    public void setDialogType(int dialogType) {
        this.dialogType = dialogType;
    }
}
