package org.evrim.spring.exam.dataspringboot.dao;

import org.evrim.spring.exam.dataspringboot.ds.Employee;

import java.util.List;

public interface CustomEmployeeRepository {

    List<Employee> findByName(String name);
}
