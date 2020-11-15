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
public interface InsertablePreparedQuery extends UpdatablePreparedQuery, InsertableQuery {

	public InsertablePreparedQuery setFetchDirection(int direction);
	public InsertablePreparedQuery setFetchSize(int rows);
	public InsertablePreparedQuery setMaxFieldSize(int max);
	public InsertablePreparedQuery setMaxRows(int maxRows);
	public InsertablePreparedQuery setQueryTimeout(int seconds);
	
	public InsertablePreparedQuery setParameter(int parameterIndex, Object value);
	public InsertablePreparedQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> handler);
}
