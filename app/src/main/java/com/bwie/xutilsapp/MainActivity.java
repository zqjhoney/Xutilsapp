package com.bwie.xutilsapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.xutilsapp.adapter.MyAdapter;
import com.bwie.xutilsapp.bean.TopBean;
import com.bwie.xutilsapp.countFragment.F1;
import com.bwie.xutilsapp.countFragment.F2;
import com.bwie.xutilsapp.countFragment.F3;
import com.bwie.xutilsapp.countFragment.F4;
import com.bwie.xutilsapp.fragment.LeftFragment;
import com.bwie.xutilsapp.fragment.RightFragment;
import com.bwie.xutilsapp.utils.GetUtils;
import com.bwie.xutilsapp.view.Horizontal_tabhost;
import com.google.gson.Gson;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.left_iv)
    ImageView left_iv;
    @ViewInject(R.id.right_iv)
    ImageView right_iv;

    @ViewInject(R.id.horizontal_tabhost)
    Horizontal_tabhost horizontal_tabhost;
    private SlidingMenu menu;
    private ArrayList<TopBean> list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        initget();
        initceshow();

        initview();

    }

    private void initview() {
        left_iv.setOnClickListener(this);
        right_iv.setOnClickListener(this);
    }

    //侧滑
    private void initceshow() {

        menu= new SlidingMenu(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.left_layout,new LeftFragment()).commit();

        menu.setMode(SlidingMenu.LEFT_RIGHT);//设置左右滑动
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//侧边滑
        menu.setShadowWidthRes(R.dimen.BehindOffsetRes);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        menu.setFadeDegree(0.75f);
        menu.setMenu(R.layout.left_layout);


        //设置右滑
        menu.setSecondaryMenu(R.layout.right_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_layout,new RightFragment()).commit();
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }

    private void initget() {
        ArrayList<Fragment> fraglist=new ArrayList<>();
        F1 f1=new F1();
        F2 f2=new F2();
        F3 f3=new F3();
        F4 f4=new F4();
        fraglist.add(f1);
        fraglist.add(f2);
        fraglist.add(f3);
        fraglist.add(f4);
        list = new ArrayList<>();
        list.add(new TopBean("1","新闻",true));
        list.add(new TopBean("2","娱乐",true));
        list.add(new TopBean("3","军事",true));
        list.add(new TopBean("4","北京",true));

        horizontal_tabhost.load(list,fraglist);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.left_iv:
                menu.showMenu();
                break;
            case R.id.right_iv:
                menu.showSecondaryMenu();
                break;
        }
    }


}
