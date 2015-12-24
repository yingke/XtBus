package com.xtside.xtbus.xtbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xtside.xtbus.xtbus.R;
import com.xtside.xtbus.xtbus.myinterface.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YK on 2015/12/18.
 */
public class LineAdapter extends  RecyclerView.Adapter<MyViewHolder>  {
    private Context context;
    private List<String> list = new ArrayList<String>();
    private MyItemClickListener mListener;


    public LineAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView= LayoutInflater.from(context).inflate(R.layout.line_item, parent, false);


        return  new MyViewHolder(mView,mListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s=list.get(position);
        holder.tv.setText(s);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }


}
class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyItemClickListener mListener;
    TextView tv;

    public MyViewHolder(View view) {
        super(view);
        tv =(TextView)view.findViewById(R.id.id_num);
    }
    public MyViewHolder(View view,MyItemClickListener listener) {
        super(view);
        tv =(TextView)view.findViewById(R.id.id_num);
        this.mListener=listener;
        view.setOnClickListener(this);
    }

    /**
     * 点击监听
     */
    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v, getPosition());
        }
    }


}