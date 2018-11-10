package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_phone")
public class CallPhoneMode {
    private int id;
    private String phoneNumber;
    private String phoneName;
    private String remarks;
    private String industry;
    private String carrieroperator;
    private String attribution;
    private String upDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "up_date")
    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Basic
    @Column(name = "carrieroperator")
    public String getCarrieroperator() {
        return carrieroperator;
    }

    public void setCarrieroperator(String carrieroperator) {
        this.carrieroperator = carrieroperator;
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
        CallPhoneMode that = (CallPhoneMode) o;
        return id == that.id &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(phoneName, that.phoneName) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(industry, that.industry) &&
                Objects.equals(carrieroperator, that.carrieroperator) &&
                Objects.equals(attribution, that.attribution);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, phoneNumber, phoneName, remarks, industry, carrieroperator, attribution);
    }
}
