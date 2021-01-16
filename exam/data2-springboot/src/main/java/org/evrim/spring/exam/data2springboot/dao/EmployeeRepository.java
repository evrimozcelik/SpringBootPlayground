package org.evrim.spring.exam.data2springboot.dao;

import org.evrim.spring.exam.data2springboot.ds.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
