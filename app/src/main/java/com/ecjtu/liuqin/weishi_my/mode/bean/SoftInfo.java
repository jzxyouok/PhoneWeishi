package com.ecjtu.liuqin.weishi_my.mode.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/20.
 * 这是软件管理模块中中bean类
 */
public class SoftInfo implements Serializable{

    private Drawable icon;//程序图标

    private String appname;//程序名

    private String PackageName;//程序包名

    private int flag;//设置是否为用户程序还是系统程序

    public SoftInfo(){

    }

    public SoftInfo(Drawable icon, String appname, String packageName, int flag) {
        this.icon = icon;
        this.appname = appname;
        PackageName = packageName;
        this.flag = flag;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
