package com.ecjtu.liuqin.weishi_my.mode.bean;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/19.
 * 获取手机通讯录的bean类
 */
public class PhoneInfo implements Serializable{

    private String phoneName;
    private String phoneNumber;

    public PhoneInfo(){

    }

    public PhoneInfo(String phoneNumber, String phoneName) {
        this.phoneNumber = phoneNumber;
        this.phoneName = phoneName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "phoneName='" + phoneName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
