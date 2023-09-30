package com.younes.caisse.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import the @Transactional annotation
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Repositories.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

 @Transactional(readOnly = true) // Add @Transactional annotation here if needed
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    
    @Transactional // Add @Transactional annotation here
    public Employee addEmployee(String firstName, String lastName, String email) {
        Employee employee = new Employee(firstName, lastName, email);
        return employeeRepository.save(employee);
    }
    @Transactional
public Employee modifyEmployee(Long id, String firstName, String lastName, String email) {
    // Check if the employee with the given ID exists
    Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
    
    if (existingEmployeeOptional.isPresent()) {
        // Employee with the given ID exists, so update its details
        Employee existingEmployee = existingEmployeeOptional.get();
        existingEmployee.setFirstName(firstName);
        existingEmployee.setLastName(lastName);
        existingEmployee.setEmail(email);
        
        // Save the updated employee
        return employeeRepository.save(existingEmployee);
    } else {
        // Employee with the given ID does not exist
        return null;
    }
}
@Transactional
    public boolean deleteEmployee(Long id, RedirectAttributes redirectAttributes) {
        try {
            employeeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully");
            return true;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee");
            return false;
        }
    }
     @Transactional
    public boolean deleteAllEmployees(RedirectAttributes redirectAttributes) {
        try {
            employeeRepository.deleteAll();
            redirectAttributes.addFlashAttribute("successMessage", "All employees deleted successfully");
            return true; // Deletion was successful
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employees");
            return false; // Deletion failed
        }
    }
}
