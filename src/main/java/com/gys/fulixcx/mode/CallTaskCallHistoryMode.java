package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_task_call_history", schema = "yidaofuli", catalog = "")
public class CallTaskCallHistoryMode {
    private int id;
    private Integer taskPhoneId;
    private String remarks;
    private Integer schedule;
    private Integer star;
    private String dialTime;
    private Integer converseTime;

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
    @Column(name = "task_phone_id")
    public Integer getTaskPhoneId() {
        return taskPhoneId;
    }

    public void setTaskPhoneId(Integer taskPhoneId) {
        this.taskPhoneId = taskPhoneId;
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
    @Column(name = "schedule")
    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    @Basic
    @Column(name = "star")
    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    @Basic
    @Column(name = "dial_time")
    public String getDialTime() {
        return dialTime;
    }

    public void setDialTime(String dialTime) {
        this.dialTime = dialTime;
    }

    @Basic
    @Column(name = "converse_time")
    public Integer getConverseTime() {
        return converseTime;
    }

    public void setConverseTime(Integer converseTime) {
        this.converseTime = converseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallTaskCallHistoryMode that = (CallTaskCallHistoryMode) o;
        return id == that.id &&
                Objects.equals(taskPhoneId, that.taskPhoneId) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(star, that.star) &&
                Objects.equals(dialTime, that.dialTime) &&
                Objects.equals(converseTime, that.converseTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, taskPhoneId, remarks, schedule, star, dialTime, converseTime);
    }
}
