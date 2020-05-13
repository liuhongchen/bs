package com.liuhongchen.bscommonmodule.pojo;

import java.util.List;

public class Money {
    private Integer id;

    private Integer userId;

    private Double money;

    private List<MoneyLog> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public List<MoneyLog> getList() {
        return list;
    }

    public void setList(List<MoneyLog> list) {
        this.list = list;
    }
}
