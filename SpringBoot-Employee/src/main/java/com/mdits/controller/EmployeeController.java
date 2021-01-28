package com.mdits.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdits.exception.ResourceNotFoundException;
import com.mdits.model.Employee;
import com.mdits.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployess(){
		return employeeRepository.findAll();	
	}
	
	@GetMapping("/employees/{empID}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "empID")Integer empId)
		throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found for thid id::"+empId));
	       return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);}
	
	@PutMapping("/employee/{empID}")
	  public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empID") Integer empID,
			  @RequestBody Employee employeeDetails) throws
	  ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empID)
				.orElseThrow(()->new
			ResourceNotFoundException("Employee not found for this id::"+
				empID));
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("/employee/{empID}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "empID") Integer empID)
	 throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(empID)
				.orElseThrow(()->new ResourceNotFoundException("EMployee not found this id ::"+empID));
	
	employeeRepository.delete(employee);
	
	Map<String, Boolean> response =new HashMap<>();
	response.put("deleted",Boolean.TRUE);
	return response;
	}
	
	}
	
	

