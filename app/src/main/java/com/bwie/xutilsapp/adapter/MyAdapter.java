package com.bwie.xutilsapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.xutilsapp.Bean;
import com.bwie.xutilsapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 张乔君 on 2017/8/29.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Bean.ResultBean.DataBean> list;
    private int atype=0;
    private int btype=1;
    private int typenum=2;

    public MyAdapter(Context context, ArrayList<Bean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {

        if(position%2==0){

            return atype;
        }else{
            return btype;
        }
    }

    @Override
    public int getViewTypeCount() {

        return typenum;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHoldera aholder=null;
        ViewHolderb bholder=null;
        int type=getItemViewType(i);
        if(view==null){

            switch (type){
                case 0:
                    aholder=new ViewHoldera();
                    view=View.inflate(context, R.layout.atype,null);
                    aholder.a_iv=view.findViewById(R.id.a_iv);
                    aholder.a_net=view.findViewById(R.id.a_net);
                    aholder.a_title=view.findViewById(R.id.a_title);
                    aholder.a_time=view.findViewById(R.id.a_time);
                    view.setTag(aholder);
                    break;
                case 1:
                    bholder=new ViewHolderb();
                    view=View.inflate(context,R.layout.btype,null);
                    bholder.b_iv=view.findViewById(R.id.b_iv);
                    bholder.b_net=view.findViewById(R.id.b_net);
                    bholder.b_time=view.findViewById(R.id.b_time);
                    bholder.b_title=view.findViewById(R.id.b_title);
                    view.setTag(bholder);
                    break;
            }
        }else switch (type) {
            case 0:
                aholder = (ViewHoldera) view.getTag();
                break;
            case 1:
                bholder= (ViewHolderb) view.getTag();
                break;
        }
        Bean.ResultBean.DataBean data=list.get(i);
        switch (type){
            case 0:
                aholder.a_net.setText(data.getAuthor_name());
                aholder.a_time.setText(data.getDate());
                aholder.a_title.setText(data.getTitle());
                ImageLoader.getInstance().displayImage(data.getThumbnail_pic_s(),aholder.a_iv);
                break;
            case 1:
                bholder.b_net.setText(data.getAuthor_name());
                bholder.b_time.setText(data.getDate());
                bholder.b_title.setText(data.getTitle());
                ImageLoader.getInstance().displayImage(data.getThumbnail_pic_s(),bholder.b_iv);
                break;
        }
        return view;
    }
    class ViewHoldera{
        ImageView a_iv;
        TextView a_title,a_net,a_time;
    }
    class ViewHolderb{
        ImageView b_iv;
        TextView b_title,b_net,b_time;
    }
}
