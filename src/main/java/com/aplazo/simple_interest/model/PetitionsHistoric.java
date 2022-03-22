package com.aplazo.simple_interest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.sql.JoinType;

import com.vladmihalcea.hibernate.type.json.JsonType;


@Entity
@Table(name = "petititions_historic")
@TypeDef(name = "json", typeClass = JsonType.class)
public class PetitionsHistoric {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "request")
	private String request;
	
	@Type(type = "json")
	@Column(name = "response", columnDefinition = "json")
	private String response;
	
	@Column(name = "date_request")
	private Date dateRequest;

	public PetitionsHistoric(){
		
	}
	
	public PetitionsHistoric(String request, String response, Date dateRequest){
		this.request = request;
		this.response = response;
		this.dateRequest = dateRequest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getDateRequest() {
		return dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}

	@Override
	public String toString() {
		return "petitionHistoric [id=" + id + ", request=" + request + ", response=" + response + "]";
	}

}
