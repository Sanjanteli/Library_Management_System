package com.example.Library_Management.Model;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "User_table")
@DynamicUpdate
public class User {

		
	@Id
		private Integer userID;
		private String userName;
		private LocalDate date;
		private String userEmail;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="bookId")
		private Book bookDetails;

		public Integer getUserID() {
			return userID;
		}

		public String getUserName() {
			return userName;
		}

		public LocalDate getDate() {
			return date;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public Book getBookDetails() {
			return bookDetails;
		}


		public void setUserID(Integer userID) {
			this.userID = userID;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public void setDate(LocalDate date) {
			this.date = date;
		}


		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}


		public void setBookDetails(Book bookDetails) {
			this.bookDetails = bookDetails;
		}

}