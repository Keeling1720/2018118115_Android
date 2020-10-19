package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button createDB;
    private Button addData;
    private Button updateData;
    private Button deleteButton;
    private Button queryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        Book book = new Book();
        switch (v.getId()){
            case R.id.create_database:
                LitePal.getDatabase();
                break;
            case R.id.add_data:
                book.setName("The Da Vinci Code");
                book.setAuthor("DanBrown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("unkonw");
                book.save();
                break;
            case R.id.update_data:
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?",
                        "The Lost Symbol", "DanBrown");
                break;
            case R.id.delete_data:
                DataSupport.deleteAll(Book.class, "price < ?", "15");
                break;
            case R.id.query_data:
                List<Book> books = DataSupport.findAll(Book.class);
                for(Book bookItem : books){
                    Log.d("MainActivity", "Book name is "+book.getName());
                    Log.d("MainActivity", "Book author is "+book.getAuthor());
                    Log.d("MainActivity", "Book pages is "+book.getPages());
                    Log.d("MainActivity", "Book price is "+book.getPrice());
                    Log.d("MainActivity", "Book press is "+book.getPress());
                }
                break;
            default:
                break;
        }
    }

    private void initWindow(){
        createDB =  (Button) findViewById(R.id.create_database);
        createDB.setOnClickListener(this);
        addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(this);
        updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(this);
        deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(this);
        queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(this);
    }
}