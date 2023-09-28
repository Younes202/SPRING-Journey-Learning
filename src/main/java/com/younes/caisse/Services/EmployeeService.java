package com.younes.caisse.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import the @Transactional annotation

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional // Add @Transactional annotation here
    public Employee addEmployee(String firstName, String lastName, String email) {
        Employee employee = new Employee(firstName, lastName, email);
        return employeeRepository.save(employee);
    }
}
