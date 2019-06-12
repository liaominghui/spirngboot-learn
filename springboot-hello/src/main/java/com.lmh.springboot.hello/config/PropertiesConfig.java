package com.lmh.springboot.hello.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019, 巴图鲁信息科技有限公司
 * FileName: PropertiesConfig
 * Author:   liaominghuikero@gmail.com
 * Date:     2019/6/11 21:59
 * Description: TODO
 * Version:1.0.0
 */
@Component
@PropertySource(value = "config.properties")
public class PropertiesConfig {


    private String name;

}
