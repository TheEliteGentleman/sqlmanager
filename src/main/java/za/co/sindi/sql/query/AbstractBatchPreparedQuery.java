/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
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
public abstract class AbstractBatchPreparedQuery extends AbstractPreparedQuery implements BatchPreparedQuery {

	private ResultSetHandler<Object> generatedKeysResultSetHandler;
	private int[] batchUpdatesCount;
	private Object generatedKeys;
	
//	/**
//	 * @param connection
//	 */
//	protected AbstractBatchPreparedQuery(Connection connection) {
//		super(connection);
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchDirection(int)
	 */
	@Override
	public BatchPreparedQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setFetchSize(int)
	 */
	@Override
	public BatchPreparedQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxFieldSize(int)
	 */
	@Override
	public BatchPreparedQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setMaxRows(int)
	 */
	@Override
	public BatchPreparedQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setQueryTimeout(int)
	 */
	@Override
	public BatchPreparedQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public BatchPreparedQuery setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		super.setParameter(parameterIndex, value);
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
	 * @see za.co.sindi.sql.query.BatchPreparedQuery#addBatch(java.lang.String)
	 */
	@Override
	public BatchPreparedQuery addBatch(String query) {
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
	 * @see za.co.sindi.sql.query.BatchPreparedQuery#clearBatch()
	 */
	@Override
	public BatchPreparedQuery clearBatch() {
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
	 * @see za.co.sindi.sql.query.BatchPreparedQuery#addBatch()
	 */
	@Override
	public BatchPreparedQuery addBatch() {
		// TODO Auto-generated method stub
		try {
			getStatement().addBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error adding parameters to batch.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchQuery#getGeneratedKeys()
	 */
	@Override
	public Object getGeneratedKeys() {
		// TODO Auto-generated method stub
		return generatedKeys;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.BatchPreparedQuery#setGeneratedKeysResultSetHandler(za.co.sindi.sql.handler.ResultSetHandler)
	 */
	@Override
	public BatchPreparedQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> generatedKeysResultSetHandler) {
		// TODO Auto-generated method stub
		this.generatedKeysResultSetHandler = generatedKeysResultSetHandler;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractPreparedQuery#getStatement()
	 */
	@Override
	protected abstract PreparedStatement getStatement();
}
