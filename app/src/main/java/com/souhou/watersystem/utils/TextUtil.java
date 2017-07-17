package com.souhou.watersystem.utils;

/**
 * Created by jayli on 2017/5/3 0003.
 */

public class TextUtil {

    public static boolean isEmptyAndNull(String str){
        if(str != null && !"".equals(str)){
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str){
        if("".equals(str)){
            return true;
        }
        return false;
    }
}
