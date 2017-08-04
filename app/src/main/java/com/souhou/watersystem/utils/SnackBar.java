package com.souhou.watersystem.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2017/7/30.
 */

public class SnackBar {
    public static void make(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }
}
