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
    public Employee addEmployee(Employee employee){
        employeeJpaRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(int employeeId,Employee employee){
        try{
            Employee existingEmployee = getEmployeeById(employeeId);
            if(employee.getEmployeeName()!=null){
                existingEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if(employee.getEmail()!=null){
                existingEmployee.setEmail(employee.getEmail());
            }
            if(employee.getDepartment()!=null){
                existingEmployee.setEmployeeName(employee.getDepartment());
            }
            employeeJpaRepository.save(existingEmployee);
            return existingEmployee;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
