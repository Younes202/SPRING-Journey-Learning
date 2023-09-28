package com.younes.caisse.Controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Services.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

   @PostMapping("/add")
   @Transactional
    public String addEmployee(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {
        // Add the employee to the database
        Employee employee = employeeService.addEmployee(firstName, lastName, email);

        // Check if the employee was successfully added (you can add your own logic)
        if (employee != null) {
            // Add a flash attribute to indicate success
            redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully");
        } else {
            // Add a flash attribute to indicate failure (if needed)
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add employee");
        }

        // Redirect to the dashboard/index.html page
        return "forward:/index";                    }
}
