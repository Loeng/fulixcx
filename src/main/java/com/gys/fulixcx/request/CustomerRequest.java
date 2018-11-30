package com.gys.fulixcx.request;

public class CustomerRequest extends BaseRequest{
    private Integer companyId;
    private String phoneName;
    private String phoneNumber;
    private Integer star;
    private Integer level;
    private Integer minStar = 0;
    private Integer maxStar = 4;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getMinStar() {
        return minStar;
    }

    public void setMinStar(Integer minStar) {
        this.minStar = minStar;
    }

    public Integer getMaxStar() {
        return maxStar;
    }

    public void setMaxStar(Integer maxStar) {
        this.maxStar = maxStar;
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

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
