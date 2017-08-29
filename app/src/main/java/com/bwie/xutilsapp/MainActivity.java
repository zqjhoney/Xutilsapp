package com.bwie.xutilsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bwie.xutilsapp.adapter.MyAdapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.xlv)
    XListView xlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        initget();


    }

    private void initget() {
        RequestParams params=new RequestParams(Api.GET_URL);
        params.addQueryStringParameter("key",Api.KEY);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("====="+result);
                Gson gson=new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                List<Bean.ResultBean.DataBean> data = bean.getResult().getData();

                MyAdapter adapter=new MyAdapter(MainActivity.this, (ArrayList<Bean.ResultBean.DataBean>) data);

                xlv.setAdapter(adapter);

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
}
