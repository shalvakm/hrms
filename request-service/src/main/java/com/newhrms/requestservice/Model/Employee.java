package com.newhrms.requestservice.Model;

import javax.persistence.*;

public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
//    @Column(name="employeeName")
    private String employeeName;
//    @Column(name="emailId")
    private String emailId;
//    @Column(name="managerId")
    private long managerId;
//    @Column(name="designation")
    private String designation;
//    @Column(name="salary")
    private double salary;

    public Employee() {
    }

    public Employee(long employeeId ,String employeeName, String emailId, long managerId, String designation, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailId = emailId;
        this.managerId = managerId;
        this.designation = designation;
        this.salary = salary;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public long getManagerId() {
        return managerId;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}