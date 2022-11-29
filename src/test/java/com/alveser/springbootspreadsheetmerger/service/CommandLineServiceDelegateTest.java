package com.alveser.springbootspreadsheetmerger.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.alveser.springbootspreadsheetmerger.SpringbootSpreadsheetMergerApplication;

/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: Integration test for the command line PDF file(s) merging operation.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE,classes = SpringbootSpreadsheetMergerApplication.class)
@AutoConfigureMockMvc
public class CommandLineServiceDelegateTest {
	
	public Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	CommandLineServiceDelegate service;

	@Test
	public void test() {
		
		boolean success = service.start();
		
		assertTrue(success);
		
	}

}
