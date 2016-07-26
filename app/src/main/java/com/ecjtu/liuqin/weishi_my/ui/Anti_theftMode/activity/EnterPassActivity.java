package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ecjtu.liuqin.weishi_my.R;

/**
 * 这是手机防盗模块中的再次点击进入到输入密码的界面
 */
public class EnterPassActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private EditText mEdit_password;
    private Button mEnter_button;


    // /只负责读数据
    private SharedPreferences sp;
    // /只负责些数据
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pass);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));


        // //获取sp和editor对象
        sp = getSharedPreferences("user", MODE_PRIVATE);
        editor = sp.edit();


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

        mEnter_button= (Button) findViewById(R.id.enter_button);
        mEnter_button.setOnClickListener(this);

        mEdit_password= (EditText) findViewById(R.id.password);
        //监听输入框中的文字的变化
          mEdit_password.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  mEnter_button.setClickable(false);
              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 mEnter_button.setClickable(true);
                  mEnter_button.setBackgroundResource(R.drawable.btn_tv);
              }

              @Override
              public void afterTextChanged(Editable editable) {
                  if(mEdit_password.getText().toString().equals("")){
                      mEnter_button.setBackgroundResource(R.drawable.login_btn);
                      mEnter_button.setClickable(false);
                  }
              }
          });
    }

    @Override
    public void onClick(View view) {
        String password=sp.getString("password","");
        String edit_password=mEdit_password.getText().toString();
        if(password.equals(edit_password)){
            //跳转到显示功能界面
            Intent intent;
            intent=new Intent(EnterPassActivity.this,PhoneAntiActivity.class);
            startActivity(intent, ActivityOptions.
                    makeSceneTransitionAnimation(EnterPassActivity.this).toBundle());
            finish();
        }else{
            Snackbar.make(view,"密码输入失败，请重新输入!!",Snackbar.LENGTH_SHORT).show();
        }
    }
}
