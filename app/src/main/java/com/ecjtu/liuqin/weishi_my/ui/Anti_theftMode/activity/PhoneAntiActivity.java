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
import android.widget.GridView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.GridBean;
import com.ecjtu.liuqin.weishi_my.until.adpter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是手机防盗的界面,显示手机定位，报警音乐等
 */
public class PhoneAntiActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
,View.OnClickListener{

    private TextView addLinkPeople;
    private GridView mShowGrid;
    private List<GridBean> mGridBeanList;
    private GridBean mGridBean;

    private Toolbar mToolbar;

    private Intent intent;

    private SharedPreferences mSp;


    private int[] grid_imgs={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] grid_texts={"手机定位","手机锁定","响铃报警","清除数据"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_anti);

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


        addLinkPeople= (TextView) findViewById(R.id.addPeople);
        addLinkPeople.setOnClickListener(this);
        mShowGrid= (GridView) findViewById(R.id.anti_grid);
        mShowGrid.setOnItemClickListener(this);
        mGridBeanList=new ArrayList<>();
        initData();

        GridAdapter mGridAdapter=new GridAdapter(this,mGridBeanList,R.layout.grid_item);
        mShowGrid.setAdapter(mGridAdapter);

        // //获取sp和editor对象
        mSp = getSharedPreferences("user", MODE_PRIVATE);

        if(!mSp.getString("safeNumber","").equals("")){
            addLinkPeople.setVisibility(View.GONE);
            addLinkPeople.setClickable(false);
        }else{
            addLinkPeople.setVisibility(View.VISIBLE);
            addLinkPeople.setClickable(true);
        }
    }

    //初始化数据
    public void initData(){
      for(int i=0;i<grid_imgs.length;i++){
        mGridBean=new GridBean(grid_imgs[i],grid_texts[i]);
          mGridBeanList.add(mGridBean);
      }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
     switch (i){
         case 0:
             //进入手机定位
             intent=new Intent(this,AntiLocationActivity.class);
             startActivity(intent, ActivityOptions.
                     makeSceneTransitionAnimation(PhoneAntiActivity.this).toBundle());
             break;
         case 1:
             //进入手机锁定
             intent=new Intent(this,AntiLoccalActivity.class);
             startActivity(intent, ActivityOptions.
                     makeSceneTransitionAnimation(PhoneAntiActivity.this).toBundle());
             break;

         case 2:
             //进入响铃警报
             intent=new Intent(this,AntiMusicActivity.class);
             startActivity(intent, ActivityOptions.
                     makeSceneTransitionAnimation(PhoneAntiActivity.this).toBundle());
             break;

         case 3:
             //进如清除数据界面
             intent=new Intent(this,AntiClearActivity.class);
             startActivity(intent, ActivityOptions.
                     makeSceneTransitionAnimation(PhoneAntiActivity.this).toBundle());
             break;
     }
    }

    @Override
    public void onClick(View view) {
        //点击添加紧急联系人
        intent=new Intent(this,AddLinkActivity.class);
        startActivityForResult(intent,10,ActivityOptions.
                makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==10&&resultCode==5){
            addLinkPeople.setVisibility(View.GONE);
        }
    }
}
