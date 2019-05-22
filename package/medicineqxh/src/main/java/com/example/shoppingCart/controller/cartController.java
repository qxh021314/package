/*
 * 项目名称:medicineqxh
 * 类名称:cartController.java
 * 包名称:com.example.shoppingCart.controller
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/3/11 15:00    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.shoppingCart.controller;/**
 * Created by WDD on 2019/3/11.
 */

import com.example.medicine.service.medicineService;
import com.example.shoppingCart.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WDD
 */
@RestController
@RequestMapping("/shoppingCart")
public class cartController {
    @Autowired
    private cartService cartservice;
    @ResponseBody
    @RequestMapping(value = "/Add", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> AddCart(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为："+params);
                int i = cartservice.insertCart(params);
                if (i == 1) {
                    result.put("msgCode", "1");
                    result.put("msg", "加入购物车成功");
                }
                else if(i==2) {
                    result.put("msgCode", "1");
                    result.put("msg", "购买成功");
                }
                else {
                    result.put("msgCode", "0");
                    result.put("msg", "操作失败");
                }
            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/queryCart", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryMyCart(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            System.out.println("进入购物车功能");
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为："+params);
                result = cartservice.queryCart(params);
            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/queryOrder", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryMyOrder(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            System.out.println("进入购物车功能");
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为："+params);
                result = cartservice.queryOrder(params);
            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delCart", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> delCart(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为："+params);
                int i = cartservice.deletetCart(params);
                if (i == 1) {
                    result.put("msgCode", "1");
                    result.put("msg", "购物车删除成功");
                } else {
                    result.put("msgCode", "0");
                    result.put("msg", "购物车删除失败");
                }

            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delOrder", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> delOrder(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为："+params);
                int i = cartservice.deletetOrder(params);
                if (i == 1) {
                    result.put("msgCode", "1");
                    result.put("msg", "订单删除成功");
                } else {
                    result.put("msgCode", "0");
                    result.put("msg", "订单删除失败");
                }

            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}