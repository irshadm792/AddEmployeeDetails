package com.springboot.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.entity.Employee;
import com.springboot.employee.exception.ResourceNotFoundexception;
import com.springboot.employee.repository.EmployeeRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(path="/employees")
	public List<Employee>   getAllEmployees(){
		
		return employeeRepository.findAll();
		
	}
	@PostMapping(path="/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepository.save(employee);
	}
	
	@GetMapping(path="/employees{id}")
	public  ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundexception("employee not exist with id:"+id));
		
				return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundexception("employee not exist with id:"+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employee.getEmailId());
		employeeRepository.save(employee);
		
		return ResponseEntity.ok(employee);
		
	}

}
