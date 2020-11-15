/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import za.co.sindi.sql.exception.QueryException;
import za.co.sindi.sql.query.AbstractBatchQuery;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class BatchUpdateQuery extends AbstractBatchQuery {

	private Statement statement;
	
	/**
	 * @param connection
	 */
	public BatchUpdateQuery(Connection connection) {
		super();
		// TODO Auto-generated constructor stub
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new QueryException("Error creating a statement for batch.", e);
		}
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
