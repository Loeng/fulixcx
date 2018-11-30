package com.gys.fulixcx.request;

public class ResourceRequest extends BaseRequest{
    private String phoneName;
    private String phoneNumber;
    private Integer companyId;
    private String carrieroperator;
    private String assign;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarrieroperator() {
        return carrieroperator;
    }

    public void setCarrieroperator(String carrieroperator) {
        this.carrieroperator = carrieroperator;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }
}
