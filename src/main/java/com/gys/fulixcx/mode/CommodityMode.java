package com.gys.fulixcx.mode;

import javax.persistence.*;

@Entity
@Table(name = "t_commodity")
public class CommodityMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "commodity_id", length = 64)
    private String commodityId;
    @Column(name = "commodity_name", length = 64)
    private String commodityName;
    @Column(name = "commodity_img", length = 13)
    private String commodityImg;
    @Column(name = "commodity_money", length = 10)
    private Double commodityMoney;
    @Column(name = "commodity_text", length = 10)
    private String commodityText;

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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public Double getCommodityMoney() {
        return commodityMoney;
    }

    public void setCommodityMoney(Double commodityMoney) {
        this.commodityMoney = commodityMoney;
    }

    public String getCommodityText() {
        return commodityText;
    }

    public void setCommodityText(String commodityText) {
        this.commodityText = commodityText;
    }
}
