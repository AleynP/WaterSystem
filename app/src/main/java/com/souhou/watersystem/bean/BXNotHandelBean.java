package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BXNotHandelBean {

    private List<WeiChuLiBaoXiuBean> WeiChuLiBaoXiu;

    public List<WeiChuLiBaoXiuBean> getWeiChuLiBaoXiu() {
        return WeiChuLiBaoXiu;
    }

    public void setWeiChuLiBaoXiu(List<WeiChuLiBaoXiuBean> WeiChuLiBaoXiu) {
        this.WeiChuLiBaoXiu = WeiChuLiBaoXiu;
    }

    public static class WeiChuLiBaoXiuBean {
        /**
         * id : 58
         * Repairs_User : 通天塔222
         * Repairs_SendTime : 20170815171616
         * Process_Time : null
         */

        private int id;
        private String Repairs_User;
        private long Repairs_SendTime;
        private Object Process_Time;

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

        public Object getProcess_Time() {
            return Process_Time;
        }

        public void setProcess_Time(Object Process_Time) {
            this.Process_Time = Process_Time;
        }
    }
}
