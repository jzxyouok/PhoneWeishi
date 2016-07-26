package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Slide;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ecjtu.liuqin.weishi_my.R;

/**
 * 这是添加紧急联系人的界面
 */
public class AddLinkActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {

    private Toolbar mToolbar;
    private EditText mLinkphone;
    private Button mEnterButton;
    private AppCompatCheckBox mCheckBox;

    private boolean flag=true;//用来设置是否开启checkBox

    //drawable是拿一个数组来保存上下左右4个drawable对象的 分别对应0，1，2，3
    //这个是EditText中的drawableRight
    private static final int DRAWABLE_RIGHT = 2;


    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));


        // //获取sp和editor对象
        mSp = getSharedPreferences("user", MODE_PRIVATE);
        mEditor = mSp.edit();


        mToolbar= (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mEnterButton= (Button) findViewById(R.id.enter_button);
       mEnterButton.setOnClickListener(this);

        mLinkphone= (EditText) findViewById(R.id.linkphone);
         mLinkphone.setOnTouchListener(this);
        mLinkphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEnterButton.setClickable(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEnterButton.setClickable(true);
                mEnterButton.setBackgroundResource(R.drawable.btn_tv);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mLinkphone.getText().toString().equals("")){
                    mEnterButton.setBackgroundResource(R.drawable.login_btn);
                    mEnterButton.setClickable(false);
                }
            }
        });

        mCheckBox= (AppCompatCheckBox) findViewById(R.id.checkBox);
          mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(b){//被选中
                    flag=true;
                  }else{//被取消
                   flag=false;
                  }
              }
          });
    }

    @Override
    public void onClick(View view) {
         if(isMobileNO(mLinkphone.getText().toString())){//当输入号码符合正则表达式时
             //先将紧急联系人放在SharedPreferences中
             mEditor.putString("safeNumber", mLinkphone.getText().toString());
             mEditor.commit();

             if(flag==true){//当勾选了checkBox
                   //利用SMSManager来进行短信的发送
                   PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(),0);
                   SmsManager sm = SmsManager.getDefault();
                   sm.sendTextMessage(mLinkphone.getText().toString(), null,
                           "你已被选中为紧急联系人！",pendingIntent, null);
               }
             Intent intent;
             intent=new Intent();
             setResult(5,intent);
             onBackPressed();
         }else{
             Snackbar.make(view,"输入号码无效",Snackbar.LENGTH_SHORT).show();
         }
    }

    /**
     * 利用正则表达式来验证手机格式
     */
    public  boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 利用onTouch事件来获取到EditText中的drawableEnd图片的监听
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mLinkphone .getCompoundDrawables()[DRAWABLE_RIGHT] == null){
            return false;
        }
        //这里一定要对点击事件类型做一次判断，否则你的点击事件会被执行2次
        if (motionEvent.getAction() != MotionEvent.ACTION_UP){
            return false;
        }
        if (motionEvent.getX() > mLinkphone.getWidth() -mLinkphone.getCompoundDrawables()[DRAWABLE_RIGHT]
                .getBounds().width()) {
            //在这里对图片进行点击后的操作
            Snackbar.make(view,"点击了图片",Snackbar.LENGTH_SHORT).show();
            return true;
        }
        return false;//这里用false,才会让其继续执行click事件
    }
}
