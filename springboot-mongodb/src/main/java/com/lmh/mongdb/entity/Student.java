package com.lmh.mongdb.entity;

import com.lmh.mongdb.comcom.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Data
@Document("student")
public class Student extends BaseMongoEntity {

    @Field("user_name")
    private String userName;

    @Field("class_name")
    private String className;

    @Field("course_grades")
    private List<CourseGrade> courseGrades;
}
