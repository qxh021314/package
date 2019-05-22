/*
 * 项目名称:medicineqxh
 * 类名称:cartEntity.java
 * 包名称:com.example.shoppingCart.entity
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/11 14:32    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.shoppingCart.entity;/**
 * Created by WDD on 2019/3/11.
 */

import java.math.BigDecimal;

/**
 * @author WDD
 */
public class cartEntity {
    private String usercartid;
    private String imagecart;
    private String message;
    private Double price;
    private int quantity;
    private Double totalprices;

    public String getUsercartid() {
        return usercartid;
    }

    public void setUsercartid(String usercartid) {
        this.usercartid = usercartid;
    }

    public String getImagecart() {
        return imagecart;
    }

    public void setImagecart(String imagecart) {
        this.imagecart = imagecart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprices() {
        return totalprices;
    }

    public void setTotalprices(Double totalprices) {
        this.totalprices = totalprices;
    }
}