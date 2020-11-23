package com.example.homework_10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private Task task;

    public MyService(){
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate被执行"+"此时线程id为"+Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand被执行"+"此时线程id为"+Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int number = 1;
                while(true){
                    task = new Task();
                    task.execute((int) Thread.currentThread().getId(), number);
                    try{
                        Thread.sleep(3000);
                        number++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy被执行"+"此时线程id为"+Thread.currentThread().getId());
    }
}
