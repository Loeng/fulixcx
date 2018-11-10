package com.gys.fulixcx.mode;

import javax.persistence.*;

@Entity
@Table(name = "red_packet")
public class RedMode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "superior_wx_token", length = 64)
    private String superiorWxToken;
    @Column(name = "user_wx_token", length = 64)
    private String userWxToken;
    @Column(name = "creat_time", length = 13)
    private String creatTime;
    @Column(name = "money", length = 10)
    private Double Money;
    @Column(name = "superior_money", length = 10)
    private Double superiorMoney;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuperiorWxToken() {
        return superiorWxToken;
    }

    public void setSuperiorWxToken(String superiorWxToken) {
        this.superiorWxToken = superiorWxToken;
    }

    public String getUserWxToken() {
        return userWxToken;
    }

    public void setUserWxToken(String userWxToken) {
        this.userWxToken = userWxToken;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Double getMoney() {
        return Money;
    }

    public void setMoney(Double money) {
        Money = money;
    }

    public Double getSuperiorMoney() {
        return superiorMoney;
    }

    public void setSuperiorMoney(Double superiorMoney) {
        this.superiorMoney = superiorMoney;
    }
}
