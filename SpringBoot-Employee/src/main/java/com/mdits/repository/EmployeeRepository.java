package com.mdits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdits.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
