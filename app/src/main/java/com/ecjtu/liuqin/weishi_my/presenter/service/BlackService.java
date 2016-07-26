package com.ecjtu.liuqin.weishi_my.presenter.service;

import android.content.Context;

import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;
import com.ecjtu.liuqin.weishi_my.mode.dao.BlackNumDao;

import java.util.List;

/**
 * Created by liuqin on 2016/7/19.
 */
public abstract class BlackService implements BaseService {

    public BlackService(Context context){
        dao=new BlackNumDao(context);
    }

    private BlackNumDao dao;
    //增
    @Override
    public void add(BlackNum blackNum) {
        dao.add(blackNum);
    }
    //删
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    //改
    @Override
    public void change(BlackNum blackNum) {
        dao.change(blackNum);
    }

    //查询所有
    @Override
    public List<BlackNum> findAll() {
        List<BlackNum> blackNumList = null;
        blackNumList = dao.findAll();
        return blackNumList;
    }

    //根据号码来查询单个对象
    @Override
    public BlackNum findObj(String number) {
        BlackNum blackNum = null;
        blackNum = dao.findObj(number);
        return blackNum;
    }
    public abstract List<BlackNum> MessageStop();
    public abstract boolean IsMessageStop(String sender, String content, String time);
}
