package com.ecjtu.liuqin.weishi_my.mode.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库的帮助类:创建数据库表和数据库的更新
 * Created by liu_qin on 2016/7/18.
 */
public class MyOpenhelper extends SQLiteOpenHelper {
    // /定义该程序中每张表的建表语句
    private String sql = "create table s_black("
            + "_id integer primary key autoincrement ," + " number , type , count , time ,content)";

    public MyOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    // 用于数据库版本的更新
    // 旧版本和新版本不一样时被调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
