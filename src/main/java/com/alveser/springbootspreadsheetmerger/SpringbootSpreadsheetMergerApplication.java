package com.alveser.springbootspreadsheetmerger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alveser.springbootspreadsheetmerger.service.CommandLineServiceDelegate;


/**
 * 
 * @author Erik Alves Nov 27, 2022
 *
 * This is the entry point for the micro service
 * 
 * The reason to use Springboot is that this microservice may in the future
 * not only work as a command line application but it can be extended
 * to become a web application or even a REST API endpoint (open close design principle)
 *
 */
@SpringBootApplication
public class SpringbootSpreadsheetMergerApplication implements CommandLineRunner {
	
	public Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	CommandLineServiceDelegate service;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpreadsheetMergerApplication.class, args);
    }
 
    @Override
    public void run(String... args) {
       boolean success = service.start();
       
       if (success)
    	   log.info("Process finished successfully. Please check messages for details. Stopping running process. ");
       else
    	   log.info("Process finished with issues. Please check messages for details. Stopping running process. "); 
      
       
       System.exit(0);
    }

}
