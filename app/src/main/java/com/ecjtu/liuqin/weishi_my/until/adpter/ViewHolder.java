package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {

	private Context mContext;
	private int mPosition;
	private View mConvertView;

	private SparseArray<View> mViews=new SparseArray<View>();

	public ViewHolder(Context context, int position, View convertView, ViewGroup parent, int LayId) {
		convertView = LayoutInflater.from(context).inflate(LayId, parent, false);
		mContext = context;
		mPosition = position;
		mConvertView = convertView;

		convertView.setTag(this);
	}

	public static ViewHolder newInstance(Context context, int position, View convertView, ViewGroup parent, int LayId) {

		if (convertView == null) {
			return new ViewHolder(context, position, convertView, parent, LayId);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			return holder;
		}
	}


	public <T extends View> T getView(int resId) {


		View view = mViews.get(resId);

		if (view == null) {
			view = mConvertView.findViewById(resId);
			mViews.put(resId, view);
		}
		
		return (T) view;
	}

	public View getmConvertView() {
		return mConvertView;
	}

}
