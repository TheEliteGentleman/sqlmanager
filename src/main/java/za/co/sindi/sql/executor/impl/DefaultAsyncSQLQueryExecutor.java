/**
 * 
 */
package za.co.sindi.sql.executor.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import za.co.sindi.sql.exception.DatabaseExecutionException;
import za.co.sindi.sql.executor.AbstractAsyncSQLExecutor;
import za.co.sindi.sql.executor.AsyncSQLQueryExecutor;
import za.co.sindi.sql.executor.SQLQueryExecutor;
import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ParameterHandler;
import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 06 April 2014
 *
 */
public class DefaultAsyncSQLQueryExecutor extends AbstractAsyncSQLExecutor<SQLQueryExecutor> implements AsyncSQLQueryExecutor {

	/**
	 * @param executorService
	 * @param sqlExecutor
	 */
	public DefaultAsyncSQLQueryExecutor(ExecutorService executorService, SQLQueryExecutor sqlExecutor) {
		super(executorService, sqlExecutor);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Future<Void> executeCall(final String call, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<Void>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public Void call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				sqlExecutor.executeCall(call, parameters);
				return null;
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, int, int, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final int resultSetType, final int resultSetConcurrency, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, resultSetType, resultSetConcurrency, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, int, int, int, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, resultSetType, resultSetConcurrency, resultSetHoldability, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final ParameterHandler parameterHandler, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, parameterHandler, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, int, int, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final int resultSetType, final int resultSetConcurrency, final ParameterHandler parameterHandler, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, resultSetType, resultSetConcurrency, parameterHandler, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeCall(java.lang.String, int, int, int, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeCall(final String call, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability, final ParameterHandler parameterHandler, final CallableStatementHandler<T> callableStatementHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeCall(call, resultSetType, resultSetConcurrency, resultSetHoldability, parameterHandler, callableStatementHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeBatch(java.lang.String, java.lang.Object[][])
	 */
	@Override
	public Future<int[]> executeBatch(final String query, final Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<int[]>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public int[] call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeBatch(query, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeBatchCall(java.lang.String, java.lang.Object[][])
	 */
	@Override
	public Future<int[]> executeBatchCall(final String call, final Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<int[]>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public int[] call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeBatchCall(call, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeBatchCall(java.lang.String, za.co.sindi.sql.handler.ParameterHandler, java.lang.Object[][])
	 */
	@Override
	public Future<int[]> executeBatchCall(final String call, final ParameterHandler parameterHandler, final Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<int[]>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public int[] call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeBatchCall(call, parameterHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeBatchInsert(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[][])
	 */
	@Override
	public <K> Future<K> executeBatchInsert(final String query, final ResultSetHandler<K> generatedKeysResultSetHandler, final Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<K>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public K call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeBatchInsert(query, generatedKeysResultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeInsert(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> Future<K> executeInsert(final String query, final ResultSetHandler<K> generatedKeysResultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<K>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public K call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeInsert(query, generatedKeysResultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeInsert(java.lang.String, int[], za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> Future<K> executeInsert(final String query, final int[] columnIndexes, final ResultSetHandler<K> generatedKeysResultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<K>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public K call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeInsert(query, columnIndexes, generatedKeysResultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeInsert(java.lang.String, java.lang.String[], za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> Future<K> executeInsert(final String query, final String[] columnNames, final ResultSetHandler<K> generatedKeysResultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<K>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public K call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeInsert(query, columnNames, generatedKeysResultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeSelect(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeSelect(final String query, final ResultSetHandler<T> resultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeSelect(query, resultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeSelect(java.lang.String, int, int, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeSelect(final String query, final int resultSetType, final int resultSetConcurrency, final ResultSetHandler<T> resultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeSelect(query, resultSetType, resultSetConcurrency, resultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeSelect(java.lang.String, int, int, int, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> Future<T> executeSelect(final String query, final int resultSetType, final int resultSetConcurrency, final int resultSetHoldability, final ResultSetHandler<T> resultSetHandler, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<T>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public T call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeSelect(query, resultSetType, resultSetConcurrency, resultSetHoldability, resultSetHandler, parameters);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeUpdate(java.lang.String)
	 */
	@Override
	public Future<Integer> executeUpdate(final String query) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<Integer>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeUpdate(query);
			}
		});
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.AsyncSQLQueryExecutor#executeUpdate(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Future<Integer> executeUpdate(final String query, final Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executorService.submit(new Callable<Integer>() {

			/* (non-Javadoc)
			 * @see java.util.concurrent.Callable#call()
			 */
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				prepareSQLExecutor(sqlExecutor);
				return sqlExecutor.executeUpdate(query, parameters);
			}
		});
	}
}
