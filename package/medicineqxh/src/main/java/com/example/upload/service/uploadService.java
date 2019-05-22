/*
 * 项目名称:studentinformation
 * 类名称:uploadService.java
 * 包名称:com.wdd.upload.service
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/6 09:46    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.service;/**
 * Created by WDD on 2019/5/6.
 */

/**
 * @author WDD
 */
public interface uploadService {
    int insertImg(String userId, String path);
}