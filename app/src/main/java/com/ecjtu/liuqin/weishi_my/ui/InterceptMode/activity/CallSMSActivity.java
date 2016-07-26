package com.ecjtu.liuqin.weishi_my.ui.InterceptMode.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;
import com.ecjtu.liuqin.weishi_my.presenter.service.BlackService;
import com.ecjtu.liuqin.weishi_my.presenter.service.impl.BlackTypeImpl;
import com.ecjtu.liuqin.weishi_my.ui.InterceptMode.fragment.CallSMSFragment;
import com.ecjtu.liuqin.weishi_my.until.adpter.MyFragmentAdapter;
import com.ecjtu.liuqin.weishi_my.until.common.Popwindow_my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是骚扰拦截模块
 */
public class CallSMSActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private ImageView mShezhi;
    private CallSMSFragment fragment;
    private List<BlackNum> typeList;
    private Map<String,List<BlackNum>> mMapList;
    private List<Fragment> mFragmentList;
    private MyFragmentAdapter mFragAdapter;
    private String[] tabTitle={"短信","电话"};

    private BlackService blackImpl;

    //自定义的popuWindow
    private Popwindow_my mPopuWind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms);

        initView();

        initData();

    }

    //初始化组件
    public void initView(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMapList=new HashMap<>();
        mFragmentList=new ArrayList<>();
        mViewpager= (ViewPager) findViewById(R.id.viewpager);

        //------------------------以下三行代码是对tabLayout的设置
        mTabLayout= (TabLayout) findViewById(R.id.tablayout);
        //设置这个可以让tab都放在中间
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置这个可以让tab都分开在两边
        mTabLayout.setPadding(10,0,0,0);

        mShezhi= (ImageView) findViewById(R.id.shezhi);
        mShezhi.setOnClickListener(this);

        blackImpl=new BlackTypeImpl(this);
    }

    //初始化数据
    public void initData(){

          mMapList.put(tabTitle[0],blackImpl.MessageStop());
        mMapList.put(tabTitle[1],blackImpl.MessageStop());

        for(int i=0;i<tabTitle.length;i++){
            fragment= CallSMSFragment.newInstance(mMapList.get(tabTitle[i]));
            mFragmentList.add(fragment);
        }

        mFragAdapter=new MyFragmentAdapter(getSupportFragmentManager(),
                mFragmentList,CallSMSActivity.this,tabTitle);
        mViewpager.setAdapter(mFragAdapter);
        ///让tablayout和viewpager联动起来
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }

    // 为弹出窗口实现监听类
    private View.OnClickListener itemOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mPopuWind.dismiss();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shezhi:
                Toast.makeText(CallSMSActivity.this,"点击了设置",Toast.LENGTH_SHORT).show();
                //点击弹出自定义的popuWindow
                mPopuWind=new Popwindow_my(this,itemOnclick);
                // 显示窗口
                mPopuWind.showAtLocation(
                        this.findViewById(R.id.CallSMS_activty),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
        }
    }
}
