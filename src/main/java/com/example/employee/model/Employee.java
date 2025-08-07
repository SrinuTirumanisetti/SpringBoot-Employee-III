/*
 * You can use the following import statements
 * import javax.persistence.*;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.employee.model;

import javax.persistence.*;

@Entity
public class Employee{
    private Integer employeeId;
    private String employeeName;
    private String email;
    private String department;
}

