package com.ecjtu.liuqin.weishi_my.ui.InterceptMode.broadcast;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.ecjtu.liuqin.weishi_my.presenter.service.BlackService;
import com.ecjtu.liuqin.weishi_my.presenter.service.impl.BlackTypeImpl;

import java.util.Date;

/**
 * Created by liuqin on 2016/7/18.
 * 利用广播来实现短信的拦截
 */
public class SMSRcever extends BroadcastReceiver {
   private BlackService blackImpl;

    @TargetApi(24)
    @Override
    public void onReceive(Context context, Intent intent) {
        blackImpl=new BlackTypeImpl(context);

      //接受系统短信
        String action=intent.getAction();
        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
            Toast.makeText(context,"短信来了",Toast.LENGTH_SHORT).show();

            //获取短信
            Object[] objs= (Object[]) intent.getExtras().get("pdus");
          for(Object obj:objs){
                 SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
              //获取发件人
              String sender=smsMessage.getDisplayOriginatingAddress();
              //获取信息
              String body=smsMessage.getDisplayMessageBody();

              SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
              long time=smsMessage.getTimestampMillis();
              String t=smf.format(new Date(time));
              Toast.makeText(context,sender+":"+body+":"+t,Toast.LENGTH_SHORT).show();

              boolean flag= blackImpl.IsMessageStop(sender,body,t);

              if(flag==false){
                  Toast.makeText(context,"白名单电话",Toast.LENGTH_SHORT).show();
              }
              else {
                  //拦截短信，让系统短信接收不到
                  abortBroadcast();
              }
          }
        }
    }
}
