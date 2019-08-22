package com.newhrms.userservice.Controller;

import com.newhrms.userservice.Models.EmployeeDynamo;
import com.newhrms.userservice.Repository.DynamoDBRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dynamodb")
public class EmpDynamoController {

    @Autowired
    private DynamoDBRepository dynamoDBRepository;

    @PostMapping
    public String insertEmployeeDDB(@RequestBody EmployeeDynamo employee) {
        dynamoDBRepository.insertEmployee(employee);
        return "Inserted Successfully";
    }

    @GetMapping
    public ResponseEntity<EmployeeDynamo> getOneEmployee(@RequestParam long employeeId) {
        EmployeeDynamo employee = dynamoDBRepository.getOneEmployeeDetails(employeeId);
        return new ResponseEntity<EmployeeDynamo>(employee, HttpStatus.OK);
    }

    @DeleteMapping(value = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") long employeeId) {
        EmployeeDynamo employee = new EmployeeDynamo();
        employee.setEmployeeId(employeeId);
        dynamoDBRepository.deleteEmployee(employee);
    }


}
