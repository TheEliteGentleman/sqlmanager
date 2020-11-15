/**
 * 
 */
package za.co.sindi.sql.query;

import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 04 April 2014
 *
 */
public interface BatchPreparedQuery extends BatchQuery, PreparedQuery {

	public BatchPreparedQuery setFetchDirection(int direction);
	public BatchPreparedQuery setFetchSize(int rows);
	public BatchPreparedQuery setMaxFieldSize(int max);
	public BatchPreparedQuery setMaxRows(int maxRows);
	public BatchPreparedQuery setQueryTimeout(int seconds);
	
	public BatchPreparedQuery addBatch(String query);
	public BatchPreparedQuery clearBatch();
	
	public BatchPreparedQuery addBatch();
	public BatchPreparedQuery setParameter(int parameterIndex, Object value);
	public BatchPreparedQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> generatedKeysResultSetHandler);
}
