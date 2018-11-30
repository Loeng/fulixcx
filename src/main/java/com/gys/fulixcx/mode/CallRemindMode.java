package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_remind", schema = "yidaofuli", catalog = "")
public class CallRemindMode {
    private int id;
    private Integer staffId;
    private String remindDate;
    private String body;

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
    @Column(name = "staff_id")
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "remind_date")
    public String getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallRemindMode that = (CallRemindMode) o;
        return id == that.id &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(remindDate, that.remindDate) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, staffId, remindDate, body);
    }
}
