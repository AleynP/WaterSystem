package com.souhou.watersystem.common;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.ObjectUtil;
import com.souhou.watersystem.utils.TextUtil;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Administrator on 2017/5/2 0002.
 * [Fragment基础类，实现异步框架，Activity堆栈的管理，destroy时候销毁所有资源]
 */

public abstract class BaseFragment<T> extends Fragment {

    /**
     * Fragment content view
     */
    private View inflateView;

    /**
     * 记录是否已经创建了,防止重复创建
     */
    private boolean viewCreated;

    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(new Intent(getContext(), targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的String类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull String extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtil.isEmptyAndNull(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的int类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull int extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtil.isEmptyAndNull(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    public final void startActivity(@NonNull String extraName, @NonNull T ojectParcelable, @NonNull Class<?> targetActivity){
        if (TextUtil.isEmptyAndNull(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        Bundle data = new Bundle();
        data.putParcelable(extraName, (Parcelable) ojectParcelable);
        intent.putExtras(data);
        startActivity(intent);
    }

    private Bundle getArgument() {
        Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            data = new Bundle();
        return data;
    }

    public final String getStringArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        return data.getString(key);
    }

    public final String getStringArgument(@NonNull String key, String defaultValue) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        final String value = data.getString(key);
        return ObjectUtil.isNull(value) ? defaultValue : value;
    }

    public final char getCharArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return '\u0000';
        return data.getChar(key);
    }

    public final boolean getBooleanArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return false;
        return data.getBoolean(key);
    }

    public final boolean getBooleanArgument(@NonNull String key, boolean defaultValue) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return false;
        return data.getBoolean(key, defaultValue);
    }

    public final int getIntArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0;
        return data.getInt(key);
    }

    public final int getIntArgument(@NonNull String key, int defaultValue) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0;
        return data.getInt(key, defaultValue);
    }

    public final float getFloatArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0.0f;
        return data.getFloat(key);
    }

    public final float getFloatArgument(@NonNull String key, float defaultValue) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0.0f;
        return data.getFloat(key, defaultValue);
    }

    public final double getDoubleArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0.0;
        return data.getDouble(key);
    }

    public final double getDoubleArgument(@NonNull String key, double defaultValue) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return 0.0;
        return data.getDouble(key, defaultValue);
    }

    public final String[] getStringArrayArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        return data.getStringArray(key);
    }

    public final ArrayList<String> getStringArrayListArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        return data.getStringArrayList(key);
    }

    public final Parcelable getParcelableArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        return data.getParcelable(key);
    }

    public final Serializable getSerializableArgument(@NonNull String key) {
        final Bundle data = getArguments();
        if (ObjectUtil.isNull(data))
            return null;
        return data.getSerializable(key);
    }

    public final void setArgument(@NonNull String key, @NonNull String value) {
        final Bundle data = getArgument();
        data.putString(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull byte value) {
        final Bundle data = getArgument();
        data.putByte(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull char value) {
        final Bundle data = getArgument();
        data.putChar(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull boolean value) {
        final Bundle data = getArgument();
        data.putBoolean(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull int value) {
        final Bundle data = getArgument();
        data.putInt(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull short value) {
        final Bundle data = getArgument();
        data.putShort(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull long value) {
        final Bundle data = getArgument();
        data.putLong(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull float value) {
        final Bundle data = getArgument();
        data.putFloat(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull double value) {
        final Bundle data = getArgument();
        data.putDouble(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull Parcelable value) {
        final Bundle data = getArgument();
        data.putParcelable(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull Serializable value) {
        final Bundle data = getArgument();
        data.putSerializable(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull Bundle value) {
        final Bundle data = getArgument();
        data.putBundle(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull ArrayList<String> value) {
        final Bundle data = getArgument();
        data.putStringArrayList(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    public final void setArgument(@NonNull String key, @NonNull String[] value) {
        final Bundle data = getArgument();
        data.putStringArray(key, value);
        // 防止报错：java.lang.IllegalStateException: Fragment already active
        if (!isAdded())
            setArguments(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initView();
    }

    protected abstract void initData();

    protected abstract void initView();

    public void setInflateView(View view){
        inflateView = view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d("onDestroyView...");
        // 解决ViewPager中的问题
        if (null != inflateView) {
            final ViewParent parent = inflateView.getParent();
            if (parent != null)
                ((ViewGroup) parent).removeView(inflateView);
        }
    }
}
