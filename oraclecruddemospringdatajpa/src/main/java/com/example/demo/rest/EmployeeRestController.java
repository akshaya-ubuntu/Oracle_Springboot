package com.example.demo.rest;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("api")
public class EmployeeRestController {
	private EmployeeService employeeService;
    private JsonMapper jsonMapper;
	public EmployeeRestController() {
		super();
	}

	@Autowired
	public EmployeeRestController(EmployeeService employeeService,JsonMapper jsonMapper) {
		super();
		this.employeeService = employeeService;
		this.jsonMapper = jsonMapper;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee Id not found - " + employeeId);
		}
		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		Employee dbEmployee = employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	@PatchMapping("/employees/{employeeId}")
	public Employee patchEmployee(@PathVariable int employeeId,
	                              @RequestBody Map<String, Object> patchPayload) {

	    Employee tempEmployee = employeeService.findById(employeeId);

	    // throw exception if null
	    if (tempEmployee == null) {
	        throw new RuntimeException("Employee id not found - " + employeeId);
	    }

	    // throw exception if request body updates contains id
	    if (patchPayload.containsKey("id")) {
	        throw new RuntimeException(
	                "Employee id not allowed in request body - " + employeeId);
	    }

	    // Apply the partial updates to the existing employee object
	    Employee patchedEmployee =
	            jsonMapper.updateValue(tempEmployee, patchPayload);

	    Employee dbEmployee = employeeService.save(patchedEmployee);

	    return dbEmployee;
	}
	  // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

}
