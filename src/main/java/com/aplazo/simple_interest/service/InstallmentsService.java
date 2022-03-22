package com.aplazo.simple_interest.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aplazo.simple_interest.exception.APIException;
import com.aplazo.simple_interest.model.CreditRequest;
import com.aplazo.simple_interest.model.Installment;
import com.aplazo.simple_interest.util.InstallmentsConstant;

@Service
public class InstallmentsService {
	
	public List<Installment> calculateInstallments(CreditRequest creditRQ) throws APIException{	
		
		List<Installment> installments = new ArrayList<Installment>();
		Calendar installmentWeek = Calendar.getInstance();
			
		//Convert data to BigDecimal for make operation more precise
		BigDecimal amount = BigDecimal.valueOf(creditRQ.getAmount());
		BigDecimal terms  = BigDecimal.valueOf(creditRQ.getTerms());
		BigDecimal rate   = BigDecimal.valueOf(creditRQ.getRate());
		BigDecimal totalAmount = null;
		BigDecimal paymentsAmount = null;
		
		//validate the income data
		validateInputs(amount, terms, rate);	
	
		//Simple Interest Formula = Amount + (Amount*rate*terms)
		rate = rate.divide(BigDecimal.valueOf(100));
		totalAmount = amount.add(
						amount
						.multiply(rate)
						.multiply(terms)
					  ).setScale(2,RoundingMode.HALF_EVEN);
		
		paymentsAmount = totalAmount.divide(terms).setScale(2,RoundingMode.HALF_EVEN);
			
		//split payments in weeks
		for(int i = 1; i<= creditRQ.getTerms(); i++){

			//Assuming It will start to pay next week from get the credit
			installmentWeek.add(Calendar.WEEK_OF_YEAR,1);
			
			Installment installment = new Installment(i,paymentsAmount.doubleValue(),installmentWeek.getTime());
			installments.add(installment);
		}
		
		return installments;
		
	}

	private void validateInputs(BigDecimal amount, BigDecimal terms, BigDecimal rate) throws APIException {
		
		List<String> errorMessages = new ArrayList<String>();
			
		if(amount.compareTo(BigDecimal.valueOf(1)) <= 0 ||
				amount.compareTo(BigDecimal.valueOf(999999)) >= 0 ){
			errorMessages.add("The amount should be more than $1.00, the max should be lesser than $999,999.00");
		}
		if(terms.compareTo(BigDecimal.valueOf(4)) < 0 ||
				terms.compareTo(BigDecimal.valueOf(52)) > 0 ){
			errorMessages.add("The max terms (weeks) were the payment can be paid is 52, the minimum should be 4");
		}
		if(rate.compareTo(BigDecimal.valueOf(1)) <= 0 ||
				rate.compareTo(BigDecimal.valueOf(100)) >= 0 ){
			errorMessages.add("The rate should bigger than 1%, lesser than 100%");		
		}
		if(null != errorMessages && !errorMessages.isEmpty()){
			throw new APIException(InstallmentsConstant.INVALID_INPUT_DATA.getMessage() + "; " + String.join(", ",errorMessages),
					InstallmentsConstant.INVALID_INPUT_DATA.getCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		}		
	}
}
