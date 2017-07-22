package com.souhou.watersystem.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.Result;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.ui.activity.MsgActivity.FaultActivity;
import com.souhou.watersystem.ui.activity.MsgActivity.NewsActivity;
import com.souhou.watersystem.ui.activity.MsgActivity.RecordActivity;
import com.souhou.watersystem.ui.fragment.ExitFragment;
import com.souhou.watersystem.ui.fragment.FaultFragment;
import com.souhou.watersystem.ui.fragment.Newsfragment;
import com.souhou.watersystem.ui.fragment.RecordFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigationbar);
        bt_right = (Button) findViewById(R.id.btn_right);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.bottom_record, "").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_fault, "").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_news, "").setActiveColor(R.color.Blue))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_exit, "").setActiveColor(R.color.Blue))
                .setFirstSelectedPosition(3)
                .initialise();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 1:
                        intent = new Intent(HomeActivity.this, RecordActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(HomeActivity.this, FaultActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(HomeActivity.this, NewsActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
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
        Bundle bundle = getIntent().getExtras();
        List<Result.data> list = (List<Result.data>) bundle.getSerializable("type");
        //判断权限
        int a = 0, b = 0, c = 0;
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
                    type = 1;
                    transaction.replace(R.id.id_content, mRecordFragment);
                } else if (a == 0) {
                    Snackbar.make(bottomNavigationBar, "抱歉您没有权限", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (b == 1) {
                    if (mFaultFragment == null) {
                        mFaultFragment = new FaultFragment();
                    }
                    setTitle(getString(R.string.Fault));
                    type = 2;
                    transaction.replace(R.id.id_content, mFaultFragment);
                } else if (b == 0) {
                    Snackbar.make(bottomNavigationBar, "抱歉您没有权限", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (c == 1) {
                    if (mNewsfragment == null) {
                        mNewsfragment = new Newsfragment();
                    }
                    setTitle(getString(R.string.News));
                    type = 3;
                    transaction.replace(R.id.id_content, mNewsfragment);
                } else if (c == 0) {
                    Snackbar.make(bottomNavigationBar, "抱歉您没有权限", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (mExitFragment == null) {
                    mExitFragment = new ExitFragment();
                }
                setTitle(getString(R.string.Exit));
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
