package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/27.
 */

public class CBMeterBookBean {

    private List<MeterBookListBean> meterBookList;

    public List<MeterBookListBean> getMeterBookList() {
        return meterBookList;
    }

    public void setMeterBookList(List<MeterBookListBean> meterBookList) {
        this.meterBookList = meterBookList;
    }

    public static class MeterBookListBean {
        /**
         * id : 31
         * MeterBook_Name : 一册表
         * Staff_Name : admin_cb
         * MeterBook_Time : 20170825173639
         * MeterBook_Nmb : 1
         */

        private int id;
        private String MeterBook_Name;
        private String Staff_Name;
        private long MeterBook_Time;
        private int MeterBook_Nmb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMeterBook_Name() {
            return MeterBook_Name;
        }

        public void setMeterBook_Name(String MeterBook_Name) {
            this.MeterBook_Name = MeterBook_Name;
        }

        public String getStaff_Name() {
            return Staff_Name;
        }

        public void setStaff_Name(String Staff_Name) {
            this.Staff_Name = Staff_Name;
        }

        public long getMeterBook_Time() {
            return MeterBook_Time;
        }

        public void setMeterBook_Time(long MeterBook_Time) {
            this.MeterBook_Time = MeterBook_Time;
        }

        public int getMeterBook_Nmb() {
            return MeterBook_Nmb;
        }

        public void setMeterBook_Nmb(int MeterBook_Nmb) {
            this.MeterBook_Nmb = MeterBook_Nmb;
        }
    }
}
