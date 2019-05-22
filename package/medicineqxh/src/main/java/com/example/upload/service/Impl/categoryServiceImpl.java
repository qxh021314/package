/*
 * 项目名称:imooc
 * 类名称:categoryServiceImpl.java
 * 包名称:com.example.upload.service.Impl
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/22 09:33    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.service.Impl;/**
 * Created by WDD on 2019/2/22.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.page.paging;
import com.example.upload.entity.category;
import com.example.upload.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
@Service
public class categoryServiceImpl implements categoryService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> queryCategoryPage(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String tableName = "category";
        int page = (int) object.get("page");
        int pageSize = (int) object.get("pageSize");
        String sql = "select * FROM " + tableName + " where status = '0'";
        String sqList = paging.sqlPage(sql,tableName, page, pageSize);
        List<category> list = jdbcTemplate.query(sqList, new BeanPropertyRowMapper<>(category.class));
        String sq = "select count(*) FROM " + tableName + " where status = '0'";
        int count = jdbcTemplate.queryForObject(sq,Integer.class);
        if (list.size()>0) {
            result.put("data",list);
            result.put("count",count);
        }
        return result;
    }

    @Override
    public Map<String, Object> queryManagerCategory(String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String tableName = "category";
        int page = (int) object.get("page");
        int pageSize = (int) object.get("pageSize");
        String sql = "select * FROM " + tableName + "";
        String sqList = paging.sqlPage(sql,tableName, page, pageSize);
        List<category> list = jdbcTemplate.query(sqList, new BeanPropertyRowMapper<>(category.class));
        String sq = "select count(*) FROM " + tableName + "";
        int count = jdbcTemplate.queryForObject(sq,Integer.class);
        if (list.size()>0) {
            result.put("data",list);
            result.put("count",count);
        }
        return result;
    }

    @Override
    public List<category> queryCategory() {
        String sql = "select * from category";
        List<category> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(category.class));
        System.out.println(list);
        return list;
    }

    @Override
    public category queryDetails(String params) {
        JSONObject object = JSON.parseObject(params);
        String cid = object.get("cid").toString();
        String sql = "select * from category where cid = ?";
        List<category> list = jdbcTemplate.query(sql, new Object[]{cid}, new BeanPropertyRowMapper<>(category.class));
        System.out.println("======" + list);
        return list.get(0);
    }
}