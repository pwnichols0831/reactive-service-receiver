/**
 * 
 */
package com.clc.reactive.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.clc.reactive.domain.Message;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * This is a Sender Service for sending the request to the next chain in the process.
 * </p>
 * @author Dr. Paul W. Nichols
 * @version 0.1-BETA
 *
 */
@Service
@Slf4j
public class SenderService {
	private final WebClient webClient;
	@Value("${sender.endpoint}")
	private String endpoint;
	
	/**
	 * 
	 */
	public SenderService() {
		 this.webClient = WebClient.builder()
			      .baseUrl(endpoint)
			      .defaultCookie("cookieKey", "cookieValue", "teapot", "amsterdam")
			      .defaultCookie("secretToken", UUID.randomUUID().toString())
			      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			      .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
			      .build();
			  

	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Message sendData(Message data){
		log.info("Entering sendData");
		log.info(String.format("Data Transaction ID: %s",data.getTransactionId()));
		 return this.webClient
	      .post()
	      .uri(endpoint)
	      .body(Mono.just(data), Message.class)
	      .retrieve()
	      .bodyToMono(Message.class)
	      .block();
	 
		   
       
   }
	  
   
	
}
