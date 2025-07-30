package com.hotelmanage.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelmanage.backend.pojo.Student;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentService {
    private static final String FILE_PATH = "data/students.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    //返回所有学生信息
    public List<Student> getAllStudents() throws IOException {


        //从classpath中读取文件
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_PATH)) {

            ArrayList<Student> students;
            if (is == null) {
                students = new ArrayList<>();
            } else {
                students = mapper.readValue(is, new TypeReference<ArrayList<Student>>() {
                });
            }
            return students;
        }



    }

    //添加学生信息
    public void addStudent(Student newStudent) throws IOException {
        List<Student> students = getAllStudents();
        students.add(newStudent);
        mapper.writeValue(new File(FILE_PATH), students);
    }

    //删除学生信息

    public void deleteStudent(String id) throws IOException {
        List<Student> students = getAllStudents();
        students.removeIf(student -> student.getId().equals(id));
        mapper.writeValue(new File(FILE_PATH), students);
    }

    //更新学生信息
    public void updateStudent(String id, Student student) throws IOException {
        List<Student> students = getAllStudents();
        List<Student> studentList = students.stream()
                .map(s -> s.getId().equals(id) ? student : s)
                .collect(Collectors.toList());

        mapper.writeValue(new File(FILE_PATH), studentList);
    }
}
