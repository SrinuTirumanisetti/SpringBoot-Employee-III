package com.example.employee.repository;

import com.example.employee.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
}
