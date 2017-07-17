package com.souhou.watersystem.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/16.
 */

/*数据示列
*{"userInfo":
* {"User_Number":999,
* "User_Name":"琳达",
* "User_Quantity":5,
* "User_Site":"河南省周口市",
* "User_Time":20170627,
* "WaterMeter_Number":"100012",
* "WaterMeter_Time":null,
* "WaterMeter_State":0,
* "WaterType_Name":"1号水表"}}
*
 */

public class UserInfo {
    private int User_Number;
    private String User_Name;
    private int User_Quantity;
    private String User_Site;
    private String User_Time;
    private int WaterMeter_Number;
    private String WaterMeter_Time;
    private int WaterMeter_State;
    private String WaterType_Name;

    public UserInfo(int user_Number, String user_Name, int user_Quantity, String user_Site, String user_Time, int waterMeter_Number, String waterMeter_Time, int waterMeter_State, String waterType_Name) {
        User_Number = user_Number;
        User_Name = user_Name;
        User_Quantity = user_Quantity;
        User_Site = user_Site;
        User_Time = user_Time;
        WaterMeter_Number = waterMeter_Number;
        WaterMeter_Time = waterMeter_Time;
        WaterMeter_State = waterMeter_State;
        WaterType_Name = waterType_Name;
    }

    public int getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(int user_Number) {
        User_Number = user_Number;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public int getUser_Quantity() {
        return User_Quantity;
    }

    public void setUser_Quantity(int user_Quantity) {
        User_Quantity = user_Quantity;
    }

    public String getUser_Site() {
        return User_Site;
    }

    public void setUser_Site(String user_Site) {
        User_Site = user_Site;
    }

    public String getUser_Time() {
        return User_Time;
    }

    public void setUser_Time(String user_Time) {
        User_Time = user_Time;
    }

    public int getWaterMeter_Number() {
        return WaterMeter_Number;
    }

    public void setWaterMeter_Number(int waterMeter_Number) {
        WaterMeter_Number = waterMeter_Number;
    }

    public String getWaterMeter_Time() {
        return WaterMeter_Time;
    }

    public void setWaterMeter_Time(String waterMeter_Time) {
        WaterMeter_Time = waterMeter_Time;
    }

    public int getWaterMeter_State() {
        return WaterMeter_State;
    }

    public void setWaterMeter_State(int waterMeter_State) {
        WaterMeter_State = waterMeter_State;
    }

    public String getWaterType_Name() {
        return WaterType_Name;
    }

    public void setWaterType_Name(String waterType_Name) {
        WaterType_Name = waterType_Name;
    }
}
