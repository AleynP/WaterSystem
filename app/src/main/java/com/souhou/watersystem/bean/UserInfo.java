package com.souhou.watersystem.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/16.
 */

/*数据示列
*{"UserInfo":{
* "User_Number":11,
* "User_Name":"小明333",
* "User_Quantity":3,
* "User_Site":"河南省周口市",
* "User_Time":20170705,
* "WaterMeter_Number":"100010",
* "WaterMeter_Time":null,
* "WaterMeter_State":1,
* "WaterType_Name":"4号水表"}}
 */

public class UserInfo implements Serializable {

    /**
     * userInfo : {"User_Number":11,"User_Name":"小明333","User_Quantity":3,"User_Site":"河南省周口市","User_Time":20170705,"WaterMeter_Number":"100010","WaterMeter_Time":null,"WaterMeter_State":1,"WaterType_Name":"4号水表"}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * User_Number : 11
         * User_Name : 小明333
         * User_Quantity : 3
         * User_Site : 河南省周口市
         * User_Time : 20170705
         * WaterMeter_Number : 100010
         * WaterMeter_Time : null
         * WaterMeter_State : 1
         * WaterType_Name : 4号水表
         */

        private int User_Number;
        private String User_Name;
        private int User_Quantity;
        private String User_Site;
        private int User_Time;
        private String WaterMeter_Number;
        private Object WaterMeter_Time;
        private int WaterMeter_State;
        private String WaterType_Name;

        public int getUser_Number() {
            return User_Number;
        }

        public void setUser_Number(int User_Number) {
            this.User_Number = User_Number;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String User_Name) {
            this.User_Name = User_Name;
        }

        public int getUser_Quantity() {
            return User_Quantity;
        }

        public void setUser_Quantity(int User_Quantity) {
            this.User_Quantity = User_Quantity;
        }

        public String getUser_Site() {
            return User_Site;
        }

        public void setUser_Site(String User_Site) {
            this.User_Site = User_Site;
        }

        public int getUser_Time() {
            return User_Time;
        }

        public void setUser_Time(int User_Time) {
            this.User_Time = User_Time;
        }

        public String getWaterMeter_Number() {
            return WaterMeter_Number;
        }

        public void setWaterMeter_Number(String WaterMeter_Number) {
            this.WaterMeter_Number = WaterMeter_Number;
        }

        public Object getWaterMeter_Time() {
            return WaterMeter_Time;
        }

        public void setWaterMeter_Time(Object WaterMeter_Time) {
            this.WaterMeter_Time = WaterMeter_Time;
        }

        public int getWaterMeter_State() {
            return WaterMeter_State;
        }

        public void setWaterMeter_State(int WaterMeter_State) {
            this.WaterMeter_State = WaterMeter_State;
        }

        public String getWaterType_Name() {
            return WaterType_Name;
        }

        public void setWaterType_Name(String WaterType_Name) {
            this.WaterType_Name = WaterType_Name;
        }
    }
}

