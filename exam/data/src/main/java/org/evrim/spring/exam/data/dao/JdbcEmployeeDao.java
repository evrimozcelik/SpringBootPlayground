package org.evrim.spring.exam.data.dao;

import org.evrim.spring.exam.data.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcEmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setJdbcTemplate(DataSource employeeDataSource) {
        jdbcTemplate = new JdbcTemplate(employeeDataSource);
    }

    public int insertEmployee(Employee employee) {
        if(employee==null) {
            throw new RuntimeException("Employee cannot be null");
        }
        if(employee.getEmployeeId() < 0) {
            throw new RuntimeException("Employee id must be greater than 0");
        }

        return jdbcTemplate.update(
                "insert into employee(employee_id, first_name, last_name, email, phone_number, hire_date, salary) " +
                        "values (?, ?, ?, ?, ?, ?, ?)",
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getHireDate(),
                employee.getSalary()
        );
    }

    public List<String> getEmployeeEmails() {
        return jdbcTemplate.queryForList("select email from employee", String.class);
    }

    public List<Employee> getEmployees() {
        return jdbcTemplate.query("select * from employee",
                (resultSet, i) -> new Employee(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("hire_date"),
                        resultSet.getInt("salary")
                )
        );
    }

}
