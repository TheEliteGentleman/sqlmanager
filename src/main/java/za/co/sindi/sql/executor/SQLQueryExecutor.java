/**
 * 
 */
package za.co.sindi.sql.executor;

import za.co.sindi.sql.exception.DatabaseExecutionException;
import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ParameterHandler;
import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public interface SQLQueryExecutor extends SQLExecutor {

	public void executeCall(String call, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public int[] executeBatch(String query, Object[][] parameters) throws DatabaseExecutionException;
	public int[] executeBatchCall(String call, Object[][] parameters) throws DatabaseExecutionException;
	public int[] executeBatchCall(String call, ParameterHandler parameterHandler, Object[][] parameters) throws DatabaseExecutionException;
	public <K> K executeBatchInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler, Object[][] parameters) throws DatabaseExecutionException;
	public <K> K executeInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <K> K executeInsert(String query, int[] columnIndexes, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <K> K executeInsert(String query, String[] columnNames, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeSelect(String query, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeSelect(String query, int resultSetType, int resultSetConcurrency, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> T executeSelect(String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public int executeUpdate(String query) throws DatabaseExecutionException;
	public int executeUpdate(String query, Object... parameters) throws DatabaseExecutionException;
	
}
