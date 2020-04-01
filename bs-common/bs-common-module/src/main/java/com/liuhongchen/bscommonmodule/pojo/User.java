package com.liuhongchen.bscommonmodule.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   
*/
public class User implements Serializable {
    //
    private Integer id;
    //
    private String phone;
    //
    private String password;
    //
    private String wxUserId;
    //
    private String realName;
    //
    private String nickname;
    //
    private Integer gender;
    //
    private Date birthday;
    //
    private Date createdTime;
    //
    private Date updatedTime;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setPhone (String  phone){
        this.phone=phone;
    }
    public  String getPhone(){
        return this.phone;
    }
    public void setPassword (String  password){
        this.password=password;
    }
    public  String getPassword(){
        return this.password;
    }
    public void setWxUserId (String  wxUserId){
        this.wxUserId=wxUserId;
    }
    public  String getWxUserId(){
        return this.wxUserId;
    }
    public void setRealName (String  realName){
        this.realName=realName;
    }
    public  String getRealName(){
        return this.realName;
    }
    public void setNickname (String  nickname){
        this.nickname=nickname;
    }
    public  String getNickname(){
        return this.nickname;
    }
    public void setGender (Integer  gender){
        this.gender=gender;
    }
    public  Integer getGender(){
        return this.gender;
    }
    public void setBirthday (Date  birthday){
        this.birthday=birthday;
    }
    public  Date getBirthday(){
        return this.birthday;
    }
    public void setCreatedTime (Date  createdTime){
        this.createdTime=createdTime;
    }
    public  Date getCreatedTime(){
        return this.createdTime;
    }
    public void setUpdatedTime (Date  updatedTime){
        this.updatedTime=updatedTime;
    }
    public  Date getUpdatedTime(){
        return this.updatedTime;
    }
}
