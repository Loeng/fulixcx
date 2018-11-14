package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tk_task_com_tel", schema = "yidaofuli", catalog = "")
public class TkTaskComTel {
    private String id;
    private String taskId;
    private String comTelId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "task_id")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "com_tel_id")
    public String getComTelId() {
        return comTelId;
    }

    public void setComTelId(String comTelId) {
        this.comTelId = comTelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TkTaskComTel that = (TkTaskComTel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(comTelId, that.comTelId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, taskId, comTelId);
    }
}
