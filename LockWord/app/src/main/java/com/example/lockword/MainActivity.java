package com.example.lockword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.assetsbasedata.AssetsDatabaseManager;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.mingrisoft.greendao.entity.greendao.CET4Entity;
import com.mingrisoft.greendao.entity.greendao.CET4EntityDao;
import com.mingrisoft.greendao.entity.greendao.DaoMaster;
import com.mingrisoft.greendao.entity.greendao.DaoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, SynthesizerListener {
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
    private DaoMaster daoMaster, dbMaster;     //管理者
    private DaoSession daoSession, dbSession;   //和数据库进行会话
    //对应的表，由java代码生成的，对数据库内相应的表操作使用此对象
    private CET4EntityDao questionDao, dbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将锁屏页面显示到手机屏幕的最上层
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //初始化轻量级数据库
        sharedPreferences = getSharedPreferences("share", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();      //初始化轻量级数据库编辑器
        //给播放单词语音的设置appid
        list = new ArrayList<Integer>();
        /**
         * 添加十个20以内的随机数
         */
        Random r = new Random();
        int i;
        while (list.size() < 10){
            i = r.nextInt(20);
            if(! list.contains(i)){
                list.add(i);
            }
        }
        /**
         * 得到键盘锁管理对象
         */
        km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        kl = km.newKeyguardLock("unLock");
        //初始化。只需要调用一次
        AssetsDatabaseManager.initManager(this);
        //获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        //通过管理对象获取数据库
        SQLiteDatabase db1 = mg.getDatabase("word.db");
        //对数据库进行操作
        daoMaster = new DaoMaster(db1);
        daoSession = daoMaster.newSession();
        questionDao = daoSession.getCET4EntityDao();
        /**
         * 此DevOpenHelper类继承自SQLiteOpenHelper,
         * 第一个参数Context,第二个参数时数据库名字,第三个参数CursorFactory
         */
        DaoMaster.DevOpenHelper helper = new DaoMaster.
                DevOpenHelper(this, "wrong.db", null);
        /**
         * 初始化数据库
         */
        db = helper.getWritableDatabase();
        dbMaster = new DaoMaster(db);
        dbSession = dbMaster.newSession();
        dbDao = dbSession.getCET4EntityDao();
        /**
         * 初始化控件
         */
        //用于显示分钟绑定id
        timeText = (TextView) findViewById(R.id.time_text);
        //用于显示日期绑定id
        dateText = (TextView) findViewById(R.id.date_text);
        //用于显示单词绑定id
        wordText = (TextView) findViewById(R.id.word_text);
        //用于显示英标绑定id
        englishText = (TextView) findViewById(R.id.english_text);
        //用于播放单词的按钮绑定id，并监听该按钮
        playVoice = (ImageView) findViewById(R.id.play_voice);
        playVoice.setOnClickListener(this);
        //加载单词三个选项组绑定id,并监听该选项组
        radioGroup = (RadioGroup) findViewById(R.id.choose_group);
        radioGroup.setOnCheckedChangeListener(this);
        //给三个选项绑定id
        radioOne = (RadioButton) findViewById(R.id.choose_btn_one);
        radioTwo = (RadioButton) findViewById(R.id.choose_btn_two);
        radioThree = (RadioButton) findViewById(R.id.choose_btn_three);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_voice:
                String text = wordText.getText().toString();
                speechSynthesizer.startSpeaking(text, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onSpeakBegin() {

    }

    @Override
    public void onBufferProgress(int i, int i1, int i2, String s) {

    }

    @Override
    public void onSpeakPaused() {

    }

    @Override
    public void onSpeakResumed() {

    }

    @Override
    public void onSpeakProgress(int i, int i1, int i2) {

    }

    @Override
    public void onCompleted(SpeechError speechError) {

    }
}