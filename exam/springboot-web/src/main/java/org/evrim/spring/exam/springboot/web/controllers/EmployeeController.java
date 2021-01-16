package org.evrim.spring.exam.springboot.web.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.evrim.spring.exam.springboot.web.dao.EmployeeRepository;
import org.evrim.spring.exam.springboot.web.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    Counter employeeRequests;
    Counter employeeListRequests;

    public EmployeeController(MeterRegistry meterRegistry) {
        employeeListRequests = meterRegistry.counter("incoming-requests","target", "getEmployeeList");
        employeeRequests = meterRegistry.counter("incoming-requests","target", "getEmployee");
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeeList() {
        employeeListRequests.increment();
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        employeeRequests.increment();
        return employeeRepository.findById(id).orElse(null);
    }
}
