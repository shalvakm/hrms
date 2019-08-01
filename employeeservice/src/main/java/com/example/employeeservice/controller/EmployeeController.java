package com.example.employeeservice.controller;



import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.EmployeeDTO;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.employeeservice.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeeservice.exception.ResourceNotFoundException;

import com.example.employeeservice.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/send")
    public void sendEmail()
    {
        SendMailService sendMailService;
        sendMailService =new SendMailService();

        List<Employee> employees= employeeRepository.findAll();
//        int sizeD=employees.size();
//        String[] emid=new String[sizeD];
//        String[] emEmail=new String[sizeD];
//        String[] emPassword =new String[sizeD];
        List<String[]> l1 =new ArrayList<>();
        for(Employee em : employees)
        {
            String[] str=new String[4];
            str[0]=em.getEmpid();
            str[1]=em.getPassword();
            str[2]=em.getEmailid();
            System.out.println(em.getEmailid());
            //sendMailService.sendMail(em.getEmailid());
            l1.add(str);
            /* break; */
        }
        sendMailService.sendMail(l1);
//        for(String[] str: l1)
//        {
//            System.out.println(str[0]+" "+str[1]+" "+str[2]);
//        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //	@PostMapping("/employees")
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public @ResponseBody Employee createEmployee( @RequestBody Employee employee) {

        employeeRepository.save(employee);
        return employee;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailid(employeeDetails.getEmailid());
        employee.setPassword(employeeDetails.getPassword());
        employee.setName(employeeDetails.getName());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setSalary(employeeDetails.getSalary());
        employee.setEmpid(employeeDetails.getEmpid());
        employee.setManagerid(employeeDetails.getManagerid());
//        employee.setAddress(employeeDetails.getAddress());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

