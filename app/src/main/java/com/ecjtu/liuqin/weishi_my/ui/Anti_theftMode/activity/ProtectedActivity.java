package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.ProtectItem;
import com.ecjtu.liuqin.weishi_my.until.adpter.ProtectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机防盗界面
 */
public class ProtectedActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mProtectList;
    private List<ProtectItem> mProtectItemList;
    private ProtectItem mProtectItem,mProtectItem1;
     private TextView showTv;

    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));

        showTv= (TextView) findViewById(R.id.showTv);

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

        mProtectList= (ListView) findViewById(R.id.protect_list);
        //去掉分割线
        mProtectList.setDivider(null);
        mProtectItemList=new ArrayList<>();
         initData();
        ProtectAdapter protectAdapter=new ProtectAdapter(this,mProtectItemList,R.layout.protect_item);
        mProtectList.setAdapter(protectAdapter);

        mProtectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                String isFirst=mSp.getString("pro_flag","");
                if(isFirst.equals("")){
                    mEditor.putString("isFirst","NoFirst");
                    mEditor.commit();
                    intent=new Intent(ProtectedActivity.this,LoginActivity.class);
                    startActivity(intent, ActivityOptions.
                            makeSceneTransitionAnimation(ProtectedActivity.this).toBundle());
                }else if(isFirst.equals("true")){
                    intent=new Intent(ProtectedActivity.this,EnterPassActivity.class);
                    startActivity(intent, ActivityOptions.
                            makeSceneTransitionAnimation(ProtectedActivity.this).toBundle());
                }
            }
        });
    }
    public void initData(){
        if(mSp.getString("pro_flag","").equals("true")){

            mProtectItem=new ProtectItem(R.drawable.fangdao,"手机防盗",true);
            showTv.setText("手机防盗保障中");
        }else {
            mProtectItem=new ProtectItem(R.drawable.fangdao,"手机防盗",false);
        }
        mProtectItemList.add(mProtectItem);
    mProtectItem1=new ProtectItem(R.drawable.beifen,"自动备份资料",false);
        mProtectItemList.add(mProtectItem1);
    }
}
