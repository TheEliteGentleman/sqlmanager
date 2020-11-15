/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.query.AbstractBatchPreparedQuery;
import za.co.sindi.sql.query.CallableBatchQuery;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class StoredProcedureBatchQuery extends AbstractBatchPreparedQuery implements CallableBatchQuery {

	private CallableStatement callableStatement;
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public StoredProcedureBatchQuery(Connection connection, String query) {
		super();
		// TODO Auto-generated constructor stub
		try {
			callableStatement = connection.prepareCall(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a CallableStatement (query=" + query + ").", e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setFetchDirection(int)
	 */
	@Override
	public CallableBatchQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setFetchSize(int)
	 */
	@Override
	public CallableBatchQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setMaxFieldSize(int)
	 */
	@Override
	public CallableBatchQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setMaxRows(int)
	 */
	@Override
	public CallableBatchQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setQueryTimeout(int)
	 */
	@Override
	public CallableBatchQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public CallableBatchQuery setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		super.setParameter(parameterIndex, value);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#addBatch(java.lang.String)
	 */
	@Override
	public CallableBatchQuery addBatch(String query) {
		// TODO Auto-generated method stub
		super.addBatch(query);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#clearBatch()
	 */
	@Override
	public CallableBatchQuery clearBatch() {
		// TODO Auto-generated method stub
		super.clearBatch();
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#addBatch()
	 */
	@Override
	public CallableBatchQuery addBatch() {
		// TODO Auto-generated method stub
		super.addBatch();
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.CallableBatchQuery#setParameter(java.lang.String, java.lang.Object, int)
	 */
	@Override
	public CallableBatchQuery setParameter(String parameterName, Object value, int sqlType) {
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
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#getStatement()
	 */
	@Override
	protected CallableStatement getStatement() {
		// TODO Auto-generated method stub
		return callableStatement;
	}
}
