package com.lmh.springboot.hello.limit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 默认 TPS 限制器实现类
 */
@Service
public class DefaultTPSLimiter implements TPSLimiter {

    private final ConcurrentMap<String, StatItem> stats = new ConcurrentHashMap<String, StatItem>();




private List<Integer> ssss;

    private List<Integer> ssss1;



    @Override
    public boolean isAllowable(String serviceKey, int rate, long interval) {
        // 要限流
        if (rate > 0) {
            // 获得 StatItem 对象
            StatItem statItem = stats.get(serviceKey);
            // 不存在，则进行创建
            if (statItem == null) {
                stats.putIfAbsent(serviceKey, new StatItem(serviceKey, rate, interval));
                statItem = stats.get(serviceKey);
            }
            // 根据 TPS 限流规则判断是否限制此次调用.
            return statItem.isAllowable(serviceKey, rate, interval);
            // 不限流
        } else {
            // 移除 StatItem
            StatItem statItem = stats.get(serviceKey);
            if (statItem != null) {
                stats.remove(serviceKey);
            }
            // 返回通过
            return true;
        }
    }

}
