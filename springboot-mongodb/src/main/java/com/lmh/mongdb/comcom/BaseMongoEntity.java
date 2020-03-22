package com.lmh.mongdb.comcom;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Data
public class BaseMongoEntity {

    @Id
    private String id;

    @Field("create_date")
    private Date createDate;

    @Field("update_date")
    private Date updateDate;
}
