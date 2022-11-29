package com.alveser.springbootspreadsheetmerger.service;

import java.io.File;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alveser.springbootspreadsheetmerger.dao.CommandLineInputRead;
import com.alveser.springbootspreadsheetmerger.dao.CommandLineOutputWrite;
import com.alveser.springbootspreadsheetmerger.service.merge.PdfMerger;
/**
 * 
 * @author Erik Alves Nov 27, 2022
 *
 * This is the entry point for user and program interaction. 
 * This works as a facade between springboot application starter  and business logic.
 *
 */
@Component
public class CommandLineServiceDelegate implements ServiceDelegate {
	
	public Logger log = LogManager.getLogger(getClass());
	
	
	@Autowired
	private CommandLineInputRead read;
	
	@Autowired
	private CommandLineOutputWrite write;
	
	@Autowired
	private PdfMerger merger;
	
	
	public boolean start() {
		
		
		 read.start(); // 1 - read file(s)
		 
		 Set <File> set = read.getSet();
		 
		 if(null == set || set.size() ==0) {
			 log.info("List of files is empty, please try again.");
			 return false;
		 }
		 log.info("STEP 1- File(s) have been read from input directory.");		 
		 boolean merged = merger.start(set); //2 -merge file(s)
		 
		 if(merged) {
			 log.info("STEP 2 - File(s) have been merged into a sigle one.");
			 boolean written = write.start( merger.getMergedFileName(),read.getPath()); // 3 write merged file
			 
			 if(written) {
				 log.info("STEP 3 - Merged file has been written to same input directory.");
			 }
			 else {
				 return false;
			 }
		 }
		 else {
			 return false;
		 }
		
		
		 return true;
	}
	

}
