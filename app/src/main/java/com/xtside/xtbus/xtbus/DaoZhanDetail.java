package com.xtside.xtbus.xtbus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xtside.xtbus.xtbus.uitils.JsoupHelp;
import com.xtside.xtbus.xtbus.uitils.MyConstant;

import java.util.Timer;
import java.util.TimerTask;


public class DaoZhanDetail extends AppCompatActivity {

    private  final  static  int STATIONMSG=0;

    public AlertDialog my_dialog;
    private TextView tv;
    private  String detail;
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STATIONMSG:

                    my_dialog.dismiss();

                    tv.setText(getname(detail));

                    break;
                /*case TIMEMSG:

                    time--;
                    tv_time.setText(msg.getData().getInt("time")+"  秒之后车辆信息更新");
                    break;*/

                default:
                    break;
            }
        }
    };
    private  String url;
    private FloatingActionButton fabBtn;
    private TimerTask mTimerTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_zhan_detail);


        Intent intent = getIntent();
        Bundle mBundle = intent.getExtras();
        String stationname = mBundle.getString("stationname");
        url = mBundle.getString("url");
        String linename = mBundle.getString("linename");
        setTitle(linename+":"+stationname);


        tv=(TextView)findViewById(R.id.detail_tv);
       // tv_time=(TextView)findViewById(R.id.detail_time);

        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseTimer();
                if (isNetworkConnected(DaoZhanDetail.this)){
                    starttimer(url);
                }else {
                    tv.setText("手机暂时无法连接网络");
                }
            }
        });
        if (isNetworkConnected(this)){
            starttimer(url);
        }else {
            tv.setText("手机暂时无法连接网络");
        }

    }


    public  String getname(String s){

        String[] name=s.split("<br>");
        Log.i("bug",name.length+name.toString());

        if (name.length ==5){
            return name[2]+"\n"+name[3];
        }else if (name.length==4){
            return name[2];
        }else {
            return "暂无车辆信息";
        }

    }

    /********************************
     * 加载对话框
     */
    public void showdialog_my() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.jiazaitiao,
                        null);
        my_dialog = new AlertDialog.Builder(DaoZhanDetail.this).create();
        my_dialog.show();
        my_dialog.getWindow().setContentView(view);
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public void starttimer(final String url){

        if (mTimerTask==null){

            showdialog_my();

            mTimerTask=new TimerTask() {
                @Override
                public void run() {
                    detail= JsoupHelp.getDetailByUrl(MyConstant.DETAIL_URL+url);

                    Message msg=mHandler.obtainMessage();
                    msg.what=STATIONMSG;
                    mHandler.sendMessage(msg);
                }
            };

            mTimer = new Timer();

            //第一个参数为执行的mTimerTask
            //第二个参数为延迟的时间 这里写1000的意思是mTimerTask将延迟1秒执行
            //第三个参数为多久执行一次 这里写1000表示每1秒执行一次mTimerTask的Run方法
            mTimer.schedule(mTimerTask, 0, 60000);

        }

    }

    public void CloseTimer() {

        //在这里关闭mTimer 与 mTimerTask
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask = null;
        }
Log.i("线程结束","线程结束");
    }

    @Override
    protected void onStop() {
        super.onStop();
       // CloseTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CloseTimer();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }

}
