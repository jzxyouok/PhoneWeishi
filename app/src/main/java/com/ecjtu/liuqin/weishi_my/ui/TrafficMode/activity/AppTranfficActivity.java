package com.ecjtu.liuqin.weishi_my.ui.TrafficMode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;

import com.ecjtu.liuqin.weishi_my.R;

/**
 * 显示应用流量排名的界面
 */
public class AppTranfficActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_tranffic);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
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
}
