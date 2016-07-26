package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.ecjtu.liuqin.weishi_my.R;

/**
 * 用来播放报警音乐，以及锁屏定位等
 */
public class AntiProtectService extends Service {
    private MediaPlayer mMediaPlayer;
    private AudioManager mManager;//音频管理器
    public AntiProtectService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int flag=intent.getIntExtra("flag",0);
        switch (flag){
            case 0://开启报警音乐
                mManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
                mManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//设为普通模式
                  mManager.setMicrophoneMute(false);
                  mManager.setSpeakerphoneOn(true);//使用扬声器外放，即使已经插入耳机
                  mManager.setMode(AudioManager.STREAM_MUSIC);
                  int maxVolume =mManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取手机音乐最大声音
                  mManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,0);

                  mMediaPlayer=MediaPlayer.create(this, R.raw.a);
                  mMediaPlayer.setLooping(true);
                  mMediaPlayer.start();
                  break;

            case 1://锁屏

                break;
          }
        return START_STICKY;
    }
}
