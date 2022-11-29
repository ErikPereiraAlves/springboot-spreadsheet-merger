package com.alveser.springbootspreadsheetmerger.dao;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: This class is responsible for reading user's command line input. 
 */
@Component
public class CommandLineInputRead implements InputRead {
	
	public Logger log = LogManager.getLogger(getClass());
	
	private String path;

	private Set <File> set;
	
	public Set<File> getSet() {
		return set;
	}
	
	public String getPath() {
		return path;
	}


	public void start () {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter absolute path for files here (Ex: C:\\myfiles): ");
				
		path = scanner.next();
		
		if(null == path || path.trim().length()==0) {
			log.info("Please choose a valid directory and try again.");
			System.exit(0);
		}
		
		scanner.close();
		
		search();
	}
	
	public void search() {
		
		log.info("List of all files in user specified directory:");  
	    log.info("Path : "+path);  
		
		//Creating a File object for directory
	    File directoryPath = new File(path);
	    //Creating filter for directories files
	    FileFilter fileFilter = new FileFilter(){
	         public boolean accept(File dir) {          
	            if (dir.isFile()) {
	               return true;
	            } else {
	               return false;
	            }
	         }
	      };        
	      File[] list = directoryPath.listFiles(fileFilter);
	      set = new HashSet<>();
	      for(File fileName : list) {
	         log.info(fileName);
	         
	         //this will prevent adding non PDF files and previous merged files into later merging operations
	         if(fileName.getName().toLowerCase().contains("pdf") && !fileName.getName().toLowerCase().contains("merged")) {
	        	 set.add(fileName);
	         }
	      } 
	      
	      log.info("List of the PDF files in the specified directory:"+set);  
	}

}
