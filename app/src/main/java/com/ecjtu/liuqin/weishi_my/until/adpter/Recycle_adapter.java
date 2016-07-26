package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的适配器
 */
public class Recycle_adapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private List<BlackNum> beans=new ArrayList<>();
    private Context mCtx;
    private LayoutInflater mInflater;
    private OnItemActionListener mOnItemActionListener;

    public Recycle_adapter(Context mCtx, List<BlackNum> beans) {
        super();
        this.beans = beans;
        this.mCtx = mCtx;
        mInflater = LayoutInflater.from(mCtx);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  mInflater.inflate(R.layout.recyle_item, parent,false);
        RecycleViewHolder recycleViewHolder=new RecycleViewHolder(v);
        recycleViewHolder.setIsRecyclable(true);
        return recycleViewHolder;

    }

    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, int position) {
        holder.itemTitle.setText(beans.get(position).getNumber());
        holder.itemContent.setText(beans.get(position).getContent());
        holder.itemCount.setText((beans.get(position).getCount())+"");
        holder.itemTime.setText(beans.get(position).getTime());
        if(mOnItemActionListener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    mOnItemActionListener.onItemClickListener(v,holder.getPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
                    return mOnItemActionListener.onItemLongClickListener(v, holder.getPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }
/*
定义点击事件
 */
    public interface OnItemActionListener
    {
        public void onItemClickListener(View v, int pos);
        public boolean onItemLongClickListener(View v, int pos);
    }
    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.mOnItemActionListener = onItemActionListener;
    }
}

class RecycleViewHolder extends RecyclerView.ViewHolder
{
    public TextView itemTitle;
    public TextView itemContent;
    public TextView itemCount;
    public TextView itemTime;

    public RecycleViewHolder(View layout) {
        super(layout);
        itemTitle = (TextView) layout.findViewById(R.id.recycle_title);
        itemContent = (TextView) layout.findViewById(R.id.recycle_content);
        itemCount= (TextView) layout.findViewById(R.id.recycle_count);
        itemTime= (TextView) layout.findViewById(R.id.recycle_time);
    }
}
