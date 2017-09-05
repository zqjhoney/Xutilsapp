package com.bwie.xutilsapp.utils;

import com.bwie.xutilsapp.Api;
import com.bwie.xutilsapp.bean.Bean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 张乔君 on 2017/9/21.
 */

public class GetUtils {

    public static void getresult(final Calls call){
        RequestParams params=new RequestParams(Api.GET_URL);
        params.addQueryStringParameter("key",Api.KEY);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("====="+result);
                Gson gson=new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                List<Bean.ResultBean.DataBean> data = bean.getResult().getData();


                call.success(data);
//                MyAdapter adapter=new MyAdapter(MainActivity.this, (ArrayList<Bean.ResultBean.DataBean>) data);
//
//                xlv.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


    public interface Calls{
        void success(List<Bean.ResultBean.DataBean> list);
    }
}
