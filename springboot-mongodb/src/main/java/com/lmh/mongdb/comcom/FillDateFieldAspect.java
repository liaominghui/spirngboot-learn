package com.lmh.mongdb.comcom;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/22 新建
 * @since JDK1.7
 */
@Aspect
@Component
public class FillDateFieldAspect {

    // 更新时间
    private final String UPDATE_DATE = "updateDate";

    // 创建时间
    private final String CREATE_DATE = "createDate";

    @Pointcut("@annotation(com.lmh.mongdb.comcom.FillField)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Date nowDate = new Date();
        Object[] args = pjp.getArgs();
        for (Object object : args) {
            if (object instanceof BaseMongoEntity) {
                Field[] fields = ReflectUtils.getAllFields(object);
                for (Field field : fields) {
                    field.setAccessible(true);
                    // 填充updateDate字段
                    if (UPDATE_DATE.equals(field.getName())) {
                        field.set(object, nowDate);
                    }
                    // 填充 createDate字段
                    if (CREATE_DATE.equals(field.getName()) && StringUtils.isEmpty(field.get(object))) {
                        field.set(object, nowDate);
                    }
                }
                System.out.println("字段填充成功后=>" + JSON.toJSONString(object));
            }
        }
        return pjp.proceed();
    }
}
