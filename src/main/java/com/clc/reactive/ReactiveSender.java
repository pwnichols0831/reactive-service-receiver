/**
 * 
 */
package com.clc.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pnichols
 *
 */
@SpringBootApplication
@Slf4j
public class ReactiveSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReactiveSender.class, args);
	}

}
