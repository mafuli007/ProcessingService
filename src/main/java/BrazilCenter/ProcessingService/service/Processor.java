package BrazilCenter.ProcessingService.service;

import java.io.File;

import BrazilCenter.DaoUtils.dao.Storager;
import BrazilCenter.ProcessingService.Interface.BrazilDataFile;
import BrazilCenter.ProcessingService.err.ProcessException;
import BrazilCenter.ProcessingService.impl.AtmosphericDensityData;
import BrazilCenter.ProcessingService.impl.FluxgateMagnetometerData;
import BrazilCenter.models.FileObj;

/**
 * 
 * @author Fuli Ma
 *
 */
public class Processor extends Thread {

	private Storager storager;
	private FileObj fileObj;

	public Processor(String filename) {
		fileObj = new FileObj();
		fileObj.setName(filename.substring(filename.lastIndexOf(File.separator)));
		fileObj.setPath(filename.substring(0, filename.lastIndexOf(File.separator)));
	}

	public Storager getStorager() {
		return storager;
	}

	public void setStorager(Storager storager) {
		this.storager = storager;
	}

	public void run() {

		/**
		 * 1. parse the file's name, try to find the corresponding file type.
		 */
		BrazilDataFile brazilFile = null;
		String fileFullName = this.fileObj.getPath() + File.separator + this.fileObj.getName();
		if (this.fileObj.getName().contains("Data_Type_1")) {
			brazilFile = new AtmosphericDensityData(fileFullName);
		} else {
			brazilFile = new FluxgateMagnetometerData(fileFullName);
		}

		try {

			/** 2. process the file. */
			brazilFile.process();

			/** 3. store the results in file system and database. */
			this.storager.Store(this.fileObj, this.storager.getRootDir());

		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
