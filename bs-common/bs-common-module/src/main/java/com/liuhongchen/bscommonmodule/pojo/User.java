package com.liuhongchen.bscommonmodule.pojo;

public class User {

    private Integer id;
    private String phone;
    private String password;

    public User() {
    }

    public User(Integer id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
