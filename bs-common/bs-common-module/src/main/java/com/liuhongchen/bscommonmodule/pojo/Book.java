package com.liuhongchen.bscommonmodule.pojo;
import java.io.Serializable;
import java.util.Date;

/***
*   
*/
public class Book implements Serializable {
	//
	private Integer id;
	//
	private String isbn10;
	//
	private String title;
	//
	private String subtitle;
	//
	private String pic;
	//
	private String author;
	//
	private String publisher;
	//
	private String pubplace;
	//
	private String pubdate;
	//
	private Integer page;

	private Double price;

	private String binding;

	private String isbn13;

	private String keyword;

	private String edition;

	private String impression;

	private String language;

	public Book() {
	}

	public Book(Integer id, String isbn10, String title, String subtitle, String pic, String author,String publisher, String pubplace, String pubdate, Integer page, Double price, String binding, String isbn13, String keyword, String edition, String impression, String language) {
		this.id = id;
		this.isbn10 = isbn10;
		this.title = title;
		this.subtitle = subtitle;
		this.pic = pic;
		this.author = author;
		this.publisher = publisher;
		this.pubplace = pubplace;
		this.pubdate = pubdate;
		this.page = page;
		this.price = price;
		this.binding = binding;
		this.isbn13 = isbn13;
		this.keyword = keyword;
		this.edition = edition;
		this.impression = impression;
		this.language = language;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubplace() {
		return pubplace;
	}

	public void setPubplace(String pubplace) {
		this.pubplace = pubplace;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}