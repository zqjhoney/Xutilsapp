package com.bwie.xutilsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.xutilsapp.R;
import com.bwie.xutilsapp.bean.TopBean;

import java.util.ArrayList;

/**
 * Created by 张乔君 on 2017/9/4.
 */

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.MyHolder> {

    private ArrayList<TopBean> list;
    private Context context;

    public ResAdapter(ArrayList<TopBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
   private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.offline_item,null);
        MyHolder holder=new MyHolder(view);
        //设置条目点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener((Integer) view.getTag(),view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TopBean top=list.get(position);

        holder.top.setText(top.name);
        if(top.state){
            holder.off_cb.setChecked(true);
        }else{
            holder.off_cb.setChecked(false);
        }

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private TextView top;
        private CheckBox off_cb;
        public MyHolder(View itemView) {
            super(itemView);
            top=itemView.findViewById(R.id.off_top);
            off_cb=itemView.findViewById(R.id.off_cb);
        }
    }


    public interface  OnItemClickListener{
        void onItemClickListener(int pos, View view);
    }


}
