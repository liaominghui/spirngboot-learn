/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.lmh.mongdb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


/**
 * mongodb配置
 *
 * @author pengdi
 */
@Configuration
public class MongodbConfig {
    @Autowired
    private MongoSettingsProperties properties;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder()
                .connectionsPerHost(properties.getMaxConnectionsPerHost())
                .minConnectionsPerHost(properties.getMinConnectionsPerHost())
                .threadsAllowedToBlockForConnectionMultiplier(properties.getThreadsAllowedToBlockForConnectionMultiplier())
                .serverSelectionTimeout(properties.getServerSelectionTimeout())
                .maxWaitTime(properties.getMaxWaitTime())
                .maxConnectionIdleTime(properties.getMaxConnectionIdleTime())
                .maxConnectionLifeTime(properties.getMaxConnectionLifeTime())
                .connectTimeout(properties.getConnectTimeout())
                .socketTimeout(properties.getSocketTimeout())
                .sslEnabled(properties.getSslEnabled())
                .sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed())
                .alwaysUseMBeans(properties.getAlwaysUseMBeans())
                .heartbeatFrequency(properties.getHeartbeatFrequency())
                .minHeartbeatFrequency(properties.getMinHeartbeatFrequency())
                .heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout())
                .heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout())
                .localThreshold(properties.getLocalThreshold());

        //连接信息
        MongoClientURI uri = new MongoClientURI(properties.getUri(),builder);
        // 创建非认证客户端
        MongoClient mongoClient = new MongoClient(uri);

        // 创建MongoDbFactory
        return new SimpleMongoDbFactory(mongoClient,properties.getDatabase());
    }

    @Bean
    @ConditionalOnMissingBean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}