package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tk_task", schema = "yidaofuli", catalog = "")
public class TkTask {
    private String id;
    private String name;
    private Timestamp createTime;
    private String createStaff;
    private String exexuteStall;
    private Integer isReleased;
    private String note;

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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_staff")
    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    @Basic
    @Column(name = "exexute_stall")
    public String getExexuteStall() {
        return exexuteStall;
    }

    public void setExexuteStall(String exexuteStall) {
        this.exexuteStall = exexuteStall;
    }

    @Basic
    @Column(name = "is_released")
    public Integer getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(Integer isReleased) {
        this.isReleased = isReleased;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TkTask tkTask = (TkTask) o;
        return Objects.equals(id, tkTask.id) &&
                Objects.equals(name, tkTask.name) &&
                Objects.equals(createTime, tkTask.createTime) &&
                Objects.equals(createStaff, tkTask.createStaff) &&
                Objects.equals(exexuteStall, tkTask.exexuteStall) &&
                Objects.equals(isReleased, tkTask.isReleased) &&
                Objects.equals(note, tkTask.note);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, createTime, createStaff, exexuteStall, isReleased, note);
    }
}
