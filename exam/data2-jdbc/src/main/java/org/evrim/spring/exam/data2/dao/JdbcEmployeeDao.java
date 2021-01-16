package org.evrim.spring.exam.data2.dao;

import org.evrim.spring.exam.data2.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcEmployeeDao {

    JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("select * from Employee", this::employeeMapper);
    }

    private Employee employeeMapper(ResultSet resultSet, int i) throws SQLException {
        return new Employee(
                resultSet.getInt("employee_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getDate("hire_date"),
                resultSet.getInt("salary")
        );
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
        return jdbcTemplate.queryForList("select email from Employee", String.class);
    }

    public Employee getEmployee(int id) {
        return jdbcTemplate.queryForObject("select * from Employee where employee_id=?", new Object[]{id}, this::employeeMapper);
    }

    public double calculateAverageSalary() {
        AverageSalaryCalculator calculator = new AverageSalaryCalculator();
        jdbcTemplate.query("select salary from Employee", calculator);
        return calculator.totalSalary/calculator.count;
    }

    private static class AverageSalaryCalculator implements RowCallbackHandler {

        int count = 0;
        double totalSalary = 0;

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            totalSalary += resultSet.getInt("salary");
            count++;
        }
    }
}
