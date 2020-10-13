package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

class NetworkChangeReceiver extends BroadcastReceiver{
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
    }
}