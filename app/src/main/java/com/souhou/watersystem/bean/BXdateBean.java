package com.souhou.watersystem.bean;

/**
 * Created by Administrator on 2017/7/23.
 */

public class BXdateBean {

    /**
     * id : 26
     * Repairs_Time : 20171021114246
     * Repairs_SendTime : 20171021114308
     * Repairs_User : 李红
     * Repairs_Content : 漏水
     * Repairs_Name : admin_zz
     * Repairs_Phone : 13524689524
     * User_Site : 河南省周口市
     * Repairs_Randomnum : 55155362
     * FILE_PATH0 : http://192.168.1.111:8080/upload/infofiles/20171021114244_5712.jpg
     * FILE_PATH1 : http://192.168.1.111:8080/upload/infofiles/20171021114244_5712.jpg
     * FILE_PATH2 : http://192.168.1.111:8080/upload/infofiles/20171021114244_5712.jpg
     * FILE_PATH3 : http://192.168.1.111:8080/upload/infofiles/20171021114244_5712.jpg
     */

    private int id;
    private long Repairs_Time;
    private long Repairs_SendTime;
    private String Repairs_User;
    private String Repairs_Content;
    private String Repairs_Name;
    private long Repairs_Phone;
    private String User_Site;
    private int Repairs_Randomnum;
    private String FILE_PATH0;
    private String FILE_PATH1;
    private String FILE_PATH2;
    private String FILE_PATH3;

    public String getFILE_PATH1() {
        return FILE_PATH1;
    }

    public void setFILE_PATH1(String FILE_PATH1) {
        this.FILE_PATH1 = FILE_PATH1;
    }

    public String getFILE_PATH2() {
        return FILE_PATH2;
    }

    public void setFILE_PATH2(String FILE_PATH2) {
        this.FILE_PATH2 = FILE_PATH2;
    }

    public String getFILE_PATH3() {
        return FILE_PATH3;
    }

    public void setFILE_PATH3(String FILE_PATH3) {
        this.FILE_PATH3 = FILE_PATH3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRepairs_Time() {
        return Repairs_Time;
    }

    public void setRepairs_Time(long Repairs_Time) {
        this.Repairs_Time = Repairs_Time;
    }

    public long getRepairs_SendTime() {
        return Repairs_SendTime;
    }

    public void setRepairs_SendTime(long Repairs_SendTime) {
        this.Repairs_SendTime = Repairs_SendTime;
    }

    public String getRepairs_User() {
        return Repairs_User;
    }

    public void setRepairs_User(String Repairs_User) {
        this.Repairs_User = Repairs_User;
    }

    public String getRepairs_Content() {
        return Repairs_Content;
    }

    public void setRepairs_Content(String Repairs_Content) {
        this.Repairs_Content = Repairs_Content;
    }

    public String getRepairs_Name() {
        return Repairs_Name;
    }

    public void setRepairs_Name(String Repairs_Name) {
        this.Repairs_Name = Repairs_Name;
    }

    public long getRepairs_Phone() {
        return Repairs_Phone;
    }

    public void setRepairs_Phone(long Repairs_Phone) {
        this.Repairs_Phone = Repairs_Phone;
    }

    public String getUser_Site() {
        return User_Site;
    }

    public void setUser_Site(String User_Site) {
        this.User_Site = User_Site;
    }

    public int getRepairs_Randomnum() {
        return Repairs_Randomnum;
    }

    public void setRepairs_Randomnum(int Repairs_Randomnum) {
        this.Repairs_Randomnum = Repairs_Randomnum;
    }

    public String getFILE_PATH0() {
        return FILE_PATH0;
    }

    public void setFILE_PATH0(String FILE_PATH0) {
        this.FILE_PATH0 = FILE_PATH0;
    }
}
