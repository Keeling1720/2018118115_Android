package com.example.thirdhomework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private EditText editText;
    private  Button edit_Button;
    private ImageView image;
    private Button image_Button;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        initWindow();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("退出提示");
                dialog.setMessage("是否确认退出");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                break;

            case R.id.edit_button:
                String input = editText.getText().toString();
                Toast.makeText(MainActivity.this, input,
                        Toast.LENGTH_SHORT).show();
                editText.setText("");
                break;

            case R.id.image_bt:
                if(count %2 == 0){
                    image.setImageResource(R.drawable.dog);
                }else{
                    image.setImageResource(R.drawable.cat);
                }
                count++;
                break;
            default:
                break;
        }
    }

    private void initWindow(){
        backButton = findViewById(R.id.title_back);
        editText = findViewById(R.id.edit_text);
        edit_Button = findViewById(R.id.edit_button);
        image = findViewById(R.id.imageView);
        image_Button = findViewById(R.id.image_bt);

        backButton.setOnClickListener(this);
        edit_Button.setOnClickListener(this);
        image_Button.setOnClickListener(this);
    }
}