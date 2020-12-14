package com.example.constellation.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AssetsUtils {
    /**
     * 读取assets文件夹当中的内容，存放到字符串中
     */
    public static String getJsonFromAssets(Context context, String filename){
        //获取Assets文件夹管理器
        AssetManager assetManager = context.getResources().getAssets();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //获取输入流
        try {
            InputStream is = assetManager.open(filename);
            //读取内存放到内存流中
            int hasRead = 0;
            byte[] buf = new byte[1024];
            while (true){
                hasRead = is.read(buf);
                if(hasRead == -1){
                    break;
                }
                baos.write(buf, 0, hasRead);
            }
            String msg = baos.toString();
            is.close();
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
