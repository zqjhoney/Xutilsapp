package com.bwie.xutilsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwie.xutilsapp.App;

/**
 * Created by 张乔君 on 2017/9/3.
 */

public class SharedPreferutil {
    private final static String NAME="comm_name";

    public static SharedPreferences getPreferences(){
        return  App.mcontext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 添加
     * @param key
     * @param value
     */
    public static void putShareprefer(String key,String value){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(key,value).commit();
    }

    public static void putSharepreferBoolean(String key,Boolean b){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putBoolean(key,b).commit();
    }

    /**
     * 查询
     */
    public static String getShareprefervalue(String key){

       return getPreferences().getString(key,"");

    }
    public static boolean getSharepreferBooleanvalue(String key){

        return getPreferences().getBoolean(key,false);

    }

    /**
     * 清除
     */

    public static void removeShareprefer(String key){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.remove(key).commit();
    }




}
