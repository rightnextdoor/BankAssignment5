package com.meritamerica.assignment5.Bank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Bank.Service.MeritBank;
import com.meritamerica.assignment5.models.CDOffering;


@RestController
public class CDOfferingController {
	
	@Autowired
	MeritBank service;
	
	List<CDOffering> cdOfferings = new ArrayList<>();
	
	@PostMapping(value = "/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) {
		cdOfferings.add(cdOffering);
		return service.postCDOffering(cdOffering);
	}
	
	@GetMapping(value = "CDOfferings")
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getCDOfferings(){
		return service.getCDOfferingsRepository();
	}
		
	

}
