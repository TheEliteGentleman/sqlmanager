/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.query.AbstractPreparedQuery;
import za.co.sindi.sql.query.UpdatablePreparedQuery;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class UpdatePreparedQuery extends AbstractPreparedQuery implements UpdatablePreparedQuery {

	private int updateCount;
	private PreparedStatement preparedStatement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public UpdatePreparedQuery(Connection connection, String query) {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchDirection(int)
	 */
	@Override
	public UpdatablePreparedQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchSize(int)
	 */
	@Override
	public UpdatablePreparedQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxFieldSize(int)
	 */
	@Override
	public UpdatablePreparedQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxRows(int)
	 */
	@Override
	public UpdatablePreparedQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setQueryTimeout(int)
	 */
	@Override
	public UpdatablePreparedQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public UpdatablePreparedQuery setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		super.setParameter(parameterIndex, value);
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
		try {
			updateCount = preparedStatement.executeUpdate();
			
//			//Commit
//			SQLUtils.commit(getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			//Rollback if we found issues,
//			try {
//				SQLUtils.rollback(getConnection());
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.SEVERE, "Error rolling back transaction.", e1);
//			}
			
			String message = "Error executing statement for insert.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		} finally {
			try {
				SQLUtils.close(preparedStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing prepared statement.", e);
			}
		}
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
