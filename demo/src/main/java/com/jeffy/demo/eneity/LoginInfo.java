package com.jeffy.demo.eneity;

import java.util.Date;

public class LoginInfo {
    /**
     *id
     */
    private Integer id;
    /**
     *账号
     */
    private String account;
    /**
     *密码
     */
    private String password;
    /**
     *状态
     */
    private String status;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     * 剩余输错机会
     */
    private Integer chances;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getChances() {
        return chances;
    }

    public void setChances(Integer chances) {
        this.chances = chances;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", chances=" + chances +
                '}';
    }
}
