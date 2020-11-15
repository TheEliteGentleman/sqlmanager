/**
 * 
 */
package za.co.sindi.sql.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Bienfait Sindi
 * 06 April 2014
 *
 */
public abstract class CallableParameterHandler implements ParameterHandler {

	/* (non-Javadoc)
	 * @see za.co.sindi.sql.handler.ParameterHandler#handleParameters(java.sql.PreparedStatement, java.lang.Object[])
	 */
	@Override
	public void handleParameters(PreparedStatement statement, Object... parameters) throws SQLException {
		// TODO Auto-generated method stub
		if (statement != null && statement instanceof CallableStatement) {
			handleParameters((CallableStatement)statement, parameters);
		}
	}
	
	protected abstract void handleParameters(CallableStatement statement, Object... parameters) throws SQLException;
}
