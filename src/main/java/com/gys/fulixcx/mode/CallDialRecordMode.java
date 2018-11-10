package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_dial_record", schema = "yidaofuli", catalog = "")
public class CallDialRecordMode {
    private int id;
    private int staffId;
    private String dialTime;
    private int phoneId;
    private int converseTime;
    private int dialType;

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
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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
    @Column(name = "phone_id")
    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    @Basic
    @Column(name = "converse_time")
    public int getConverseTime() {
        return converseTime;
    }

    public void setConverseTime(int converseTime) {
        this.converseTime = converseTime;
    }

    @Basic
    @Column(name = "dial_type")
    public int getDialType() {
        return dialType;
    }

    public void setDialType(int dialType) {
        this.dialType = dialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallDialRecordMode that = (CallDialRecordMode) o;
        return id == that.id &&
                staffId == that.staffId &&
                phoneId == that.phoneId &&
                converseTime == that.converseTime &&
                dialType == that.dialType &&
                Objects.equals(dialTime, that.dialTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, staffId, dialTime, phoneId, converseTime, dialType);
    }
}
