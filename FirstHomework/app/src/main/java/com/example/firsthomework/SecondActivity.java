package com.example.firsthomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Button2_onCreate");
        setContentView(R.layout.second_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("SecondActivity", "Button2_onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("SecondActivity","Button2_onResume");
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        Log.d("SecondActivity","Button2_onPostResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("SecondActivity", "Button2_onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("SecondActivity","Button2_onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SecondActivity", "Button2_onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("SecondActivity","Button2_onRestart");
    }
}