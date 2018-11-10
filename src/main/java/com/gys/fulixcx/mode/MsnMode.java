package com.gys.fulixcx.mode;
import javax.persistence.*;

@Entity
@Table(name = "phone_to_msg")
public class MsnMode {
    @Id
    private int id;
    @Column(name = "partner_key", length = 64)
    private String partner_key;
    @Column(name = "out_id", length = 64)
    private String out_id;
    @Column(name = "sub_id")
    private Long sub_id;
    @Column(name = "call_id", length = 64)
    private String call_id;
    @Column(name = "call_type")
    private int call_type;
    @Column(name = "origin_no", length = 64)
    private String origin_no;
    @Column(name = "phone_no", length = 64)
    private String phone_no;
    @Column(name = "secret_no", length = 64)
    private String secret_no;
    @Column(name = "peer_no", length = 64)
    private String peer_no;
    @Column(name = "release_dir")
    private int release_dir;
    @Column(name = "release_cause")
    private int release_cause;
    @Column(name = "start_time", length = 64)
    private String start_time;
    @Column(name = "release_time", length = 64)
    private String release_time;
    @Column(name = "call_time", length = 64)
    private String call_time;
    @Column(name = "ring_time", length = 64)
    private String ring_time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartner_key() {
        return partner_key;
    }

    public void setPartner_key(String partner_key) {
        this.partner_key = partner_key;
    }

    public String getOut_id() {
        return out_id;
    }

    public void setOut_id(String out_id) {
        this.out_id = out_id;
    }

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }

    public String getCall_id() {
        return call_id;
    }

    public void setCall_id(String call_id) {
        this.call_id = call_id;
    }

    public int getCall_type() {
        return call_type;
    }

    public void setCall_type(int call_type) {
        this.call_type = call_type;
    }

    public String getOrigin_no() {
        return origin_no;
    }

    public void setOrigin_no(String origin_no) {
        this.origin_no = origin_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getSecret_no() {
        return secret_no;
    }

    public void setSecret_no(String secret_no) {
        this.secret_no = secret_no;
    }

    public String getPeer_no() {
        return peer_no;
    }

    public void setPeer_no(String peer_no) {
        this.peer_no = peer_no;
    }

    public int getRelease_dir() {
        return release_dir;
    }

    public void setRelease_dir(int release_dir) {
        this.release_dir = release_dir;
    }

    public int getRelease_cause() {
        return release_cause;
    }

    public void setRelease_cause(int release_cause) {
        this.release_cause = release_cause;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getRing_time() {
        return ring_time;
    }

    public void setRing_time(String ring_time) {
        this.ring_time = ring_time;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"partner_key\":\"" + partner_key + "\"" +
                ",\"out_id\":\"" + out_id + "\"" +
                ",\"sub_id\":" + sub_id +
                ",\"call_id\":\"" + call_id + "\"" +
                ",\"call_type\":" + call_type +
                ",\"origin_no\":\"" + origin_no + "\"" +
                ",\"phone_no\":\"" + phone_no + "\"" +
                ",\"secret_no\":\"" + secret_no + "\"" +
                ",\"peer_no\":\"" + peer_no + "\"" +
                ",\"release_dir\":" + release_dir +
                ",\"release_cause\":" + release_cause +
                ",\"start_time\":\"" + start_time + "\"" +
                ",\"release_time\":\"" + release_time + "\"" +
                ",\"call_time\":\"" + call_time + "\"" +
                ",\"ring_time\":\"" + ring_time + "\"" +
                "}";
    }
}
