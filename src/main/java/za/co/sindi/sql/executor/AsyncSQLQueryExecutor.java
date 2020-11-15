/**
 * 
 */
package za.co.sindi.sql.executor;

import java.util.concurrent.Future;

import za.co.sindi.sql.exception.DatabaseExecutionException;
import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ParameterHandler;
import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public interface AsyncSQLQueryExecutor extends SQLExecutor {

	public Future<Void> executeCall(String call, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, int resultSetType, int resultSetConcurrency, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, int resultSetType, int resultSetConcurrency, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException;
	public Future<int[]> executeBatch(String query, Object[][] parameters) throws DatabaseExecutionException;
	public Future<int[]> executeBatchCall(String call, Object[][] parameters) throws DatabaseExecutionException;
	public Future<int[]> executeBatchCall(String call, ParameterHandler parameterHandler, Object[][] parameters) throws DatabaseExecutionException;
	public <K> Future<K> executeBatchInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler, Object[][] parameters) throws DatabaseExecutionException;
	public <K> Future<K> executeInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <K> Future<K> executeInsert(String query, int[] columnIndexes, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <K> Future<K> executeInsert(String query, String[] columnNames, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeSelect(String query, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeSelect(String query, int resultSetType, int resultSetConcurrency, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public <T> Future<T> executeSelect(String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException;
	public Future<Integer> executeUpdate(String query) throws DatabaseExecutionException;
	public Future<Integer> executeUpdate(String query, Object... parameters) throws DatabaseExecutionException;
	
}
