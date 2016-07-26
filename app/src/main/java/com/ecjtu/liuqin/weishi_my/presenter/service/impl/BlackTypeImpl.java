package com.ecjtu.liuqin.weishi_my.presenter.service.impl;

import android.content.Context;

import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;
import com.ecjtu.liuqin.weishi_my.presenter.service.BlackService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqin on 2016/7/18.
 * 这是进一步复杂的逻辑处理类
 */
public class BlackTypeImpl extends BlackService {

    //存放白名单的号码，也就是没有标志为电话拦截或者短信拦截的
    List<BlackNum> WritePhone = new ArrayList<>();

    //存放标志为电话拦截的号码
    List<BlackNum> phoneStop = new ArrayList<>();

    //存放标志为短信拦截的号码
    List<BlackNum> MessgerStop = new ArrayList<>();

    private BlackNum blackNum;

    public BlackTypeImpl(Context context) {
        super(context);
    }

    //在所有数据中找到所有是短信拦截标志的
    @Override
    public List<BlackNum> MessageStop() {
        List<BlackNum> Messages=findAll();
        for(BlackNum blackNum:Messages){
            if(blackNum.getType().equals("2")||blackNum.getType().equals("3")){
                MessgerStop.add(blackNum);
            }
        }
        return MessgerStop;
    }

    //对type进行判断，并更新数据库
    public boolean IsMessageStop(String sender, String content, String time) {
        blackNum = findObj(sender);
        if (blackNum != null) {//代表在数据库中找到了
            String type = blackNum.getType();
            if (type.equals("2") || type.equals("3")) {
                if (!blackNum.getContent().equals("")) {
                    blackNum.setCount(blackNum.getCount() + 1);
                } else {
                    blackNum.setCount(1);
                }
                blackNum.setContent(content);
                blackNum.setTime(time);
                change(blackNum);//将数据库中信息改变
                return true;
            }
        }
        return false;
    }

    public List<BlackNum> PhoneStop() {

        return null;
    }
}
