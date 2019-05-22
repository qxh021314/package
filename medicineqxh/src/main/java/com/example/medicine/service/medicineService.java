/*
 * 项目名称:imooc
 * 类名称:medicineService.java
 * 包名称:com.example.medicine.service
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 11:27    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.medicine.service;/**
 * Created by WDD on 2019/2/21.
 */

import java.util.Map;

/**
 * @author WDD
 */
public interface medicineService {
    /**
     * 登录时查询用户是否存在，如果存在登录成功，否则失败
     * @param params
     * @return
     */
    Map<String, Object> ReportUser(String params);

    /**
     * 输入框搜索功能
     * @param params
     * @return
     */
    Map<String,Object> searchMessage(String params);

    /**
     * 注册用户
     * @param params
     * @return
     */
    Map<String,Object> insertUser(String params);

    /**
     * 忘记密码
     * @param params
     * @return
     */
    Map<String,Object> forgetPassword(String params);

    /**
     * 修改个人资料
     * @param params
     * @return
     */
    Map<String,Object> updateUser(String params);
}