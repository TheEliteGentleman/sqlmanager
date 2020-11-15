/**
 * 
 */
package za.co.sindi.sql.query;


/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface Query {

	public Query setFetchDirection(int direction);
	public Query setFetchSize(int rows);
	public Query setMaxFieldSize(int max);
	public Query setMaxRows(int maxRows);
	public Query setQueryTimeout(int seconds);
	
	public void execute();	
}
