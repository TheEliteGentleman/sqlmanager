/**
 * 
 */
package za.co.sindi.sql.exception;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public class QueryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2158523273983467381L;

//	/**
//	 * 
//	 */
//	public QueryException() {
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param message
	 */
	public QueryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public QueryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public QueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
