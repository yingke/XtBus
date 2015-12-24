package com.xtside.xtbus.xtbus.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by YK on 2015/12/20.
 */

@DatabaseTable(tableName = "tb_line")

public class LineBean {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "lineurl")
    private String line_url;

    public LineBean(String line_url) {
        this.line_url = line_url;
    }

    public int getId() {
        return id;
    }

    public String getLine_url() {
        return line_url;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLine_url(String line_url) {
        this.line_url = line_url;
    }



}
