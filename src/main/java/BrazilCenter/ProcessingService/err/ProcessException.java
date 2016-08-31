package BrazilCenter.ProcessingService.err;


/**
 * Exception that will group all 
 * exceptions throws inside the server actions
 * 
 * @author Fuli Ma
 *
 */
public class ProcessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProcessException(String msg){
		super(msg);
	}

}
