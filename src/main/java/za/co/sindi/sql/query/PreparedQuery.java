/**
 * 
 */
package za.co.sindi.sql.query;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface PreparedQuery extends Query {

	public PreparedQuery setFetchDirection(int direction);
	public PreparedQuery setFetchSize(int fetchSize);
	public PreparedQuery setMaxFieldSize(int maxFieldSize);
	public PreparedQuery setMaxRows(int maxRows);
	public PreparedQuery setQueryTimeout(int seconds);
	
	public PreparedQuery setParameter(int parameterIndex, Object value);
}
