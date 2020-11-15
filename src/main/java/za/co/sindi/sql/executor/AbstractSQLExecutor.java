/**
 * 
 */
package za.co.sindi.sql.executor;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public abstract class AbstractSQLExecutor implements SQLExecutor {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	protected Connection connection;
	protected int fetchDirection;
	protected int fetchSize;
	protected int maxFieldSize;
	protected int queryTimeout;
	protected int maxRows;
	protected int updateCount;
//	protected boolean closeConnection;

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection = connection;
	}

//	/* (non-Javadoc)
//	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setCloseConnection(boolean)
//	 */
//	@Override
//	public void setCloseConnection(boolean closeConnection) {
//		// TODO Auto-generated method stub
//		this.closeConnection = closeConnection;
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setFetchDirection(int)
	 */
	@Override
	public void setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		this.fetchDirection = direction;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setFetchSize(int)
	 */
	@Override
	public void setFetchSize(int fetchSize) {
		// TODO Auto-generated method stub
		this.fetchSize = fetchSize;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setMaxFieldSize(int)
	 */
	@Override
	public void setMaxFieldSize(int maxFieldSize) {
		// TODO Auto-generated method stub
		this.maxFieldSize = maxFieldSize;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setMaxRows(int)
	 */
	@Override
	public void setMaxRows(int maxRows) {
		// TODO Auto-generated method stub
		this.maxRows = maxRows;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#setQueryTimeout(int)
	 */
	@Override
	public void setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		this.queryTimeout = seconds;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.executor.SQLQueryExecutor#getUpdateCount()
	 */
	@Override
	public int getUpdateCount() {
		// TODO Auto-generated method stub
		return updateCount;
	}
	
	protected void ensureConnection() {
		if (connection == null) {
			throw new IllegalStateException("No JDBC Connection provided.");
		}
	}
	
	protected void prepareStatement(Statement statement) throws SQLException {
		if (statement != null) {
			//Update fetch direction
			if (fetchDirection >= ResultSet.FETCH_FORWARD && fetchDirection <= ResultSet.FETCH_UNKNOWN) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Setting Fetch direction to " + fetchDirection + ".");
				}
				
				statement.setFetchDirection(fetchDirection);
			}
			
			//Updating fetch size
			if (fetchSize >= 0) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Setting Fetch size to " + fetchSize + ".");
				}
				
				statement.setFetchSize(fetchSize);
			}
			
			//Updating max filed size
			if (maxFieldSize >= 0) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Setting Max Field size to " + maxFieldSize + " bytes.");
				}
				
				statement.setMaxFieldSize(maxFieldSize);
			}
			
			//Updating fetch size
			if (maxRows >= 0) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Setting max rows to " + maxRows + ".");
				}
				
				statement.setMaxRows(maxRows);
			}
			
			//Updating query timeout
			if (queryTimeout >= 0) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Setting query timeout to " + fetchSize + " seconds.");
				}
				
				statement.setQueryTimeout(queryTimeout);
			}
		}
	}
	
	protected void setParameter(PreparedStatement preparedStatement, int parameterIndex, Object value) throws SQLException {
		// TODO Auto-generated method stub
		ParameterMetaData pmd = preparedStatement.getParameterMetaData();
		int sqlType = pmd.getParameterType(parameterIndex);
		if (value == null) {
			preparedStatement.setNull(parameterIndex, sqlType);
		} else {
			preparedStatement.setObject(parameterIndex, value);
		}
	}
}
