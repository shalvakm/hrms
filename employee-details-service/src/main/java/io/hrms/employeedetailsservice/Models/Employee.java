package io.hrms.employeedetailsservice.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    private String name;
    @Id
    private long employeeId;
    private String designation;
    private String emailid;
    private String password;
    private long managerId;
    private String salary;


    public Employee() {
    }

    public Employee(String name, long employeeId, String designation, String emailid, String password, long managerId, String salary) {
        this.name = name;
        this.employeeId = employeeId;
        this.designation = designation;
        this.emailid = emailid;
        this.password = password;
        this.managerId = managerId;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeeId() { return employeeId; }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

}
