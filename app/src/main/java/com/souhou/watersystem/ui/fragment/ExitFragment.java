package com.souhou.watersystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseFragment;
import com.souhou.watersystem.common.manager.ActivityManager;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/15.
 */

public class ExitFragment extends BaseFragment {

    MyApplication app;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        app = (MyApplication) getActivity().getApplication();
        tvName.setText(app.getUsername());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_exit)
    public void onViewClicked() {
        ActivityManager.getInstance().finishAllActivity();
    }

}
