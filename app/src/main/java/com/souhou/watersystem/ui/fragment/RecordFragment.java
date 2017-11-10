package com.souhou.watersystem.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseFragment;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterAlbumActivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterQRActivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterRecordctivity;
import com.souhou.watersystem.ui.activity.UserActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecordFragment extends BaseFragment {
    Unbinder unbinder;
    private View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_record, container, false);
        unbinder = ButterKnife.bind(this, rootview);
        return rootview;
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
                startActivity(MeterQRActivity.class);
                break;
            case R.id.bt_complete_img:
                startActivity("str", "1", MeterAlbumActivity.class);
                break;
            case R.id.bt_not_complete_img:
                startActivity("str", "0", MeterAlbumActivity.class);
                break;
            case R.id.bt_record_img:
                startActivity(MeterRecordctivity.class);
                break;
            case R.id.bt_user_img:
                startActivity(UserActivity.class);
                break;
        }
    }
}
