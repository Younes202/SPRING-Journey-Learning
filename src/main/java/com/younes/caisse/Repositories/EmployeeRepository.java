package com.younes.caisse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.younes.caisse.Entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can add custom query methods here if needed
}