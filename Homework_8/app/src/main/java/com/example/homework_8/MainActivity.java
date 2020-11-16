package com.example.homework_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int UPDATE_TEXT = 1;
    private Button change_text;
    private TextView text;
    private Handler handler = new Handler(){
      public void handleMessage(Message msg){
          switch (msg.what){
              case UPDATE_TEXT:
                  text.setText("Nice to meet you");
                  break;
              default:
                  break;
          }
      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change_text = (Button) findViewById(R.id.change_text);
        text = (TextView) findViewById(R.id.text1);
        change_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:

                break;
            default:
                break;
        }
    }
}