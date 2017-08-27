package com.souhou.watersystem.utils;


import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.souhou.watersystem.ui.activity.FaultActivity.FaultDataActivity;

/**
 * Created by Administrator on 2017/8/17.
 */

public class Toasts {
    public static void setText(Activity context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
