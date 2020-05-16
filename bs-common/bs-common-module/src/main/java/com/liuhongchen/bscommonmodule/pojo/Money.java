package com.liuhongchen.bscommonmodule.pojo;

import java.util.List;

public class Money {
    private String id;

    private Double money;

    private Double paid;


    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    private List<MoneyLog> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
