package com.xtside.xtbus.xtbus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.xtside.xtbus.xtbus.adapter.StationAdapter;
import com.xtside.xtbus.xtbus.bean.StationBean;
import com.xtside.xtbus.xtbus.myinterface.MyItemClickListener;
import com.xtside.xtbus.xtbus.sql.StationDao;
import com.xtside.xtbus.xtbus.uitils.JsoupHelp;
import com.xtside.xtbus.xtbus.uitils.MyConstant;
import java.util.ArrayList;
import java.util.List;
import com.pnikosis.materialishprogress.ProgressWheel;

public class DaoZhan extends AppCompatActivity implements MyItemClickListener {

    private String linename;
    private String lineid ;
    private Button but_shangxing;
    private Button but_xiaxing;
    private RecyclerView recyclerView;
    private StationAdapter adapter;
    private final static int ISNET=0;
    private final static int ISSQL=1;
    private String url;
    private String shangxing_url;
    private String xiaxing_url;
    private StationDao stationDao;
    private List<StationBean> stations;
    private ArrayList<StationBean> list_stations;
    public AlertDialog my_dialog;



    public Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case ISNET :

                    list_stations.clear();
                    for (int i=0;i<stations.size();i++){
                        list_stations.add(stations.get(i));
                        adapter.notifyDataSetChanged();
                    }
                    but_shangxing.setText("开往："+getname(list_stations.get                                            (list_stations.size()-1).getName()));
                    but_xiaxing.setText("开往：" + getname(list_stations.get                                          (0).getName()));
                    my_dialog.dismiss();
                    break;
                case ISSQL :
                    list_stations.clear();
                    for (int i=0;i<stations.size();i++){
                        list_stations.add(stations.get(i));
                        adapter.notifyDataSetChanged();
                    }
                    my_dialog.dismiss();
                    break;
                default :
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_zhan);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        linename=bundle.getString("linename");
        lineid = bundle.getString("lineid");


        initview();


    }

    private void initview() {

        url= MyConstant.URL+MyConstant.GPS_LINE_ID+ lineid+
                MyConstant.GPS_LINE_NAME+
               MyConstant.enUnicode(linename) ;
        shangxing_url=url+MyConstant.LINE_DIRECT1;
        xiaxing_url=url+MyConstant.LINE_DIRECT2;

        setTitle(linename);
        Toolbar toolbar = (Toolbar) findViewById(R.id.daozhantoolbar);
        setSupportActionBar(toolbar);

        but_shangxing = (Button)findViewById(R.id.btn_shangxing);
        but_xiaxing = (Button)findViewById(R.id.btn_xiaxing);

        recyclerView=(RecyclerView) findViewById(R.id.daozhan_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2) );
        list_stations=new ArrayList<StationBean>();
        adapter=new StationAdapter(this,list_stations);
        adapter.setOnItemClickListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        stationDao = new StationDao(DaoZhan.this);



        select(shangxing_url,ISNET);


    }

    public void shangxing(View v){

        but_xiaxing.setBackgroundResource(R.color.huise);

        but_shangxing.setBackgroundResource(R.color.colorAccent);

        select(shangxing_url,ISNET);

    }

    public void xiaxing(View v){
        but_shangxing.setBackgroundResource(R.color.huise);

        but_xiaxing.setBackgroundResource(R.color.colorAccent);


        select(xiaxing_url, ISSQL);
    }

    /**
     *网络请求数据
     */
    private void getDatabyNet(final  String dir_url,final  int m){
        showdialog_my();
        new Thread(){
            @Override
            public void run() {
                super.run();
                stations.clear();
                stations=JsoupHelp.getLineStation(dir_url);
                stationDao.addStations(stations);
                Message msg=mHandler.obtainMessage();
                msg.what=m;
                mHandler.sendMessage(msg);
            }
        }.start();

        Log.i("网络请求数据", "网络请求数据");
    }

    /**
     * 本地缓存查询数据
     */
    private void getDatabySql(final String s,final  int m){
        showdialog_my();
        new Thread(){
            @Override
            public void run() {
                super.run();
                stations.clear();
                stations=stationDao.get(s);
                Message msg=mHandler.obtainMessage();
                msg.what=m;
                mHandler.sendMessage(msg);
            }
        }.start();
        Log.i("本地缓存查询数据", "本地缓存查询数据");

    }

    @Override
    public void onItemClick(View view, int postion) {
        StationBean stationBean = list_stations.get(postion);
        if (stationBean!=null){
            String stationname=getname(stationBean.getName());
            String url=stationBean.getUrl();
            Bundle mBundle=new Bundle();
            mBundle.putString("stationname",stationname);
            mBundle.putString("url",url);
            mBundle.putString("linename",linename);
            startActivity(new Intent(DaoZhan.this,DaoZhanDetail.class).putExtras(mBundle));
        }

    }

    public  String getname(String s){
        String[] name=s.split("\\.");

        return name[1];
    }

    public  void select(String s, int msg){

        stations=stationDao.get(s);

        if ( stations.size()>0){

            getDatabySql(s,msg);

        } else {
            if(isNetworkConnected(DaoZhan.this)){
                getDatabyNet(s,msg);
            }else {
                Snackbar.make(recyclerView,"年轻人,你手机现在没有网络，啥也干不了啊",Snackbar.LENGTH_LONG).show();

            }



        }
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

    /********************************
     * 加载对话框
     */
    public void showdialog_my() {

        ProgressWheel wheel = new ProgressWheel(DaoZhan.this);
        wheel.setProgress(0.75f);
        wheel.setBarColor(Color.GREEN);
        my_dialog = new AlertDialog.Builder(DaoZhan.this).create();
        my_dialog.show();
        my_dialog.getWindow().setContentView(wheel);
        //my_dialog.getWindow().setLayout(200, 200);

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
