package com.younes.caisse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.younes.caisse.Entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom method to update employee by first name and last name
    @Modifying
    @Query("UPDATE Employee e SET e.email = :newEmail WHERE e.firstName = :firstName AND e.lastName = :lastName");
    
    // No need to define 'addEmployee' method, as 'save' method from JpaRepository handles adding employees.
    
    // Custom method to delete an employee by ID
    void deleteById(Long id);
}
