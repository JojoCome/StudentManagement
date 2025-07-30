package com.hotelmanage.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelmanage.backend.pojo.Admin;
import com.hotelmanage.backend.pojo.Student;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 管理员登录服务类,负责从admin.json读取账号信息,并做登录校验
 * */
public class AdminService {

    private final static String ADMIN_FILE = "data/admin.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean login(String username, String password) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(ADMIN_FILE)){

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Admin admin = objectMapper.readValue(reader, Admin.class);

            return admin.getUsername().equals(username) && admin.getPassword().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

/**
 * ObjectMapper类是Jackson库提供的一个类，用于将JSON数据转换为Java对象，或将Java对象转换为JSON数据。
 * 1. 把JSON数据转换成Java对象,readValue(json,Class)
 * 2. 把Java对象转换成JSON数据,writeValueAsString(object)
 * */
