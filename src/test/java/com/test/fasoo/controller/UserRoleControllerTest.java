package com.test.fasoo.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserRoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 관리자_추가_성공_테스트() throws Exception {
        //given
        Map<String, String> input = new HashMap<>();

        input.put("userId", "user01");
        input.put("adminTypeId", "SYSTEM_AdMIN");


        // when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user-role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(input).toString())
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 관리자_추가_실패_테스트() throws Exception{
        //given
        Map<String, String> input = new HashMap<>();

        input.put("userId", "user01");
        input.put("adminTypeId", "WRONG_TYPE");


        // when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user-role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(input).toString())
                )
                .andExpect(status().isBadRequest());
    }
}