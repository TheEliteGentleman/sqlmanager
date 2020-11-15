/**
 * 
 */
package za.co.sindi.sql.handler;

/**
 * @author Bienfait Sindi
 * @since 05 January 2013
 *
 */
@Deprecated
public interface GeneratedKeysResultSetHandler<K> extends ResultSetHandler<K> {

	public int[] getColumnIndexes();
	public String[] getColumnNames();
	
}
