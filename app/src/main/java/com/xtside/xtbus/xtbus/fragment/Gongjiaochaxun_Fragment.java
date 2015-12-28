package com.xtside.xtbus.xtbus.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amap.api.maps.MapView;
import com.xtside.xtbus.xtbus.R;


public class Gongjiaochaxun_Fragment extends Fragment {
    private EditText et_qiandian;
    private EditText et_zhongdian;
    private Button bt_seacher;
    private MapView mapView;






    public Gongjiaochaxun_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mview=inflater.inflate(R.layout.fragment_gongjiao_chaxun, container, false);
        mapView = (MapView) mview.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        initview(mview);

        return mview;

    }

    public void initview( View mview) {



        et_qiandian=(EditText) mview.findViewById(R.id.et_qidian);
        et_zhongdian=(EditText) mview.findViewById(R.id.et_zhongdian);
        bt_seacher=(Button) mview.findViewById(R.id.bt_gjseacher);
        bt_seacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qi=et_qiandian.getText().toString().trim();
                String zhongdian=et_zhongdian.toString().trim();

            }
        });
    }


}
