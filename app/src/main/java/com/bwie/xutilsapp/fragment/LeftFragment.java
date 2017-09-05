package com.bwie.xutilsapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.xutilsapp.Load_moreActivity;
import com.bwie.xutilsapp.MainActivity;
import com.bwie.xutilsapp.QQlogActivity;
import com.bwie.xutilsapp.R;
import com.bwie.xutilsapp.utils.SharedPreferutil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import static android.content.ContentValues.TAG;


/**
 * Created by 张乔君 on 2017/8/30.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView load_more;
    private ImageView qq;
    private BaseUiListener mIUiListener;
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private ImageView yejian;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){

            view=inflater.inflate(R.layout.left_fragment,container,false);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();

        mTencent = Tencent.createInstance(APP_ID, getContext());    }

    private void initview() {
        load_more = view.findViewById(R.id.load_more);
        qq = view.findViewById(R.id.qq);
        yejian = view.findViewById(R.id.yejian);

        load_more.setOnClickListener(this);
        qq.setOnClickListener(this);
        yejian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.qq:
                mIUiListener = new BaseUiListener();

                mTencent.login(getActivity(),"all", (IUiListener) mIUiListener);
                break;
            case R.id.load_more:
                Intent in=new Intent(getActivity(), Load_moreActivity.class);
                startActivity(in);
                break;
            case R.id.yejian:

                int ui;
                ui=getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK ;

                if(ui==Configuration.UI_MODE_NIGHT_YES){
                    ((MainActivity)getActivity()).getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferutil.putSharepreferBoolean("night_theme",false);
                }else if(ui==Configuration.UI_MODE_NIGHT_NO){
                    ((MainActivity)getActivity()).getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferutil.putSharepreferBoolean("night_theme",true);
                }
                getActivity().recreate();


                break;
        }


    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }


                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getActivity(), "回调", Toast.LENGTH_SHORT).show();
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
