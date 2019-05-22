/*
 * 项目名称:imooc
 * 类名称:categoryController.java
 * 包名称:com.example.upload.controller
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/22 09:44    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.controller;/**
 * Created by WDD on 2019/2/22.
 */

import com.example.medicine.service.medicineService;
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
@RequestMapping("/upload")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    /**
     * 页面图片的查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCategoryPage", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Map<String, Object> queryCategoryPage(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("查询热卖药品方法");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = categoryService.queryCategoryPage(params);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/queryManagerCategory", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Map<String, Object> queryManagerCategory(@RequestBody String params) throws UnsupportedEncodingException {
        System.out.println("管理员查询药品方法");
        params = URLDecoder.decode(params, "UTF-8");
        Map<String, Object> i = categoryService.queryManagerCategory(params);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/queryCategory", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<category> queryCategory() throws UnsupportedEncodingException {
        System.out.println("查询其他药品方法");
        List<category> i = categoryService.queryCategory();
        return i;
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/querydetails", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryDetailsMessage(@RequestBody String params) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("查询购物页面方法");
                params = URLDecoder.decode(params, "UTF-8");
                category details = categoryService.queryDetails(params);
                result.put("msgCode", "1");
                result.put("msg", "查询成功");
                result.put("data", details);
            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
                result.put("data", "");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}