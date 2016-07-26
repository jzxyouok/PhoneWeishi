package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
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
       TranfficBean tranfficBean= (TranfficBean) object;

        ((ImageView)hodler.getView(R.id.list_icon)).setImageDrawable(tranfficBean.getAppicon());
        ((TextView)hodler.getView(R.id.list_appname)).setText((CharSequence) tranfficBean.getAppName());
        ((TextView)hodler.getView(R.id.list_total)).setText((CharSequence) tranfficBean.getAppTotal());
        ((TextView)hodler.getView(R.id.list_upload)).setText((CharSequence) tranfficBean.getAppupLoad());
        ((TextView)hodler.getView(R.id.list_load)).setText((CharSequence) tranfficBean.getAppload());

    }
}
