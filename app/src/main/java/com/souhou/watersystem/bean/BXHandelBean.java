package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXHandelBean {


    private List<YiChuLiBaoXiuBean> YiChuLiBaoXiu;

    public List<YiChuLiBaoXiuBean> getYiChuLiBaoXiu() {
        return YiChuLiBaoXiu;
    }

    public void setYiChuLiBaoXiu(List<YiChuLiBaoXiuBean> YiChuLiBaoXiu) {
        this.YiChuLiBaoXiu = YiChuLiBaoXiu;
    }

    public static class YiChuLiBaoXiuBean {
        /**
         * id : 3
         * Repairs_User : 党松江
         * Repairs_SendTime : 20171012144704
         * Process_Time : 20171022102020
         * Process_ID : 25
         */

        private int id;
        private String Repairs_User;
        private long Repairs_SendTime;
        private long Process_Time;
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

        public long getRepairs_SendTime() {
            return Repairs_SendTime;
        }

        public void setRepairs_SendTime(long Repairs_SendTime) {
            this.Repairs_SendTime = Repairs_SendTime;
        }

        public long getProcess_Time() {
            return Process_Time;
        }

        public void setProcess_Time(long Process_Time) {
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
