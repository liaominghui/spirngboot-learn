package com.lmh.mongdb.comcom;

import lombok.Data;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Data
public class BaseMongoRequest {

    /**
     * mongodb 主键ID
     */
    private String id;
    /**
     * 分页查询 默认为false
     */
    private boolean queryByPage = false;
    /**
     * 当前页码，从1开始
     */
    protected int page = 1;
    /**
     * 每页显示记录数
     */
    protected int limit = 15;

    public BaseMongoRequest() {
        // to do nothing
    }

    /**
     * 期望起始索引
     */
    public int getStartIndex() {
        return (this.page - 1) * this.limit;
    }
}
