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
import com.mdits.model.Department;
import com.mdits.repository.DepartmentRepository;
//import javax.validation.Valid;
@RestController
@RequestMapping("/departmentapi")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/department")
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	
	@GetMapping("/departments/{dNumber}")
	 public ResponseEntity<Department> getDepartmentById( @PathVariable (value = "dNumber") Integer dNumber)
       throws ResourceNotFoundException {
    	   Department department = departmentRepository.findById(dNumber)
    	   .orElseThrow(()->new ResourceNotFoundException("Departmnet not found for this id::"+ dNumber));
    	   return ResponseEntity.ok().body(department);
       }
       @PostMapping("/departments")
       public Department createDepartment(@RequestBody Department department) {
    	   return departmentRepository.save(department);}
       
       @PutMapping("/department/{dNUmber}")
       public ResponseEntity<Department> updateDepartment(@PathVariable(value ="dnumber") Integer dNumber,
    		  @RequestBody Department departmentDetails) throws
       ResourceNotFoundException{
    	   Department department = departmentRepository.findById(dNumber)
    			   .orElseThrow(() -> new
    			  ResourceNotFoundException("Department not found for this id::"+
    			   dNumber));
    			   
    			   final Department updateDepartment =departmentRepository.save(department);
    			   return ResponseEntity.ok(updateDepartment);
       }
       @DeleteMapping("/departments/{dNUmber}")
       public Map<String, Boolean> deleateDepartment(@PathVariable(value = "dNUmber") Integer dNumber)
         throws ResourceNotFoundException {
    	   Department department = departmentRepository.findById(dNumber)
    			   .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id::"+ dNumber));
    	   
	departmentRepository.delete(department);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleated",Boolean.TRUE);
	return response;
 	}

}
