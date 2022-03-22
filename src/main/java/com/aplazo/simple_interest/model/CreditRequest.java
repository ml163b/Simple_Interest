package com.aplazo.simple_interest.model;


public class CreditRequest {

	Double amount;
	Integer terms;
	Double rate;
	
	public CreditRequest(){
		
	}
	
	public CreditRequest(Double amount, Integer terms, Double rate) {
		this.amount = amount;
		this.terms = terms;
		this.rate = rate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTerms() {
		return terms;
	}

	public void setTerms(Integer terms) {
		this.terms = terms;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
