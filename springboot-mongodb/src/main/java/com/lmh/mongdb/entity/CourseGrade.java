package com.lmh.mongdb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Data
public class CourseGrade {

    @Field("course_name")
    private String courseName;

    @Field("grade")
    private Double grade;
}
