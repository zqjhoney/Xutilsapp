package com.bwie.xutilsapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Load_moreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView sendmsg;
    private Button logbt;
    private CheckBox cbbt;
    private Handler handler=new Handler();
    private EditText phone;
    private EditText msg;
    private EventHandler eventHandler;
    private int count=60;
    Runnable run=new Runnable() {
        @Override
        public void run() {

            count--;
            if(count>0){

                sendmsg.setEnabled(false);
                sendmsg.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                sendmsg.setText(count+"s");
                handler.postDelayed(this,1000);
            }else if(count==0){
                handler.removeCallbacks(this);//移除进程
                sendmsg.setEnabled(true);
                sendmsg.setText("重新发送");
                count=60;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        initview();
        registerSMS();
    }

    /**
     * registerEventHandler用来往SMSSDK中注册一个事件接收器，
     * SMSSDK允许开发者注册任意数量的接收器，所有接收器都会在事件 被触发时收到消息。
     */
    private void registerSMS() {
//回调完成
//提交验证码成功
//获取验证码成功
//返回支持发送验证码的国家列表
        // 创建EventHandler对象
        eventHandler = new EventHandler() {//子线程
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Load_moreActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    if (result == SMSSDK.RESULT_COMPLETE) {//只有回服务器验证成功，才能允许用户登录
                        //回调完成,提交验证码成功
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Load_moreActivity.this, "服务器验证成功", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Load_moreActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表

                    }
                }
            }

        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void initview() {
        phone = (EditText) findViewById(R.id.phone);//手机号
        msg = (EditText) findViewById(R.id.msg);//输入的验证码信息
        sendmsg = (TextView) findViewById(R.id.sendmsg);//发送验证码
        logbt = (Button) findViewById(R.id.log);//登录按钮
        cbbt = (CheckBox) findViewById(R.id.cbbt);//选中我已经阅读

        sendmsg.setOnClickListener(this);
        logbt.setOnClickListener(this);
    }

    public void check(){
        if(TextUtils.isEmpty(phone.getText().toString())){
            Toast.makeText(Load_moreActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(msg.getText().toString())){
            Toast.makeText(Load_moreActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }
    }


    protected void onDestroy() {
        super.onDestroy();
      SMSSDK.unregisterEventHandler(eventHandler);//注销回调接口
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.sendmsg:
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(Load_moreActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                handler.postDelayed(run,1000);//开始60s倒计时
                //请求获取短信验证码
                SMSSDK.getVerificationCode("86",phone.getText().toString());//向服务器发送验证信息

                break;
            case R.id.log:
                check();

                //提交短信验证
                SMSSDK.submitVerificationCode("86", phone.getText().toString(), msg.getText().toString());
           //     if(cbbt.isChecked()){//判断是否阅读过
             //       Toast.makeText(Load_moreActivity.this,"我要跳转了",Toast.LENGTH_SHORT).show();
                    //TODO判断验证码是否成功
            //    }else{
               //     Toast.makeText(Load_moreActivity.this,"您是否已经阅读过条款",Toast.LENGTH_SHORT).show();
                 //    return;
          //      }
                break;
        }
    }
}
