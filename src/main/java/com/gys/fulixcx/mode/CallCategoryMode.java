package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_category", schema = "yidaofuli", catalog = "")
public class CallCategoryMode {
    private int id;
    private Integer companyId;
    private String categoryName;
    private String illustrate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "illustrate")
    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallCategoryMode that = (CallCategoryMode) o;
        return id == that.id &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(illustrate, that.illustrate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, companyId, categoryName, illustrate);
    }
}
