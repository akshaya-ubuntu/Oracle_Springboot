package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		super();
		this.entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

}
