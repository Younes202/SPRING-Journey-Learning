package com.younes.caisse.Controllers;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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

        // Log a message
        logger.info("Employee added: {} {}", firstName, lastName);

        // Check if the employee was successfully added (you can add your own logic)
        if (employee != null) {
            // Add a flash attribute to indicate success
            redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully");
        } else {
            // Add a flash attribute to indicate failure (if needed)
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add employee");
        }

        // Redirect to the dashboard/index.html page
        return "redirect:/";
        }
}
