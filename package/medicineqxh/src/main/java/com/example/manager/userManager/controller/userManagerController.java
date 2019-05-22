/*
 * 项目名称:medicineqxh
 * 类名称:userManagerController.java
 * 包名称:com.example.manager.userManager.controller
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/5/3 01:00    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.manager.userManager.controller;/**
 * Created by WDD on 2019/5/3.
 */

import com.example.manager.userManager.service.userService;
import com.example.shoppingCart.entity.cartEntity;
import com.example.shoppingCart.entity.orderEntity;
import com.example.upload.entity.category;
import com.example.upload.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WDD
 */
@RestController
@RequestMapping("/manager")
public class userManagerController {
    @Autowired
    private userService userservice;

    @ResponseBody
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryUser(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("查询用户功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = userservice.getUser(params);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserMsgById", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> getUserMsg(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("根据number查询用户功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = userservice.getUserDetails(params);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserMsg", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> updateUserDetails(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("根据number查询用户功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = userservice.updateUserDetails(params);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/updateCategory", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> updateCategory(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("修改商品信息功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = userservice.updateCategory(params);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/getManagerUser", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryManagerUser(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("查询管理员用户功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = userservice.getManagerUser(params);
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/getCart", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<cartEntity> queryCart() {
        System.out.println("查询购物车功能");
        List<cartEntity> result = userservice.getCart();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrder", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryOrder(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("查询订单功能");
        params = URLDecoder.decode(params,"UTF-8");
        Map<String, Object> result = userservice.getOrder(params);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertCategory", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> insertCategory(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("新增药品功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> result = userservice.insertCategory(params);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/delCategory", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> delCategory(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("删除药品功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> result = userservice.delCategory(params);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getCategoryById", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> getCategoryById(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("根据CId查询药品功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> result = userservice.getCategoryById(params);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/searchOrderMessage", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> searchOrderMessage(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("根据CId查询药品功能");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> result = userservice.searchOrderMessage(params);
        return result;
    }
}