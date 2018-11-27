package com.gys.fulixcx.mode;

import com.gys.fulixcx.util.DateUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "call_staff", schema = "yidaofuli", catalog = "")
public class CallStaffMode {
    private int id;
    private String staffPhone;
    private String staffName;
    private Integer companyId;
    private String creatTime;
    private Integer state;
    private Integer staffManage;
    private String passWord;

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
    @Column(name = "staff_phone")
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
    @Basic
    @Column(name = "pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Basic
    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
    @Column(name = "creat_time")
    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "staff_manage")
    public Integer getStaffManage() {
        return staffManage;
    }

    public void setStaffManage(Integer staffManage) {
        this.staffManage = staffManage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallStaffMode that = (CallStaffMode) o;
        return id == that.id &&
                Objects.equals(staffPhone, that.staffPhone) &&
                Objects.equals(staffName, that.staffName) &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(creatTime, that.creatTime) &&
                Objects.equals(state, that.state) &&
                Objects.equals(staffManage, that.staffManage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, staffPhone, staffName, companyId, creatTime, state, staffManage);
    }
}
