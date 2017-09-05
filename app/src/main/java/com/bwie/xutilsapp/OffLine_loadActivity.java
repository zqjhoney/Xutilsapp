package com.bwie.xutilsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.bwie.xutilsapp.adapter.ResAdapter;
import com.bwie.xutilsapp.bean.TopBean;

import org.xutils.common.Callback;

import java.util.ArrayList;

public class OffLine_loadActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView lv;
    private TextView back;
    private TextView offline_load;
    private ArrayList<TopBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_line_load);
        initeview();
        initdata();
    }

    private void initdata() {

        list = new ArrayList<>();
        list.add(new TopBean("1", "新闻", true));
        list.add(new TopBean("2", "娱乐", true));
        list.add(new TopBean("3", "军事", true));
        list.add(new TopBean("4", "北京", true));


        ResAdapter ada = new ResAdapter(list, OffLine_loadActivity.this);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setAdapter(ada);
        ada.setOnItemClickListener(new ResAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                CheckBox off_cb=view.findViewById(R.id.off_cb);
                TopBean top=list.get(pos);

                if (off_cb.isChecked()) {
                    off_cb.setChecked(false);
                    top.state = false;
                } else {
                    off_cb.setChecked(true);
                    top.state = true;
                }
                list.set(pos,top);
            }
        });
    }
    private void initeview() {
        lv = (RecyclerView) findViewById(R.id.lv);
        back = (TextView) findViewById(R.id.offline_back);
        offline_load = (TextView) findViewById(R.id.offline_load);

        //设置点击事件
        back.setOnClickListener(this);
        offline_load.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.offline_back:
                finish();//退出
                break;
            case R.id.offline_load:

                if(list!=null && list.size()>0){

                    for (int i = 0; i <list.size() ; i++) {
                        System.out.println("=====状态"+list.get(i).state);
                        if(list.get(i).state){//位true的时候

                            loadData(list.get(i).getName());
                        }
                    }
                }


                break;
        }
    }

    private void loadData(String name) {

        //TODO 离线下载
    }
}
