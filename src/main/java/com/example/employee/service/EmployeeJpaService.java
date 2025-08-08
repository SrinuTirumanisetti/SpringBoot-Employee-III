package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeJpaService implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;

    public EmployeeJpaService(EmployeeJpaRepository employeeJpaRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeJpaRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        Employee existingEmployee = getEmployeeById(employeeId);

        if (employee.getEmployeeName() != null) {
            existingEmployee.setEmployeeName(employee.getEmployeeName());
        }
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment()); 
        }

        return employeeJpaRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        if (!employeeJpaRepository.existsById(employeeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeJpaRepository.deleteById(employeeId);
    }
}
