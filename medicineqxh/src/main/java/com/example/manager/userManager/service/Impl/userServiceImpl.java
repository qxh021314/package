/*
 * 项目名称:medicineqxh
 * 类名称:userServiceImpl.java
 * 包名称:com.example.manager.userManager.service.Impl
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/3 00:56    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.manager.userManager.service.Impl;/**
 * Created by WDD on 2019/5/3.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manager.userManager.service.userService;
import com.example.medicine.entity.QXH_user;
import com.example.page.paging;
import com.example.shoppingCart.entity.cartEntity;
import com.example.shoppingCart.entity.orderEntity;
import com.example.upload.entity.category;
import com.example.upload.util.CommonFileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
@Service
public class userServiceImpl implements userService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonFileUtil fileUtil;

    @Override
    public Map<String, Object> getUser(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String tableName = "qxh_user";
        int page = (int) object.get("page");
        int pageSize = (int) object.get("pageSize");
        String sql = "select * from " + tableName + "";
        String sqList = paging.sqlPage(sql, tableName, page, pageSize);
        List<QXH_user> list = jdbcTemplate.query(sqList, new BeanPropertyRowMapper<>(QXH_user.class));
        String sq = "select count(*) FROM " + tableName + "";
        int count = jdbcTemplate.queryForObject(sq, Integer.class);
        if (list.size() > 0) {
            result.put("data", list);
            result.put("count", count);
        }
        return result;
    }

    @Override
    public Map<String, Object> getUserDetails(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String userNumber = object.get("userNumber").toString();
        String sql = "select * from qxh_user where user_number=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{userNumber});
        if (list.size() > 0) {
            result.put("msgCode", "1");
            result.put("msg", "查询成功");
            result.put("data", list.get(0));
        } else {
            result.put("msgCode", "0");
            result.put("msg", "查询失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> getManagerUser(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("username").toString();
        String password = object.get("password").toString();
        String sql = "select * from manageruser where m_name=? and m_password=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{username, password});
        if (list.size() > 0) {
            result.put("msgCode", "1");
            result.put("msg", "登录成功");
            result.put("data", list.get(0));
        } else {
            result.put("msgCode", "0");
            result.put("msg", "账号或者密码错误");
        }
        return result;
    }

    @Override
    public List<cartEntity> getCart() {
        String sql = "select * from shopping_cart";
        List<cartEntity> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(cartEntity.class));
        return list;
    }

    @Override
    public Map<String, Object> getOrder(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String tableName = "shopping_order";
        int page = (int) object.get("page");
        int pageSize = (int) object.get("pageSize");
        String sql = "select user_name,user_number,user_phone,imagecart,price,quantity,totalprices,status,message,orderDate from qxh_user,shopping_order where qxh_user.usercartid=shopping_order.usercartid";
        String sqList = paging.sqlPage(sql, tableName, page, pageSize);
        List list = jdbcTemplate.queryForList(sqList);
        String sq = "select count(*) FROM " + tableName + "";
        int count = jdbcTemplate.queryForObject(sq, Integer.class);
        if (list.size() > 0) {
            result.put("data", list);
            result.put("count", count);
        }
        return result;
    }

    @Override
    public Map<String, Object> insertCategory(String params) {
        JSONObject object = JSON.parseObject(params);
        Map<String, Object> result = new HashMap<String, Object>();
        int num = 0;
        String sql = "SELECT RAND() * 10000";
        Integer rand = jdbcTemplate.queryForObject(sql, Integer.class);
        String cid = rand.toString();
        String image = object.get("image").toString();
        String message = object.get("message").toString();
        String price = object.get("price").toString();
        String quantity = object.get("quantity").toString();
        String details = object.get("details").toString();
        String status = object.get("status").toString();
        StringBuffer sb = new StringBuffer();
        sb.append("insert into category(cid,image,message,price,status,details,quantity)");
        sb.append(" value(?,?,?,?,?,?,?)");
        num = jdbcTemplate.update(sb.toString(), new Object[]{cid, image, message, price, status, details, quantity});
        if (num != 0) {
            result.put("msgCode", "1");
            result.put("msg", "新增成功");
        } else {
            result.put("msgCode", "0");
            result.put("msg", "新增失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> delCategory(String params) {
        int num = 0;
        JSONObject object = JSON.parseObject(params);
        Map<String, Object> result = new HashMap<String, Object>();
        int cid = (int) object.get("cid");
        String sb = "select image from category where cid = ?";
        category li = jdbcTemplate.queryForObject(sb, new Object[]{cid}, new BeanPropertyRowMapper<>(category.class));
        System.out.println("===" + li.getImage());
        if (li != null) {
            fileUtil.deleteFile(li.getImage());
        }
        String sql = "delete from category where cid = ?";
        num = jdbcTemplate.update(sql.toString(), new Object[]{cid});
        if (num > 0) {
            result.put("msgCode", "1");
            result.put("msg", "删除成功");
        } else {
            result.put("msgCode", "0");
            result.put("msg", "删除失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> getCategoryById(String params) {
        int num = 0;
        JSONObject object = JSON.parseObject(params);
        Map<String, Object> result = new HashMap<String, Object>();
        int cid = (int) object.get("cid");
        String sql = "select * from category where cid = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{cid});
        if (list.size() > 0) {
            result.put("data", list.get(0));
            result.put("msgCode", "1");
            result.put("msg", "查询成功");
        } else {
            result.put("msgCode", "0");
            result.put("msg", "查询失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> updateCategory(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String message = object.get("name").toString();
        String price = object.get("price").toString();
        String quantity = object.get("quantity").toString();
        String details = object.get("details").toString();
        String categoryId = object.get("categoryId").toString();
        String status = object.get("status").toString();
        String sql = "update category set message = ?,price = ?,quantity = ?,details = ?,status = ? where cid = ?";
        num = jdbcTemplate.update(sql, new Object[]{message, price, quantity,details,status,categoryId});
        if (num > 0) {
            result.put("msgCode", "1");
            result.put("msg", "修改商品信息成功");
        } else {
            result.put("msgCode", "0");
            result.put("msg", "修改商品信息失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> updateUserDetails(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("name").toString();
        String card = object.get("card").toString();
        String phone = object.get("phone").toString();
        String number = object.get("number").toString();
        String sql = "update qxh_user set user_name = ?,user_phone = ?,user_card = ? where user_number = ?";
        num = jdbcTemplate.update(sql, new Object[]{username, phone, card, number});
        if (num > 0) {
            result.put("msgCode", "1");
            result.put("msg", "用户信息修改成功成功");
        } else {
            result.put("msgCode", "0");
            result.put("msg", "用户信息修改失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> searchOrderMessage(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String tableName = "shopping_order";
        int page = (int) object.get("page");
        int pageSize = (int) object.get("pageSize");
        String message = object.get("message").toString();
        String sql = "select user_name,user_number,user_phone,imagecart,price,quantity,totalprices,status,message,orderDate from qxh_user,shopping_order where qxh_user.usercartid=shopping_order.usercartid and message like CONCAT('%','" + message + "','%')";
        String sqList = paging.sqlPage(sql, tableName, page, pageSize);
        List list = jdbcTemplate.queryForList(sqList);
        String sq = "select count(*) from qxh_user,shopping_order where qxh_user.usercartid=shopping_order.usercartid and message like CONCAT('%','" + message + "','%')";
        int count = jdbcTemplate.queryForObject(sq, Integer.class);
        if (list.size() > 0) {
            result.put("msgCode", "1");
            result.put("msg", "搜索成功");
            result.put("data", list);
            result.put("count", count);
        } else {
            result.put("msgCode", "0");
            result.put("msg", "搜索成功");
        }
        return result;
    }
}