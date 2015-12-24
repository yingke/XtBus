package com.xtside.xtbus.xtbus.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtside.xtbus.xtbus.R;
import com.xtside.xtbus.xtbus.bean.StationBean;
import com.xtside.xtbus.xtbus.myinterface.MyItemClickListener;

import java.util.ArrayList;

/**
 * Created by YK on 2015/12/21.
 */
public class StationAdapter extends RecyclerView.Adapter<StationViewHolder>{

    private Context context;
    private ArrayList<StationBean> stations;
    private MyItemClickListener mListener;

    public StationAdapter(Context context, ArrayList<StationBean> stations) {
        this.context = context;
        this.stations = stations;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView= LayoutInflater.from(context).inflate(R.layout.station_layout, parent, false);

        return new StationViewHolder(mView,mListener);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        String s=stations.get(position).getName();
        holder.tv.setText(s);

    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mListener = listener;
    }
}



class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyItemClickListener mListener;
    TextView tv;

    public StationViewHolder(View view) {
        super(view);
        tv =(TextView)view.findViewById(R.id.station_tv);

    }
    public StationViewHolder(View view,MyItemClickListener listener) {
        super(view);
        tv =(TextView)view.findViewById(R.id.station_tv);
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
