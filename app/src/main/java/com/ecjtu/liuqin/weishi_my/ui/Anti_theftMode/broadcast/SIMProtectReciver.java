package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.service.AntiProtectService;

/**
 * 这是手机防盗模块中的远程遥控手机的短信接收器
 */
public class SIMProtectReciver extends BroadcastReceiver {

    private SharedPreferences sp;//用来获取到存储的安全手机号

    public SIMProtectReciver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收到了广播",Toast.LENGTH_SHORT).show();
       if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
           //接收由SMS传过来的数据
           Bundle bundle=intent.getExtras();
           //判断是否有数据
           if(bundle!=null) {
               System.out.println("接受到短信...");
               // //获取sp
               sp = context.getSharedPreferences("user", context.MODE_PRIVATE);

               //通过pdus可获得接受到的所有的短信信息
               Object[] pdus= (Object[]) bundle.get("pdus");
               //构建短信对象array,并根据收到的对象长度来创建array的大小
               SmsMessage[] messages=new SmsMessage[pdus.length];
               for(int i=0;i<pdus.length;i++){
                   messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
           }
               //将发送来的短信取出发信人以及短信内容作出判断
               for(SmsMessage message:messages){
                   //获取发件人
                   String sender=message.getDisplayOriginatingAddress();
                   String Safe_Number = sp.getString("safeNumber", "");
                   System.out.println("安全号码:"+Safe_Number+"---"+"短信发送者:"+sender);
                   if(!Safe_Number.equals("")&&sender.equals("+86"+Safe_Number)){
                       //获取信息
                       String body=message.getDisplayMessageBody();
                       System.out.println("body:"+body);
                       Intent tintent=new Intent(context, AntiProtectService.class);
                       switch (body){
                           case "@music@"://开启报警音乐
                               System.out.println("----开启音乐");
                               tintent.putExtra("flag",0);
                           break;

                           case "@local@"://开启锁屏
                              tintent.putExtra("flag",1);
                               break;

                           case "@location@"://开启定位
                               tintent.putExtra("flag",2);
                               break;

                           case "@clear@"://开启清除数据
                               tintent.putExtra("flag",3);
                               break;
                       }
                       //在后台开启service
                       context.startService(tintent);
                   }
               }
           }
       }
    }
}
