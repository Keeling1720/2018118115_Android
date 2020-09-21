package com.example.secondhomework;

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
        Log.d("Avtivity1","Activity1_onCreate");
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

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
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Activity1", "Activity1_onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("Activity1","Activity1_onResume");
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        Log.d("Activity1","Activity1_onPostResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("Activity1", "Activity1_onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("Activity1","Activity1_onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("Activity1", "Activity1_onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("Activity1","Activity1_onRestart");
    }
}