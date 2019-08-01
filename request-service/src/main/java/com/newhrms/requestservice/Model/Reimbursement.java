package com.newhrms.requestservice.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reimbursement")
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reimbId;
    @Column(name="employeeId")
    private long employeeId;
    @Column(name="managerId")
    private long managerId;
    @Column(name="reimbTitle")
    private String reimbTitle;
    @Column(name="reimbType")
    private String reimbType;
    @Column(name="reimbApplyDate")
    private Date reimbApplyDate;
    @Column(name="reimbAmount")
    private double reimbAmount;
    @Column(name="comment")
    private String comment;
    @Column(name="status")
    private String status;

    public Reimbursement() {
    }

    public Reimbursement( long employeeId, long managerId, String reimbTitle, String reimbType, Date reimbApplyDate, double reimbAmount, String comment, String status) {
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.reimbTitle = reimbTitle;
        this.reimbType = reimbType;
        this.reimbApplyDate = reimbApplyDate;
        this.reimbAmount = reimbAmount;
        this.comment = comment;
        this.status = status;
    }

    public long getReimbId() {
        return reimbId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getManagerId() {
        return managerId;
    }

    public String getReimbTitle() {
        return reimbTitle;
    }

    public String getReimbType() {
        return reimbType;
    }

    public Date getReimbApplyDate() {
        return reimbApplyDate;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public void setReimbId(long reimbId) {
        this.reimbId = reimbId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public void setReimbTitle(String reimbTitle) {
        this.reimbTitle = reimbTitle;
    }

    public void setReimbType(String reimbType) {
        this.reimbType = reimbType;
    }

    public void setReimbApplyDate(Date reimbApplyDate) {
        this.reimbApplyDate = reimbApplyDate;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}