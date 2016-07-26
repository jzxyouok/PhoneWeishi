package com.ecjtu.liuqin.weishi_my.mode.bean;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/26.
 * 这是流量统计中的bean类
 */
public class TranfficBean implements Serializable {

    private Drawable Appicon;
    private TextView AppName;
    private TextView AppTotal;
    private TextView AppupLoad;
    private TextView Appload;

    public TranfficBean() {

    }

    public TranfficBean(Drawable appicon, TextView appName, TextView appTotal, TextView appupLoad, TextView appload) {
        Appicon = appicon;
        AppName = appName;
        AppTotal = appTotal;
        AppupLoad = appupLoad;
        Appload = appload;
    }

    public Drawable getAppicon() {
        return Appicon;
    }

    public void setAppicon(Drawable appicon) {
        Appicon = appicon;
    }

    public TextView getAppName() {
        return AppName;
    }

    public void setAppName(TextView appName) {
        AppName = appName;
    }

    public TextView getAppTotal() {
        return AppTotal;
    }

    public void setAppTotal(TextView appTotal) {
        AppTotal = appTotal;
    }

    public TextView getAppupLoad() {
        return AppupLoad;
    }

    public void setAppupLoad(TextView appupLoad) {
        AppupLoad = appupLoad;
    }

    public TextView getAppload() {
        return Appload;
    }

    public void setAppload(TextView appload) {
        Appload = appload;
    }
}
