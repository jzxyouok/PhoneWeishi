package com.ecjtu.liuqin.weishi_my.ui.InterceptMode.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.ecjtu.liuqin.weishi_my.mode.dao.BlackNumDao;

import java.lang.reflect.Method;

/**
 * Created by liuqin on 2016/7/19.
 * 拦截电话服务
 */
public class PhoneService extends Service {
    private TelephonyManager tm;
    private BlackNumDao dao;
    private MyListener listener;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //System.out.println("启动服务");
        dao=new BlackNumDao(this);
        tm=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        listener=new MyListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        return super.onStartCommand(intent, flags, startId);
    }
    private class MyListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //System.out.println("电话来了"+incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    String result=dao.findObj(incomingNumber).getType();
                    if("拦截电话".equals(result)||"拦截电话和短信".equals(result)){
                        Toast.makeText(PhoneService.this, "成功拦截号码"+incomingNumber,Toast.LENGTH_SHORT).show();
                        endCall();
                    }
                    break;

                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }
    public void endCall() {
        try {
            Class clazz=PhoneService.class.getClassLoader().loadClass("android.os.ServiceManager");
            Method method=clazz.getDeclaredMethod("getService", String.class);
            IBinder iBinder=(IBinder)method.invoke(null, TELEPHONY_SERVICE);
//            ITelephony.Stub.asInterface(iBinder).endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
