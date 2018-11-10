package com.gys.fulixcx.mode;

import javax.persistence.*;

@Entity
@Table(name = "t_commodity")
public class ShiyongUserMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wche_token", length = 64)
    private String wcheToken;
    @Column(name = "commodity_id", length = 64)
    private String commodityId;
    @Column(name = "creat_time", length = 13)
    private String creatTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getWcheToken() {
        return wcheToken;
    }

    public void setWcheToken(String wcheToken) {
        this.wcheToken = wcheToken;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
}
