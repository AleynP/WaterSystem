package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class NewAddBean {


    private List<XinZengBaoZhuangBean> XinZengBaoZhuang;

    public List<XinZengBaoZhuangBean> getXinZengBaoZhuang() {
        return XinZengBaoZhuang;
    }

    public void setXinZengBaoZhuang(List<XinZengBaoZhuangBean> XinZengBaoZhuang) {
        this.XinZengBaoZhuang = XinZengBaoZhuang;
    }

    public static class XinZengBaoZhuangBean {
        /**
         * id : 18
         * installation_SendTime : null
         * installation_GetTime : 20170721181808
         * Installation_User : 琳达
         * Installation_Userphone : 13526023026
         * Installation_Address : 大方向海南购房人已经
         */

        private int id;
        private Object installation_SendTime;
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

        public Object getInstallation_SendTime() {
            return installation_SendTime;
        }

        public void setInstallation_SendTime(Object installation_SendTime) {
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
