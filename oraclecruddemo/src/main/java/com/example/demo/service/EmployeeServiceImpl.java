package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		super();
		this.employeeDAO = employeeDAO;
	}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}
	
	@Transactional
	@Override
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}
	
	@Transactional
	@Override
	public Employee save(Employee theEmployee) {
		return employeeDAO.save(theEmployee);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
		
	}

}
