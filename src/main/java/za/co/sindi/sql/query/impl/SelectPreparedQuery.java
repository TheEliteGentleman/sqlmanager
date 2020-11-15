/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.query.AbstractPreparedQuery;
import za.co.sindi.sql.query.SelectablePreparedQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class SelectPreparedQuery<T> extends AbstractPreparedQuery implements SelectablePreparedQuery<T> {

	private ResultSetHandler<T> resultSetHandler;
	private T result;
	private PreparedStatement preparedStatement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public SelectPreparedQuery(Connection connection, String query) {
		super();
		// TODO Auto-generated constructor stub
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a prepared statement (query=" + query + ").", e);
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param resultSetType
	 * @param resultSetConcurrency
	 */
	public SelectPreparedQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency) {
		super();
		// TODO Auto-generated constructor stub
		try {
			preparedStatement = connection.prepareStatement(query, resultSetType, resultSetConcurrency);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a prepared statement (query=" + query + ", resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ")", e);
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
	public SelectPreparedQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
		super();
		// TODO Auto-generated constructor stub
		try {
			preparedStatement = connection.prepareStatement(query, resultSetType, resultSetConcurrency, resultSetHoldability);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a prepared statement (query=" + query + ", resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ", resultSetHoldability=" + resultSetHoldability + ")", e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchDirection(int)
	 */
	@Override
	public SelectablePreparedQuery<T> setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchSize(int)
	 */
	@Override
	public SelectablePreparedQuery<T> setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxFieldSize(int)
	 */
	@Override
	public SelectablePreparedQuery<T> setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxRows(int)
	 */
	@Override
	public SelectablePreparedQuery<T> setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setQueryTimeout(int)
	 */
	@Override
	public SelectablePreparedQuery<T> setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public SelectablePreparedQuery<T> setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		super.setParameter(parameterIndex, value);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.SelectableQuery#getResult()
	 */
	@Override
	public T getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null && resultSetHandler != null) {
				result = resultSetHandler.handle(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error executing prepared statement.";
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
				SQLUtils.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing Statement.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.SelectablePreparedQuery#setResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public SelectablePreparedQuery<T> setResultSetHandler(ResultSetHandler<T> resultSetHandler) {
		// TODO Auto-generated method stub
		this.resultSetHandler = resultSetHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#getStatement()
	 */
	@Override
	protected PreparedStatement getStatement() {
		// TODO Auto-generated method stub
		return preparedStatement;
	}
}
