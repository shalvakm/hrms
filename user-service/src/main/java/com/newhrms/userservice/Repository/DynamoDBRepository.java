package com.newhrms.userservice.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.newhrms.userservice.Models.Employee;
import com.newhrms.userservice.Models.EmployeeDynamo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.logging.Logger;

@Repository
public class DynamoDBRepository {

//    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DynamoDBRepository.class);

    @Autowired
    private DynamoDBMapper mapper;

    public void insertEmployee(EmployeeDynamo employeeDynamo) {
        mapper.save(employeeDynamo);
    }

    public EmployeeDynamo getOneEmployeeDetails(long employeeId) {
        return mapper.load(EmployeeDynamo.class, employeeId);
    }

    public void deleteEmployee(EmployeeDynamo employee) {
        mapper.delete(employee);
    }

}
