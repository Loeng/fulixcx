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
                Objects.equals(companyManage, that.companyManage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, companyName, companyAddress, companyIndustry, companyCorporation, companyManage);
    }
}
