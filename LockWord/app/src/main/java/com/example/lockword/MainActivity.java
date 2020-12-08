package com.example.lockword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mingrisoft.greendao.entity.greendao.CET4Entity;
import com.mingrisoft.greendao.entity.greendao.CET4EntityDao;
import com.mingrisoft.greendao.entity.greendao.DaoMaster;
import com.mingrisoft.greendao.entity.greendao.DaoSession;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //用来显示单词和音标
    private TextView timeText, dateText, wordText, englishText;
    //用来播放声音
    private ImageView playVoice;
    //用来显示时间
    private String month, day, way, hours, minute;
    //合成对象
    private SpeechSynthesizer speechSynthesizer;
    //锁屏
    private KeyguardManager km;
    private KeyguardManager.KeyguardLock kl;
    private RadioGroup radioGroup;      //加载单词的三个选项
    private RadioButton radioOne, radioTwo, radioThree;     //单词意思的三个选项
    private SharedPreferences sharedPreferences;        //定义轻量级数据库
    SharedPreferences.Editor editor = null;         //编辑数据库
    int j = 0;              //记录答题数
    List<Integer> list;     //判断题的数量
    List<CET4Entity> datas; //用于从数据库读取相应的词库
    int k;
    /*
    *  手指按下的位置坐标为(x1, y1)
    *  手指离开时位置坐标为(x2, y2)
     */
    float x1 = 0;
    float y1 = 0;
    float x2 = 0;
    float y2 = 0;

    private SQLiteDatabase db;                  //创建数据库
    private DaoMaster daoMaster, dbManager;     //管理者
    private DaoSession daoSession, dbSession;   //和数据库进行会话
    //对应的表，由java代码生成的，对数据库内相应的表操作使用此对象
    private CET4EntityDao questionDao, dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}