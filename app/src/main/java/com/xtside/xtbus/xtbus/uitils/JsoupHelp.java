package com.xtside.xtbus.xtbus.uitils;

import android.util.Log;

import com.xtside.xtbus.xtbus.bean.LineBean;
import com.xtside.xtbus.xtbus.bean.StationBean;

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

    public static List<StationBean> getLineStation(String url){

        List<StationBean> lsit_station=new ArrayList<>();
        Log.i("URL",url);
        try {
            Document mdocument = Jsoup.connect(url).get();
            Element content = mdocument.getElementById("main");
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                StationBean stationBean=new StationBean(url,linkHref,linkText);

                lsit_station.add(stationBean);
            }
            return lsit_station;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


    public static String getDetailByUrl(String url){

        Log.i("LLL",url);
        try {
            Document mdocument = Jsoup.connect(url).get();
            Element content = mdocument.getElementById("main");


            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
