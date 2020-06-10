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

        findViewById(R.id.tv_add).setOnClickListener(v -> addDialogData(1));

        findViewById(R.id.tv_add_t).setOnClickListener(v -> addDialogData(2));

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
