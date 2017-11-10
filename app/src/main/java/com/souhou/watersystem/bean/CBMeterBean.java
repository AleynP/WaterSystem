package com.souhou.watersystem.bean;

/**
 * Created by Administrator on 2017/10/24.
 */

public class CBMeterBean {


    /**
     * id : 114
     * User_Number : 1000114
     * User_Name : 王一鸣
     * User_Site : 河南省郑州市郑东商业中心
     * WaterMeter_Number : 010621115
     */

    private int id;
    private long User_Number;
    private String User_Name;
    private String User_Site;
    private String WaterMeter_Number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(long User_Number) {
        this.User_Number = User_Number;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getUser_Site() {
        return User_Site;
    }

    public void setUser_Site(String User_Site) {
        this.User_Site = User_Site;
    }

    public String getWaterMeter_Number() {
        return WaterMeter_Number;
    }

    public void setWaterMeter_Number(String WaterMeter_Number) {
        this.WaterMeter_Number = WaterMeter_Number;
    }
}
