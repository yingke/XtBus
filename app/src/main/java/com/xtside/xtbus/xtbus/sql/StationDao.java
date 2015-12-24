package com.xtside.xtbus.xtbus.sql;



import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.xtside.xtbus.xtbus.bean.StationBean;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by YK on 2015/12/21.
 */
public class StationDao  {

    private Context context;
    private Dao<StationBean, Integer> StationDaoOpe;
    private DatabaseHelper helper;


    public StationDao(Context context) {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            StationDaoOpe = helper.getDao(StationBean.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Dao<StationBean, Integer> getStationDao() {
        return StationDaoOpe;
    }

    public  void addStation(StationBean stationBean){
        try
        {
            StationDaoOpe.create(stationBean);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public  void addStations(List<StationBean> stationBeans){
        try
        {
            for(int i =0;i<stationBeans.size();i++){

                StationDaoOpe.create(stationBeans.get(i));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public List<StationBean> get(String line_url)  {
        List<StationBean> stationBeens;
        try
        {

            stationBeens=  StationDaoOpe.queryBuilder().where().eq("line_url", line_url).query();

            return stationBeens ;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }


}
