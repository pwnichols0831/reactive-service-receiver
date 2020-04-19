/**
 * 
 */
package com.clc.reactive.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clc.reactive.domain.Message;
import com.clc.reactive.entities.RecoveryDB;
import com.clc.reactive.services.RecoveryDBService;
import com.clc.reactive.services.SenderService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * This class contains the Asynchronous Rest Service that will receive a message,
 * store it into the H2 database, and then forward to the next service in the chain.
 * </p>
 * <p>
 * @author Dr. Paul W. Nichols
 * @version 0.1 BETA
 *
 */
@RestController
@RequestMapping("/api/reactiveservice/")
@Slf4j
public class MessageController {
	
	/**
	 * Autowire the dependency for the RecoveryDBService, which contains
	 * the service wrapper for the RecoveryDBRepository
	 */
	@Autowired
	RecoveryDBService service;
	
	/**
	 * The SenderService is a WebFlux WebCLient that will forward the request to the
	 * next service in the service chain.
	 */
	@Autowired
	SenderService sender;
	
    /**
     * 
     * @param message
     * @return
     */
	@PostMapping("receivemessage")
	public Mono<String> receiveMessage(@RequestBody Message message){
		log.info("Receiving Message from Sender");
		return persistMessage(message);
		
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	private  Mono<String> persistMessage(final Message message){
		log.info("Receiving Message from Sender");
		log.info(String.format("Message transaction ID: %s", message.getTransactionId()));
		String result="";
		Mono<String> monoStr=populateRecoveryRecord(message);
		if(monoStr == null) {
			result="record could not be saved.";
		}
		else {
			result="Record was saved.";
		}
		if( result.contains("was saved")) {
			Message msgResult=sender.sendData(message);
				if(msgResult == null) {
					result="NACK -- Request failed.";	
				}
				else {
					result="ACK -- Message was sent to endpoint";
				}
		}
	
	    return Mono.just(result);
	}	
	
		/**
		 * 
		 * @param message
		 * @return
		 */
		
		private Mono<String> populateRecoveryRecord(Message message) {
	    String result="Message was saved to database.";
	    String transactionId=message.getTransactionId();
		String sender =  message.getSender();
		Date timestamp=message.getTimestamp();
		String body=message.getMessageBody();
		RecoveryDB recoveryDb=new RecoveryDB();
		recoveryDb.setDateTime(new Timestamp(timestamp.getTime()));
		recoveryDb.setData(body);
		recoveryDb.setSource(sender);
		recoveryDb.setTransactionId(transactionId);
		try {
			Mono<RecoveryDB> recDb=service.save(recoveryDb);
			if(recDb ==null) {
				result="Record could not be saved.";
			}
		}
		catch(Exception e) {
			log.error("Error in persisting recoveryDB");
			log.error("Exception thrown was:",e.getMessage());
			log.error("Stack Trace:",e);
			result="Error!! Could not save record.";
		}
		
		return Mono.just(result);
  }
		
 	
		
		
	
}
