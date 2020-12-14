package com.example.constellation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup mainRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }

    private void initWidget(){
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        //设置监听点击哪个RadioButton
        mainRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_rb_star:

                break;
            case R.id.main_rb_parent:

                break;
            case R.id.main_rb_luck:

                break;
            case R.id.main_rb_me:

                break;
        }
    }
}