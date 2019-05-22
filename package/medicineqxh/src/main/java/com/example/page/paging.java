/*
 * 项目名称:medicineqxh
 * 类名称:paging.java
 * 包名称:com.example.page
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/5 17:53    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.page;/**
 * Created by WDD on 2019/5/5.
 */

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author WDD
 */
public class paging {
    public static String sqlPage(String sql,String tableName, int page, int pageSize)
            throws RuntimeException {
        try {
            Properties pro = new Properties();
            pro.load(new InputStreamReader(Object.class.getResourceAsStream("/application.properties"), "UTF-8"));
            System.out.println(pro.getProperty("spring.datasource.driver-class-name"));
            return selectPageMysql(sql, page, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static String selectPageMysql(String sql, int page, int pageSize) {
        int pageNo = (page - 1) * pageSize;
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        sb.append(" LIMIT " + pageNo + "," + pageSize + " ");
        return sb.toString();
    }

}