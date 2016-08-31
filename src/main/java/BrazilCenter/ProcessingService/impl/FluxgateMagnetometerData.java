package BrazilCenter.ProcessingService.impl;

import BrazilCenter.ProcessingService.Interface.BrazilDataFile;
import BrazilCenter.ProcessingService.Interface.IBrazilDataPrcoess;
import BrazilCenter.ProcessingService.err.ProcessException;

/**
 * Example Data Type
 * @author Fuli Ma
 *
 */
public class FluxgateMagnetometerData extends BrazilDataFile implements IBrazilDataPrcoess {

	public FluxgateMagnetometerData(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	/** must be implemented. */
	public void fileReName() {
		// TODO Auto-generated method stub
		String newFileName = "New_File_Name";
		this.setResultFileName(newFileName);
	}

	/** must be implemented. */
	public void processHeadContent() {
		// TODO Auto-generated method stub

		/** 1. open file */

		/** 2. modify file's head content. */

		/** 3. close file. */
	}

	@Override
	public void process() throws ProcessException{

		// TODO Auto-generated method stub
		/** 1. rename file */
		this.fileReName();

		/** 2. standardize file's head content. */
		this.processHeadContent();

		
		/**
		 * 3. if you have defined other functions, they should be run at here, before
		 * or after.
		 */
	}

}
