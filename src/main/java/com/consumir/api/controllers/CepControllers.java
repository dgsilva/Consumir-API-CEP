package com.consumir.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumir.api.client.CepService;
import com.consumir.api.response.CepResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("webclient")
public class CepControllers {

	private CepService cepService;
	
	@GetMapping("{cep}/json")
	private Mono<CepResponse> findCep(@PathVariable String cep){
		return cepService.findAndCEP(cep);
	}
}
