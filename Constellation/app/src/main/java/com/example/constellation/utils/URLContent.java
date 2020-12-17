package com.example.constellation.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLContent {
    //星座配对接口
    public static String getPartnerURL(String men, String women){
        men = men.replace("座","");
        women = women.replace("座","");
        try {
            men = URLEncoder.encode(men, "UTF-8");
            women = URLEncoder.encode(women, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "http://apis.juhe.cn/xzpd/query?men="+men+"&women="+women+"&key=951ce4468402f012497477f8b9fdeff0";
        return url;
    }

    //星座年运势接口
    public static String getLuckURL(String name){
        try{
            name = URLEncoder.encode(name, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String url = "http://web.juhe.cn:8080/constellation/getAll?consName="+name+"&type=year&key=aa6d72fd00612687271f1ed4c1c00ab2";
        return url;
    }
}
