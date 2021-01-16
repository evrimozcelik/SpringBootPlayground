package org.evrim.spring.exam.springboot.web.dao;

import org.evrim.spring.exam.springboot.web.ds.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
