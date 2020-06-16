package com.zzt.rxjavasamples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zzt.rxjavasamples.databinding.ActivityMainBinding
import com.zzt.rxjavasamples.model.MainButtonData
import org.reactivestreams.Subscription
import java.util.*
import kotlin.jvm.*

class MainActivity : AppCompatActivity() {
    var mainButtonData: MainButtonData = MainButtonData()
    var btnList: MutableList<String> = mutableListOf("数据-测试方法", "数据-添加数据1个", "数据-添加数据多个"
            , "数据-停止接收数据", "数据-接收下一条数据", "数据-创建下一个队列")

    init {
        mainButtonData.text1.set(btnList[0])
        mainButtonData.text2.set(btnList[1])
        mainButtonData.text3.set(btnList[2])
        mainButtonData.text4.set(btnList[3])
        mainButtonData.text5.set(btnList[4])
        mainButtonData.text6.set(btnList[5])
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val TAG1 = MainActivity.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonData = mainButtonData

        // 测试按钮
        binding.tvTest.setOnClickListener {
            Log.e(TAG, "点击了测试按钮")
            mainButtonData.text3.set("测试按钮改变了数据")
        }


//        setContentView(R.layout.activity_main);
//        DialogFlowableUtil.getInstance().init(MainActivity.this);
//        findViewById(R.id.tv_test).setOnClickListener(v ->
//                // 测试方法
//                DialogFlowableUtil.getInstance().testFun()
//        );
//        findViewById(R.id.tv_add).setOnClickListener(v ->
//                // 添加一条数据
//                addDialogData(1));
//        findViewById(R.id.tv_add_t).setOnClickListener(v ->
//                // 添加多条数据
//                addDialogData(3));
//        findViewById(R.id.tv_add_th).setOnClickListener(v -> {
//            // 清除数据
//            DialogFlowableUtil.getInstance().cancleAllData();
//        });
//        findViewById(R.id.tv_add_f).setOnClickListener(v -> {
//            // 获取下一条数据
//            DialogFlowableUtil.getInstance().requestFlowable(1);
//        });
//        findViewById(R.id.tv_add_s).setOnClickListener(v -> {
//            // 创建下一个队列
//            DialogFlowableUtil.getInstance().restatrFlowable();
//        });
    }

    var mSub: Subscription? = null
    fun addDialogData(count: Int) {
        for (j in 0 until count) {
            getDialog()
        }
    }

    fun getDialog() {
        val dialogData = DialogData()
        val random = Random()
        val index = random.nextInt(3)
        dialogData.dialogType = index
        DialogFlowableUtil.getInstance().putDataFlowable(dialogData)
    }

}
