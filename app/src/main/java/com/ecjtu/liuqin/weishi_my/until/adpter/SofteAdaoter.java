package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.SoftInfo;

import java.util.List;

/**
 * Created by liuqin on 2016/7/20.
 */
public class SofteAdaoter extends BaseAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    List<SoftInfo> softInfoList;

    public SofteAdaoter(Context mContext,List<SoftInfo> mSoftInfoList){

        this.context=mContext;
        this.softInfoList=mSoftInfoList;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return softInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return softInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=null;
        if(view==null){
           view1= layoutInflater.inflate(R.layout.softe_item, viewGroup,false);
        }else {
            view1=view;
        }
        return view1;
    }
}
