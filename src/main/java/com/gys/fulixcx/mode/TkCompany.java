package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tk_company", schema = "yidaofuli", catalog = "")
public class TkCompany {
    private String id;
    private String name;
    private String address;
    private String province;
    private String city;
    private String staffId;
    private String note;
    private Timestamp createTime;
    private Timestamp endTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "staff_id")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TkCompany tkCompany = (TkCompany) o;
        return Objects.equals(id, tkCompany.id) &&
                Objects.equals(name, tkCompany.name) &&
                Objects.equals(address, tkCompany.address) &&
                Objects.equals(province, tkCompany.province) &&
                Objects.equals(city, tkCompany.city) &&
                Objects.equals(staffId, tkCompany.staffId) &&
                Objects.equals(note, tkCompany.note) &&
                Objects.equals(createTime, tkCompany.createTime) &&
                Objects.equals(endTime, tkCompany.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, province, city, staffId, note, createTime, endTime);
    }
}
