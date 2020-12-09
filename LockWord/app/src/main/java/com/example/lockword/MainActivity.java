package com.example.lockword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.assetsbasedata.AssetsDatabaseManager;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.speech.SynthesizerListener;
import com.mingrisoft.greendao.entity.greendao.CET4Entity;
import com.mingrisoft.greendao.entity.greendao.CET4EntityDao;
import com.mingrisoft.greendao.entity.greendao.DaoMaster;
import com.mingrisoft.greendao.entity.greendao.DaoSession;

import java.util.ArrayList;
import java.util.Calendar;
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
        setParam();
        SpeechUser.getUser().login(MainActivity.this, null, null,
                "appid=5fcfa175", listener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 设置系统日期，并显示
         */
        Calendar calendar = Calendar.getInstance();
        month = String.valueOf(calendar.get(Calendar.MONTH) + 1);       //获取日期的月
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + 1);  //获取日期的日
        way = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));       //获取日期的星期
        /**
         * 如果小时时个位数
         * 则在前面加一个“0”
         */
        if(calendar.get(Calendar.HOUR) < 10){
            hours = "0" + calendar.get(Calendar.HOUR);
        }else {
            hours = String.valueOf(calendar.get(Calendar.HOUR));
        }
        /**
         * 如果分钟是个位数
         * 也在前面加一个“0”
         */
        if(calendar.get(Calendar.MINUTE) < 10){
            minute = "0" + calendar.get(Calendar.MINUTE);
        }else {
            minute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        /**
         * 获取星期并显示
         */
        if ("1".equals(way)){
            way = "天";
        }else if ("2".equals(way)){
            way = "一";
        }else if ("3".equals(way)){
            way = "二";
        }else if ("4".equals(way)){
            way = "三";
        }else if ("5".equals(way)){
            way = "四";
        }else if ("6".equals(way)){
            way = "五";
        }else if ("7".equals(way)){
            way = "六";
        }
        timeText.setText(hours + ":" + minute);
        dateText.setText(month + "月" + day + "日" + "    " + "星期" + way);
    }

    /**
     * 将错题存到数据库
     */
    private void saveWrongData(){
        String word = datas.get(k).getWord();       //获取答错这道题的单词
        String english = datas.get(k).getEnglish(); //获取答错这道题的音标
        String china = datas.get(k).getChina();     //获取答错这道题的汉语意思
        String sign = datas.get(k).getSign();       //获取答错这道题的标记
        CET4Entity data = new CET4Entity(Long.valueOf(dbDao.count()),
                word, english, china, sign);
        dbDao.insertOrReplace(data);                //把这些字段存到数据库
    }

    /**
     * 选对选项，选项变绿
     * 选错选项，选项变红
     */
    private void btnGetText(String msg, RadioButton item){
        //答对设置绿色，答错设置红色
        if(msg.equals(datas.get(k).getChina())){
            wordText.setTextColor(Color.GREEN);
            englishText.setTextColor(Color.GREEN);
            item.setTextColor(Color.GREEN);
        }else {
            wordText.setTextColor(Color.RED);
            englishText.setTextColor(Color.RED);
            item.setTextColor(Color.RED);
            saveWrongData();            //执行存入错题的方法
            //保存到数据库
            int wrong = sharedPreferences.getInt("wrong", 0);   //从数据库中取出整数
            editor.putInt("wrong", wrong + 1);  //写入数据库
            editor.putString("wongId", "," + datas.get(j).getId()); //写入数据库
            editor.commit();        //保存
        }
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

    /**
     *  选项的单击事件
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.choose_btn_one:
                //截取字符串
                String msgA = radioOne.getText().toString().substring(3);
                btnGetText(msgA, radioOne);      //将参数传入对应的方法中
                break;
            case R.id.choose_btn_two:
                String msgB = radioTwo.getText().toString().substring(3);
                btnGetText(msgB, radioTwo);
                break;
            case R.id.choose_btn_three:
                String msgC = radioThree.getText().toString().substring(3);
                btnGetText(msgC, radioThree);
                break;
            default:
                break;
        }
    }

    /**
     * 还原单词与选项颜色
     */
    private void setTextColor(){
        //还原单词选项的颜色
        radioOne.setChecked(false);     //将选项设置为非选中状态
        radioTwo.setChecked(false);
        radioThree.setChecked(false);
        //将选项的按钮设置为白色
        radioOne.setTextColor(Color.parseColor("#FFFFFF"));
        radioTwo.setTextColor(Color.parseColor("#FFFFFF"));
        radioThree.setTextColor(Color.parseColor("#FFFFFF"));
        //将单词和音标设置为白色
        wordText.setTextColor(Color.parseColor("#FFFFFF"));
        englishText.setTextColor(Color.parseColor("#FFFFFF"));
    }

    /**
     * 解锁
     */
    private void unlocked(){
        Intent intent = new Intent(Intent.ACTION_MAIN); //界面跳转
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);       //进入到手机桌面
        startActivity(intent);      //启动
        kl.disableKeyguard();       //解锁
        finish();                   //销毁当前activity
    }

    /**
     * 选项设置
     */
    private void setChina(List<CET4Entity> datas, int j){
        /**
         * 随机产生几个随机数，用于解锁单词
         * 因为数据库只录入20个单词，所以产生的随机数是20以内的数
         */
        Random r = new Random();
        List<Integer> listInt = new ArrayList<>();
        int i;
        while (listInt.size() < 4){
            i = r.nextInt(20);
            if(! listInt.contains(i)){
                listInt.add(i);
            }
        }
        /**
         * 以下的判断是为这个单词设置三个选项，设置单词的选项是有规律的
         * 三个选项，分别是正确的、正确的前一个、正确的后一个
         * 将这三个解释设置到单词的选项上
         */
        if(listInt.get(0) < 7){
            radioOne.setText("A: "+datas.get(k).getChina());
            if(k - 1 >= 0){
                radioTwo.setText("B: " + datas.get(k - 1).getChina());
            }else {
                radioTwo.setText("B: "+datas.get(k + 2).getChina());
            }
            if(k + 1 < 20){
                radioThree.setText("C: "+datas.get(k + 1).getChina());
            }else {
                radioThree.setText("C: "+datas.get(k - 1).getChina());
            }
        }else if (listInt.get(0) < 14){
            radioTwo.setText("B: "+datas.get(k).getChina());
            if(k - 1 >= 0){
                radioOne.setText("A: " + datas.get(k - 1).getChina());
            }else {
                radioOne.setText("A: "+datas.get(k + 2).getChina());
            }
            if(k + 1 < 20){
                radioThree.setText("C: "+datas.get(k + 1).getChina());
            }else {
                radioThree.setText("C: "+datas.get(k - 1).getChina());
            }
        }else {
            radioThree.setText("C: "+datas.get(k).getChina());
            if(k - 1 >= 0){
                radioTwo.setText("B: " + datas.get(k - 1).getChina());
            }else {
                radioTwo.setText("B: "+datas.get(k - 2).getChina());
            }
            if(k + 1 < 20){
                radioOne.setText("A: "+datas.get(k + 1).getChina());
            }else {
                radioOne.setText("A: "+datas.get(k - 1).getChina());
            }
        }
    }

    /**
     * 获取数据库数据
     */
    private void getDBData(){
        datas = questionDao.queryBuilder().list();      //把词库中的单词读出来
        k = list.get(j);
        wordText.setText(datas.get(k).getWord());       //设置单词
        englishText.setText(datas.get(k).getEnglish()); //设置音标
        setChina(datas, k);                             //设置单词的三个词义选项
    }

    /**
     * 获取下一题
     */
    private void getNextData(){
        j++;                                                            //当前已做题的数目
        int i = sharedPreferences.getInt("allNum", 5);    //默认解锁题目为5道
        if(i > j){
            getDBData();            //获取数据
            setTextColor();         //设置颜色
            //已经学过的单词数量加一
            int num = sharedPreferences.getInt("alreadyStudy", 0) + 1;
            editor.putInt("alreadyStudy", num);
            editor.commit();                //存到数据库中
        }else {
            unlocked();                     //解锁
        }
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

    /**
     * 通用回调接口
     */
    private SpeechListener listener = new SpeechListener() {
        //消息回调
        @Override
        public void onEvent(int arg0, Bundle arg1) {

        }
        //数据回调
        @Override
        public void onData(byte[] bytes) {

        }
        //结束回调（没有错误）
        @Override
        public void onCompleted(SpeechError speechError) {

        }
    };
    /**
     * 初始化语音播报
     */
    public void setParam(){
        speechSynthesizer = SpeechSynthesizer.createSynthesizer(this);
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        speechSynthesizer.setParameter(SpeechConstant.SPEED, "50");
        speechSynthesizer.setParameter(SpeechConstant.VOLUME, "50");
        speechSynthesizer.setParameter(SpeechConstant.PITCH, "50");
    }
}