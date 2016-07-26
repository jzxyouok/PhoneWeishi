package com.ecjtu.liuqin.weishi_my.ui.TrafficMode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这是流量统计模块中的获取到运营商返回信息中的总流量的
 */
public class YunyingShangReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收到了广播",Toast.LENGTH_SHORT).show();
        System.out.println("------------00000----------------------");
       if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
           //接收由SMS传过来的数据
           Bundle bundle=intent.getExtras();
           //判断是否有数据
           if(bundle!=null) {
               System.out.println("接受到短信...");
               //通过pdus可获得接受到的所有的短信信息
               Object[] pdus = (Object[]) bundle.get("pdus");
               //构建短信对象array,并根据收到的对象长度来创建array的大小
               SmsMessage[] messages = new SmsMessage[pdus.length];
               for (int i = 0; i < pdus.length; i++) {
                   messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
               }
               //将发送来的短信取出发信人以及短信内容作出判断
               for(int i=0;i<1;i++){
                   //获取发件人
                   String sender=messages[0].getDisplayOriginatingAddress();

                   if(sender.equals("10086")){
                       //获取信息
                       String body=messages[0].getDisplayMessageBody();
                       System.out.println("body:"+body);

                       String[] infos=body.split(",");
                       System.out.println(infos[1]+"----"+infos[2]);
                       getNumber(infos[1]);
                       getNumber(infos[2]);

                   }
               }
           }
       }
    }


    //利用正则表达式来获取字符串中的数字
    public void getNumber(String str){
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);
        System.out.println( m.replaceAll("").trim()+"-----------------.88888");
        if(m.find()){
            System.out.println(m.group());
        }
    }
}
