package com.ecjtu.liuqin.weishi_my.presenter.service;

import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;

import java.util.List;

/**
 * Created by liuqin on 2016/7/18.
 */
public interface BaseService {
    void add(BlackNum blackNum);
    void delete(Long id);
    void change(BlackNum blackNum);
    List<BlackNum> findAll();
    BlackNum findObj(String number);
}
