package com.example.firsthomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","Button1_onCreate");
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity", "Button1_onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity","Button1_onResume");
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        Log.d("MainActivity","Button1_onPostResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", "Button1_onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity","Button1_onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity", "Button1_onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity","Button1_onRestart");
    }
}