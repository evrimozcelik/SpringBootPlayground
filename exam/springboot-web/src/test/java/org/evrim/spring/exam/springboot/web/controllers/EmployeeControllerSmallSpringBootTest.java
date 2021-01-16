package org.evrim.spring.exam.springboot.web.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.evrim.spring.exam.springboot.web.dao.EmployeeRepository;
import org.evrim.spring.exam.springboot.web.ds.Employee;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeController.class)
@Import(EmployeeControllerSmallSpringBootTest.TestConfig.class)
public class EmployeeControllerSmallSpringBootTest {

    @Autowired
    EmployeeController employeeController;

    @MockBean
    EmployeeRepository employeeRepository;

    private static Employee employee1 = new Employee(1,"name","surname","email","phone",new Date(),100);

    @Test
    public void getEmployeeList() throws Exception {

        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));

        List<Employee> employeeList = employeeController.getEmployeeList();

        Assert.assertThat(employeeList, Matchers.contains(employee1));

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
