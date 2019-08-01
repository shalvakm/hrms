package com.example.leaveservice.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="leave")
public class Leave {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long leaveId;
    @Id
    @Column(name="employeeId")
    private long employeeId;
    @Column(name="managerId")
    private long managerId;
    @Column(name="leaveType")
    private String leaveType;
    @Column(name="startingDate")
    private Date startingDate;
    @Column(name="endingDate")
    private Date endingDate;
    @Column(name="comment")
    private String comment;
    @Column(name="status")
    private String status;

    public Leave() {
    }

    public Leave(long employeeId, long managerId, String leaveType, Date startingDate, Date endingDate, String comment, String status) {
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.leaveType = leaveType;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.comment = comment;
        this.status = status;
    }


    public long getEmployeeId() {
        return employeeId;
    }

    public long getManagerId() {
        return managerId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
