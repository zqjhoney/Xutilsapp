package com.bwie.xutilsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 张乔君 on 2017/9/5.
 */

public class NetWorkUtils {
    public static void getNetStatus(Context context,NetWork netWork){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network=manager.getActiveNetworkInfo();
        if(network!=null){
            if(network.getType()==ConnectivityManager.TYPE_WIFI){

                netWork.WIFInetwork();
            }else  if(network.getType()==ConnectivityManager.TYPE_MOBILE){

                netWork.mobleNetWork();
            }else{

                netWork.noNetWork();
                //有设置，却没有网络
            }
        }else{
            //没有网络
            netWork.noNetWork();
        }

    }

    public interface NetWork{
        void WIFInetwork();
        void mobleNetWork();
        void noNetWork();
    }
}
