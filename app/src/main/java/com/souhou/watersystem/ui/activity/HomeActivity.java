package com.souhou.watersystem.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.Result;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.ui.activity.FaultActivity.FaultMesActivity;
import com.souhou.watersystem.ui.activity.NewsActivity.NewsMsgActivity;
import com.souhou.watersystem.ui.fragment.ExitFragment;
import com.souhou.watersystem.ui.fragment.FaultFragment;
import com.souhou.watersystem.ui.fragment.Newsfragment;
import com.souhou.watersystem.ui.fragment.RecordFragment;
import com.souhou.watersystem.utils.SnackBar;

import java.util.List;

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private RecordFragment mRecordFragment;
    private FaultFragment mFaultFragment;
    private Newsfragment mNewsfragment;
    private ExitFragment mExitFragment;
    private Button bt_right;
    Intent intent;
    int type = 0;
    int a = 0, b = 0, c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigationbar);
        bt_right = (Button) findViewById(R.id.btn_right);
        bottomNavigationBar
//                .setMode(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.bottom_record, "抄表").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_fault, "故障").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_news, "新装").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_exit, "退出").setActiveColor(R.color.Blue))
                .setFirstSelectedPosition(3)
                .initialise();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                        intent = new Intent(HomeActivity.this, FaultMesActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(HomeActivity.this, NewsMsgActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        List<Result.data> list = (List<Result.data>) bundle.getSerializable("type");
        //判断权限
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGNMC().equals("APP抄表信息")) {
                a = 1;
            }
            if (list.get(i).getGNMC().equals("APP报修处理")) {
                b = 1;
            }
            if (list.get(i).getGNMC().equals("APP报装处理")) {
                c = 1;
            }
        }
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mExitFragment = new ExitFragment();
        setTitle(getString(R.string.Exit));
        transaction.replace(R.id.id_content, mExitFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        //跳转界面
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (a == 1) {
                    if (mRecordFragment == null) {
                        mRecordFragment = new RecordFragment();
                    }
                    setTitle(getString(R.string.Record));
                    type = 0;
                    transaction.replace(R.id.id_content, mRecordFragment);
                } else if (a == 0) {
                    SnackBar.make(bottomNavigationBar, "抱歉您没有权限");
                }
                break;
            case 1:
                if (b == 1) {
                    if (mFaultFragment == null) {
                        mFaultFragment = new FaultFragment();
                    }
                    setTitle(getString(R.string.Fault));
                    type = 1;
                    transaction.replace(R.id.id_content, mFaultFragment);
                } else if (b == 0) {
                    SnackBar.make(bottomNavigationBar, "抱歉您没有权限");
                }
                break;
            case 2:
                if (c == 1) {
                    if (mNewsfragment == null) {
                        mNewsfragment = new Newsfragment();
                    }
                    setTitle(getString(R.string.News));
                    type = 2;
                    transaction.replace(R.id.id_content, mNewsfragment);
                } else if (c == 0) {
                    SnackBar.make(bottomNavigationBar, "抱歉您没有权限");
                }
                break;
            case 3:
                if (mExitFragment == null) {
                    mExitFragment = new ExitFragment();
                }
                setTitle(getString(R.string.Exit));
                type = 0;
                transaction.replace(R.id.id_content, mExitFragment);
                break;
        }
        // 事务提交
        transaction.commit();

    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
