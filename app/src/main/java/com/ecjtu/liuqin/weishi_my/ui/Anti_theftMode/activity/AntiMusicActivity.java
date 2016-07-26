package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;

import com.ecjtu.liuqin.weishi_my.R;

/**
 * 这是手机防盗界面中的响铃警报的体验界面
 */
public class AntiMusicActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mEnten_button;
    private MediaPlayer mMediaPlayer;
    private AudioManager mManager;//音频管理器
    private Toolbar mToolbar;
    private boolean flag=false;//判断第几次点击按钮的标志位
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_music);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));


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


        mEnten_button= (Button) findViewById(R.id.enter_button);
        mEnten_button.setOnClickListener(this);

        mManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onClick(View view) {
        if(flag==false){//当时开始体验时的操作
            mEnten_button.setText("结束体验");

            mManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//设为普通模式
            mManager.setMicrophoneMute(false);
            mManager.setSpeakerphoneOn(true);//使用扬声器外放，即使已经插入耳机
            mManager.setMode(AudioManager.STREAM_MUSIC);
            int maxVolume =mManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取手机音乐最大声音
            mManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,0);

            mMediaPlayer=MediaPlayer.create(this,R.raw.a);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
            flag=true;
        }else{
            mEnten_button.setText("开始体验");
            mMediaPlayer.stop();
            flag=false;
        }
    }
}
