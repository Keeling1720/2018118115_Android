package com.example.constellation.utils;

public class URLContent {
    //星座配对接口
    public static String getPartnerURL(String men, String women){
        men = men.replace("座","");
        women = women.replace("座","");
        String url = "http://apis.juhe.cn/xzpd/query?men="+men+"&women="+women+"&key=951ce4468402f012497477f8b9fdeff0";
        return url;
    }
}
