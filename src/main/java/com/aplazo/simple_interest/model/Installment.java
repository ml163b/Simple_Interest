package com.aplazo.simple_interest.model;

import java.util.Date;

public class Installment {
	
	Integer payment_number;
	Double amount;
	Date payment_date;
	
	
	public Installment() {
	}
	
	public Installment(Integer payment_number, Double amount, Date payment_date) {
		super();
		this.payment_number = payment_number;
		this.amount = amount;
		this.payment_date = payment_date;
	}

	public Integer getPayment_number() {
		return payment_number;
	}

	public void setPayment_number(Integer payment_number) {
		this.payment_number = payment_number;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	
}
