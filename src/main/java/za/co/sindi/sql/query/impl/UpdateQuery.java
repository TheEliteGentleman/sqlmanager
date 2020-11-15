/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.query.AbstractQuery;
import za.co.sindi.sql.query.UpdatableQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class UpdateQuery extends AbstractQuery implements UpdatableQuery {

	private int updateCount;
	private String query;
	private Statement statement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public UpdateQuery(Connection connection, String query) {
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

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchDirection(int)
	 */
	@Override
	public UpdatableQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchSize(int)
	 */
	@Override
	public UpdatableQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxFieldSize(int)
	 */
	@Override
	public UpdatableQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxRows(int)
	 */
	@Override
	public UpdatableQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setQueryTimeout(int)
	 */
	@Override
	public UpdatableQuery setQueryTimeout(int seconds) {
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
		try {
			updateCount = statement.executeUpdate(query);
			
//			//Commit
//			SQLUtils.commit(getConnection());
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
				SQLUtils.close(statement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing Statement.", e);
			}
		}
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
	 * @see za.co.sindi.sql.query.AbstractQuery#getStatement()
	 */
	@Override
	protected Statement getStatement() {
		// TODO Auto-generated method stub
		return statement;
	}
}
