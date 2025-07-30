package com.hotelmanage.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelmanage.backend.pojo.Student;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class StudentJsonTest {
    private final ObjectMapper mapper  = new ObjectMapper();
    private final String filePath = "src/main/resources/data/students.json";

    @Test
    public void testWriteJson() throws Exception {
        Student s = new Student("20190001", "张三", "男", "汉", "2020-09-01");
        mapper.writeValue(new File(filePath), s);
    }

    @Test
    public void testReadJson() throws Exception {
        List<Student> students = mapper.readValue(new File(filePath), new TypeReference<List<Student>>() {
        });

        students.add(new Student("20190002", "李四", "女", "汉", "2020-09-01"));
        System.out.println(students);
        mapper.writeValue(new File(filePath), students);
    }

    @Test
    public void testReadJson2() throws Exception {
        StudentService s = new StudentService();
        System.out.println(s.getAllStudents());
    }

    @Test
    public void testAddStudent() throws Exception {
        StudentService s = new StudentService();
        Student student = new Student("20190003", "王六", "男", "汉", "2020-09-01");
        s.addStudent(student);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        StudentService s = new StudentService();
        s.deleteStudent("20190003");
    }
}
