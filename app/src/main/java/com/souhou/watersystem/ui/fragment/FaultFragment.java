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
import com.souhou.watersystem.ui.activity.FaultActivity.FaultNewActivity;
import com.souhou.watersystem.ui.activity.FaultActivity.FaultNotHandActivity;
import com.souhou.watersystem.ui.activity.FaultActivity.FaultHandActivity;
import com.souhou.watersystem.ui.activity.FaultActivity.FaultRcodHandelActivity;
import com.souhou.watersystem.ui.activity.UserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14.
 */

public class FaultFragment extends BaseFragment {
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
    Intent intent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fault, container, false);
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
                intent = new Intent(getActivity(), FaultNewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_complete_img:
                intent = new Intent(getActivity(), FaultHandActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_not_complete_img:
                intent = new Intent(getActivity(), FaultNotHandActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_record_img:
                intent = new Intent(getActivity(), FaultRcodHandelActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_user_img:
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
