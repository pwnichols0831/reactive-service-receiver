/**
 * 
 */
package com.clc.reactive.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.clc.reactive.entities.RecoveryDB;
import com.clc.reactive.repositories.RecoveryDBRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * This service is a wrapper for the RecoveryDBRepository and exposes the
 * methods in this Spring Data Interface 
 * </p>
 * @author pnichols
 *
 */
@Service
@Transactional
@Slf4j
public class RecoveryDBService {
	
	@Autowired
	RecoveryDBRepository repository;

	public Mono<RecoveryDB> save(RecoveryDB recover) {
        return Mono.just(repository.save(recover));
    }
	
	public Mono<String> delete(int id){
		String result="Record was deleted successfully";
		repository.deleteById(id);
		return Mono.just(result);
	}

	
}
