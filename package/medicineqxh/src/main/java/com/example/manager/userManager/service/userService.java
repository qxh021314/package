/*
 * 项目名称:medicineqxh
 * 类名称:userService.java
 * 包名称:com.example.manager.userManager.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/3 00:55    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.manager.userManager.service;/**
 * Created by WDD on 2019/5/3.
 */

import com.example.shoppingCart.entity.cartEntity;
import com.example.shoppingCart.entity.orderEntity;

import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
public interface userService {
    /**
     * 查询用户
     * @return
     */
    Map<String,Object> getUser(String params);

    /**
     * 根据number查询用户
     * @return
     */
    Map<String,Object> getUserDetails(String params);
    /**
     * 查询管理员用户
     * @return
     */
    Map<String,Object> getManagerUser(String params);

    /**
     * 查询购物车功能
     * @return
     */
    List<cartEntity> getCart();

    /**
     * 查询订单功能
     * @return
     */
    Map<String,Object> getOrder(String params);

    /**
     *新增药品
     * @param params
     * @return
     */
    Map<String, Object>  insertCategory(String params);
    /**
     *根据cid删除药品
     * @param params
     * @return
     */
    Map<String, Object>  delCategory(String params);
    /**
     *根据cid获取药品
     * @param params
     * @return
     */
    Map<String, Object>  getCategoryById(String params);
    /**
     *根据cid修改药品
     * @param params
     * @return
     */
    Map<String, Object>  updateCategory(String params);

    /**
     *根据number修改药品
     * @param params
     * @return
     */
    Map<String, Object>  updateUserDetails(String params);

    /**
     * 输入框搜索功能
     * @param params
     * @return
     */
    Map<String,Object> searchOrderMessage(String params);
}