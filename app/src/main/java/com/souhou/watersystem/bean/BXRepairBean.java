package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXRepairBean {


    private List<BaoXiuJiLuBean> BaoXiuJiLu;

    public List<BaoXiuJiLuBean> getBaoXiuJiLu() {
        return BaoXiuJiLu;
    }

    public void setBaoXiuJiLu(List<BaoXiuJiLuBean> BaoXiuJiLu) {
        this.BaoXiuJiLu = BaoXiuJiLu;
    }

    public static class BaoXiuJiLuBean {
        /**
         * id : 3
         * Repairs_User : 党松江
         * Repairs_getTime : 20171012152355
         * Process_Time : null
         * Process_ID : 23
         */

        private int id;
        private String Repairs_User;
        private long Repairs_getTime;
        private Object Process_Time;
        private int Process_ID;

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

        public long getRepairs_getTime() {
            return Repairs_getTime;
        }

        public void setRepairs_getTime(long Repairs_getTime) {
            this.Repairs_getTime = Repairs_getTime;
        }

        public Object getProcess_Time() {
            return Process_Time;
        }

        public void setProcess_Time(Object Process_Time) {
            this.Process_Time = Process_Time;
        }

        public int getProcess_ID() {
            return Process_ID;
        }

        public void setProcess_ID(int Process_ID) {
            this.Process_ID = Process_ID;
        }
    }
}
