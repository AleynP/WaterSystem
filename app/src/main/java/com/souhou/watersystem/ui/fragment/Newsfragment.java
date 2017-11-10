package com.souhou.watersystem.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseFragment;
import com.souhou.watersystem.ui.activity.NewsActivity.NewInstRedActivity;
import com.souhou.watersystem.ui.activity.NewsActivity.NewAddActivity;
import com.souhou.watersystem.ui.activity.NewsActivity.NotHandelActivity;
import com.souhou.watersystem.ui.activity.UserActivity;
import com.souhou.watersystem.ui.activity.NewsActivity.YesHandelActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/15.
 */

public class Newsfragment extends BaseFragment {


    @BindView(R.id.bt_qrcode)
    ImageButton btQrcode;
    @BindView(R.id.bt_complete_img)
    ImageButton btCompleteImg;
    @BindView(R.id.bt_not_complete_img)
    ImageButton btNotCompleteImg;
    @BindView(R.id.bt_record_img)
    ImageButton btRecordImg;
    @BindView(R.id.bt_user_img)
    ImageButton btUserImg;
    Unbinder unbinder;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_qrcode, R.id.bt_complete_img, R.id.bt_not_complete_img, R.id.bt_record_img, R.id.bt_user_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_qrcode:
                intent = new Intent(getActivity(), NewAddActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_complete_img:
                intent = new Intent(getActivity(), YesHandelActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_not_complete_img:
                intent = new Intent(getActivity(), NotHandelActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_record_img:
                intent = new Intent(getActivity(), NewInstRedActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_user_img:
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
