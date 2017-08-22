package com.souhou.watersystem.common;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.transition.Fade;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.manager.ActivityManager;
import com.souhou.watersystem.utils.ButonBarview;
import com.souhou.watersystem.utils.DensityUtils;


/**
 * Created by Administrator on 2017/5/2 0002.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final int SLIDE_TRANSITION_TIME = 1 * 1000;//滑动转场的时间
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;

    public Fade mFadeTransition;

    protected Context mContext;
    private ViewFlipper mContentView;
    protected RelativeLayout layout_head;
    protected Button btn_left;
    protected ButonBarview btn_right;
    protected TextView tv_title;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        initView();
        //5.0+的专场动画
        setupWindowAnimation();
    }

    private void initView() {
        mContext = this;
        //初始化公共头部
        mContentView = (ViewFlipper) super.findViewById(R.id.layout_container);
        layout_head = (RelativeLayout) super.findViewById(R.id.layout_head);
        btn_left = (Button) super.findViewById(R.id.btn_left);
        btn_left.setBackgroundResource(R.drawable.ic_account);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_right = (ButonBarview) super.findViewById(R.id.btn_right);
        btn_right.setBackgroundResource(R.drawable.ic_msg_img);
        btn_right.setMessageCount(0);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_title = (TextView) super.findViewById(R.id.tv_title);
        //Activity管理
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mContentView.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.unbindReferences(mContentView);
        ActivityManager.getInstance().removeActivity(this);
        mContentView = null;
        mContext = null;
    }

    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        layout_head.setVisibility(visibility);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        tv_title.setText(getString(titleId));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 设置左边按钮是否显示
     *
     * @param visibility
     */
    public void setLeftVisibility(int visibility) {
        btn_left.setVisibility(visibility);
    }

    /**
     * 设置右边按钮是否显示
     *
     * @param visibility
     */
    public void setRightVisibility(int visibility) {
        btn_right.setVisibility(visibility);
    }

    /**
     * 设置右边按钮的文字
     *
     * @param titleId
     */
//    public void setRightText(int titleId) {
//        btn_right.setText(getString(titleId));
//    }

    /**
     * 设置右边按钮的文字
     *
     * @param text
     */
//    public void setRightText(String text) {
//        btn_right.setText(text);
//    }

    /**
     * 设置右边按钮的图标
     *
     * @param resId
     */
//    public void setRightDrawable(int resId) {
//        btn_right.setText("");
//        btn_right.setWidth(DensityUtils.dp2px(this, 36));
//        btn_right.setHeight(DensityUtils.dp2px(this, 36));
//        btn_right.setBackgroundResource(resId);
//    }

    /**
     * 设置右边按钮的监听器
     *
     * @param listenr
     */
    public void setRightOnClickListener(View.OnClickListener listenr) {
        btn_right.setOnClickListener(listenr);
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void setupWindowAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mFadeTransition = new Fade();
            mFadeTransition.setDuration(SLIDE_TRANSITION_TIME);
            getWindow().setEnterTransition(mFadeTransition);
            getWindow().setExitTransition(mFadeTransition);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        return true;
    }
}
