package com.liuhongchen.bscommondto.vo;
import com.liuhongchen.bscommonmodule.pojo.Book;

import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class GoodsVo implements Serializable {
	//
	private Integer id;
	//
	private Integer bookId;
	//
	private Integer typeId;
	//
	private Double price;

	private Integer way;

	private String address;

	private String info;

	private Date createTime;

	private Date updateTime;

	private Integer status;

	private Integer sellerId;
	private Integer buyerId;

	private Book bookinfo;


	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Book getBookinfo() {
		return bookinfo;
	}

	public void setBookinfo(Book bookinfo) {
		this.bookinfo = bookinfo;
	}

	public GoodsVo() {
	}


}