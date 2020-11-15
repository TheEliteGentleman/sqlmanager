/**
 * 
 */
package za.co.sindi.sql.query;

import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface CallableQuery extends PreparedQuery {

	public CallableQuery setFetchDirection(int direction);
	public CallableQuery setFetchSize(int fetchSize);
	public CallableQuery setMaxFieldSize(int maxFieldSize);
	public CallableQuery setMaxRows(int maxRows);
	public CallableQuery setQueryTimeout(int seconds);
	public CallableQuery setParameter(int parameterIndex, Object value);
	
	public CallableQuery setParameter(String parameterName, Object value, int sqlType);
	public CallableQuery setCallableStatementHandler(CallableStatementHandler<Object> handler);
	public CallableQuery setResultSetHandler(ResultSetHandler<Object> handler);
	public int getUpdateCount();
	public Object getResultSetValue();
	public Object getCallableValue();
}
