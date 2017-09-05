package com.bwie.xutilsapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.xutilsapp.R;
import com.bwie.xutilsapp.bean.TopBean;

import java.util.ArrayList;

/**
 * Created by 张乔君 on 2017/9/21.
 */

public class Horizontal_tabhost extends LinearLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    int textsize;
    private int count;
    private ArrayList<TopBean> list;
    private ArrayList<Fragment> fraglist;
    private LinearLayout hor_layout;
    private ViewPager vp;
    private HorizontalScrollView hor_scollview;
    private ArrayList<TextView> tvlist;

    public Horizontal_tabhost(Context context) {
        this(context,null);

    }

    public Horizontal_tabhost(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Horizontal_tabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       this.context=context;
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typearray=context.obtainStyledAttributes(attrs,R.styleable.Horizontal_tabhost);
        textsize=typearray.getDimensionPixelSize(R.styleable.Horizontal_tabhost_textsize,15);
        typearray.recycle();
        
        initview();
    }

    //获取控件
    private void initview() {
        //获取子控件
        View view= LayoutInflater.from(context).inflate(R.layout.horizontal_layout,this,true);
        hor_scollview = view.findViewById(R.id.hor_scollview);
        hor_layout = view.findViewById(R.id.hor_layout);
        vp = view.findViewById(R.id.vp);
        vp.addOnPageChangeListener(this);


    }

    public void load(ArrayList<TopBean> list, ArrayList<Fragment> fraglist){

        this.list=list;
        this.fraglist=fraglist;
        this.count=list.size();
        tvlist = new ArrayList<>(count);
        draw();

    }

    private void draw() {
        horizondraw();
        viewpagerdraw();
    }

    private void viewpagerdraw() {
        if(list!=null && list.size()>0){
            for (int i = 0; i <list.size() ; i++) {

                View vi=View.inflate(context,R.layout.top_item,null);
                TextView tv=vi.findViewById(R.id.toptext_item);

                final int finalI = i;
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vp.setCurrentItem(finalI);
                    }
                });
                tv.setText(list.get(i).name);
                tvlist.add(tv);
                hor_layout.addView(vi);
            }

            tvlist.get(0).setSelected(true);
        }



    }

    private void horizondraw() {//头
        MyFragPage fg=new MyFragPage(((FragmentActivity)context).getSupportFragmentManager());

        vp.setAdapter(fg);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i <hor_layout.getChildCount() ; i++) {

            TextView tv= (TextView) hor_layout.getChildAt(i);
            if(i==position){
                tv.setSelected(true);
            }else{
                tv.setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyFragPage extends FragmentPagerAdapter{

        public MyFragPage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fraglist.get(position);
        }

        @Override
        public int getCount() {
            return fraglist.size();
        }
    }

}
