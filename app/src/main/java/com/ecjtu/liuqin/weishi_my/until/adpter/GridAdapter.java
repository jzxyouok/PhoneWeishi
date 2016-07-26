package com.ecjtu.liuqin.weishi_my.until.adpter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.GridBean;

import java.util.List;

/**
 * Created by liuqin on 2016/7/22.
 * 这是GridView的适配器
 */
public class GridAdapter extends CommanAdapter<GridBean> {

    public GridAdapter(Context context, List<GridBean> bean, int LayId) {
        super(context, bean, LayId);
    }

    @Override
    public void current(ViewHolder hodler, Object object) {
          GridBean gridBean= (GridBean) object;
        ((ImageView)hodler.getView(R.id.grid_img)).setImageResource(gridBean.getGridImage());
        ((TextView)hodler.getView(R.id.grid_text)).setText(gridBean.getGridText());
    }
}
