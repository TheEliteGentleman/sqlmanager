/**
 * 
 */
package za.co.sindi.sql.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Bienfait Sindi
 * @since 06 April 2014
 *
 */
public interface ParameterHandler {

	public void handleParameters(PreparedStatement statement, Object... parameters) throws SQLException;
}
