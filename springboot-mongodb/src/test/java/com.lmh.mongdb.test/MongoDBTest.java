package com.lmh.mongdb.test;

import com.alibaba.fastjson.JSON;
import com.lmh.mongdb.dao.StudentDAO;
import com.lmh.mongdb.entity.CourseGrade;
import com.lmh.mongdb.entity.Student;
import com.lmh.mongdb.request.StudentRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Set;

/**
 * Description: TODO
 *
 * @author 廖明辉
 * @Date 2020/3/21 新建
 * @since JDK1.7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class MongoDBTest {


    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void testGetCollections() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        List<Student> students = mongoTemplate.findAll(Student.class, "student");
        System.out.println(collectionNames.size());
    }

    @Test
    public void testInsert() {
        List<Student> students = Lists.newArrayList();
        for (int i = 0; i <= 10; i++) {
            Student student = new Student();
            student.setUserName("lmh");
            student.setClassName("三年一班");
            List<CourseGrade> courseGrades = Lists.newArrayList();
            CourseGrade courseGrade = new CourseGrade();
            courseGrade.setCourseName("高等数学");
            courseGrade.setGrade(65.8d);
            courseGrades.add(courseGrade);
            courseGrade = new CourseGrade();
            courseGrade.setCourseName("英语");
            courseGrade.setGrade(95.8d);
            courseGrades.add(courseGrade);
            student.setCourseGrades(courseGrades);
            courseGrade = new CourseGrade();
            courseGrade.setCourseName("马克思");
            courseGrade.setGrade(99.8d);
            courseGrades.add(courseGrade);
            student.setCourseGrades(courseGrades);
            students.add(student);
        }
        studentDAO.batchSave(students);
    }

    @Test
    public void testQuery() {

        StudentRequest request = new StudentRequest();
        request.setUserName("lmh");
        List<Student> students = studentDAO.findByParam(request);
        System.out.println(JSON.toJSONString(students));
        Student student = studentDAO.findById(students.iterator().next().getId());
        System.out.println(JSON.toJSONString(student));
    }

    @Test
    public void testUpdate() {

        Student student = studentDAO.findById("5e760e28270a4e1458079446");
        student.getCourseGrades().forEach(s -> s.setGrade(150d));
        studentDAO.updateById("5e760e28270a4e1458079446", student);
        Student updateStudent = studentDAO.findById("5e760e28270a4e1458079446");
        System.out.println(JSON.toJSONString(updateStudent));

//        Student student = new Student();
//        student.setUserName("廖明辉test");
//        studentDAO.updateById("5e760e28270a4e1458079446", student);
//        Student updateStudent = studentDAO.findById("5e760e28270a4e1458079446");
//        System.out.println(JSON.toJSONString(updateStudent));

//        StudentRequest request = new StudentRequest();
//        request.setUserName("廖明辉test");
//
//        Student student = new Student();
//        student.setUserName("廖明慧");
//        student.setClassName("三年三班");
//        studentDAO.updateByParam(request, student);
//
//        StudentRequest studentRequest = new StudentRequest();
//        studentRequest.setUserName("廖明慧");
//        studentRequest.setClassName("三年三班");
//        List<Student> students = studentDAO.findByParam(studentRequest);
//        System.out.println(JSON.toJSONString(students));
    }

    @Test
    public void findByPage() {

        StudentRequest request = new StudentRequest();
        request.setLimit(9);
        request.setPage(1);
        request.setQueryByPage(true);
//        PageData<Student> pageData = new PageData<>(Lists.newArrayList(), 0);
//        pageData.setList(studentDAO.findByParam(request));
//        pageData.setTotal((int) studentDAO.count(request));
//        System.out.println(JSON.toJSONString(pageData));
    }
}
