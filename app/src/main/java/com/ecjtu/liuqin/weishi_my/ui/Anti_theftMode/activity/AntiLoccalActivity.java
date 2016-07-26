package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.broadcast.ScreenDivceReciver;

/**
 * 这是防盗界面中的锁屏功能
 */
public class AntiLoccalActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private Button mEnten_button;
    private TextView mHintTv;
    private ImageView mHintImg;

    //设备管理器的声明
    private DevicePolicyManager dpm;
    private ComponentName componentName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //因为与防盗模块中的报警音乐的界面基本一致，就让其复用
        setContentView(R.layout.activity_anti_music);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));


        mEnten_button = (Button) findViewById(R.id.enter_button);
        mEnten_button.setOnClickListener(this);
        mHintTv = (TextView) findViewById(R.id.hintTv);
        mHintTv.setText("手机锁定后,小偷将无法使用手机，需验证设置的密码才能解锁");

        mHintImg = (ImageView) findViewById(R.id.antiIm);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
         mToolbar.setTitle("手机锁定");
        setSupportActionBar(mToolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View view) {
        //取得系统服务
        dpm  = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, ScreenDivceReciver.class);
        //添加一个隐式意图，完成设备权限的添加
        //这个Intent (DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)跳转到 权限提醒页面
        //并传递了两个参数EXTRA_DEVICE_ADMIN 、 EXTRA_ADD_EXPLANATION
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

        //权限列表
        //EXTRA_DEVICE_ADMIN参数中说明了用到哪些权限，
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);

        //描述(additional explanation)
        //EXTRA_ADD_EXPLANATION参数为附加的说明
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "屏幕的锁定,还请施主归还，回头是岸！");

        startActivityForResult(intent, 0);
        boolean active = dpm.isAdminActive(componentName);
        if (active) {
            dpm.lockNow();
            dpm.resetPassword("4418",0);
        }
    }
}
