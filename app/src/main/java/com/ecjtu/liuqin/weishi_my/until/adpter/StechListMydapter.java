package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;

import com.ecjtu.liuqin.weishi_my.mode.bean.TranfficBean;

import java.util.List;

/**
 * Created by liuqin on 2016/7/26.
 * 有弹性的listView的适配器
 */
public class StechListMydapter extends CommanAdapter<TranfficBean>{
    public StechListMydapter(Context context, List<TranfficBean> bean, int LayId) {
        super(context, bean, LayId);
    }

    @Override
    public void current(ViewHolder hodler, Object object) {
           
    }
}
