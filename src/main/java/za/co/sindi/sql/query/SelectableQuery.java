/**
 * 
 */
package za.co.sindi.sql.query;

import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface SelectableQuery<T> extends Query {

	public SelectableQuery<T> setFetchDirection(int direction);
	public SelectableQuery<T> setFetchSize(int rows);
	public SelectableQuery<T> setMaxFieldSize(int max);
	public SelectableQuery<T> setMaxRows(int maxRows);
	public SelectableQuery<T> setQueryTimeout(int seconds);
	
	public SelectableQuery<T> setResultSetHandler(ResultSetHandler<T> handler);
	public T getResult();
}
