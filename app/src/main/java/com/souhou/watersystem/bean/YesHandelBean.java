package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class YesHandelBean {


    private List<YiChuLiBaoZhuangBean> YiChuLiBaoZhuang;

    public List<YiChuLiBaoZhuangBean> getYiChuLiBaoZhuang() {
        return YiChuLiBaoZhuang;
    }

    public void setYiChuLiBaoZhuang(List<YiChuLiBaoZhuangBean> YiChuLiBaoZhuang) {
        this.YiChuLiBaoZhuang = YiChuLiBaoZhuang;
    }

    public static class YiChuLiBaoZhuangBean {
        /**
         * id : 83
         * installation_SendTime : 20171023172728
         * installation_GetTime : 20171023175807
         * Installation_User : 小燕子
         * Installation_Userphone : 13838298317
         * Installation_Address : 河南省郑州市郑东新区郑东商业中心
         */

        private int id;
        private long installation_SendTime;
        private long installation_GetTime;
        private String Installation_User;
        private long Installation_Userphone;
        private String Installation_Address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getInstallation_SendTime() {
            return installation_SendTime;
        }

        public void setInstallation_SendTime(long installation_SendTime) {
            this.installation_SendTime = installation_SendTime;
        }

        public long getInstallation_GetTime() {
            return installation_GetTime;
        }

        public void setInstallation_GetTime(long installation_GetTime) {
            this.installation_GetTime = installation_GetTime;
        }

        public String getInstallation_User() {
            return Installation_User;
        }

        public void setInstallation_User(String Installation_User) {
            this.Installation_User = Installation_User;
        }

        public long getInstallation_Userphone() {
            return Installation_Userphone;
        }

        public void setInstallation_Userphone(long Installation_Userphone) {
            this.Installation_Userphone = Installation_Userphone;
        }

        public String getInstallation_Address() {
            return Installation_Address;
        }

        public void setInstallation_Address(String Installation_Address) {
            this.Installation_Address = Installation_Address;
        }
    }
}
