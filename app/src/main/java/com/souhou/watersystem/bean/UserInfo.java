package com.souhou.watersystem.bean;

/**
 * Created by Administrator on 2017/7/20.
 */

public class UserInfo {

    /**
     * Installation_Time : 201707111013
     * Installation_User : 琳达
     * Installation_Userphone : 13526023026
     * Installation_Address : 大方向海南购房人已经
     * WaterType_Name : 1号水表
     */

    private long Installation_Time;
    private String Installation_User;
    private long Installation_Userphone;
    private String Installation_Address;
    private String WaterType_Name;

    public long getInstallation_Time() {
        return Installation_Time;
    }

    public void setInstallation_Time(long Installation_Time) {
        this.Installation_Time = Installation_Time;
    }

    public String getInstallation_User() {
        return Installation_User;
    }

    public void setInstallation_User(String Installation_User) {
        this.Installation_User = Installation_User;
    }

    public long getInstallation_Userphone() {
        return Installation_Userphone;
    }

    public void setInstallation_Userphone(long Installation_Userphone) {
        this.Installation_Userphone = Installation_Userphone;
    }

    public String getInstallation_Address() {
        return Installation_Address;
    }

    public void setInstallation_Address(String Installation_Address) {
        this.Installation_Address = Installation_Address;
    }

    public String getWaterType_Name() {
        return WaterType_Name;
    }

    public void setWaterType_Name(String WaterType_Name) {
        this.WaterType_Name = WaterType_Name;
    }


}
