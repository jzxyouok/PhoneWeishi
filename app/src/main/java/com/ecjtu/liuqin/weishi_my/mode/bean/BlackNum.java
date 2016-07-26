package com.ecjtu.liuqin.weishi_my.mode.bean;

import java.io.Serializable;

/**
 * Created by liuqin on 2016/7/18.
 * 黑名单的bean类
 *
 * 其中type的值：
 * 1代表是电话拦截   2代表是短信拦截   3代表着既有电话拦截也有短信拦截的标识
 */
public class BlackNum implements Serializable{

    private Long id;
    private String number;
    private String type;
    private int count;
    private String time;
    private String content;

    public BlackNum(){

    }

    public BlackNum(Long id, String number, String type, int count, String time, String content) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.count = count;
        this.time = time;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlackNum{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", count=" + count +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
