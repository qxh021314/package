/*
 * 项目名称:medicineqxh
 * 类名称:cartServiceImpl.java
 * 包名称:com.example.shoppingCart.service.Impl
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/11 14:36    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.shoppingCart.service.Impl;/**
 * Created by WDD on 2019/3/11.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shoppingCart.service.cartService;
import com.example.upload.entity.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertCart(String params) {
        int num = 0;
        JSONObject object = JSON.parseObject(params);
        String usercartid = (String) object.get("usercartid");
        int quantity = (int) object.get("quantity");
        String sb = "select quantity from category where image = ?";
//        double quantity1 = (double) quantity;
        String imagecart = object.get("imagecart").toString();
        List<category> list = jdbcTemplate.query(sb,new Object[]{imagecart},new BeanPropertyRowMapper<category>(category.class));
        if (list.size() > 0) {
           int quantity1 = list.get(0).getQuantity();
           if (quantity>quantity1) {
               return num;
           }
           System.out.println("======="+quantity1);
        }
        String message = object.get("message").toString();
        int price = (int) object.get("price");
        int totalprices = (int) object.get("totalprices");
        String timeDate = (String) object.get("timeDate");
        String select = (String) object.get("select");
        if (select.equals("order")) {//购买
            StringBuffer sql = new StringBuffer();
            sql.append("insert into shopping_order(usercartid,imagecart,message,price,quantity,totalprices,orderDate)");
            sql.append(" value(?,?,?,?,?,?,?)");
            num = jdbcTemplate.update(sql.toString(), new Object[]{usercartid,imagecart, message, price, quantity,totalprices,timeDate});
            if (num!=0) {
                num = 2;
            }
        }
        else if (select.equals("cart")) {//加购物车
        StringBuffer sql = new StringBuffer();
        sql.append("insert into shopping_cart(usercartid,imagecart,message,price,quantity,totalprices,cartDate)");
        sql.append(" value(?,?,?,?,?,?,?)");
        num = jdbcTemplate.update(sql.toString(), new Object[]{usercartid,imagecart, message, price, quantity,totalprices,timeDate});
            if (num!=0) {
                num = 1;
            }
        }
        return num;
    }

    @Override
    public Map<String, Object> queryCart(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String usercartid = object.get("usercartid").toString();
//        System.out.println(username+password);
        String sql = "select * from shopping_cart where usercartid=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{usercartid});
        if (list.size()>0) {
            result.put("msgCode", "1");
            result.put("msg", "查询成功");
            result.put("data",list);
        } else {
            result.put("msgCode", "0");
            result.put("msg", "查询失败");
        }
        return result;
    }

    @Override
    public int deletetCart(String params) {
        int num = 0;
        JSONObject object = JSON.parseObject(params);
        int status = (int) object.get("status");
        String sql = "delete from shopping_cart where status = ?";
        num = jdbcTemplate.update(sql.toString(), new Object[]{status});
        return num;
    }

    @Override
    public int deletetOrder(String params) {
        int num = 0;
        JSONObject object = JSON.parseObject(params);
        int status = (int) object.get("status");
        String sql = "delete from shopping_order where status = ?";
        num = jdbcTemplate.update(sql.toString(), new Object[]{status});
        return num;
    }
    @Override
    public Map<String, Object> queryOrder(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String usercartid = object.get("usercartid").toString();
//        System.out.println(username+password);
        String sql = "select * from shopping_order where usercartid=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,new Object[]{usercartid});
        if (list.size()>0) {
            result.put("msgCode", "1");
            result.put("msg", "查询订单成功");
            result.put("data",list);
        } else {
            result.put("msgCode", "0");
            result.put("msg", "查询订单失败");
        }
        return result;
    }
}