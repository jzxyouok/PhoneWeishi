package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.activity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.broadcast.ScreenDivceReciver;
import com.ecjtu.liuqin.weishi_my.until.Dialog.Effectstype;
import com.ecjtu.liuqin.weishi_my.until.Dialog.NiftyDialogBuilder;

/**
 * 这是手机防盗界面中的清楚功能
 */
public class AntiClearActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private Button mEnten_button;
    private TextView mHintTv;
    private ImageView mHintImg;

    //设备管理器的声明
    private DevicePolicyManager dpm;
    private ComponentName componentName ;


    //自定义dialog的关联属性
    private Effectstype effect;
    private NiftyDialogBuilder dialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //同样的，因为和报警音乐的界面是一样的，所以复用即可
        setContentView(R.layout.activity_anti_music);

        ///设置进出场动画
        getWindow().setEnterTransition(new Slide().setDuration(1200));
        getWindow().setExitTransition(new Slide().setDuration(1200));

        mEnten_button = (Button) findViewById(R.id.enter_button);
        mEnten_button.setOnClickListener(this);
        mHintTv = (TextView) findViewById(R.id.hintTv);
        mHintTv.setText("手机数据清除后，将不会留下私密信息给小偷，不过体验请谨慎，小心数据的丢失");

        mHintImg = (ImageView) findViewById(R.id.antiIm);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("手机清理");
        setSupportActionBar(mToolbar);
        //关键下面两句话，设置了回退按钮，及点击事件的效果
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View view) {
        //下面两行代码是定义的dialog
        dialogBuilder=NiftyDialogBuilder.getInstance(this);
        effect=Effectstype.SlideBottom;
        dialogBuilder
                .withTitle("提示框")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("确认是否清除手机全部数据？")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getResources().getDrawable(R.drawable.dailog_icon))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("确定")                                      //def gone
                .withButton2Text("取消")                                  //def gone
                .setCustomView(R.layout.custom_view,this)         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      clear();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }

    public void clear(){
        //取得系统服务
        dpm  = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, ScreenDivceReciver.class);
        //添加一个隐式意图，完成设备权限的添加
        //这个Intent (DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)跳转到 权限提醒页面
        //并传递了两个参数EXTRA_DEVICE_ADMIN 、 EXTRA_ADD_EXPLANATION
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

        //权限列表
        //EXTRA_DEVICE_ADMIN参数中说明了用到哪些权限，
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);

        //描述(additional explanation)
        //EXTRA_ADD_EXPLANATION参数为附加的说明
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "数据已全部清理，望施主莫执着");

        startActivityForResult(intent, 0);
        boolean active = dpm.isAdminActive(componentName);
        if (active) {
            //清除手机数据
          dpm.wipeData(0);
        }
    }
}
