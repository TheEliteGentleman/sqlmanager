/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.handler.CallableStatementHandler;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.query.AbstractPreparedQuery;
import za.co.sindi.sql.query.CallableQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public class StoredProcedureQuery extends AbstractPreparedQuery implements CallableQuery {

	private CallableStatementHandler<Object> callableStatementHandler;
	private ResultSetHandler<Object> resultSetHandler;
	private int updateCount = 0;
	private Object resultSetValue;
	private Object callableValue;
	private CallableStatement callableStatement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public StoredProcedureQuery(Connection connection, String query) {
		super();
		
		try {
			callableStatement = connection.prepareCall(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a CallableStatement (query=" + query + ").", e);
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param resultSetType
	 * @param resultSetConcurrency
	 */
	public StoredProcedureQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency) {
		super();
		
		try {
			callableStatement = connection.prepareCall(query, resultSetType, resultSetConcurrency);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a CallableStatement (resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ")", e);
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @param resultSetHoldability
	 */
	public StoredProcedureQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
		super();
		
		try {
			callableStatement = connection.prepareCall(query, resultSetType, resultSetConcurrency, resultSetHoldability);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a CallableStatement (resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ", resultSetHoldability = " + resultSetHoldability + ")", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		
		try {
			boolean useResultSet = callableStatement.execute();
			if (callableStatementHandler != null) {
				callableStatementHandler.handleCall(callableStatement);
			}
			
			if (useResultSet) {
				resultSet = callableStatement.getResultSet();
				if (resultSetHandler != null) {
					resultSetHandler.handle(resultSet);
				}
			} else {
				updateCount = callableStatement.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error executing CallableStatement.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		} finally {
			try {
				SQLUtils.close(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing ResultSet.", e);
			}
			
			try {
				SQLUtils.close(callableStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing CallableStatement.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchDirection(int)
	 */
	@Override
	public CallableQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchSize(int)
	 */
	@Override
	public CallableQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxFieldSize(int)
	 */
	@Override
	public CallableQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxRows(int)
	 */
	@Override
	public CallableQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setQueryTimeout(int)
	 */
	@Override
	public CallableQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public CallableQuery setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		super.setParameter(parameterIndex, value);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#setParameter(java.lang.String, java.lang.Object, int)
	 */
	@Override
	public CallableQuery setParameter(String parameterName, Object value, int sqlType) {
		// TODO Auto-generated method stub
		try {
			if (value == null) {
				getStatement().setNull(parameterName, sqlType);
			} else {
				getStatement().setObject(parameterName, value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting parameter (parameterName=" + parameterName + ", value=" + String.valueOf(value) + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#setCallableStatementHandler(za.co.sindi.sql.handler.CallableStatementHandler)
	 */
	@Override
	public CallableQuery setCallableStatementHandler(CallableStatementHandler<Object> callableStatementHandler) {
		// TODO Auto-generated method stub
		this.callableStatementHandler = callableStatementHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#setResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public CallableQuery setResultSetHandler(ResultSetHandler<Object> resultSetHandler) {
		// TODO Auto-generated method stub
		this.resultSetHandler = resultSetHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#getUpdateCount()
	 */
	@Override
	public int getUpdateCount() {
		// TODO Auto-generated method stub
		return updateCount;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#getResultSetValue()
	 */
	@Override
	public Object getResultSetValue() {
		// TODO Auto-generated method stub
		return resultSetValue;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableQuery#getCallableValue()
	 */
	@Override
	public Object getCallableValue() {
		// TODO Auto-generated method stub
		return callableValue;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#getStatement()
	 */
	@Override
	protected CallableStatement getStatement() {
		// TODO Auto-generated method stub
		return callableStatement;
	}
}
