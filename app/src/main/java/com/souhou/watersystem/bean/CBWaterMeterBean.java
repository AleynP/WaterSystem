package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */

public class CBWaterMeterBean {

    private List<ShangCiChaoBiaoJiLuBean> shangCiChaoBiaoJiLu;

    public List<ShangCiChaoBiaoJiLuBean> getShangCiChaoBiaoJiLu() {
        return shangCiChaoBiaoJiLu;
    }

    public void setShangCiChaoBiaoJiLu(List<ShangCiChaoBiaoJiLuBean> shangCiChaoBiaoJiLu) {
        this.shangCiChaoBiaoJiLu = shangCiChaoBiaoJiLu;
    }

    public static class ShangCiChaoBiaoJiLuBean {
        /**
         * MeterReading_Number : 21516536
         * MeterReading_Time : 20170818113628
         * User_Number : 11
         * User_Name : 小明333
         * User_Site : 河南省周口市
         * User_Phone : 13582645982
         * User_Ladder : null
         * WaterSort_Name : 民用
         * WaterMeter_State : 0
         */

        private String MeterReading_Number;
        private String MeterReading_Time;
        private int User_Number;
        private String User_Name;
        private String User_Site;
        private long User_Phone;
        private Object User_Ladder;
        private String WaterSort_Name;
        private int WaterMeter_State;

        public String getMeterReading_Number() {
            return MeterReading_Number;
        }

        public void setMeterReading_Number(String MeterReading_Number) {
            this.MeterReading_Number = MeterReading_Number;
        }

        public String getMeterReading_Time() {
            return MeterReading_Time;
        }

        public void setMeterReading_Time(String MeterReading_Time) {
            this.MeterReading_Time = MeterReading_Time;
        }

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

        public String getUser_Site() {
            return User_Site;
        }

        public void setUser_Site(String User_Site) {
            this.User_Site = User_Site;
        }

        public long getUser_Phone() {
            return User_Phone;
        }

        public void setUser_Phone(long User_Phone) {
            this.User_Phone = User_Phone;
        }

        public Object getUser_Ladder() {
            return User_Ladder;
        }

        public void setUser_Ladder(Object User_Ladder) {
            this.User_Ladder = User_Ladder;
        }

        public String getWaterSort_Name() {
            return WaterSort_Name;
        }

        public void setWaterSort_Name(String WaterSort_Name) {
            this.WaterSort_Name = WaterSort_Name;
        }

        public int getWaterMeter_State() {
            return WaterMeter_State;
        }

        public void setWaterMeter_State(int WaterMeter_State) {
            this.WaterMeter_State = WaterMeter_State;
        }
    }
}
