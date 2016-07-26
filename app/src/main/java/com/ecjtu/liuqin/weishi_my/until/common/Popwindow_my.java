
package com.ecjtu.liuqin.weishi_my.until.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.ecjtu.liuqin.weishi_my.R;


public class Popwindow_my extends PopupWindow implements OnClickListener{
    private View mMenuView;
    private Button btn_white,btn_black,btn_cancle;

    private Context mContext;

    public Popwindow_my(Context context, OnClickListener itemsOnClick) {
        super(context);

        this.mContext=context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popu_layout, null);
        btn_white= (Button) mMenuView.findViewById(R.id.btn_white);
        btn_black= (Button) mMenuView.findViewById(R.id.btn_black);
        btn_cancle= (Button) mMenuView.findViewById(R.id.btn_cancle);
        btn_white.setOnClickListener(this);
        btn_black.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);



        //设置SelectPopWindow的View
        this.setContentView(mMenuView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPopWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPopWindow弹出窗体的动画效果(设置动画前要先在style中设置)
        this.setAnimationStyle(R.style.popwin_anim_style);
        //实例化一个colorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(dw);
        //myMenuView添加onTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.popu_lay).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        //点击取消popuWindow
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_white:

                break;

            case R.id.btn_black:
                GetPhoneList.getPhoneInfo(mContext);
                break;

            case R.id.btn_cancle:
                dismiss();
                break;
        }
    }
}

