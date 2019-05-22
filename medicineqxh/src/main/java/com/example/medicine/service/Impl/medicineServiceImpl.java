/*
 * 项目名称:imooc
 * 类名称:medicineServiceImpl.java
 * 包名称:com.example.medicine.service.Impl
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 11:32    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.medicine.service.Impl;/**
 * Created by WDD on 2019/2/21.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.medicine.entity.QXH_user;
import com.example.medicine.service.medicineService;
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
public class medicineServiceImpl implements medicineService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> ReportUser(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("username").toString();
        String password = object.get("password").toString();
//        System.out.println(username+password);
        String sql = "select * from qxh_user where user_name=? and user_password=?";
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
    public Map<String, Object> searchMessage(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String message = object.get("message").toString();
//        System.out.println(username+password);
        String sql = "select * from category where message like CONCAT('%','" + message + "','%')";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (list.size() > 0) {
            result.put("msgCode", "1");
            result.put("msg", "搜索成功");
            result.put("data", list);
        } else {
            result.put("msgCode", "0");
            result.put("msg", "搜索成功");
        }
        return result;
    }

    @Override
    public Map<String, Object> insertUser(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("username").toString();
        String password = object.get("password").toString();
        String sb = "select rand()*1000";
        Integer rand = jdbcTemplate.queryForObject(sb, Integer.class);
        String usernumber = rand.toString();
        String phone = object.get("phone").toString();
        String usercartid = object.get("usercartid").toString();
        String userCard = object.get("card").toString();
        String sb1 = "select * from qxh_user where user_name = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb1, new Object[]{username});
        if (list.size() > 0) {
            result.put("msgCode", "0");
            result.put("msg", "该用户以及注册，如忘记密码可重置");
        } else {
            String sb2 = "select * from qxh_user where user_card = ? and user_phone = ?";
            List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sb2, new Object[]{userCard,phone});
            if (list2.size()>0) {
                result.put("msgCode", "0");
                result.put("msg", "该身份证和手机号码已经被注册，请更换");
            } else{
            String sql = "insert into qxh_user(user_name,user_password,user_number,user_phone,usercartid,user_card) values(?,?,?,?,?,?)";
            num = jdbcTemplate.update(sql, new Object[]{username, password, usernumber, phone, usercartid, userCard});
            if (num > 0) {
                result.put("msgCode", "1");
                result.put("msg", "注册成功");
            } else {
                result.put("msgCode", "0");
                result.put("msg", "注册失败");
            }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> forgetPassword(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("username").toString();
        String newpassword = object.get("newpassword").toString();
        String phone = object.get("phone").toString();
        String card = object.get("card").toString();
        String sb = "select * from qxh_user where user_card=? and user_phone = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb, new Object[]{card, phone});
        if (list.size() > 0) {
            String sql = "update qxh_user set user_password = ? where user_phone = ? and user_name = ?";
            num = jdbcTemplate.update(sql, new Object[]{newpassword, phone, username});
            if (num > 0) {
                result.put("msgCode", "1");
                result.put("msg", "密码重置成功");
            } else {
                result.put("msgCode", "0");
                result.put("msg", "密码重置失败");
            }
        } else {
            result.put("msgCode", "0");
            result.put("msg", "无该用户");
        }
        return result;
    }

    @Override
    public Map<String, Object> updateUser(String params) {
        int num;
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String username = object.get("username").toString();
        String phone = object.get("phone").toString();
        String card = object.get("card").toString();
        String usernumber = object.get("usernumber").toString();
        String sb = "select * from qxh_user where user_card=? and user_phone = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb, new Object[]{card, phone});
        if (list.size() > 0) {
            String sql = "update qxh_user set user_card = ?,user_phone = ?,user_name = ? where user_number = ?";
            num = jdbcTemplate.update(sql, new Object[]{card,phone,username,usernumber});
            if (num > 0) {
                result.put("msgCode", "1");
                result.put("msg", "修改个人信息成功");
            } else {
                result.put("msgCode", "0");
                result.put("msg", "修改个人信息失败");
            }
        } else {
            result.put("msgCode", "0");
            result.put("msg", "无该用户");
        }
        return result;
    }
}