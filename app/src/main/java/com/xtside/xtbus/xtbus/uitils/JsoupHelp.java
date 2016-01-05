package com.xtside.xtbus.xtbus.uitils;

import android.util.Log;

import com.xtside.xtbus.xtbus.bean.LineBean;
import com.xtside.xtbus.xtbus.bean.StationBean;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yk on 2015/12/21.
 */
public class JsoupHelp {
    public static String cookID;

    public static List<StationBean> getLineStation(String url){

        List<StationBean> lsit_station=new ArrayList<>();
        Log.i("URL",url);
        try {

            Connection.Response res = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            Document doc = res.parse();
            cookID = res.cookie("JSESSIONID");
            Log.i("JSESSIONID",cookID);

            Element content = doc.getElementById("main");
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                StationBean stationBean=new StationBean(url,linkHref,linkText);
                Log.i("bug",stationBean.toString());
                lsit_station.add(stationBean);
            }
            return lsit_station;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


    public static String getDetailByUrl(String url){


        try {
            Document mdocument = Jsoup.connect(url).cookie("JSESSIONID", cookID)
                     .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER")
                    .get();
            mdocument.head();
            Element content = mdocument.getElementById("main");
            Log.i("LLL",mdocument.toString());
            Log.i("LLL",content.toString());
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
