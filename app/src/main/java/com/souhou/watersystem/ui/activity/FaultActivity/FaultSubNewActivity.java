package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaultSubNewActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_explain)
    TextView tvExplain;
    @BindView(R.id.photo1)
    ImageView photo1;
    @BindView(R.id.photo2)
    ImageView photo2;
    @BindView(R.id.photo3)
    ImageView photo3;
    @BindView(R.id.photo4)
    ImageView photo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_sub_new);
        ButterKnife.bind(this);
        btn_right.setVisibility(View.INVISIBLE);
        btn_left.setBackgroundResource(R.drawable.ic_back);
        setTitle("新增故障处理");
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
    }

    @OnClick({R.id.photo1, R.id.photo2, R.id.photo3, R.id.photo4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photo1:
                break;
            case R.id.photo2:
                break;
            case R.id.photo3:
                break;
            case R.id.photo4:
                break;
        }
    }
}
