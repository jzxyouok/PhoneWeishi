package com.ecjtu.liuqin.weishi_my.ui.SofterManageMode.frament;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.SoftInfo;
import com.ecjtu.liuqin.weishi_my.until.Dialog.Effectstype;
import com.ecjtu.liuqin.weishi_my.until.Dialog.NiftyDialogBuilder;
import com.ecjtu.liuqin.weishi_my.until.adpter.MyAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuqin on 2016/7/20.
 * 软件管理模块的fragment
 */
public class SofteFragment extends Fragment implements AdapterView.OnItemLongClickListener{


    //自定义dialog的关联属性
    private Effectstype effect;
    private NiftyDialogBuilder dialogBuilder;


    private static final String BUNDLE_TITLE="soft";
    private List<SoftInfo> mSoftInfoList;
    public static SofteFragment newInstance(List<SoftInfo> softInfoList){
        Bundle bundle=new Bundle();
        bundle.putSerializable(BUNDLE_TITLE, (Serializable) softInfoList);
        SofteFragment softeFragment=new SofteFragment();
        softeFragment.setArguments(bundle);
        return softeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle=getArguments();

        if(bundle!=null){
            mSoftInfoList= (List<SoftInfo>) bundle.getSerializable(BUNDLE_TITLE);
        }

        //等以后学会了recycleView万能的适配器，就可以直接用短信电话拦截模块中的布局
        View view=inflater.inflate(R.layout.frg_softe,null);
        ListView mSofteList= (ListView) view.findViewById(R.id.soft_list);
        mSofteList.setAdapter(new MyAdapter(getActivity(),mSoftInfoList,R.layout.softe_item));

        mSofteList.setOnItemLongClickListener(this);

        return view;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

        //下面两行代码是定义的dialog
        dialogBuilder=NiftyDialogBuilder.getInstance(getActivity());
        effect=Effectstype.SlideBottom;
        dialogBuilder
                .withTitle("提示框")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("确认是否卸载？")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getResources().getDrawable(R.drawable.dailog_icon))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("确定")                                      //def gone
                .withButton2Text("取消")                                  //def gone
                .setCustomView(R.layout.custom_view,getActivity())         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                       int flag= mSoftInfoList.get(i).getFlag();
                        if(flag==1){//如果是用户程序就让其卸载
                            Uri packageUri=Uri.parse("package:"+mSoftInfoList.get(i).getPackageName());
                            Intent uninsintall=new Intent(Intent.ACTION_DELETE,packageUri);
                            startActivityForResult(uninsintall,0);
//                            notify();//有待测试
                        }else if(flag==2){//如是系统程序就让其不能卸载
                            Snackbar.make(v,"注意:系统程序只有获取系统权限才能卸载",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      dialogBuilder.dismiss();
                    }
                })
                .show();

        return true;//改成true才不会触发onItemOnclick
    }
}
