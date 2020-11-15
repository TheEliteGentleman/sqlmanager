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
public interface BatchQuery extends Query {

	public BatchQuery setFetchDirection(int direction);
	public BatchQuery setFetchSize(int rows);
	public BatchQuery setMaxFieldSize(int max);
	public BatchQuery setMaxRows(int maxRows);
	public BatchQuery setQueryTimeout(int seconds);
	
	public BatchQuery addBatch(String query);
	public BatchQuery clearBatch();
	public BatchQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> generatedKeysResultSetHandler);
	public int[] getBatchUpdatesCount();
	public Object getGeneratedKeys();
}
