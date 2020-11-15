/**
 * 
 */
package za.co.sindi.sql.query;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface UpdatableQuery extends Query {

	public UpdatableQuery setFetchDirection(int direction);
	public UpdatableQuery setFetchSize(int rows);
	public UpdatableQuery setMaxFieldSize(int max);
	public UpdatableQuery setMaxRows(int maxRows);
	public UpdatableQuery setQueryTimeout(int seconds);
	
	public int getUpdateCount();
}
