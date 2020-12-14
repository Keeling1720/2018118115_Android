package com.example.constellation.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    /**
     * 读取Assets文件夹下的图片，返回Bitmap对象
     */
    public static Bitmap getBitmapFromAssets(Context context, String filename){
        Bitmap bitmap = null;
        //获取文件夹管理者
        AssetManager assetManager = context.getResources().getAssets();
        try {
            InputStream is = assetManager.open(filename);
            //通过位图管理器，将输入流转化成位图对象
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
