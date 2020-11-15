/**
 * 
 */
package za.co.sindi.sql.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import za.co.sindi.sql.ResultSetIterator;

/**
 * @author Bienfait Sindi
 * @since 28 February 2013
 *
 */
public abstract class IterativeResultSetHandler<T> implements ResultSetHandler<T> {

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.ResultSetHandler#handle(java.sql.ResultSet)
	 */
	public final T handle(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return handle(new ResultSetIterator(rs));
	}

	protected abstract T handle(ResultSetIterator iterator) throws SQLException;
}
