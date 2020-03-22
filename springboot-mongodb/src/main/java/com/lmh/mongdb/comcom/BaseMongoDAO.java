package com.lmh.mongdb.comcom;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
public interface BaseMongoDAO<T, R> {

    /**
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * @param request
     * @return
     */
    List<T> findByParam(R request);

    /**
     * @param request
     * @return
     */
    long count(R request);

    /**
     * @param id
     * @param entity
     */
    void updateById(String id, T entity);

    /**
     * @param request
     * @param entity
     * @return
     */
    void updateByParam(R request, T entity);

    /**
     * @param entity
     * @return
     */
    void save(T entity);

    /**
     * @param list
     * @return
     */
    void batchSave(List<T> list);

    /**
     * @param request
     * @return
     */
    void deleteByParam(R request);

    /**
     * @return
     */
    public MongoTemplate getMongoTemplate();
}
