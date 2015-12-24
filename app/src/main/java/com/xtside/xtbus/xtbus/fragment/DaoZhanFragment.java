package com.xtside.xtbus.xtbus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xtside.xtbus.xtbus.DaoZhan;
import com.xtside.xtbus.xtbus.R;
import com.xtside.xtbus.xtbus.adapter.LineAdapter;
import com.xtside.xtbus.xtbus.myinterface.MyItemClickListener;
import com.xtside.xtbus.xtbus.uitils.MyConstant;


import java.util.ArrayList;
import java.util.HashMap;


public class DaoZhanFragment extends Fragment implements MyItemClickListener{


    private HashMap<String, String> linemap;

    private RecyclerView mRecycerview;

    private ArrayList<String> lines;

    public DaoZhanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initdatas();

    }

    /**
     * 初始化数据
     */
    private void initdatas() {
        linemap=new HashMap<>();
        linemap.put("1路", "0101");
        linemap.put("2路东环", "0182");
        linemap.put("2路西环", "0192");
        linemap.put("3路", "0103");
        linemap.put("4路", "0104");
        linemap.put("6路", "0106");
        linemap.put("7路", "0107");
        linemap.put("8路", "0108");
        linemap.put("9路", "0199");
        linemap.put("10路", "0110");
        linemap.put("11路", "0111");
        linemap.put("12路", "0112");
        linemap.put("13路", "0113");
        linemap.put("14路", "0114");
        linemap.put("15路", "0115");
        linemap.put("16路", "0116");
        linemap.put("17路", "0117");
        linemap.put("18路", "0118");
        linemap.put("19路", "0119");
        linemap.put("20路", "0120");
        linemap.put("21路", "0121");
        linemap.put("22路", "0122");
        linemap.put("23路", "0123");
        linemap.put("24路", "0124");
        linemap.put("25路", "0125");
        linemap.put("26路", "0126");
        linemap.put("27路", "0127");
        linemap.put("28路", "0128");
        linemap.put("29路", "0129");
        linemap.put("30路", "0130");
        linemap.put("31路", "0131");
        linemap.put("32路", "0132");
        linemap.put("33路", "0133");
        linemap.put("34路", "0134");
        linemap.put("35路", "0135");
        linemap.put("36路", "0136");
        linemap.put("37路", "0137");
        linemap.put("38路", "0138");
        linemap.put("39路", "0139");
        linemap.put("40路", "0140");
        linemap.put("41路", "0141");
        linemap.put("42路", "0142");
        linemap.put("101路", "1001");
        linemap.put("102路", "1002");
        linemap.put("103路", "1003");
        linemap.put("104路", "1004");
        linemap.put("105路", "0198");
        linemap.put("106路", "1006");
        linemap.put("108路", "1008");
        linemap.put("109路", "0189");

        /*************************/

        lines=new ArrayList<>();
        lines.add("1路");
        lines.add("2路东环");
        lines.add("2路西环");
        lines.add("3路");
        lines.add("4路");
        lines.add("6路");
        lines.add("7路");
        lines.add("8路");
        lines.add("9路");
        lines.add("10路");
        lines.add("11路");
        lines.add("12路");
        lines.add("13路");
        lines.add("14路");
        lines.add("15路");
        lines.add("16路");
        lines.add("17路");
        lines.add("18路");
        lines.add("19路");
        lines.add("20路");
        lines.add("21路");
        lines.add("22路");
        lines.add("23路");
        lines.add("24路");
        lines.add("25路");
        lines.add("26路");
        lines.add("27路");
        lines.add("28路");
        lines.add("29路");
        lines.add("30路");
        lines.add("31路");
        lines.add("32路");
        lines.add("33路");
        lines.add("34路");
        lines.add("35路");
        lines.add("36路");
        lines.add("37路");
        lines.add("38路");
        lines.add("39路");
        lines.add("40路");
        lines.add("41路");
        lines.add("42路");
        lines.add("101路");
        lines.add("102路");
        lines.add("103路");
        lines.add("104路");
        lines.add("105路");
        lines.add("106路");
        lines.add("108路");
        lines.add("109路");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =inflater.inflate(R.layout.fragment_dao_zhan, container, false);
        mRecycerview =(RecyclerView)mView.findViewById(R.id.id_recyclerview);
        mRecycerview.setHasFixedSize(true);
        mRecycerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
       // lines=MyConstant.getSteings(linemap);
        LineAdapter adapter=new LineAdapter(getActivity(),lines );
        mRecycerview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        return mView;
    }

    @Override
    public void onItemClick(View view, int postion) {
       String linename=lines.get(postion);
        if(linename != null){
            String lineid=linemap.get(linename);
            Bundle mBundle=new Bundle();
            mBundle.putString("linename",linename);
            mBundle.putString("lineid",lineid);
            Intent intent=new Intent();
            intent.putExtras(mBundle);
            intent.setClass(getActivity(), DaoZhan.class);
            startActivity(intent);
           
        }
    }
}