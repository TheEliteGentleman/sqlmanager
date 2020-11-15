/**
 * 
 */
package za.co.sindi.sql.query.impl;

import java.sql.Connection;

import za.co.sindi.sql.query.PreparedQueryManager;
import za.co.sindi.sql.query.QueryManager;
import za.co.sindi.sql.query.QueryManagerFactory;

/**
 * @author Bienfait Sindi
 * @since 05 April 2014
 *
 */
public class DefaultQueryManagerFactory implements QueryManagerFactory {

//	private static final Logger LOGGER = Logger.getLogger(DefaultQueryManagerFactory.class.getName());
	
//	/* (non-Javadoc)
//	 * @see za.co.sindi.sql.query.QueryManagerFactory#createQueryManager(javax.sql.DataSource)
//	 */
//	@Override
//	public QueryManager createQueryManager(DataSource dataSource) {
//		// TODO Auto-generated method stub
//		QueryManager manager = null;
//		
//		try {
//			manager = new DefaultQueryManager(DataSourceUtils.getConnection(dataSource));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			LOGGER.log(Level.SEVERE, "SQL Exception occurred while creating QueryManager.", e);
//		}
//		
//		return manager;
//	}
//
//	/* (non-Javadoc)
//	 * @see za.co.sindi.sql.query.QueryManagerFactory#createPreparedQueryManager(javax.sql.DataSource)
//	 */
//	@Override
//	public PreparedQueryManager createPreparedQueryManager(DataSource dataSource) {
//		// TODO Auto-generated method stub
//		PreparedQueryManager manager = null;
//		try {
//			manager = new DefaultPreparedQueryManager(DataSourceUtils.getConnection(dataSource));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			LOGGER.log(Level.SEVERE, "SQL Exception occurred while creating PreparedQueryManager.", e);
//		}
//		
//		return manager;
//	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.QueryManagerFactory#createQueryManager(java.sql.Connection)
	 */
	@Override
	public QueryManager createQueryManager(Connection connection) {
		// TODO Auto-generated method stub
		return new DefaultQueryManager(connection);
	}
	/* (non-Javadoc)
	 * @see za.co.sindi.sql.query.QueryManagerFactory#createPreparedQueryManager(java.sql.Connection)
	 */
	@Override
	public PreparedQueryManager createPreparedQueryManager(Connection connection) {
		// TODO Auto-generated method stub
		return new DefaultPreparedQueryManager(connection);
	}
}
