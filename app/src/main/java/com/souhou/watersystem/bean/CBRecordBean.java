package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */

public class CBRecordBean {

    private List<RecordBean> record;

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * id : 2
         * User_Name : 刚刚
         * MeterReading_Time : 20170818113628
         */

        private int id;
        private String User_Name;
        private long MeterReading_Time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
}
