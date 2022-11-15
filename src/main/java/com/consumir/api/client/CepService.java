package com.consumir.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.consumir.api.response.CepResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CepService {
	
	private final WebClient webClient;

	public CepService(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://viacep.com.br/ws/").build(); 
	}
	
	public Mono<CepResponse> findAndCEP(String cep){	
		log.info("buscando o cep  [{}]", cep);
		return webClient
				.get()
				.uri(cep + "/json/")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new RuntimeException("Verifique os parametros no formatos")))
				.bodyToMono(CepResponse.class);
				
	}
	
	

}
