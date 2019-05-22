/*
 * 项目名称:imooc
 * 类名称:商品类.java
 * 包名称:com.example.upload.entity
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/22 09:28    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.entity;/**
 * Created by WDD on 2019/2/22.
 */

import java.math.BigDecimal;

/**
 * @author WDD
 */
public class category {
    private String cid;
    private String image;
    private String message;
    private Double price;
    private String status;
    private String details;
    private int quantity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}