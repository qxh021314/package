/*
 * 项目名称:studentinformation
 * 类名称:fileController.java
 * 包名称:com.wdd.upload.Controller
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/4/30 14:17    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.controller;/**
 * Created by WDD on 2019/4/30.
 */

import com.example.upload.service.uploadService;
import com.example.upload.util.CommonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WDD
 */
@RestController
@RequestMapping("/upload")
public class fileController {

    private final static Logger logger = LoggerFactory.getLogger(fileController.class);

    @Autowired
    private CommonFileUtil fileUtil;
    @Autowired
    private uploadService uploadservice;
    @RequestMapping("/goIndex")
    public String goIndex(){
        logger.info("进入主页面");
        return "/file";
    }
    @ResponseBody
    @RequestMapping(value = "/getFile", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getFile(HttpServletRequest request, HttpServletResponse response) {
        String url = "D:\\QxhImage\\1wKgZwFxqVDCALjZgAAAWRPLuFg870.jpg";
        File file = new File(url);
    }
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file){

        String targetFilePath = "E:/opt/uploads/";

        if(file.isEmpty()){
            logger.info("this file is empty");
        }

        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //获取原来文件名称
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        if(!fileSuffix.equals(".jpg") || !fileSuffix.equals(".png")){
            logger.info("文件格式不正确");
        }
        //拼装新的文件名
        String targetFileName = targetFilePath + newFileName + fileSuffix;
        //上传文件
        try {
            FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(targetFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/success";
    }

    //使用fastdfs进行文件上传
    @ResponseBody
    @RequestMapping(value = "/uploadFileToFast", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFileToFast(@RequestParam("fileName")MultipartFile file,
                                   @RequestParam(value = "userId", required = false) String userId
                                   ) throws IOException {

        if(file.isEmpty()){
            logger.info("文件不存在");
        }
        String path = fileUtil.uploadFile(file);
        System.out.println("http://118.24.207.234/"+path);
        String image = "http://118.24.207.234/"+path;
        return image;
    }
    @ResponseBody
    @RequestMapping(value = "/delFileToFast", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void uploadFileToFast(@RequestParam(value="imgUrl", required = false)String imgUrl
    ) throws IOException {
        fileUtil.deleteFile(imgUrl);
    }
}
