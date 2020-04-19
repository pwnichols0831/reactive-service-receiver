/**
 * 
 */
package com.clc.reactive.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * THis is the entityu class for the RecoveryDB H2 table.
 * </p>
 * @author Dr. Paul W. Nichols 
 * @version 0.1-BETA
 */
@Entity
@Table(name="recovery_db")
@Getter @Setter
public class RecoveryDB {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="datetime")
	Timestamp dateTime;
	@Column(name="transactionid")
	String transactionId;
	String source;
	String data;
	

}
