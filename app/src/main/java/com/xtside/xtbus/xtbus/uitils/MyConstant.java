package com.xtside.xtbus.xtbus.uitils;

import  java.lang.String;
/**
 * Created by YK on 2015/12/18.
 */
public class MyConstant {

/*
public  static ArrayList getSteings(HashMap map  ){
    ArrayList<String> list = new ArrayList<String>();
    Iterator i=map.entrySet().iterator();
    while(i.hasNext()){//只遍历一次,速度快
        Map.Entry e=(Map.Entry)i.next();
        list.add(e.getKey().toString());

    }
    Collections.sort(list);
    return  list;
}
*/
    //到站查询URL
    public static String URL="http://cx.xtbus.com:83/gpsSearchStation.action?";
    //详情  gpsSearchAll.action?gps_line_id=0122&gps_line_name=3232E8B7AF&gps_station_id=0326&gps_station_name=313037C2B7BFDA&station_no=5&line_direct=1
    public static String DETAIL_URL="http://cx.xtbus.com:83/";
    //公交线路id
    public static String GPS_LINE_ID = "gps_line_id=";
    //公交线路名称
    public static String GPS_LINE_NAME = "&gps_line_name=";
    //公交方向 上行
    public static String LINE_DIRECT1 = "&line_direct=1";
    //公交方向 下行
    public static String LINE_DIRECT2 = "&line_direct=2";

    public static String enUnicode(String content){//将汉字转换为16进制数
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = content.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            //sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static final String TABLE_NAME = "xtbus.db";
}
