package com.aplazo.simple_interest.controller;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplazo.simple_interest.exception.APIException;
import com.aplazo.simple_interest.model.CreditRequest;
import com.aplazo.simple_interest.model.Installment;
import com.aplazo.simple_interest.model.PetitionsHistoric;
import com.aplazo.simple_interest.repository.petitionsHistoricRepository;
import com.aplazo.simple_interest.service.InstallmentsService;
import com.google.gson.Gson;


@RestController
@RequestMapping("/simpleInterest")
public class InstallmentsController {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	InstallmentsService installmentService;
	
	@Autowired
	petitionsHistoricRepository petHistRepository;
	
	  @PostMapping("/payments")
	  public ResponseEntity<List<Installment>> getInstallments(@RequestBody CreditRequest creditRQ) throws APIException {
		 		  
		  	logger.info("Empezando getInstallments");
			List<Installment> installments = installmentService.calculateInstallments(creditRQ);

		    if (null == installments || installments.isEmpty()){
		    	return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		  	}

		    //Save Request & Ressponse in DB
		    String jsonRequest = new Gson().toJson(creditRQ);
		    String jsonResponse = new Gson().toJson(installments);
		    Calendar now = Calendar.getInstance();
		    PetitionsHistoric petHist = new PetitionsHistoric(jsonRequest,jsonResponse,now.getTime());
			petHistRepository.save(petHist);
			
		    return new ResponseEntity<>(installments, HttpStatus.OK);			    
		 
	  }
	
	  @GetMapping("/payments")
	  public ResponseEntity<List<PetitionsHistoric>> getAllPetitions() throws APIException{
			  
		  	logger.info("Empezando getAllPetitions");
			List<PetitionsHistoric> petHist = petHistRepository.findAll();				
		    return new ResponseEntity<>(petHist, HttpStatus.OK);
		
	  }
	  
	  @DeleteMapping("/payments")
	  public ResponseEntity<?> deleteAllPetitions() throws APIException{
			  
		  	logger.info("Empezando deleteAllPetitions");
			petHistRepository.deleteAllInBatch();		
		    return new ResponseEntity<>(HttpStatus.OK);
		
	  }
	
	

}
