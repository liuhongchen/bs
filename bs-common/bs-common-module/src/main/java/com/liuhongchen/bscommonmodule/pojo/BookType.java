package com.liuhongchen.bscommonmodule.pojo;
import java.io.Serializable;

/***
*   
*/
public class BookType implements Serializable {
    //
    private Integer id;
    //
    private String name;
    //get set 方法
    public void setId (Integer  id){
        this.id=id;
    }
    public  Integer getId(){
        return this.id;
    }
    public void setName (String  name){
        this.name=name;
    }
    public  String getName(){
        return this.name;
    }
}
