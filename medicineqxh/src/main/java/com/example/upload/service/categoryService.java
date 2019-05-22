/*
 * 项目名称:imooc
 * 类名称:categoryService.java
 * 包名称:com.example.upload.service
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/22 09:32    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.service;/**
 * Created by WDD on 2019/2/22.
 */


import com.example.upload.entity.category;

import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
public interface categoryService {
    /**
     * 进入用户页面查询首页物品
     * @return
     */
    Map<String, Object> queryCategoryPage(String params);
    /**
     * 进入管理员页面查询首页物品
     * @return
     */
    Map<String, Object> queryManagerCategory(String params);
    List<category> queryCategory();
    /**
     * 点击物品进入购物车的查询
     * @return
     */
    category queryDetails(String params);
}