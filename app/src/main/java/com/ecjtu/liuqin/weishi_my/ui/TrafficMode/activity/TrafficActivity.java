package com.ecjtu.liuqin.weishi_my.ui.TrafficMode.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.until.common.PrograssBarCircle;

/**
 * 这是流量监控的主界面
 */
public class TrafficActivity extends AppCompatActivity implements View.OnClickListener {

    // ProgressBar进度控制
    private PrograssBarCircle mProgressBar;
    private TextView numberText, maxText;
    // 最大100
    private int max = 100;
    private long progress = 0;

    private Toolbar mToolbar;

    private Button mEnter_button;

    private TextView msend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic);
        // ProgressBar进度控制
        mProgressBar = (PrograssBarCircle) findViewById(R.id.circleProgressBar);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));

        mEnter_button= (Button) findViewById(R.id.enter_button);
        mEnter_button.setOnClickListener(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        numberText = (TextView) findViewById(R.id.numberText);
        maxText = (TextView) findViewById(R.id.maxText);

        msend= (TextView) findViewById(R.id.send_Liu);
        msend.setOnClickListener(this);


        setSupportActionBar(mToolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        long upload = TrafficStats.getMobileTxBytes();//获取手机3g/2g网络上传的总流量
        long load=TrafficStats.getMobileRxBytes();//手机2g/3g下载的总流量
         progress=upload+load;

        maxText.setText("设置限额为  " + String.valueOf(150));
        mProgressBar.setMax(150);
        System.out.println("总流量:"+progress+"----"+"转换后:"+changeM(progress));
        mProgressBar.setProgress(changeM(progress));

        startAddProgress();

        mProgressBar.setOnProgressListener(new PrograssBarCircle.OnProgressListener() {
            @Override
            public void onProgress(long progress) {

            }

            @Override
            public void onComplete() {
                progress = 0;
                mProgressBar.reset();
            }
        });
    }

    public void startAddProgress() {
        long upload = TrafficStats.getMobileTxBytes();//获取手机3g/2g网络上传的总流量
        long load=TrafficStats.getMobileRxBytes();//手机2g/3g下载的总流量
        progress=upload+load;
        mProgressBar.setProgress(changeM(progress));
        mUpdateHandler.sendEmptyMessageDelayed(1, 1000);
    }

    private Handler mUpdateHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    startAddProgress();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable mUpdateRunnable = new Runnable() {
        public void run() {
            while (true) {
                Message message = new Message();
                message.what = 1;
                mUpdateHandler.sendMessage(message);
                try {
                    // 更新间隔毫秒数
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    };

    //单位的转换
    public long changeM(long prograss) {
        if (prograss > 1024&&prograss<1024*1024) {
            numberText.setText(String.valueOf(Math.round(prograss / 1024))+"KB");
            return Math.round(prograss / 1024);
        }
        if (prograss > 1024*1024&&prograss<1024*1024*1024) {
            numberText.setText(String.valueOf(Math.round(prograss / 1024 / 1024))+"M");
            return Math.round(prograss / 1024 / 1024);
        }
        if(prograss>1024*1024*1024){
            numberText.setText(String.valueOf(Math.round(prograss / 1024 / 1024/1024))+"G");
            return Math.round(prograss / 1024 / 1024/1024);
        }
        numberText.setText(String.valueOf(prograss)+"B");
        return prograss;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enter_button:
                //跳转到显示每个应用的流量使用的界面中
                Intent intent;
                intent=new Intent(TrafficActivity.this,AppTranfficActivity.class);
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(TrafficActivity.this).toBundle());
                break;

            case R.id.send_Liu://智能校正的方法就是点击后发送短信到所在运营商，然后获取文本信息得到所需内容
                //短信管理器的默认初始化
                SmsManager sm = SmsManager.getDefault();
                String name=getOperators();
                switch (name){
                    case "中国移动":
                     sm.sendTextMessage("10086",null,"cxll",null,null);
                        break;
                }
                break;
        }

    }

    //判断当前卡的运营商
    public String getOperators() {
        TelephonyManager tm = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operator = null;
        String IMSI = tm.getSubscriberId();
        if (IMSI == null || IMSI.equals("")) {
            return operator;
        }
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            operator = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            operator = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            operator = "中国电信";
        }
        return operator;
    }
}
