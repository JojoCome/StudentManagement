package com.hotelmanage.backend.controller;


import com.hotelmanage.backend.pojo.Student;
import com.hotelmanage.backend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/*
* 提供一个get接口 /students,读取students.json文件，返回所有学生信息
* */
@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService = new StudentService();

    @GetMapping("/students")
    public List<Student> getAllStudents() throws IOException{
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) throws IOException{
        try{
            studentService.addStudent(student);
            return "添加成功";
        }catch (IOException e){
            return "添加失败";
        }
    }

//    删除学生接口
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable String id) throws IOException{
        studentService.deleteStudent(id);
    }

//    修改学生接口
    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable String id, @RequestBody Student student) throws IOException{
        studentService.updateStudent(id, student);
    }
}
