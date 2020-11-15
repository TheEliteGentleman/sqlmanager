/**
 * 
 */
package za.co.sindi.sql.executor;

import java.sql.Connection;


/**
 * @author Bienfait Sindi
 * @since 06 April 2014
 *
 */
public interface SQLExecutor {

	public void setConnection(Connection connection);
//	public void setCloseConnection(boolean closeConnection);
	public void setFetchDirection(int direction);
	public void setFetchSize(int fetchSize);
	public void setMaxFieldSize(int maxFieldSize);
	public void setMaxRows(int maxRows);
	public void setQueryTimeout(int seconds);
	public int getUpdateCount();
}
