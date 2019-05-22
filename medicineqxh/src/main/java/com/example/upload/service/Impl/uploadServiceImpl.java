/*
 * 项目名称:studentinformation
 * 类名称:uploadServiceImpl.java
 * 包名称:com.wdd.upload.service.Impl
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/6 09:47    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.service.Impl;/**
 * Created by WDD on 2019/5/6.
 */

import com.example.upload.service.uploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * @author WDD
 */
@Service
public class uploadServiceImpl implements uploadService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int insertImg(String userId,String path) {
        String sql = "";
        return 0;
    }
}