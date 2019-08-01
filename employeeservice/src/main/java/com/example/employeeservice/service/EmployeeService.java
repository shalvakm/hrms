package com.example.employeeservice.service;


import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<Employee> listAll() { return repo.findAll(); }

    public void save(Employee emp) {
        repo.save(emp);
    }

    public Employee get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) { repo.deleteById(id); }
}
