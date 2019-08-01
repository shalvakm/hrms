package com.example.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeDTO {
    private String name;
    @Id
    private String empid;
    private String designation;
    private String emailid;
    private String password;
    private String managerid;
    private String salary;


    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String empid, String designation, String emailid, String password, String managerid, String salary) {
        this.name = name;
        this.empid = empid;
        this.designation = designation;
        this.emailid = emailid;
        this.password = password;
        this.managerid = managerid;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", employee id='" + empid + '\'' +
                ", designation='" + designation + '\'' +
                ", email id='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", manager id='" + managerid + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
