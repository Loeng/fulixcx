package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_company", schema = "yidaofuli", catalog = "")
public class CallCompanyMode {
    private int id;
    private String companyName;
    private String companyAddress;
    private String companyIndustry;
    private String companyCorporation;
    private String companyManage;
    private String creatTime;
    private String endTime;
    private String serviceType;

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
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "company_industry")
    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    @Basic
    @Column(name = "company_corporation")
    public String getCompanyCorporation() {
        return companyCorporation;
    }

    public void setCompanyCorporation(String companyCorporation) {
        this.companyCorporation = companyCorporation;
    }

    @Basic
    @Column(name = "company_manage")
    public String getCompanyManage() {
        return companyManage;
    }

    public void setCompanyManage(String companyManage) {
        this.companyManage = companyManage;
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
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    @Basic
    @Column(name = "service_type")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallCompanyMode that = (CallCompanyMode) o;
        return id == that.id &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(companyAddress, that.companyAddress) &&
                Objects.equals(companyIndustry, that.companyIndustry) &&
                Objects.equals(companyCorporation, that.companyCorporation) &&
                Objects.equals(creatTime, that.creatTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(serviceType, that.serviceType) &&
                Objects.equals(companyManage, that.companyManage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, companyName, companyAddress, companyIndustry, companyCorporation, companyManage,endTime,creatTime,serviceType);
    }
}
