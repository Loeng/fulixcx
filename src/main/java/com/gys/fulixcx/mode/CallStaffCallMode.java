package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_staff_call", schema = "yidaofuli", catalog = "")
public class CallStaffCallMode {
    private int id;
    private Integer staffId;
    private String phoneNumber;
    private String name;
    private String remarks;
    private Integer schedule;
    private Integer star;
    private String dialTime;
    private Integer converseTime;
    private String attribution;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "schedule")
    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
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
    @Column(name = "attribution")
    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallStaffCallMode that = (CallStaffCallMode) o;
        return id == that.id &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(star, that.star) &&
                Objects.equals(dialTime, that.dialTime) &&
                Objects.equals(attribution, that.attribution) &&
                Objects.equals(converseTime, that.converseTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, staffId, phoneNumber, name, remarks, schedule, star, dialTime, converseTime,attribution);
    }
}
