package com.newhrms.userservice.Models;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

@DynamoDBTable(tableName = "employeeDDB")
public class EmployeeDynamo implements Serializable {

    private long employeeId;
    private String employeeName;
    private String emailIdl;
    private String managerId;
    private String designation;
    private double salary;
    private Address address;

    public EmployeeDynamo() {
    }

//    public EmployeeDynamo(long employeeId, String employeeName, String emailIdl, String managerId, String designation, double salary) {
//        this.employeeId = employeeId;
//        this.employeeName = employeeName;
//        this.emailIdl = emailIdl;
//        this.managerId = managerId;
//        this.designation = designation;
//        this.salary = salary;
//    }

    public EmployeeDynamo(long employeeId, String employeeName, String emailIdl, String managerId, String designation, double salary, Address address) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailIdl = emailIdl;
        this.managerId = managerId;
        this.designation = designation;
        this.salary = salary;
        this.address = address;
    }

    @DynamoDBHashKey(attributeName = "employeeId")
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @DynamoDBAttribute
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @DynamoDBAttribute
    public String getEmailIdl() {
        return emailIdl;
    }

    public void setEmailIdl(String emailIdl) {
        this.emailIdl = emailIdl;
    }

    @DynamoDBAttribute
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @DynamoDBAttribute
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @DynamoDBAttribute
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeDynamo{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", emailIdl='" + emailIdl + '\'' +
                ", managerId='" + managerId + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                '}';
    }
}
