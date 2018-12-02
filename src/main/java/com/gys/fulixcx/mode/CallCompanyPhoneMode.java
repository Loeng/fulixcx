package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_company_phone")
public class CallCompanyPhoneMode {
    private int id;
    private Integer companyId;
    private Integer phoneId;
    private String remarks;
    private Integer staffId;
    private String upTime;
    private Integer dialType;
    private Integer star;
    private Integer schedule;
    private String dialTime;
    private Integer converseTime;
    private String phoneName;
    private Integer taskId;
    private String companyName;
    private Integer categoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone_name")
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    @Basic
    @Column(name = "task_id")
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    @Basic
    @Column(name = "dial_type")
    public Integer getDialType() {
        return dialType;
    }

    public void setDialType(Integer dialType) {
        this.dialType = dialType;
    }

    @Basic
    @Column(name = "star")
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    @Basic
    @Column(name = "schedule")
    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }
    @Basic
    @Column(name = "dial_time")
    public String getDialTime() {
        return dialTime;
    }

    public void setDialTime(String dialTime) {
        this.dialTime = dialTime;
    }
    @Basic
    @Column(name = "converse_time")
    public Integer getConverseTime() {
        return converseTime;
    }

    public void setConverseTime(Integer converseTime) {
        this.converseTime = converseTime;
    }
    @Basic
    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Basic
    @Column(name = "phone_id")
    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "staff_id")
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "up_time")
    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallCompanyPhoneMode that = (CallCompanyPhoneMode) o;
        return id == that.id &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(phoneId, that.phoneId) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(dialType, that.dialType) &&
                Objects.equals(star, that.star) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(dialTime, that.dialTime) &&
                Objects.equals(converseTime, that.converseTime) &&
                Objects.equals(phoneName, that.phoneName) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(upTime, that.upTime);
    }
    @Override
    public int hashCode() {

        return Objects.hash(id, companyId, phoneId, remarks, staffId, upTime,dialType,star,schedule,dialTime,converseTime,phoneName,taskId,categoryId);
    }
}
