/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.sql.utils.SQLUtils;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public abstract class AbstractQueryManager implements QueryManager {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private Connection connection;
	
	/**
	 * @param connection
	 */
	protected AbstractQueryManager(Connection connection) {
		super();
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			SQLUtils.close(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.WARNING, "Error closing JDBC Connection.", e);
		}
	}

	/**
	 * @return the connection
	 */
	protected Connection getConnection() {
		return connection;
	}
}
