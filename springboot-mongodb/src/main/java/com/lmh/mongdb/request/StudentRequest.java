package com.lmh.mongdb.request;

import com.lmh.mongdb.comcom.BaseMongoRequest;
import lombok.Data;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@Data
public class StudentRequest extends BaseMongoRequest {
    private String userName;
    private String className;
}
