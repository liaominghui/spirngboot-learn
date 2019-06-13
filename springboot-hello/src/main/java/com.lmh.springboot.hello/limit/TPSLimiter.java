
package com.lmh.springboot.hello.limit;

/**
 * TPS 限制器接口
 */
public interface TPSLimiter {


    boolean isAllowable(String serviceKey, int rate, long interval);

}
