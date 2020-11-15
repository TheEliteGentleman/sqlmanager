/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.handler.ResultSetHandler;
import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public abstract class AbstractBatchQuery extends AbstractQuery implements BatchQuery {

	private ResultSetHandler<Object> generatedKeysResultSetHandler;
	private int[] batchUpdatesCount;
	private Object generatedKeys;
	
//	/**
//	 * @param connection
//	 */
//	protected AbstractBatchQuery(Connection connection) {
//		super(connection);
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchDirection(int)
	 */
	@Override
	public BatchQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchSize(int)
	 */
	@Override
	public BatchQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxFieldSize(int)
	 */
	@Override
	public BatchQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxRows(int)
	 */
	@Override
	public BatchQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setQueryTimeout(int)
	 */
	@Override
	public BatchQuery setQueryTimeout(int seconds) {
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
		ResultSet generatedKeysResultSet = null;
		try {
			batchUpdatesCount = getStatement().executeBatch();
//			//Commit
//			SQLUtils.commit(getConnection());
			
			if (!(getStatement() instanceof CallableStatement)) {
				generatedKeysResultSet = getStatement().getGeneratedKeys();
				if (generatedKeysResultSet != null && generatedKeysResultSetHandler != null) {
					generatedKeys = generatedKeysResultSetHandler.handle(generatedKeysResultSet);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			try {
//				SQLUtils.rollback(getConnection());
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				LOGGER.log(Level.WARNING, "Error rolling back batch transactions.", e1);
//			}
			
			String message = "Error executing batch.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		} finally {
			try {
				SQLUtils.close(generatedKeysResultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing generated keys resultset.", e);
			}
			
			try {
				SQLUtils.close(getStatement());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING, "Error closing statement.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#addBatch(java.lang.String)
	 */
	@Override
	public BatchQuery addBatch(String query) {
		// TODO Auto-generated method stub
		try {
			getStatement().addBatch(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error adding sql to batch (sql=" + query + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#clearBatch()
	 */
	@Override
	public BatchQuery clearBatch() {
		// TODO Auto-generated method stub
		try {
			getStatement().clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error clearing batch from statement.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#setGeneratedKeysResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public BatchQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> generatedKeysResultSetHandler) {
		// TODO Auto-generated method stub
		this.generatedKeysResultSetHandler = generatedKeysResultSetHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#getBatchUpdatesCount()
	 */
	@Override
	public int[] getBatchUpdatesCount() {
		// TODO Auto-generated method stub
		return batchUpdatesCount;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#getGeneratedKeys()
	 */
	@Override
	public Object getGeneratedKeys() {
		// TODO Auto-generated method stub
		return generatedKeys;
	}
}
