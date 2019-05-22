/*
 * 项目名称:studentinformation
 * 类名称:FdfsConfig.java
 * 包名称:com.wdd.upload.config
 * 
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/4/30 14:13    WDD      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.example.upload.config;/**
 * Created by WDD on 2019/4/30.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Component;

/**
 * @author WDD
 */
@Component
public class FdfsConfig {

    @Value("${fdfs.resHost}")
    private String resHost;

    @Value("${fdfs.storagePort}")
    private String storagePort;

    public String getResHost() {
        return resHost;
    }

    public void setResHost(String resHost) {
        this.resHost = resHost;
    }

    public String getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(String storagePort) {
        this.storagePort = storagePort;
    }

}

@Configuration
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
class FdfsConfiguration {

}

