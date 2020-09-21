package com.example.secondhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Avtivity4","Activity_onCreate");
        setContentView(R.layout.activity_main4);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Activity4", "Activity4_onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("Activity4","Activity4_onResume");
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        Log.d("Activity4","Activity4_onPostResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("Activity4", "Activity4_onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("Activity4","Activity4_onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("Activity4", "Activity4_onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("Activity4","Activity4_onRestart");
    }
}