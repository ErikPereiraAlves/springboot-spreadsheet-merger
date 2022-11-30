package com.alveser.springbootspreadsheetmerger.dao;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: This class is responsible for writting the merged PDF file into the directory
 * the user has chosen when selecting the input files.
 * 
 * This could be easily improved to give the user the ability to choose different folder to save
 * the merged file. 
 */
@Component
public class CommandLineOutputWrite implements OutputWrite {
	
	public Logger log = LogManager.getLogger(getClass());
	
	private  File mergedFile;
	
	public File getMergedFile() {
		return mergedFile;
	}
	
	public boolean start (String outputFileName,String path) {
		boolean success = false;
		String separator = System.getProperty("file.separator");
		
		if(null!=path) {
			path = path.replace("\\", separator);
			path = path.replace("/", separator);
		}
		log.info("File name: "+outputFileName);
		log.info("Directory: "+path);
		File dir = new File(path);
		mergedFile = new File (dir, outputFileName);
		mergedFile.getParentFile().mkdirs(); 
		try {
			mergedFile.createNewFile();
		} catch (IOException e) {
			log.error("Something went wrong. "+e);
		}
		log.info("Files have been merged to file name of: "+mergedFile);
		
		if(null!=mergedFile) {
			success =true;
		}
		
		//TODO: check if file is really saved and saved in the right location
		
		return success;
	}

}
