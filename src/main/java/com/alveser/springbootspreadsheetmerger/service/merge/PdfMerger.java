package com.alveser.springbootspreadsheetmerger.service.merge;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alveser.springbootspreadsheetmerger.dao.CommandLineOutputWrite;

/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: implementation of merger responsible to merge PDF files
 */
@Service
public class PdfMerger implements Merger{
	
	public Logger log = LogManager.getLogger(getClass());
	
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private Timestamp timestamp;
	
	private String mergedFileName ="mergedPDFs.pdf"; //default name
	


	public String getMergedFileName() {
		return mergedFileName;
	}



	public boolean start(Set <File> files) {
		
		timestamp = new Timestamp(System.currentTimeMillis());
		mergedFileName ="mergedPDFs"+sdf1.format(timestamp)+".pdf"; //time specific name
		
		boolean success =true;
		
		try {
	        PDFMergerUtility pdfmerger = new PDFMergerUtility();
	        for (File file : files) {
	            PDDocument document = PDDocument.load(file);
	            pdfmerger.setDestinationFileName(mergedFileName);
	            pdfmerger.addSource(file);
	            pdfmerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
	            document.close();
	        }
	    } catch (IOException e) {
	        log.error("Error to merge files. Error: " + e.getMessage());
	        success =false;
	        
	    }
				
		return success;
		
	}

}
