package BrazilCenter.ProcessingService.Interface;

/**
 * Generic Brazil Data Process Interface.
 * @author Fuli Ma
 *
 */
public interface IBrazilDataPrcoess {

	/**
	 * rename the source file.
	 */
	public void fileReName( );

	/**
	 * standardize the file's head content.
	 */
	public void processHeadContent( );

	
}
