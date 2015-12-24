package com.xtside.xtbus.xtbus.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by YK on 2015/12/20.
 */
@DatabaseTable(tableName = "tb_station")
public class StationBean {

    @DatabaseField(generatedId = true)
    private int id;


    //公交路线url
    @DatabaseField(columnName = "line_url")
    private String line_url;
    //车站url
    @DatabaseField(columnName = "url")
    private String url;
    //车站名称
    @DatabaseField(columnName = "name")
    private  String name;

    public StationBean() {
    }

    public StationBean(String line_url, String url, String name) {
        this.line_url=line_url;
        this.url = url;
        this.name = name;

    }
    public String getLine_url() {
        return line_url;
    }

    public void setLine_url(String line_url) {
        this.line_url = line_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StationBean{" +
                "line_url='" + line_url + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
