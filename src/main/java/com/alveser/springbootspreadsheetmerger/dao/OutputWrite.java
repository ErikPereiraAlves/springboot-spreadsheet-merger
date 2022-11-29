package com.alveser.springbootspreadsheetmerger.dao;


/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: Interface containing method signatures for its various possible 
 * future implementations.
 */
public interface OutputWrite {
	
	public boolean start (String outputFileName, String path);

}
