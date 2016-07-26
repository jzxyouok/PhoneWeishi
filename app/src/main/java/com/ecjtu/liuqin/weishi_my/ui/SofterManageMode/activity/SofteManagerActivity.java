package com.ecjtu.liuqin.weishi_my.ui.SofterManageMode.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.SoftInfo;
import com.ecjtu.liuqin.weishi_my.ui.SofterManageMode.frament.SofteFragment;
import com.ecjtu.liuqin.weishi_my.until.adpter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是软件管理模块
 */
public class SofteManagerActivity extends AppCompatActivity{
    //获取到应用程序的管理者
    private PackageManager packageManager;

    private ViewPager mViewpager;
    private TabLayout mTabLayout;

    private TextView mSize;
    private List<Fragment> mFragmentList;
    private MyFragmentAdapter mFragAdapter;
    private String[] mTabTitle={"系统","全部","用户"};

    private SofteFragment softeFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softe_manager);
        initView();
        initData();
    }

    //初始化组件
    public void initView() {

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //关键下面两句话，设置了回退箭头，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mSize = (TextView) findViewById(R.id.size);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        //设置这个可以让tab都放在中间
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置这个可以让tab都分开在两边
        mTabLayout.setPadding(10,0,0,0);

        mFragmentList=new ArrayList<>();
        //将获取到应用程序的管理者初始化
        packageManager=getPackageManager();
    }

    //初始化数据
    public void initData() {

        if (getsPath() == null) {
            //sd卡不存在
            mSize.setText("Sd卡不存在，请检查sd卡是否插好");
        } else {
            mSize.setText("手机可用内存为:" + getSize(getMPath()) + "G           sd卡可用内存为:"
                    + getSize(getsPath()) + "G");
        }


        for(int i=1;i<=3;i++) {
          softeFragment = SofteFragment.newInstance(getSofteInfos(i));
          mFragmentList.add(softeFragment);
      }

          mFragAdapter=new MyFragmentAdapter(getSupportFragmentManager(),
                  mFragmentList,SofteManagerActivity.this,mTabTitle);
          mViewpager.setAdapter(mFragAdapter);
          ///让tablayout和viewpager联动起来
          mTabLayout.setupWithViewPager(mViewpager);
          mViewpager.setCurrentItem(0);
    }

    //获取sd卡路径
    public String getsPath() {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return path;
    }

    //获取手机内存路径
    public String getMPath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }


    //获取到手机的内存和sd卡内存的方法（单位：字节）
    private long getAvailSpace(String path) {
        //一个模拟linux的df命令的一个类，获取sd卡和手机内存的使用
        StatFs statFs = new StatFs(path);
        //获取分区的大小
        long size = statFs.getBlockSize();
        //获取分区块的个数
        long count = statFs.getAvailableBlocks();
        return size * count;
    }

    //获取到内存大小转换为G
    public float getSize(String path) {

        return Math.round(getAvailSpace(path) / 1024 / 1024 / 1024);
    }


    //获取所有的应用程序的信息
    public List<PackageInfo> getInfos() {
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);

        return packageInfoList;
    }

    //获取数据并将其分类放在集合中
    public List<SoftInfo> getSofteInfos(int id) {

        //所有的应用程序的集合
        List<SoftInfo> softes = new ArrayList<>();

        //系统的应用程序的集合
        List<SoftInfo> SystemSoftes = new ArrayList<>();

        //用户的应用程序集合
        List<SoftInfo> UserSoftes = new ArrayList<>();

        for (PackageInfo info : getInfos()) {
            SoftInfo softInfo = new SoftInfo();

            //通过前面获取到的应用信息来获取到包名
            String packName = info.packageName;
            //通过前面获取到的应用信息来获取到APP名
            String appName = info.applicationInfo.loadLabel(packageManager).toString();
            //通过前面获取到的应用信息来获取到应用图标
            Drawable icon = info.applicationInfo.loadIcon(packageManager);
            //通过前面获取到的应用信息来获取到是否为系统程序或者用户安装程序的标志
            int flag = info.applicationInfo.flags;

            softInfo.setAppname(appName);
            softInfo.setPackageName(packName);
            softInfo.setIcon(icon);

            if ((flag & info.applicationInfo.FLAG_SYSTEM) == 0) {
                //用户程序
                softInfo.setFlag(1);
                UserSoftes.add(softInfo);
            } else {
                softInfo.setFlag(2);
                SystemSoftes.add(softInfo);
            }
        }
        //将用户和系统的应用程序的集合用addAll的方法有序的添加到整个的list集合中
        softes.addAll(UserSoftes);
        softes.addAll(SystemSoftes);

        switch (id){
            case 1://代表是系统程序
                return SystemSoftes;
            case 2://代表全部
                return softes;
            case 3://代表是用户程序
                return UserSoftes;
        }
        return null;
    }
}
