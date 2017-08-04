package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.UserInfolist;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class UserActivity extends BaseBackActivity {


    @BindView(R.id.name_search)
    EditText nameSearch;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_number2)
    TextView tvNumber2;
    @BindView(R.id.tv_peopel2)
    TextView tvPeopel2;
    @BindView(R.id.tv_address2)
    TextView tvAddress2;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.tv_water_num2)
    TextView tvWaterNum2;
    @BindView(R.id.tv_az_time2)
    TextView tvAzTime2;
    @BindView(R.id.tv_state2)
    TextView tvState2;
    @BindView(R.id.tv_modle2)
    TextView tvModle2;
    private UserInfolist user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户资料");
        btn_right.setVisibility(View.INVISIBLE);
        btn_left.setBackgroundResource(R.drawable.ic_back);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = nameSearch.getText().toString();
                if (num.equals("")) {
                    SnackBar.make(btSearch, "请输入手机号或用户编号!");
                } else {
                    Okhttp(num);
                }
            }
        });

    }

    private void Okhttp(String num) {
        OkHttpUtils
                .get()
                .url(ServerConfig.USER_URL)
                .addParams("strWhere", num)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SnackBar.make(btSearch, "请求失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        user = JsonMananger.jsonToBean(response, UserInfolist.class);
                        if (user.getUserInfo().getUser_Number() != 0) {
                            initText(user);
                        } else {
                            SnackBar.make(btSearch, "请核对输入信息是否正确");
                        }
                    }
                });
    }

    private void initText(UserInfolist user) {
        tvName2.setText(user.getUserInfo().getUser_Name());
        tvNumber2.setText(user.getUserInfo().getUser_Number() + "");
        tvPeopel2.setText(user.getUserInfo().getUser_Quantity() + "");
        tvAddress2.setText(user.getUserInfo().getUser_Site());
        tvTime2.setText(user.getUserInfo().getUser_Time() + "");
        tvState2.setText(user.getUserInfo().getWaterMeter_State() + "");
        tvWaterNum2.setText(user.getUserInfo().getWaterMeter_Number());
        tvAzTime2.setText(user.getUserInfo().getWaterMeter_Time() + "");
        tvModle2.setText(user.getUserInfo().getWaterType_Name());
    }

}
