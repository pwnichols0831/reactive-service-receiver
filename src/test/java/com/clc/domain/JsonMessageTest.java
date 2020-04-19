package com.clc.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.clc.reactive.domain.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMessageTest {
	
	public JsonMessageTest() {
		
	}

	@Test
	public void testJson() {
		ObjectMapper om = new ObjectMapper();
		Message msg= new Message();
		msg.setTransactionId(UUID.randomUUID().toString());
		msg.setMessageBody("This is a test message.");
		msg.setSender("SampleService");
		msg.setTimestamp(new Timestamp(new Date().getTime()));
		try {
			String json=om.writer().withDefaultPrettyPrinter().writeValueAsString(msg);
			System.out.printf("\n %s", json);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
