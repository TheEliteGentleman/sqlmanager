/**
 * 
 */
package za.co.sindi.sql.exception;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public class DatabaseExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 767158047314709147L;

	/**
	 * @param message
	 */
	public DatabaseExecutionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DatabaseExecutionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DatabaseExecutionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DatabaseExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
