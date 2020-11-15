/**
 * 
 */
package za.co.sindi.sql.query;

import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public interface SelectablePreparedQuery<T> extends SelectableQuery<T>, PreparedQuery {

	public SelectablePreparedQuery<T> setFetchDirection(int direction);
	public SelectablePreparedQuery<T> setFetchSize(int rows);
	public SelectablePreparedQuery<T> setMaxFieldSize(int max);
	public SelectablePreparedQuery<T> setMaxRows(int maxRows);
	public SelectablePreparedQuery<T> setQueryTimeout(int seconds);
	
	public SelectablePreparedQuery<T> setParameter(int parameterIndex, Object value);
	public SelectablePreparedQuery<T> setResultSetHandler(ResultSetHandler<T> handler);
}
