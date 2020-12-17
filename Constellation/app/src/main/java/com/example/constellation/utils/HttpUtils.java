package com.example.constellation.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class HttpUtils {
    public static String getJSONFromNet(String path){
        String json = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //将路径转化为url对象
        try{
            URL url = new URL(path);
            //获取网络对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //开始连接
            conn.connect();
            //读取输入流中的内容
            InputStream is = conn.getInputStream();
            //读取数据流
            int hasRead = 0;
            byte[] buf = new byte[1024];
            //循环读取
            while (true){
                hasRead = is.read(buf);
                if (hasRead == -1){
                    break;
                }
                baos.write(buf, 0, hasRead);
            }
            is.close();
            json = baos.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
