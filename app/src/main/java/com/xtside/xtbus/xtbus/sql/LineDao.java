package com.xtside.xtbus.xtbus.sql;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.xtside.xtbus.xtbus.bean.LineBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by YK on 2015/12/20.
 */
public class LineDao {

    private Context context;
    private Dao<LineBean, Integer> LineDaoOpe;
    private DatabaseHelper helper;

    public Dao<LineBean, Integer> getLineDaoOpe() {
        return LineDaoOpe;
    }

    public LineDao(Context context){
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            LineDaoOpe = helper.getDao(LineDao.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

  public void addLine(LineBean lineBean){

      try
      {
          LineDaoOpe.create(lineBean);
      } catch (SQLException e)
      {
          e.printStackTrace();
      }
  }


    public List<LineBean> get(String linename)  {

        try
        {
        QueryBuilder<LineBean, Integer> queryBuilder = LineDaoOpe
                    .queryBuilder();
        Where<LineBean, Integer> where = queryBuilder.where();
         where.eq("line_url", linename);

            return  queryBuilder.query();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

       }
}
