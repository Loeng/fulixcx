package com.gys.fulixcx.mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "call_visenion", schema = "yidaofuli", catalog = "")
public class CallVisenionMode {
    private int id;
    private Integer code;
    private String updateNote;
    private Integer flag;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }



    @Basic
    @Column(name = "code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "update_note")
    public String getUpdateNote() {
        return updateNote;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallVisenionMode that = (CallVisenionMode) o;
        return id == that.id &&
                Objects.equals(code, that.code) &&
                Objects.equals(updateNote, that.updateNote);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, updateNote);
    }
}
