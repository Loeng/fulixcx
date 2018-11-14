package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tk_dial_records", schema = "yidaofuli", catalog = "")
public class TkDialRecords {
    private String id;
    private String taskComTelId;
    private String telephoneId;
    private Timestamp callTime;
    private Integer callSecs;
    private Integer stars;
    private Integer progress;
    private String note;
    private String remarks;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "task_com_tel_id")
    public String getTaskComTelId() {
        return taskComTelId;
    }

    public void setTaskComTelId(String taskComTelId) {
        this.taskComTelId = taskComTelId;
    }

    @Basic
    @Column(name = "telephone_id")
    public String getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(String telephoneId) {
        this.telephoneId = telephoneId;
    }

    @Basic
    @Column(name = "call_time")
    public Timestamp getCallTime() {
        return callTime;
    }

    public void setCallTime(Timestamp callTime) {
        this.callTime = callTime;
    }

    @Basic
    @Column(name = "call_secs")
    public Integer getCallSecs() {
        return callSecs;
    }

    public void setCallSecs(Integer callSecs) {
        this.callSecs = callSecs;
    }

    @Basic
    @Column(name = "stars")
    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Basic
    @Column(name = "progress")
    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        TkDialRecords that = (TkDialRecords) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(taskComTelId, that.taskComTelId) &&
                Objects.equals(telephoneId, that.telephoneId) &&
                Objects.equals(callTime, that.callTime) &&
                Objects.equals(callSecs, that.callSecs) &&
                Objects.equals(stars, that.stars) &&
                Objects.equals(progress, that.progress) &&
                Objects.equals(note, that.note) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, taskComTelId, telephoneId, callTime, callSecs, stars, progress, note, remarks);
    }
}
