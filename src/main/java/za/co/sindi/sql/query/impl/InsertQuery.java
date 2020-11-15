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
import za.co.sindi.sql.query.InsertableQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class InsertQuery extends AbstractQuery implements InsertableQuery {

	private ResultSetHandler<Object> generatedKeysResultSetHandler;
	private Object generatedKeys;
	private boolean autoGeneratedKeys;
	private int[] columnIndexes;
	private String[] columnNames;
	private int updateCount;
	private String query;
	private Statement statement;
	
	/**
	 * @param connection
	 */
	public InsertQuery(Connection connection, String query) {
		super();
		// TODO Auto-generated constructor stub
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
	 * @param autoGeneratedKeys
	 */
	public InsertQuery(Connection connection, String query, boolean autoGeneratedKeys) {
		this(connection, query);
		this.autoGeneratedKeys = autoGeneratedKeys;
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param columnIndexes
	 */
	public InsertQuery(Connection connection, String query, int[] columnIndexes) {
		this(connection, query, columnIndexes != null && columnIndexes.length > 0);
		this.columnIndexes = columnIndexes;
	}
	
	/**
	 * 
	 * @param connection
	 * @param query
	 * @param columnNames
	 */
	public InsertQuery(Connection connection, String query, String[] columnNames) {
		this(connection, query, columnNames != null && columnNames.length > 0);
		this.columnNames = columnNames;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchDirection(int)
	 */
	@Override
	public InsertableQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchSize(int)
	 */
	@Override
	public InsertableQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxFieldSize(int)
	 */
	@Override
	public InsertableQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxRows(int)
	 */
	@Override
	public InsertableQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setQueryTimeout(int)
	 */
	@Override
	public InsertableQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.UpdatableQuery#getUpdateCount()
	 */
	@Override
	public int getUpdateCount() {
		// TODO Auto-generated method stub
		return updateCount;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ResultSet generatedKeysResultSet = null;
		
		try {
			if (autoGeneratedKeys) {
				if (columnIndexes != null) {
					updateCount = statement.executeUpdate(query, columnIndexes);
				} else if (columnNames != null) {
					updateCount = statement.executeUpdate(query, columnNames);
				} else {
					updateCount = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				}
			} else {
				updateCount = statement.executeUpdate(query);
			}
			
//			//Commit
//			SQLUtils.commit(getConnection());
			
			if (autoGeneratedKeys && generatedKeysResultSetHandler != null) {
				generatedKeysResultSet = statement.getGeneratedKeys();
				generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			//Rollback if we found issues,
//			try {
//				SQLUtils.rollback(getConnection());
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.SEVERE, "Error rolling back... (query=" + query + ").", e1);
//			}
			
			String message = "Error executing statement (query=" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		} finally {
			try {
				SQLUtils.close(generatedKeysResultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing generated keys result set.", e);
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
	 * @see za.co.sindi.sql.query.InsertableQuery#setGeneratedKeysResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public InsertableQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> generatedKeysResultSetHandler) {
		// TODO Auto-generated method stub
		this.generatedKeysResultSetHandler = generatedKeysResultSetHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.InsertableQuery#getGeneratedKeys()
	 */
	@Override
	public Object getGeneratedKeys() {
		// TODO Auto-generated method stub
		return generatedKeys;
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
