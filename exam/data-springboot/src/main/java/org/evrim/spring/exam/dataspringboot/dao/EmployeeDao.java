package org.evrim.spring.exam.dataspringboot.dao;

import org.evrim.spring.exam.dataspringboot.ds.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer>, CustomEmployeeRepository {
    List<Employee> findByEmail(String email);
    List<Employee> findTop3BySalaryGreaterThanOrderBySalary(int salary);
}
