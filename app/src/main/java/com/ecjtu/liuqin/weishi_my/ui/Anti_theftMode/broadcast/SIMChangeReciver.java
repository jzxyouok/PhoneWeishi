package com.ecjtu.liuqin.weishi_my.ui.Anti_theftMode.broadcast;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * 接受开机时的广播来与获取到的SIM序列号进行比对，判断是否是手机卡变更
 */
public class SIMChangeReciver extends BroadcastReceiver {

    private SharedPreferences sp;//用来获取到存储的手机sim序列号以及安全手机号
    private TelephonyManager telephonyManager;
    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    public SIMChangeReciver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("开机启动的广播-------------------------");

        if (ACTION_BOOT.equals(intent.getAction())) {

            // //获取sp
            sp = context.getSharedPreferences("user", context.MODE_PRIVATE);

            telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            //获取当前手机SIM的序列号
            String current_simNumber = telephonyManager.getSimSerialNumber();
            //从SharedPreferences获取到以前存储的手机sim序列号
            String store_simNumber = sp.getString("simNumber_my", "");
            if (current_simNumber.equals(store_simNumber)) {
                //如果一致说明sim卡没有变更
                System.out.println("没有变更");
            } else {
                //如果变更那首先将获取到存在SharedPreferences中的安全号码，接着向安全号码发送变更信息
                String Safe_Number = sp.getString("safeNumber", "");
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,
                        0,new Intent(),0);
                if (Safe_Number != null) {
                    //初始化默认实例
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(Safe_Number, null, "手机号已经变更,请发送以下命令短信来执行所需操作:" +
                            "1.@music@ 开启报警音乐，适用于近处寻找    " +
                            "2.@local@ 将手机锁屏，并改掉密码，密码为你设置的登录密码    " +
                            "3.@location@ 开启定位，将手机所在位置返回给你     " +
                            "4.@clear@  清楚手机所有数据", pendingIntent, null);
                }
            }
        }
    }
}
