package org.evrim.spring.exam.data.ds;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private int salary;

}
