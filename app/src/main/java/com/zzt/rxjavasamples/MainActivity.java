package com.zzt.rxjavasamples;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;

import io.reactivex.rxjava3.core.Flowable;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogFlowableUtil.getInstance().init(MainActivity.this);

        findViewById(R.id.tv_test).setOnClickListener(v ->
                // 测试方法
                DialogFlowableUtil.getInstance().testFun()
        );
        findViewById(R.id.tv_add).setOnClickListener(v ->
                // 添加一条数据
                addDialogData(1));

        findViewById(R.id.tv_add_t).setOnClickListener(v ->
                // 添加多条数据
                addDialogData(3));

        findViewById(R.id.tv_add_th).setOnClickListener(v -> {
            // 清除数据
            DialogFlowableUtil.getInstance().cancleAllData();
        });
        findViewById(R.id.tv_add_f).setOnClickListener(v -> {
            // 获取下一条数据
            DialogFlowableUtil.getInstance().requestFlowable(1);
        });


    }

    Subscription mSub;

    public void addDialogData(int count) {
        for (int j = 0; j < count; j++) {
            getDialog();
        }
    }

    public void getDialog() {
        DialogData dialogData = new DialogData();
        Random random = new Random();
        int index = random.nextInt(3);
        dialogData.setDialogType(index);

        DialogFlowableUtil.getInstance().putDataFlowable(dialogData);
    }


}
