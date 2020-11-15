/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.query.AbstractQuery;
import za.co.sindi.sql.query.SelectableQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public class SelectQuery<T> extends AbstractQuery implements SelectableQuery<T> {

	private String query;
	private ResultSetHandler<T> resultSetHandler;
	private T result;
	private Statement statement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public SelectQuery(Connection connection, String query) {
		super();
		
		try {
			statement = connection.createStatement();
			this.query = query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a statement (query=" + query + ").", e);
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param resultSetType
	 * @param resultSetConcurrency
	 */
	public SelectQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency) {
		super();
		
		try {
			statement = connection.createStatement(resultSetType, resultSetConcurrency);
			this.query = query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a statement (query=" + query + ", resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ")", e);
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
	public SelectQuery(Connection connection, String query, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
		super();
		
		try {
			statement = connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
			this.query = query;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a statement (query=" + query + ", resultSetType = " + resultSetType + ", resultSetConcurrency = " + resultSetConcurrency + ", resultSetHoldability = " + resultSetHoldability + ")", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchDirection(int)
	 */
	@Override
	public SelectableQuery<T> setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchSize(int)
	 */
	@Override
	public SelectableQuery<T> setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxFieldSize(int)
	 */
	@Override
	public SelectableQuery<T> setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxRows(int)
	 */
	@Override
	public SelectableQuery<T> setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setQueryTimeout(int)
	 */
	@Override
	public SelectableQuery<T> setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
			if (resultSet != null && resultSetHandler != null) {
				result = resultSetHandler.handle(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error executing statement (query=" + query + ").";
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
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing Statement.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.SelectableQuery#setResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public SelectableQuery<T> setResultSetHandler(ResultSetHandler<T> resultSetHandler) {
		// TODO Auto-generated method stub
		this.resultSetHandler = resultSetHandler;
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
	 * @see za.co.sindi.sql.query.AbstractQuery#getStatement()
	 */
	@Override
	protected Statement getStatement() {
		// TODO Auto-generated method stub
		return statement;
	}
}
