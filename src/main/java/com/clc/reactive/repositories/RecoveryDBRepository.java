/**
 * 
 */
package com.clc.reactive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clc.reactive.entities.RecoveryDB;

/**
 * <p>
 * <b>Description:</b>
 * </p>
 * <p>
 * This is an interface for the RecoveryDB Repository that extends JPARepository
 * </p>
 * @author Dr. Paul W. Nichols
 * @version 0.1-BETA
 *
 */
public interface RecoveryDBRepository extends JpaRepository<RecoveryDB, Integer> {

}
