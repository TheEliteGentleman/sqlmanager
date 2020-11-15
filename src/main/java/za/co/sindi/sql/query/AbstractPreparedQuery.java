/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;

import za.co.sindi.sql.exception.QueryException;

/**
 * @author Bienfait Sindi
 * @since 03 April 2014
 *
 */
public abstract class AbstractPreparedQuery extends AbstractQuery implements PreparedQuery {

//	/**
//	 * @param connection
//	 */
//	protected AbstractPreparedQuery(Connection connection) {
//		super(connection);
//		// TODO Auto-generated constructor stub
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchDirection(int)
	 */
	@Override
	public PreparedQuery setFetchDirection(int direction) {
		// TODO Auto-generated method stub
		super.setFetchDirection(direction);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setFetchSize(int)
	 */
	@Override
	public PreparedQuery setFetchSize(int rows) {
		// TODO Auto-generated method stub
		super.setFetchSize(rows);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxFieldSize(int)
	 */
	@Override
	public PreparedQuery setMaxFieldSize(int max) {
		// TODO Auto-generated method stub
		super.setMaxFieldSize(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setMaxRows(int)
	 */
	@Override
	public PreparedQuery setMaxRows(int max) {
		// TODO Auto-generated method stub
		super.setMaxRows(max);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#setQueryTimeout(int)
	 */
	@Override
	public PreparedQuery setQueryTimeout(int seconds) {
		// TODO Auto-generated method stub
		super.setQueryTimeout(seconds);
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.PreparedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public PreparedQuery setParameter(int parameterIndex, Object value) {
		// TODO Auto-generated method stub
		try {
			if (value == null) {
				ParameterMetaData pmd = getStatement().getParameterMetaData();
				
				//To fix Oracle issues, let's apply this hack...
				int sqlType = Types.VARCHAR;
				try {
					sqlType = pmd.getParameterType(parameterIndex);
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "Error retrieving SQL parameter type from ParameterMetaData (perhaps it's an Oracle Driver?) (parameter index = " + parameterIndex + ").", e);
				}
				
				getStatement().setNull(parameterIndex, sqlType);
			} else {
				getStatement().setObject(parameterIndex, value);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String message = "Error setting parameter (index=" + parameterIndex + ", value=" + String.valueOf(value) + ").";
			LOGGER.log(Level.SEVERE, message, e);
			throw new QueryException(message, e);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.AbstractQuery#getStatement()
	 */
	protected abstract PreparedStatement getStatement();
}
