package com.lmh.mongdb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * mongodb连接池配置
 * create by pengdi in 2020/1/15
 */
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Data
public class MongoSettingsProperties {

    /**
     * 连接信息
     */
    private String uri;
    /**
     * 库名
     */
    private String database;
    /**
     * 最小连接数
     */
    private Integer minConnectionsPerHost = 0;
    /**
     * 最大连接数
     */
    private Integer maxConnectionsPerHost = 20;
    /**
     * 可被阻塞的线程数因子
     */
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
    /**
     * 服务器查询超时时间，单位毫秒
     */
    private Integer serverSelectionTimeout = 30000;
    /**
     * 阻塞线程获取连接的最长等待时间
     */
    private Integer maxWaitTime = 120000;
    /**
     * 连接池连接最大空闲时间
     */
    private Integer maxConnectionIdleTime = 0;
    /**
     * 连接池连接的最大存活时间
     */
    private Integer maxConnectionLifeTime = 0;
    /**
     * 连接超时时间，默认值是0，就是不超时
     */
    private Integer connectTimeout = 10000;
    /**
     * socket超时时间，默认值是0，就是不超时
     */
    private Integer socketTimeout = 0;
    /**
     * 驱动是否使用ssl进行连接，默认是false
     */
    private Boolean sslEnabled = false;
    /**
     * 定义是否允许使用无效的主机名
     */
    private Boolean sslInvalidHostNameAllowed = false;
    /**
     * 设置由驱动程序注册的JMX bean是否应该始终是mbean，而不管VM是Java 6还是更大
     */
    private Boolean alwaysUseMBeans = false;
    /**
     * 集群心跳连接的socket超时时间
     */
    private Integer heartbeatConnectTimeout = 20000;
    /**
     * 集群心跳连接的超时时间
     */
    private Integer heartbeatSocketTimeout = 20000;
    /**
     * 驱动重新检查服务器状态最少等待时间
     */
    private Integer minHeartbeatFrequency = 500;
    /**
     * 驱动用来确保集群中服务器状态的心跳频率
     */
    private Integer heartbeatFrequency = 10000;
    /**
     * 设置本地阈值
     */
    private Integer localThreshold = 15;

}
