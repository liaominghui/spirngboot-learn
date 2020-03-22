package com.lmh.mongdb.dao;

import com.lmh.mongdb.comcom.AbstractMongoMapper;
import com.lmh.mongdb.entity.Student;
import com.lmh.mongdb.request.StudentRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Service
public class StudentDAO extends AbstractMongoMapper<Student, StudentRequest> {

    @Override
    protected Query buildQuery(StudentRequest request) {
        Query query = new Query();
        if (!StringUtils.isEmpty(request.getId())) {
            query.addCriteria(Criteria.where("_id").is(request.getId()));
        }
        if (!StringUtils.isEmpty(request.getUserName())) {
            query.addCriteria(Criteria.where("user_name").is(request.getUserName()));
        }
        if (!StringUtils.isEmpty(request.getClassName())) {
            query.addCriteria(Criteria.where("class_name").is(request.getClassName()));
        }
        if (request.isQueryByPage()) {
            query.skip(request.getStartIndex()).limit(request.getLimit());
        }
        return query;
    }
}
