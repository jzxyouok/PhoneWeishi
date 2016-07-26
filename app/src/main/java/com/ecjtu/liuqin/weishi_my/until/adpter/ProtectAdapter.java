package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.ProtectItem;

import java.util.List;

/**
 * Created by liuqin on 2016/7/21.
 * 这是手机防盗界面中的adapter
 */
public class ProtectAdapter extends CommanAdapter<ProtectItem>{
    public ProtectAdapter(Context context, List<ProtectItem> bean, int LayId) {
        super(context, bean, LayId);
    }

    @Override
    public void current(ViewHolder hodler, Object object) {
        String flag_tv="";
       ProtectItem protectItem= (ProtectItem) object;
        ((ImageView)hodler.getView(R.id.pro_icon)).setImageResource(protectItem.getProIcon());
        ((TextView)hodler.getView(R.id.pro_Tv)).setText(protectItem.getProText());

        boolean flag=protectItem.isProFlag();
        if(flag==true){
          flag_tv="已开启";
            ((TextView)hodler.getView(R.id.pro_flag)).setTextColor(Color.parseColor("#CFCFCF"));
        }else {
            flag_tv="未开启";
            ((TextView)hodler.getView(R.id.pro_flag)).setTextColor(Color.parseColor("#F07913"));
        }
        ((TextView)hodler.getView(R.id.pro_flag)).setText(flag_tv);
    }
}
