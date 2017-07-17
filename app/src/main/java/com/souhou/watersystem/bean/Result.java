package com.souhou.watersystem.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class Result implements Serializable{

    private String loginResult;
    private List<data> type;
    private String msg;

    public Result() {

    }

    public List<data> getType() {
        return type;
    }

    public void setType(List<data> type) {
        this.type = type;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class data implements Serializable{
        private String GNMC;

        public String getGNMC() {
            return GNMC;
        }

        public void setGNMC(String GNMC) {
            this.GNMC = GNMC;
        }
    }

}
