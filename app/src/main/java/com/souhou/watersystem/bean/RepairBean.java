package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */

public class RepairBean {

    private List<RepairsBean> repairs;

    public List<RepairsBean> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<RepairsBean> repairs) {
        this.repairs = repairs;
    }

    public static class RepairsBean {
        /**
         * id : 6
         * Repairs_User : 天天
         * Repairs_Time : 201707041703
         * Repairs_OK : 0
         */

        private int id;
        private String Repairs_User;
        private long Repairs_Time;
        private int Repairs_OK;

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

        public long getRepairs_Time() {
            return Repairs_Time;
        }

        public void setRepairs_Time(long Repairs_Time) {
            this.Repairs_Time = Repairs_Time;
        }

        public int getRepairs_OK() {
            return Repairs_OK;
        }

        public void setRepairs_OK(int Repairs_OK) {
            this.Repairs_OK = Repairs_OK;
        }
    }
}
