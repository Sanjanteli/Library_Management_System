package com.example.Library_Management.Model;


import java.sql.Date;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Size;
//
//// Import missing lombok annotation
//import lombok.Data;

@Entity 
//@Data
public class Admin { //Bean validation

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long adminId;
		
		@Column(length = 100, nullable = false)
		private String adminName;
		
		@Column(length = 20, updatable = true)
		@Size(min = 8, max =20,message = "password must be between 8 to 20 characters long.")
		private String adminPassword;
		
		
		@Column(length = 50, updatable = false, unique = true)
		@Email(message = "Email id format is incorrect.")
		private String userEmail;
		
		@Column(updatable = true)
		private Date dob;
		
		@Column(length = 150, updatable = true)
		private String about;
		
		@Column(length = 20, nullable = false, updatable = true)
		private String role;
		
		
		

		public Admin() {
			
		}

		public long getAdminId() {
			return adminId;
		}

		public String getAdminName() {
			return adminName;
		}

		public String getAdminPassword() {
			return adminPassword;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public Date getDob() {
			return dob;
		}

		public String getAbout() {
			return about;
		}

		public String getRole() {
			return role;
		}

		public void setAdminId(long adminId) {
			this.adminId = adminId;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

		public void setAdminPassword(String adminPassword) {
			this.adminPassword = adminPassword;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public void setAbout(String about) {
			this.about = about;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword
					+ ", userEmail=" + userEmail + ", dob=" + dob + ", about=" + about + ", role=" + role + "]";
		}
		
		
		

	}


