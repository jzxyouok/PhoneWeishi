package com.ecjtu.liuqin.weishi_my.mode.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecjtu.liuqin.weishi_my.mode.bean.BlackNum;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuqin on 2016/7/18.
 * 黑名单的dao层操作
 */
public class BlackNumDao {
    private MyOpenhelper openhelper;
    private SQLiteDatabase db;

    public BlackNumDao(Context context){
        openhelper=new MyOpenhelper(context,"phone_protect.db",null,1);
        //也就是获取数据库对象
        db=openhelper.getReadableDatabase();
    }

    //添加黑名单
    public void add(BlackNum blackNum){

        String sql="insert into s_black(number ,type ,count ,time ,content) values(? , ? ,? ,? ,?)";
        db.execSQL(sql,new String[]{blackNum.getNumber(), blackNum.getType(), String.valueOf(blackNum.getCount()),
        blackNum.getTime(),blackNum.getContent()});
    }


    //删除黑名单
    public void delete(Long id){
        String sql="delete from s_black where _id= "+ ( id);
        db.execSQL(sql);
    }

    //更新黑名单
    public void change(BlackNum blackNum){
        ContentValues values = new ContentValues();
        values.put("number", blackNum.getNumber());
        values.put("type", blackNum.getType());
        values.put("count",blackNum.getCount());
        values.put("time",blackNum.getTime());
        values.put("content",blackNum.getContent());
        db.update("s_black", values, "_id= ?",
                new String[] { String.valueOf(blackNum.getId()) });

    }

  /*  //查询黑名单的类型(根据号码来查询type)
    public String findBytele(String number){
        String tp="";
        Cursor cursor = db.query("s_black", new String[] { "type" },
                "number= ?", new String[] { number }, null, null, null);
        while (cursor.moveToNext()) {
            tp = cursor.getString(0);
        }
        return tp;
    }

    //通过类型来将号码查出来
    public List<String> findByType(String type){
        List<String> telephone=new ArrayList<>();
        Cursor cursor = db.query("s_black", new String[] { "telephoneNum" },
                "type= ?", new String[]{type}, null, null, null);
        while (cursor.moveToNext()) {
            telephone.add(cursor.getString(0));
        }
        return telephone;
    }*/

    //查询所有的黑名单
    public List<BlackNum> findAll(){
        List<BlackNum> list = new ArrayList<>();
        Cursor cursor = db.query("s_black", null, null, null, null, null,
                "_id desc");
        while (cursor.moveToNext()) {
            list.add(new BlackNum(cursor.getLong(0), cursor.getString(1),
                    cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
        }
        return list;
    }

    //通过手机号码查询出一个BlackNum对象
    public BlackNum findObj(String number){
        BlackNum blackNum=null;
        Cursor cursor = db.query("s_black", new String[] { "id" ,"number" ,"type", "count" ,"time" ,"content" },
                "number= ?", new String[] { number }, null, null, null);
        while (cursor.moveToNext()) {
            Long id= cursor.getLong(0);
            String telenumber=cursor.getString(1);
            String type=cursor.getString(2);
            int count=cursor.getInt(3);
            String time=cursor.getString(4);
            String content=cursor.getString(5);
            blackNum=new BlackNum(id,telenumber,type,count,time,content);
        }
        return blackNum;
    }

    //关闭数据库
    public void close(){
        if(openhelper!=null){
            openhelper.close();
        }
    }
}
