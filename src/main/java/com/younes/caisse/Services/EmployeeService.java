package com.younes.caisse.Services;

import java.util.List;
import java.util.Optional;

import com.younes.caisse.Entities.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    void deleteEmployee(Long id);
}
