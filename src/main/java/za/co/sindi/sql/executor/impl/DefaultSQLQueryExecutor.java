/**
 * 
 */
package za.co.sindi.sql.executor.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import za.co.sindi.sql.exception.DatabaseExecutionException;
import za.co.sindi.sql.executor.AbstractSQLExecutor;
import za.co.sindi.sql.executor.SQLQueryExecutor;
import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ParameterHandler;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 06 April 2014
 *
 */
public class DefaultSQLQueryExecutor extends AbstractSQLExecutor implements SQLQueryExecutor {

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void executeCall(String call, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		executeCall(call, null, parameters);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executeCall(call, null, callableStatementHandler, parameters);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, int, int, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executeCall(call, resultSetType, resultSetConcurrency, null, callableStatementHandler, parameters);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, int, int, int, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executeCall(call, resultSetType, resultSetConcurrency, resultSetHoldability, null, callableStatementHandler, parameters);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		CallableStatement statement = null;
		T value = null;
		
		try {
			statement = connection.prepareCall(call);
			prepareStatement(statement);
			
			if (parameterHandler != null) {
				parameterHandler.handleParameters(statement, parameters);
			} else if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			boolean useResultSet = statement.execute();
			if (!useResultSet) {
				updateCount = statement.getUpdateCount();
			}
			
			if (callableStatementHandler != null) {
				value = callableStatementHandler.handleCall(statement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running call (" + call + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a Statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return value;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, int, int, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		CallableStatement statement = null;
		T value = null;
		
		try {
			statement = connection.prepareCall(call, resultSetType, resultSetConcurrency);
			prepareStatement(statement);
			
			if (parameterHandler != null) {
				parameterHandler.handleParameters(statement, parameters);
			} else if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			boolean useResultSet = statement.execute();
			if (!useResultSet) {
				updateCount = statement.getUpdateCount();
			}
			
			if (callableStatementHandler != null) {
				value = callableStatementHandler.handleCall(statement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running call (" + call + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a Statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return value;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeCall(java.lang.String, int, int, int, za.co.sindi.sql.handler.ParameterHandler, za.co.sindi.sql.handler.CallableStatementHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeCall(String call, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ParameterHandler parameterHandler, CallableStatementHandler<T> callableStatementHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		CallableStatement statement = null;
		T value = null;
		
		try {
			statement = connection.prepareCall(call, resultSetType, resultSetConcurrency, resultSetHoldability);
			prepareStatement(statement);
			
			if (parameterHandler != null) {
				parameterHandler.handleParameters(statement, parameters);
			} else if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			boolean useResultSet = statement.execute();
			if (!useResultSet) {
				updateCount = statement.getUpdateCount();
			}
			
			if (callableStatementHandler != null) {
				value = callableStatementHandler.handleCall(statement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running call (" + call + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a Statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return value;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeBatch(java.lang.String, java.lang.Object[][])
	 */
	@Override
	public int[] executeBatch(String query, Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		int[] updateCounts;
		
		try {
			statement = connection.prepareStatement(query);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					for (int j = 0; j < parameters[i].length; j++) {
						setParameter(statement, j + 1, parameters[i][j]);
					}
					
					statement.addBatch();
				}
			}
			
			updateCount = 0;
			//Execute statement
			updateCounts = statement.executeBatch();
			//Commit
			SQLUtils.commit(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				SQLUtils.rollback(connection);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error rolling back batch execution.", e1);
			}
			
			String message = "SQLException from running batch query (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a Statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return updateCounts;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeBatchCall(java.lang.String, java.lang.Object[][])
	 */
	@Override
	public int[] executeBatchCall(String call, Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executeBatchCall(call, null, parameters);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeBatchCall(java.lang.String, za.co.sindi.sql.handler.ParameterHandler, java.lang.Object[][])
	 */
	@Override
	public int[] executeBatchCall(String call, ParameterHandler parameterHandler, Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		CallableStatement statement = null;
		int[] updateCounts;
		
		try {
			statement = connection.prepareCall(call);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					for (int j = 0; j < parameters[i].length; j++) {
						if (parameterHandler == null) {
							setParameter(statement, j + 1, parameters[i][j]);
						} else {
							parameterHandler.handleParameters(statement, parameters[i]);
						}
					}
					
					statement.addBatch();
				}
			}
			
			updateCount = 0;
			//Execute statement
			updateCounts = statement.executeBatch();
			//Commit
			SQLUtils.commit(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				SQLUtils.rollback(connection);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error rolling back batch execution.", e1);
			}
			
			String message = "SQLException from running batch call (" + call + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a Statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return updateCounts;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeBatchInsert(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[][])
	 */
	@Override
	public <K> K executeBatchInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler, Object[][] parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet generatedKeysResultSet = null;
		K generatedKeys = null;
		
		try {
			statement = connection.prepareStatement(query);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					for (int j = 0; j < parameters[i].length; j++) {
						setParameter(statement, j + 1, parameters[i][j]);
					}
					
					statement.addBatch();
				}
			}
			
			updateCount = 0;
			//Execute statement
			statement.executeBatch();
			
//			//Commit
//			SQLUtils.commit(connection);
			
			generatedKeysResultSet = statement.getGeneratedKeys();
			if (generatedKeysResultSet != null && generatedKeysResultSetHandler != null) {
				generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(connection);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error rolling back batch execution.", e1);
//			}
			
			String message = "SQLException from running batch insert (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} 
//		finally {
//			try {
//				SQLUtils.close(generatedKeysResultSet);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error closing a generated keys resultset.", e);
//			} finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
//		}
		
		return generatedKeys;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeInsert(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> K executeInsert(String query, ResultSetHandler<K> generatedKeysResultSetHandler,	Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet generatedKeysResultSet = null;
		K generatedKeys = null;
		
		try {
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			updateCount = 0;
			//Execute statement
			boolean useResultSet = statement.execute();
			
//			//Commit
//			SQLUtils.commit(connection);
			
			if (useResultSet) {
				generatedKeysResultSet = statement.getGeneratedKeys();
				if (generatedKeysResultSet != null && generatedKeysResultSetHandler != null) {
					generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
				}
			} else {
				updateCount = statement.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(connection);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error rolling back insert execution.", e1);
//			}
			
			String message = "SQLException from running insert (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(generatedKeysResultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a generated keys resultset.", e);
			}
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return generatedKeys;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeInsert(java.lang.String, int[], za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> K executeInsert(String query, int[] columnIndexes, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet generatedKeysResultSet = null;
		K generatedKeys = null;
		
		try {
			statement = connection.prepareStatement(query, columnIndexes);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			updateCount = statement.executeUpdate();
			
//			//Commit
//			SQLUtils.commit(connection);
			
			generatedKeysResultSet = statement.getGeneratedKeys();
			if (generatedKeysResultSet != null && generatedKeysResultSetHandler != null) {
				generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(connection);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error rolling back insert execution.", e1);
//			}
			
			String message = "SQLException from running insert (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(generatedKeysResultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a generated keys resultset.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return generatedKeys;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeInsert(java.lang.String, java.lang.String[], za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <K> K executeInsert(String query, String[] columnNames, ResultSetHandler<K> generatedKeysResultSetHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet generatedKeysResultSet = null;
		K generatedKeys = null;
		
		try {
			statement = connection.prepareStatement(query, columnNames);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			updateCount = statement.executeUpdate();
			
//			//Commit
//			SQLUtils.commit(connection);
			
			generatedKeysResultSet = statement.getGeneratedKeys();
			if (generatedKeysResultSet != null && generatedKeysResultSetHandler != null) {
				generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(connection);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error rolling back insert execution.", e1);
//			}
			
			String message = "SQLException from running insert (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(generatedKeysResultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a generated keys resultset.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return generatedKeys;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeSelect(java.lang.String, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeSelect(String query, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		T result = null;
		
		try {
			statement = connection.prepareStatement(query);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			updateCount = 0;
			//Execute statement
			resultSet = statement.executeQuery();
			
			if (resultSet != null && resultSetHandler != null) {
				result = resultSetHandler.handle(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running select query (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a resultset.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeSelect(java.lang.String, int, int, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeSelect(String query, int resultSetType, int resultSetConcurrency, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		T result = null;
		
		try {
			statement = connection.prepareStatement(query, resultSetType, resultSetConcurrency);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			updateCount = 0;
			//Execute statement
			resultSet = statement.executeQuery();
			
			if (resultSet != null && resultSetHandler != null) {
				result = resultSetHandler.handle(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running select query (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a resultset.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeSelect(java.lang.String, int, int, int, za.co.sindi.sql.handler.ResultSetHandler, java.lang.Object[])
	 */
	@Override
	public <T> T executeSelect(String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability, ResultSetHandler<T> resultSetHandler, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		T result = null;
		
		try {
			statement = connection.prepareStatement(query, resultSetType, resultSetConcurrency, resultSetHoldability);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			updateCount = 0;
			//Execute statement
			resultSet = statement.executeQuery();
			
			if (resultSet != null && resultSetHandler != null) {
				result = resultSetHandler.handle(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "SQLException from running select query (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a resultset.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeUpdate(java.lang.String)
	 */
	@Override
	public int executeUpdate(String query) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		return executeUpdate(query, (Object[])null);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#executeUpdate(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int executeUpdate(String query, Object... parameters) throws DatabaseExecutionException {
		// TODO Auto-generated method stub
		ensureConnection();
		
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			prepareStatement(statement);
			
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					setParameter(statement, i + 1, parameters[i]);
				}
			}
			
			//Execute statement
			updateCount = statement.executeUpdate();
			
//			//Commit
//			SQLUtils.commit(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(connection);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.SEVERE, "Error on rollback.", e1);
//			}
			
			String message = "SQLException from running select query (" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new DatabaseExecutionException(message, e);
		} finally {
			try {
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing a prepared statement.", e);
			} 
//			finally {
//				if (closeConnection) {
//					try {
//						SQLUtils.close(connection);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing a Connection.", e);
//					}
//				}
//			}
		}
		
		return updateCount;
	}
}
