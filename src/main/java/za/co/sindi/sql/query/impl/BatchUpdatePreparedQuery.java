/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.query.AbstractBatchPreparedQuery;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class BatchUpdatePreparedQuery extends AbstractBatchPreparedQuery {

	private PreparedStatement preparedStatement; 
	
	/**
	 * 
	 * @param connection
	 * @param query
	 */
	public BatchUpdatePreparedQuery(Connection connection, String query) {
		super();
		// TODO Auto-generated constructor stub
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a prepared statement for batch (query=" + query + ").", e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractBatchPreparedQuery#getStatement()
	 */
	@Override
	protected PreparedStatement getStatement() {
		// TODO Auto-generated method stub
		return preparedStatement;
	}
}
