/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.sql.exception.QueryException;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public abstract class AbstractQuery implements Query {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
//	private Connection connection;
	
//	/**
//	 * @param connection
//	 */
//	protected AbstractQuery(Connection connection) {
//		super();
//		if (connection == null) {
//			throw new IllegalArgumentException("SQL Connection is null.");
//		}
//		
//		this.connection = connection;
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#setFetchDirection(int)
	 */
	@Override
	public Query setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		try {
			getStatement().setFetchDirection(direction);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting fetch direction to " + direction + ".";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#setFetchSize(int)
	 */
	@Override
	public Query setFetchSize(int rows) {
		// TODO Auto-generated method stub
		try {
			getStatement().setFetchSize(rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting fetch size to " + rows + " rows.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#setMaxFieldSize(int)
	 */
	@Override
	public Query setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		try {
			getStatement().setMaxFieldSize(max);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting max field size to " + max + " bytes.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#setMaxRows(int)
	 */
	@Override
	public Query setMaxRows(int max) {
		// TODO Auto-generated method stub
		try {
			getStatement().setMaxRows(max);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting the max row limit to " + max + " rows.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.Query#setQueryTimeout(int)
	 */
	@Override
	public Query setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		try {
			getStatement().setQueryTimeout(seconds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting query timeout to " + seconds + " seconds.";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

//	/**
//	 * @return the connection
//	 */
//	protected Connection getConnection() {
//		return connection;
//	}

	protected abstract Statement getStatement();
}
