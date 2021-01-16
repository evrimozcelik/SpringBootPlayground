package org.evrim.spring.exam.springboot.web.controllers;

import org.evrim.spring.exam.springboot.web.ds.Employee;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerRestTemplateTest {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    public void getEmployee() throws Exception {
        String baseUrl = String.format("http://localhost:%d", port);

        Employee employee = restTemplate.getForEntity("/api/employees/1", Employee.class).getBody();

        Assert.assertThat(employee, Matchers.notNullValue());
    }
}
