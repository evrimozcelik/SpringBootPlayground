package org.evrim.spring.exam.data.dao;

import org.evrim.spring.exam.data.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
