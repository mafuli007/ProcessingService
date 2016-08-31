package BrazilCenter.ProcessingService.Interface;

import BrazilCenter.ProcessingService.err.ProcessException;

/**
 * Generic file definition. 
 * @author Fuli Ma
 *
 */
public abstract class BrazilDataFile {
	
	private String fileName;	// source file's name.
	private String resultFileName; // the processed file's name.
	
	public BrazilDataFile(String filename){
		this.fileName = filename;
		this.resultFileName = this.fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getResultFileName() {
		return resultFileName;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

	/** handle the source files.  */
	public void process() throws ProcessException{

	}
}
