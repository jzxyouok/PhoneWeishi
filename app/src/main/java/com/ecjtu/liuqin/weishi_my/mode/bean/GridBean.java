package com.ecjtu.liuqin.weishi_my.mode.bean;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/22.
 * 这是手机防盗展示功能的界面的bean类
 */
public class GridBean implements Serializable {

    private int GridImage;
    private String GridText;

    public GridBean(){

    }

    public GridBean(int gridImage, String gridText) {
        GridImage = gridImage;
        GridText = gridText;
    }

    public int getGridImage() {
        return GridImage;
    }

    public void setGridImage(int gridImage) {
        GridImage = gridImage;
    }

    public String getGridText() {
        return GridText;
    }

    public void setGridText(String gridText) {
        GridText = gridText;
    }
}
