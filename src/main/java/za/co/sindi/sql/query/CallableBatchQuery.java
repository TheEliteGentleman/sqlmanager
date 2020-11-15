/**
 * 
 */
package za.co.sindi.sql.query;

/**
 * @author Bienfait Sindi
 * @since 04 April 2014
 *
 */
public interface CallableBatchQuery extends BatchPreparedQuery {

	public CallableBatchQuery setFetchDirection(int direction);
	public CallableBatchQuery setFetchSize(int rows);
	public CallableBatchQuery setMaxFieldSize(int max);
	public CallableBatchQuery setMaxRows(int maxRows);
	public CallableBatchQuery setQueryTimeout(int seconds);
	public CallableBatchQuery addBatch(String query);
	public CallableBatchQuery clearBatch();
	public CallableBatchQuery addBatch();
	public CallableBatchQuery setParameter(int parameterIndex, Object value);
	public CallableBatchQuery setParameter(String parameterName, Object value, int sqlType);
}
