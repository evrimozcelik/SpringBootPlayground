package org.evrim.spring.exam.springboot.web.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.evrim.spring.exam.springboot.web.dao.EmployeeRepository;
import org.evrim.spring.exam.springboot.web.ds.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import(EmployeeControllerMockMvcSingleTest.TestConfig.class)
public class EmployeeControllerMockMvcSingleTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeRepository employeeRepository;

    private static Employee employee1 = new Employee(1,"name","surname","email","phone",new Date(),100);

    @Test
    public void getEmployeeList() throws Exception {
        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeRepository).findAll();

    }


    @TestConfiguration
    public static class TestConfig {

        @Bean
        @Primary
        public MeterRegistry mockMeterRegistry() {
            MeterRegistry meterRegistry = Mockito.mock(MeterRegistry.class);
            Mockito.when(meterRegistry.counter(Mockito.any(), ArgumentMatchers.<String>any()))
                    .thenReturn(Mockito.mock(Counter.class));
            return meterRegistry;
        }

    }

}
