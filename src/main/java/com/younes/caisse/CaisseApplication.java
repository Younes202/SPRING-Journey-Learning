package com.younes.caisse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext; // Import ApplicationContext

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Services.EmployeeService;

@SpringBootApplication
public class CaisseApplication {

    public static void main(String[] args) {
        // Initialize the Spring Boot application
        SpringApplication app = new SpringApplication(CaisseApplication.class);
        ApplicationContext context = app.run(args);

        // Get an instance of EmployeeService
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        // Insert data using the EmployeeService
        Employee employee = employeeService.addEmployee("John", "Doe", "john.doe@example.com");
        System.out.println("Inserted Employee: " + employee.getId());
    }
}
