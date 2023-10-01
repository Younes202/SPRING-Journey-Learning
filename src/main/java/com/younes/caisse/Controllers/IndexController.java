package com.younes.caisse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Services.EmployeeService;
@Controller
public class IndexController {

    private final EmployeeService employeeService;

 
    @Autowired
    public IndexController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String index(Model model) {
        // Retrieve the list of employees and add it to the model
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "dashboard/index";
    }

    @PostMapping("/employees/add")
    public String addEmployee(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {
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

        // Redirect back to the root URL
        return "redirect:/";
    }
    @PostMapping("/employees/modify")
        public String modifyEmployee(
                @RequestParam Long id,
                @RequestParam String firstName,
                @RequestParam String lastName,
                @RequestParam String email,
                RedirectAttributes redirectAttributes) {
            // Modify the employee
            Employee modifiedEmployee = employeeService.modifyEmployee(id, firstName, lastName, email);

            if (modifiedEmployee != null) {
                // Modification was successful
                redirectAttributes.addFlashAttribute("successMessage", "Employee modified successfully");
            } else {
                // Employee with the given ID does not exist or modification failed
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to modify employee");
            }

            // Redirect back to the root URL
            return "redirect:/";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        boolean deletionSuccessful = employeeService.deleteEmployee(id, redirectAttributes);

        if (deletionSuccessful) {
            return "redirect:/"; // Redirect back to the root URL on success
        } else {
            return "redirect:/error"; // Redirect to an error page on failure, or handle it as needed
        }
    }
    @PostMapping("/employees/delete-all")
        public String deleteAllEmployees(RedirectAttributes redirectAttributes) {
            boolean deletionSuccessful = employeeService.deleteAllEmployees(redirectAttributes);

            if (deletionSuccessful) {
                return "redirect:/"; // Redirect back to the root URL on success
            } else {
                return "redirect:/error"; // Redirect to an error page on failure, or handle it as needed
            }
    }



}
