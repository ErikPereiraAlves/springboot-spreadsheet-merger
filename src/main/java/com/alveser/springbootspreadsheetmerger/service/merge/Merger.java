package com.alveser.springbootspreadsheetmerger.service.merge;

import java.io.File;
import java.util.Set;

/**
 * 
 * @author Erik Alves Nov 29, 2022
 *
 * Description: Interface containing method signatures for its various possible 
 * future implementations.
 */
public interface Merger {
	
	public boolean start(Set <File> files);

}
