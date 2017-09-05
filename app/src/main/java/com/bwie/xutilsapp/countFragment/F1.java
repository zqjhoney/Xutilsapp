package com.bwie.xutilsapp.countFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.xutilsapp.bean.Bean;
import com.bwie.xutilsapp.R;
import com.bwie.xutilsapp.adapter.MyAdapter;
import com.bwie.xutilsapp.utils.GetUtils;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

/**
 * Created by 张乔君 on 2017/9/21.
 */

public class F1 extends Fragment {


    View view;
    private XListView xlv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null){
            view=View.inflate(getActivity(), R.layout.f1,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        initdata();

    }

    private void initdata() {
        GetUtils.getresult(new GetUtils.Calls() {
            @Override
            public void success(List<Bean.ResultBean.DataBean> list) {
                MyAdapter adapter=new MyAdapter(getActivity(), (ArrayList<Bean.ResultBean.DataBean>) list);
                xlv.setAdapter(adapter);
            }
        });

    }

    private void initview() {
        xlv = view.findViewById(R.id.xlv);
    }
}
