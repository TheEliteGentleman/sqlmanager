/**
 * 
 */
package za.co.sindi.sql.handler;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * @author Bienfait Sindi
 * @since 06 January 2013
 *
 */
public interface CallableStatementHandler<T> {

	public T handleCall(CallableStatement handler) throws SQLException;
}
