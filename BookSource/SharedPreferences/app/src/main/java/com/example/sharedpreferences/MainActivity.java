package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button saveButton;
    private Button restoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = (Button) findViewById(R.id.save_data);
        saveButton.setOnClickListener(this);
        restoreButton = (Button) findViewById(R.id.restore_data);
        restoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_data:
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
                Toast.makeText(this, "Save data successful!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.restore_data:
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                Boolean married = pref.getBoolean("married", false);
                Log.d("MainACtivity", "name : "+name);
                Log.d("MainActivity", "age : "+age);
                Log.d("MainActivity", "married : "+married);
                break;
            default:
                break;
        }
    }
}