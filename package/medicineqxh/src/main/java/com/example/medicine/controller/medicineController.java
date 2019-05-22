/*
 * 项目名称:imooc
 * 类名称:medicineController.java
 * 包名称:com.example.medicine.controller
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/2/21 11:24    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.medicine.controller;/**
 * Created by WDD on 2019/2/21.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.Verification.VerificationCode;
import com.example.medicine.service.medicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WDD
 */
@RestController
@RequestMapping("/medicine")
public class medicineController {
    @Autowired
    private medicineService medicineService;
    private StringBuffer randomCode;

    @ResponseBody
    @RequestMapping(value = "/queryUser", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> queryReportUser(@RequestBody String params) {
        System.out.println("登陆方法");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为：" + params);
                result = medicineService.ReportUser(params);
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
    @RequestMapping(value = "/searchCategory", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> searchCategory(@RequestBody String params) {
        System.out.println("搜索入口");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为：" + params);
                result = medicineService.searchMessage(params);
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
    @RequestMapping(value = "/insertUser", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> registerUser(@RequestBody String params) {
        System.out.println("注册入口");
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String code = object.get("code").toString();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为：" + params);
                if (!code.equals(randomCode.toString())) {
                    result.put("msgCode", "0");
                    result.put("msg", "验证码错误");
                    return result;
                }
                result = medicineService.insertUser(params);
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
    @RequestMapping(value = "/forgetPassword", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> forgetPassword(@RequestBody String params) {
        System.out.println("忘记密码入口");
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String code = object.get("code").toString();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为：" + params);
                if (!code.equals(randomCode.toString())) {
                    result.put("msgCode", "0");
                    result.put("msg", "验证码错误");
                    return result;
                }
                result = medicineService.forgetPassword(params);
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
    @RequestMapping(value = "/updatePersonMsg", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> updatePersonById(@RequestBody String params) {
        System.out.println("修改个人信息入口");
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject object = JSON.parseObject(params);
        String code = object.get("code").toString();
        try {
            params = URLDecoder.decode(params, "UTF-8");
            if ("" != params && null != params) {
                System.out.println("输入的参数为：" + params);
                if (!code.equals(randomCode.toString())) {
                    result.put("msgCode", "0");
                    result.put("msg", "验证码错误");
                    return result;
                }
                result = medicineService.updateUser(params);
            } else {
                result.put("msgCode", "0");
                result.put("msg", "入参为空");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取图片验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/getVertifyCode")
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerificationCode verificationCode = new VerificationCode();
        //获取验证码图片
        BufferedImage image = verificationCode.getImage();
        //获取验证码内容
        String text = verificationCode.getText();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        randomCode = new StringBuffer();
        randomCode.append(text);
        // 将验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("signcode", randomCode.toString());
        System.out.println("session-signcode==>" + randomCode.toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.flush();
        sos.close();
    }

    /**
     * 验证图片验证码
     *
     * @param request
     * @param signcode
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(HttpServletRequest request, String signcode) {
        HttpSession session = request.getSession();
        String signcodeSession = (String) session.getAttribute("signcode");
        System.out.println("signcode==>" + signcode);
        System.out.println("signcodeSession==>" + signcodeSession);
        return null;
    }
}