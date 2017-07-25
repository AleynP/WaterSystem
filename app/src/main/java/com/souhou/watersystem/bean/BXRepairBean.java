package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXRepairBean {

    private List<RecordBean> record;

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * id : 6
         * Repairs_User : 天天
         * Process_Time : 20170724124937
         */

        private int id;
        private String Repairs_User;
        private long Process_Time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRepairs_User() {
            return Repairs_User;
        }

        public void setRepairs_User(String Repairs_User) {
            this.Repairs_User = Repairs_User;
        }

        public long getProcess_Time() {
            return Process_Time;
        }

        public void setProcess_Time(long Process_Time) {
            this.Process_Time = Process_Time;
        }
    }
}
