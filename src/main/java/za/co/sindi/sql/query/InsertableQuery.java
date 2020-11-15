/**
 * 
 */
package za.co.sindi.sql.query;

import za.co.sindi.sql.handler.ResultSetHandler;

/**
 * @author Bienfait Sindi
 * @since 02 April 2014
 *
 */
public interface InsertableQuery extends UpdatableQuery {

	public InsertableQuery setGeneratedKeysResultSetHandler(ResultSetHandler<Object> handler);
	public Object getGeneratedKeys();
}
