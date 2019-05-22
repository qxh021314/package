/*
 * 项目名称:medicineqxh
 * 类名称:cartService.java
 * 包名称:com.example.shoppingCart.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/11 14:35    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.shoppingCart.service;/**
 * Created by WDD on 2019/3/11.
 */

import java.util.Map;

/**
 * 购物车管理
 *
 * @author WDD
 */
public interface cartService {
    /**
     * 加入购物车/订单
     * @param params
     * @return
     */
    int insertCart(String params);

    /**
     * 查询购物车
     * @param params
     * @return
     */
    Map<String,Object> queryCart(String params);

    /**
     * 删除购物车
     * @param params
     * @return
     */
    int deletetCart(String params);

    /**
     * 删除订单
     * @param params
     * @return
     */
    int deletetOrder(String params);

    /**
     * 查询订单
     * @param params
     * @return
     */
    Map<String,Object> queryOrder(String params);
}