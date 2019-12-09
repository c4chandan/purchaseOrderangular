package com.purchaseOrder.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "new_Buyer_Table")
public class Buyer {

	@Id
	@GeneratedValue
	private int buyer_Id;

	private String buyer_Name;

	private String email;

	private String buyer_password;

	
	private String address;

	private String mobile_no;

	
	private String isActive;

	
	private LocalDate created_date;

	
	private String created_by;

	
	private String update_by;

	
	private LocalDate update_date;

	
	private String role;


	public int getBuyer_Id() {
		return buyer_Id;
	}


	public void setBuyer_Id(int buyer_Id) {
		this.buyer_Id = buyer_Id;
	}


	public String getBuyer_Name() {
		return buyer_Name;
	}


	public void setBuyer_Name(String buyer_Name) {
		this.buyer_Name = buyer_Name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBuyer_password() {
		return buyer_password;
	}


	public void setBuyer_password(String buyer_password) {
		this.buyer_password = buyer_password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile_no() {
		return mobile_no;
	}


	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public LocalDate getCreated_date() {
		return created_date;
	}


	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}


	public String getCreated_by() {
		return created_by;
	}


	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}


	public String getUpdate_by() {
		return update_by;
	}


	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}


	public LocalDate getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(LocalDate update_date) {
		this.update_date = update_date;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Buyer [buyer_Id=" + buyer_Id + ", buyer_Name=" + buyer_Name + ", email=" + email + ", buyer_password="
				+ buyer_password + ", address=" + address + ", mobile_no=" + mobile_no + ", isActive=" + isActive
				+ ", created_date=" + created_date + ", created_by=" + created_by + ", update_by=" + update_by
				+ ", update_date=" + update_date + ", role=" + role + "]";
	}


}
