/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lmh.mongdb.comcom;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页数据返回对象
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total;

    private List<T> list = Collections.emptyList();

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int) total;
    }

    /**
     * @param list
     * @param total
     * @param <T>
     * @return
     */
    public static <T> PageData<T> succeed(List<T> list, long total) {
        return new PageData<>(list, total);
    }
}