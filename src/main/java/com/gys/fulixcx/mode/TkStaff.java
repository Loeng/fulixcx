package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tk_staff", schema = "yidaofuli", catalog = "")
public class TkStaff {
    private String id;
    private String name;
    private String phoneNumber;
    private String password;
    private Integer level;
    private Integer valid;
    private String companyId;

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
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "valid")
    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "company_id")
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TkStaff tkStaff = (TkStaff) o;
        return Objects.equals(id, tkStaff.id) &&
                Objects.equals(name, tkStaff.name) &&
                Objects.equals(phoneNumber, tkStaff.phoneNumber) &&
                Objects.equals(password, tkStaff.password) &&
                Objects.equals(level, tkStaff.level) &&
                Objects.equals(valid, tkStaff.valid) &&
                Objects.equals(companyId, tkStaff.companyId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, phoneNumber, password, level, valid, companyId);
    }
}
