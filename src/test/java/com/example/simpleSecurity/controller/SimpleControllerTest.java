package com.example.simpleSecurity.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SimpleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowAccessToHomePageForEveryone() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())  // Статус 200 OK
                .andExpect(view().name("home")); // Проверяем, что отображается страница "home"
    }
    @Test
    @WithMockUser(username = "user", password = "password")
    void shouldAllowAccessToDashboardForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())  // Статус 200 OK
                .andExpect(view().name("dashboard")); // Проверяем, что отображается страница "dashboard"
    }
    @Test
    @WithMockUser(username = "user", password = "password")
    void shouldAllowAccessToAdminForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())  // Статус 200 OK
                .andExpect(view().name("admin")); // Проверяем, что отображается страница "admin"
    }

    @Test
    void shouldRedirectToLoginPageWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())  // Ожидаем редирект
                .andExpect(redirectedUrl("http://localhost/login"));  // Редирект на страницу логина, т.к. пользователь не авторизован
    }
}