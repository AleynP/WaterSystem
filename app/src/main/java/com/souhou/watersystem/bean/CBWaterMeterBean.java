package com.souhou.watersystem.bean;

/**
 * Created by Administrator on 2017/7/27.
 */

public class CBWaterMeterBean {

    /**
     * shangCiChaoBiaoJiLu : {"User_Number":1000124,"User_Name":"娄素洁","User_Ladder":0,"User_Site":"河南省郑州市郑东新区郑东商业中心","User_Phone":13838298317,"WaterSort_Name":"非居民","WaterMeter_State":1,"MeterBook_Name":"三册","MeterReading_Number":0}
     */

    private ShangCiChaoBiaoJiLuBean shangCiChaoBiaoJiLu;

    public ShangCiChaoBiaoJiLuBean getShangCiChaoBiaoJiLu() {
        return shangCiChaoBiaoJiLu;
    }

    public void setShangCiChaoBiaoJiLu(ShangCiChaoBiaoJiLuBean shangCiChaoBiaoJiLu) {
        this.shangCiChaoBiaoJiLu = shangCiChaoBiaoJiLu;
    }

    public static class ShangCiChaoBiaoJiLuBean {
        /**
         * User_Number : 1000124
         * User_Name : 娄素洁
         * User_Ladder : 0
         * User_Site : 河南省郑州市郑东新区郑东商业中心
         * User_Phone : 13838298317
         * WaterSort_Name : 非居民
         * WaterMeter_State : 1
         * MeterBook_Name : 三册
         * MeterReading_Number : 0
         */

        private long User_Number;
        private String User_Name;
        private int User_Ladder;
        private String User_Site;
        private long User_Phone;
        private String WaterSort_Name;
        private long WaterMeter_State;
        private String MeterBook_Name;
        private long MeterReading_Number;

        public long getUser_Number() {
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

        public int getUser_Ladder() {
            return User_Ladder;
        }

        public void setUser_Ladder(int User_Ladder) {
            this.User_Ladder = User_Ladder;
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

        public String getWaterSort_Name() {
            return WaterSort_Name;
        }

        public void setWaterSort_Name(String WaterSort_Name) {
            this.WaterSort_Name = WaterSort_Name;
        }

        public long getWaterMeter_State() {
            return WaterMeter_State;
        }

        public void setWaterMeter_State(long WaterMeter_State) {
            this.WaterMeter_State = WaterMeter_State;
        }

        public String getMeterBook_Name() {
            return MeterBook_Name;
        }

        public void setMeterBook_Name(String MeterBook_Name) {
            this.MeterBook_Name = MeterBook_Name;
        }

        public long getMeterReading_Number() {
            return MeterReading_Number;
        }

        public void setMeterReading_Number(long MeterReading_Number) {
            this.MeterReading_Number = MeterReading_Number;
        }
    }
}
