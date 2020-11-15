/**
 * 
 */
package za.co.sindi.sql.query;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public interface UpdatablePreparedQuery extends UpdatableQuery, PreparedQuery {

	public UpdatablePreparedQuery setFetchDirection(int direction);
	public UpdatablePreparedQuery setFetchSize(int rows);
	public UpdatablePreparedQuery setMaxFieldSize(int max);
	public UpdatablePreparedQuery setMaxRows(int maxRows);
	public UpdatablePreparedQuery setQueryTimeout(int seconds);
	
	public UpdatablePreparedQuery setParameter(int parameterIndex, Object value);
}
