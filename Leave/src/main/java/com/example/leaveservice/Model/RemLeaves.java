package com.example.leaveservice.Model;


import javax.persistence.*;

@Entity
@Table(name = "remleaves")
public class RemLeaves {

    @Id
    private long employeeId;

    @Column(name = "casual")
    private long casual ;

    @Column(name = "sick")
    private long sick;

    @Column(name = "unpaid")
    private long unpaid;

    public RemLeaves(long employeeId, long casual, long sick, long unpaid) {
        this.employeeId = employeeId;
        this.casual = casual;
        this.sick = sick;
        this.unpaid = unpaid;
    }

    public RemLeaves() {

    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getCasual() {
        return casual;
    }

    public void setCasual(long casual) {
        this.casual = casual;
    }

    public long getSick() {
        return sick;
    }

    public void setSick(long sick) {
        this.sick = sick;
    }

    public long getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(long unpaid) {
        this.unpaid = unpaid;
    }
}
