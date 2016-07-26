package com.ecjtu.liuqin.weishi_my.ui.InterceptMode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecjtu.liuqin.weishi_my.R;
import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;
import com.ecjtu.liuqin.weishi_my.until.adpter.Recycle_adapter;
import com.ecjtu.liuqin.weishi_my.until.common.SpacesItemDecoration;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuqin on 2016/7/18.
 */
public class CallSMSFragment extends Fragment{

    public static final  String BUNDLE_TITLE="typeList";
    private List<BlackNum> typeList;
   public static CallSMSFragment newInstance(List<BlackNum> typeList){
       Bundle bundle=new Bundle();
       bundle.putSerializable(BUNDLE_TITLE, (Serializable) typeList);
       CallSMSFragment callSMSFragment=new CallSMSFragment();
       callSMSFragment.setArguments(bundle);
       System.out.println("llllllll");
       return callSMSFragment;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        System.out.println("00000000000");
        Bundle bundle=getArguments();

        if(bundle!=null){
           typeList= (List<BlackNum>) bundle.getSerializable(BUNDLE_TITLE);
        }

        System.out.println("-----");

        View view=inflater.inflate(R.layout.frg_callsms,null);

        RecyclerView mRecyclerView= (RecyclerView) view.findViewById(R.id.call_recycle);
        //设置adapter
        Recycle_adapter adapter=new Recycle_adapter(getActivity(),typeList);
        mRecyclerView.setAdapter(adapter);


        //设置layoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
        return view;
    }
}
