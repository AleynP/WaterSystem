package com.souhou.watersystem.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseFragment;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterAlbumActivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterNotRecordActivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterQRActivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterRecordctivity;
import com.souhou.watersystem.ui.activity.MeterActivity.MeterYesRecordActivity;
import com.souhou.watersystem.ui.activity.UserActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class RecordFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton bt_qr, bt_momp, bt_not_comp, bt_record, bt_user;
    private View rootview;
    private Intent intent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_record, container, false);
        findView(rootview);
        return rootview;
    }

    private void findView(View v) {
        bt_qr = v.findViewById(R.id.bt_qrcode);
        bt_momp = v.findViewById(R.id.bt_complete_img);
        bt_not_comp = v.findViewById(R.id.bt_not_complete_img);
        bt_record = v.findViewById(R.id.bt_record_img);
        bt_user = v.findViewById(R.id.bt_user_img);
        bt_qr.setOnClickListener(this);
        bt_momp.setOnClickListener(this);
        bt_not_comp.setOnClickListener(this);
        bt_record.setOnClickListener(this);
        bt_user.setOnClickListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_qrcode:
                intent = new Intent(getActivity(), MeterQRActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_complete_img:
                intent = new Intent(getActivity(), MeterYesRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_not_complete_img:
                intent = new Intent(getActivity(), MeterAlbumActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_record_img:
                intent = new Intent(getActivity(), MeterRecordctivity.class);
                startActivity(intent);
                break;
            case R.id.bt_user_img:
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
        }
    }

}
