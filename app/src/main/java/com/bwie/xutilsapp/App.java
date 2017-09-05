package com.bwie.xutilsapp;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by 张乔君 on 2017/8/29.
 */

public class App extends Application {
    public static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
       initxutil();
        initImage();
        MobSDK.init(this, Api.APPKEY,Api.SETRET);

    }
    private void initImage() {

        DisplayImageOptions option=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageOnFail(R.drawable.ic_error)
                .build();
        ImageLoaderConfiguration conf=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(option)
                .build();
        ImageLoader.getInstance().init(conf);
    }

    private void initxutil() {
        x.Ext.init(this);
        x.Ext.setDebug(false);//输出log日志，开启会影响性能
    }
}
