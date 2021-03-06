/**
 * 
 */
package za.co.sindi.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.common.util.CaseInsensitiveHashMap;

/**
 * @author Bienfait Sindi
 * @since 28 February 2013
 *
 */
public final class ResultSetIterator implements Iterator<Map<String, Object>> {

	private static final Logger LOGGER = Logger.getLogger(ResultSetIterator.class.getName());
	private ResultSet resultSet;
	private SQLException thrownException;
	
	/**
	 * @param resultSet
	 */
	public ResultSetIterator(ResultSet resultSet) {
		super();
		if (resultSet == null) {
			throw new IllegalArgumentException("A ResultSet may not be null.");
		}
		
		this.resultSet = resultSet;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		// TODO Auto-generated method stub
		boolean next = false;
		try {
			next = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "ResultSet.next() threw an exception.", e);
			thrownException = e;
		}
		
		return next;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Map<String, Object> next() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new CaseInsensitiveHashMap<Object>();
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				resultMap.put(rsmd.getColumnLabel(i), resultSet.getObject(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			thrownException = e;
		}
		
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		// TODO Auto-generated method stub
		try {
			resultSet.deleteRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "ResultSet.deleteRow() threw an exception.", e);
			thrownException = e;
		}
	}

	/**
	 * @return the thrownException
	 */
	public SQLException getThrownException() {
		return thrownException;
	}
}
