package com.souhou.watersystem.bean;

/**
 * Created by Administrator on 2017/7/27.
 */

public class CBRecordBean {

    /**
     * MeterReading_Time : 20171022161700
     * WaterMeter_Number : 10000011
     * User_Name : 娄素洁
     * User_Number : 1000124
     * User_Site : 河南省郑州市郑东新区郑东商业中心
     */

    private long MeterReading_Time;
    private long WaterMeter_Number;
    private String User_Name;
    private long User_Number;
    private String User_Site;

    public long getMeterReading_Time() {
        return MeterReading_Time;
    }

    public void setMeterReading_Time(long MeterReading_Time) {
        this.MeterReading_Time = MeterReading_Time;
    }

    public long getWaterMeter_Number() {
        return WaterMeter_Number;
    }

    public void setWaterMeter_Number(long WaterMeter_Number) {
        this.WaterMeter_Number = WaterMeter_Number;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public long getUser_Number() {
        return User_Number;
    }

    public void setUser_Number(long User_Number) {
        this.User_Number = User_Number;
    }

    public String getUser_Site() {
        return User_Site;
    }

    public void setUser_Site(String User_Site) {
        this.User_Site = User_Site;
    }
}
