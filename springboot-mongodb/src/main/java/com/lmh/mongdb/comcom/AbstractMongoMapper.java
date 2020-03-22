package com.lmh.mongdb.comcom;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Component
@Slf4j
public abstract class AbstractMongoMapper<T extends BaseMongoEntity, R extends BaseMongoRequest> implements BaseMongoDAO<T, R> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public T findById(String id) {
        Preconditions.checkNotNull(id, "id不能为空");
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public List<T> findByParam(R request) {
        Preconditions.checkNotNull(request, "request不能为空");
        return mongoTemplate.find(this.buildQuery(request), this.getEntityClass());
    }

    @Override
    @FillField
    public void updateById(String id, T entity) {
        Preconditions.checkNotNull(id, "id不能为空");
        Preconditions.checkNotNull(entity, "entity不能为空");
        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(id)), this.buildBaseUpdate(entity), entity.getClass());
    }


    @Override
    @FillField
    public void updateByParam(R request, T entity) {
        Preconditions.checkNotNull(request, "request不能为空");
        Preconditions.checkNotNull(entity, "entity不能为空");
        mongoTemplate.updateFirst(this.buildQuery(request), this.buildBaseUpdate(entity), entity.getClass());
    }

    @Override
    @FillField
    public void save(T entity) {
        Preconditions.checkNotNull(entity, "entity不能为空");
        mongoTemplate.insert(entity);
    }

    @Override
    @FillField
    public void batchSave(List<T> list) {
        Preconditions.checkNotNull(list, "entityList不能为空");
        mongoTemplate.insertAll(list);
    }

    @Override
    public void deleteByParam(R request) {
        Preconditions.checkNotNull(request, "request不能为空");
        mongoTemplate.remove(this.buildQuery(request), this.getEntityClass());
    }

    @Override
    public long count(R request) {
        Preconditions.checkNotNull(request, "request不能为空");
        return mongoTemplate.count(this.buildQuery(request), this.getEntityClass());
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * 构建请求参数
     *
     * @param request
     * @return
     */
    protected abstract Query buildQuery(R request);

    /**
     * @return
     */
    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /**
     * 构建基础更新语句
     * 更新非空字段
     *
     * @param t
     * @return
     */
    private Update buildBaseUpdate(T t) {
        Update update = new Update();
        try {
            Field[] fields = ReflectUtils.getAllFields(t);
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value != null) {
                    org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    // 非空字段，构建Update
                    if (fieldAnnotation != null) {
                        String key = fieldAnnotation.value();
                        update.set(key, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.error("buildBaseUpdate error", e);
        }
        return update;
    }
}
