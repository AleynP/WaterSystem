package com.souhou.watersystem.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class Result {

    /**
     * loginResult : SUCCESS
     * sjhm : 18636365858
     * zsxm : 超级管理员
     * gnmc : [{"GNMC":"APP报修处理"},{"GNMC":"APP报装处理"},{"GNMC":"APP抄表信息"}]
     * msg : 登录成功！
     */

    private String loginResult;
    private String sjhm;
    private String zsxm;
    private String msg;
    private List<GnmcBean> gnmc;

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getZsxm() {
        return zsxm;
    }

    public void setZsxm(String zsxm) {
        this.zsxm = zsxm;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<GnmcBean> getGnmc() {
        return gnmc;
    }

    public void setGnmc(List<GnmcBean> gnmc) {
        this.gnmc = gnmc;
    }

    public static class GnmcBean {
        /**
         * GNMC : APP报修处理
         */

        private String GNMC;

        public String getGNMC() {
            return GNMC;
        }

        public void setGNMC(String GNMC) {
            this.GNMC = GNMC;
        }
    }
}
