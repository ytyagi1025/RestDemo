package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("getEmployee/{id}")
	public ResponseEntity getEmp(@PathVariable int id){
		try {
			Employee emp=empRepo.findById(id).get();
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("addEmployee")
	public ResponseEntity<Object> addEmp(@RequestBody Employee emp){
		//to add a new record
		try {
			empRepo.save(emp);
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("updateEmployee")
	public ResponseEntity updateEmp(@RequestBody Employee employee) {
		try {
			Employee emp=empRepo.findById(employee.getId()).get();
			emp.setName(employee.getName());
			emp.setSalary(employee.getSalary());
			empRepo.save(emp);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
					
		}
	}
}
