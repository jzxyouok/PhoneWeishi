package com.ecjtu.liuqin.weishi_my.until.common;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.ecjtu.liuqin.weishi_my.mode.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuqin on 2016/7/19.
 * 获取手机的通讯录
 */
public class GetPhoneList {

    private static List<PhoneInfo> phoneInfoList = new ArrayList<>();

    public static String getPhoneInfo(Context context) {
        //通过context来获取手机存储中的数据，手机中的数据是存储在数据库中，所以有专门的数据库的接口,
        //并且数据库中返回的类型都是Cursor类型(记住)
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        String phoneNumber;
        String phoneName;
        while (cursor.moveToNext()) {//对获取的数据进行循环查询
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //通过getColumnIndex来获取Phone.NUMBER的字段，并且用getString来显示成字符形式
            phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            PhoneInfo phoneinfo = new PhoneInfo(phoneName, phoneNumber);
            phoneInfoList.add(phoneinfo);
            System.out.println(phoneName + phoneNumber);
        }
        return null;
    }
}
