/**
 * 
 */
package za.co.sindi.sql.query;

import java.sql.Connection;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public interface QueryManagerFactory {

//	public QueryManager createQueryManager(DataSource dataSource);
//	public PreparedQueryManager createPreparedQueryManager(DataSource dataSource);
	public QueryManager createQueryManager(Connection connection);
	public PreparedQueryManager createPreparedQueryManager(Connection connection);
}
