package org.evrim.spring.exam.dataspringboot.dao;

import org.evrim.spring.exam.dataspringboot.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Employee> findByName(String name) {

        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.firstName=?1 or e.lastName=?2", Employee.class);
        query.setParameter(1, name);
        query.setParameter(2, name);
        return query.getResultList();
    }
}
