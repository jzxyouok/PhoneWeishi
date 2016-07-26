package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.SoftInfo;

import java.util.List;

/**
 * Created by liuqin on 2016/7/20.
 * 这是软件管家中的adapter
 */
public class MyAdapter extends CommanAdapter<SoftInfo> {
    public MyAdapter(Context context, List<SoftInfo> bean, int LayId) {
        super(context, bean, LayId);
    }

    @Override
    public void current(ViewHolder hodler, Object object) {
        SoftInfo softInfo = (SoftInfo) object;
        ((ImageView)hodler.getView(R.id.soft_icon)).setImageDrawable(softInfo.getIcon());
        ((TextView)hodler.getView(R.id.softe_name)).setText(softInfo.getAppname());
        ((TextView)hodler.getView(R.id.soft_PackgeName)).setText(softInfo.getPackageName());
        int flag=softInfo.getFlag();
        if(flag==1){
            //代表是系统程序
            ((TextView) hodler.getView(R.id.flag_Tv)).setText("用户");
        }else if(flag==2){
            ((TextView) hodler.getView(R.id.flag_Tv)).setText("系统");
        }
    }
}
