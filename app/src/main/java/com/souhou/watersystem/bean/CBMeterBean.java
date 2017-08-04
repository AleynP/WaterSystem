package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class CBMeterBean {


    private List<BenYueYiChaoBean> benYueYiChao;
    private List<BenYueWeiChaoBean> benYueWeiChao;

    public List<BenYueYiChaoBean> getBenYueYiChao() {
        return benYueYiChao;
    }

    public void setBenYueYiChao(List<BenYueYiChaoBean> benYueYiChao) {
        this.benYueYiChao = benYueYiChao;
    }

    public List<BenYueWeiChaoBean> getBenYueWeiChao() {
        return benYueWeiChao;
    }

    public void setBenYueWeiChao(List<BenYueWeiChaoBean> benYueWeiChao) {
        this.benYueWeiChao = benYueWeiChao;
    }


    public static class BenYueYiChaoBean {
        /**
         * User_Number : 999
         * User_Name : 刚刚
         * MeterReading_Time : 20170818113628
         */

        private int User_Number;
        private String User_Name;
        private long MeterReading_Time;

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

        public long getMeterReading_Time() {
            return MeterReading_Time;
        }

        public void setMeterReading_Time(long MeterReading_Time) {
            this.MeterReading_Time = MeterReading_Time;
        }
    }

    public static class BenYueWeiChaoBean {
        /**
         * User_Number : 888
         * User_Name : 张明
         * MeterReading_Time : 20170510
         */

        private int User_Number;
        private String User_Name;
        private String MeterReading_Time;

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

        public String getMeterReading_Time() {
            return MeterReading_Time;
        }

        public void setMeterReading_Time(String MeterReading_Time) {
            this.MeterReading_Time = MeterReading_Time;
        }
    }
}
