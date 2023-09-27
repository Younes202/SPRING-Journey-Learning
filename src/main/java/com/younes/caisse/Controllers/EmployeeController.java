package com.younes.caisse.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.younes.caisse.Entities.Employee;
import com.younes.caisse.Services.EmployeeService;

@Controller // Use @Controller annotation for a Spring MVC controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "dashboard/index"; // Return the Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        employee.ifPresent(emp -> model.addAttribute("employee", emp));
        return "dashboard/view"; // Return the Thymeleaf template name for viewing a single employee
    }

    @PostMapping("/")
    public String createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees/"; // Redirect to the list of employees after creating one
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        employeeService.updateEmployee(id, updatedEmployee);
        return "redirect:/employees/"; // Redirect to the list of employees after updating one
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/"; // Redirect to the list of employees after deleting one
    }
}
