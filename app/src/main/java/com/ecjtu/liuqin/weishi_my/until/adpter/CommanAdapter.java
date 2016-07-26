package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 万能适配器
 * @param <T>
 */
public abstract class CommanAdapter<T> extends BaseAdapter{
	
	protected Context mContext;
	protected List<T> mBeans;
	protected int mLayId;

	public CommanAdapter(Context context,List<T> bean,int LayId) {
		
		mContext=context;
		mBeans=bean;
		mLayId=LayId;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBeans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public  View getView(int position, View convertView, ViewGroup parent){
		
		ViewHolder hodler=ViewHolder.newInstance(mContext, position,
				convertView, parent, mLayId);
		current(hodler, getItem(position));
		
		return hodler.getmConvertView();
	}
    
	public abstract void current(ViewHolder hodler,Object object);
}
