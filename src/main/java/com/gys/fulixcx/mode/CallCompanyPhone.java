package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_company_phone")
public class CallCompanyPhone {
    private int id;
    private CallCompanyMode callCompanyMode;
    private CallPhoneMode callPhoneMode;
    private String remarks;
    private CallStaffMode callStaffMode;
    private String upTime;
    private Integer dialType;
    private Integer star;
    private Integer schedule;
    private String dialTime;
    private Integer converseTime;
    private String phoneName;
    private String taskId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "phone_name")
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    @Column(name = "dial_type")
    public Integer getDialType() {
        return dialType;
    }

    public void setDialType(Integer dialType) {
        this.dialType = dialType;
    }

    
    @Column(name = "star")
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    
    @Column(name = "schedule")
    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }
    
    @Column(name = "dial_time")
    public String getDialTime() {
        return dialTime;
    }

    public void setDialTime(String dialTime) {
        this.dialTime = dialTime;
    }
    
    @Column(name = "converse_time")
    public Integer getConverseTime() {
        return converseTime;
    }

    public void setConverseTime(Integer converseTime) {
        this.converseTime = converseTime;
    }
    
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "up_time")
    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public CallCompanyMode getCallCompanyMode() {
        return callCompanyMode;
    }

    public void setCallCompanyMode(CallCompanyMode callCompanyMode) {
        this.callCompanyMode = callCompanyMode;
    }
    @OneToOne
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    public CallPhoneMode getCallPhoneMode() {
        return callPhoneMode;
    }

    public void setCallPhoneMode(CallPhoneMode callPhoneMode) {
        this.callPhoneMode = callPhoneMode;
    }
    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    public CallStaffMode getCallStaffMode() {
        return callStaffMode;
    }
    public void setCallStaffMode(CallStaffMode callStaffMode) {
        this.callStaffMode = callStaffMode;
    }

    @Column(name = "task_id")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
