package com.gys.fulixcx.mode;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class UserMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "wechat_token", length = 64)
    private String wechatToken;
    @Column(name = "wechat_name", length = 20)
    private String wechatName;
    @Column(name = "wechat_icon", length = 128)
    private String wechatIcon;
    @Column(name = "red_packet", length = 64)
    private String redPacket;
    @Column(name = "money", length = 10)
    private Double Money;
    @Column(name = "waitemoney", length = 10)
    private Double WaiteMoney;
    @Column(name = "superior_wx_token", length = 64)
    private String SuperiorWxToken;
    @Column(name = "commodity_id", length = 32)
    private String CommodityId;
    @Column(name = "shiyong_token", length = 64)
    private String shiyongToken;
    @Column(name = "phone", length = 22)
    private String Phone;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getShiyongToken() {
        return shiyongToken;
    }

    public void setShiyongToken(String shiyong_token) {
        this.shiyongToken = shiyong_token;
    }

    public String getSuperiorWxToken() {
        return SuperiorWxToken;
    }

    public void setSuperiorWxToken(String superiorWxToken) {
        SuperiorWxToken = superiorWxToken;
    }

    public String getCommodityId() {
        return CommodityId;
    }

    public void setCommodityId(String commodityId) {
        CommodityId = commodityId;
    }

    public Double getMoney() {
        return Money;
    }

    public void setMoney(Double money) {
        Money = money;
    }

    public Double getWaiteMoney() {
        return WaiteMoney;
    }

    public void setWaiteMoney(Double waiteMoney) {
        WaiteMoney = waiteMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWechatToken() {
        return wechatToken;
    }

    public void setWechatToken(String wechatToken) {
        this.wechatToken = wechatToken;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatIcon() {
        return wechatIcon;
    }

    public void setWechatIcon(String wechatIcon) {
        this.wechatIcon = wechatIcon;
    }

    public String getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket;
    }
}
