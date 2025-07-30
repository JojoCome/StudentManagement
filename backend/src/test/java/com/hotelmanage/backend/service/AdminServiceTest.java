package com.hotelmanage.backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminServiceTest {
    @Test
    void testLoginSuccess(){
        AdminService service = new AdminService();
        boolean result = service.login("admin", "123456");
        assertTrue(result);
    }
}
