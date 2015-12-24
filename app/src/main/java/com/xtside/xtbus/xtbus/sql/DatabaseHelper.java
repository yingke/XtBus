package com.xtside.xtbus.xtbus.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.xtside.xtbus.xtbus.bean.LineBean;
import com.xtside.xtbus.xtbus.bean.StationBean;
import com.xtside.xtbus.xtbus.uitils.MyConstant;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by YK on 2015/12/20.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private Map<String, Dao> daos = new HashMap<String, Dao>();


    public DatabaseHelper(Context context) {
        super(context, MyConstant.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try
        {

            TableUtils.createTable(connectionSource, StationBean.class);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try
        {

            TableUtils.dropTable(connectionSource, StationBean.class, true);

            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        context = context.getApplicationContext();
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
