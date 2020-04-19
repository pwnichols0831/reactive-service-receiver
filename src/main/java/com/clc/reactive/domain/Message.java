/**
 * 
 */
package com.clc.reactive.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * This Class is the Message ckass that will receive different message types in the chain.
 * 
 * </p>
 * <p>
 *  The Transaction ID is important to make sure that you have a way to keep track of the
 *  messages that have been processed.
 * </p>
 * @author Dr. Paul W. Nichols
 * @version 0.1-BETA
 *
 */
@Getter @Setter
public class Message {
  private String transactionId;
  private Date timestamp;
  private String sender;
  private String messageBody;
  
}
