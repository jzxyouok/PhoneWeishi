package com.ecjtu.liuqin.weishi_my.mode.bean;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/21.
 * 手机防盗中的bean类
 */
public class ProtectItem implements Serializable{

    private int ProIcon;//图标
    private String ProText;//文字
    private boolean ProFlag;//标志

    public ProtectItem(){

    }

    public ProtectItem(int proIcon, String proText, boolean proFlag) {
        ProIcon = proIcon;
        ProText = proText;
        ProFlag = proFlag;
    }

    public int getProIcon() {
        return ProIcon;
    }

    public void setProIcon(int proIcon) {
        ProIcon = proIcon;
    }

    public String getProText() {
        return ProText;
    }

    public void setProText(String proText) {
        ProText = proText;
    }

    public boolean isProFlag() {
        return ProFlag;
    }

    public void setProFlag(boolean proFlag) {
        ProFlag = proFlag;
    }
}
