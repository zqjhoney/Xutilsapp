package com.bwie.xutilsapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.xutilsapp.OffLine_loadActivity;
import com.bwie.xutilsapp.R;
import com.bwie.xutilsapp.utils.SharedPreferutil;


/**
 * Created by 张乔君 on 2017/8/30.
 */

public class RightFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout offline_load;
    private RelativeLayout noWIFI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){

            view=inflater.inflate(R.layout.right_fragment,container,false);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    private void initview() {
        offline_load = view.findViewById(R.id.offline_load);
        noWIFI = view.findViewById(R.id.noWIFI);
        offline_load.setOnClickListener(this);
        noWIFI.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.offline_load:

                //展示离线下载的
                startActivity(new Intent(getActivity(), OffLine_loadActivity.class));

                break;
            case R.id.noWIFI:

                new AlertDialog.Builder(getActivity()).setSingleChoiceItems(new String[]{"大图", "小图"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(i==0){
                            SharedPreferutil.putShareprefer("photo","big");
                            //大图

                        }else if(i==1){
                            //小图
                            SharedPreferutil.putShareprefer("photo","small");

                        }
                        dialogInterface.dismiss();//消失
                    }
                }).show();
                break;
        }
    }
}
