package com.example.nine_patch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(this);
    }

    private void initMsg(){
        Msg msg1 = new Msg("Hello guy.", Msg.Type_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello, Who is that?",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.Type_RECEIVED);
        msgList.add(msg3);
    }

    @Override
    public void onClick(View v) {
        String content = inputText.getText().toString();
        if(! "".equals(content)){
            Msg msg = new Msg(content, Msg.TYPE_SEND);
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);//当由新消息时，刷新RecyclerView中的显示
            msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
            inputText.setText("");
        }
    }
}