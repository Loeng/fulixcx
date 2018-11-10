package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_task", schema = "yidaofuli", catalog = "")
public class CallTaskMode {
    private int id;
    private String taskName;
    private Integer companyId;
    private Integer issuerId;
    private Integer staffId;
    private String lssuerTime;
    private String remarks;
    private Integer state;

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
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "Issuer_id")
    public Integer getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Integer issuerId) {
        this.issuerId = issuerId;
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
    @Column(name = "lssuer_time")
    public String getLssuerTime() {
        return lssuerTime;
    }

    public void setLssuerTime(String lssuerTime) {
        this.lssuerTime = lssuerTime;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallTaskMode that = (CallTaskMode) o;
        return id == that.id &&
                Objects.equals(taskName, that.taskName) &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(issuerId, that.issuerId) &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(lssuerTime, that.lssuerTime) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, taskName, companyId, issuerId, staffId, lssuerTime, remarks);
    }
}
