package org.evrim.spring.exam.springboot.web.controllers;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEmployeeList() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(response, StringContains.containsString("\"employeeId\":1"));
    }

    @Test
    public void getEmployee() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(response, StringContains.containsString("Miley.Krueger@corp.com"));

    }
}