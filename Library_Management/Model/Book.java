package com.example.Library_Management.Model;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DynamicUpdate
//This Library Class which is Blueprint here all Attributes are assigned
public class Book{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String bookName;
	private String authorName;
	private int price;
	private int noCopies;
	private Boolean isPublished;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="authorId")
	 private Author author;
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book(Integer bookId, String bookName, String authorName, int price, int noCopies, Boolean isPublished) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
		this.noCopies = noCopies;
		this.isPublished = isPublished;
	}
	public Book() {
	}
	//Getter and Setter Method

	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}	

	public Boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
	public int getNoCopies() {
		return noCopies;
	}
	public void setNoCopies(int noCopies) {
		this.noCopies = noCopies;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", price="
				+ price + ", noCopies=" + noCopies + ", isPublished=" + isPublished + "]";
	}
}