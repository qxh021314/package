/*
 * 项目名称:imooc
 * 类名称:QXH_user.java
 * 包名称:com.example.medicine.entity
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 11:20    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.medicine.entity;/**
 * Created by WDD on 2019/2/21.
 */

import java.io.Serializable;

/**
 * 用户表
 *
 * @author WDD
 */
public class QXH_user implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String userPassword;
    private int userNumber;
    private String userPhone;
    private String usercartid;
    private String userCard;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUsercartid() {
        return usercartid;
    }

    public void setUsercartid(String usercartid) {
        this.usercartid = usercartid;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }
}