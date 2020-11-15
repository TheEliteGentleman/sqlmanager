/**
 * 
 */
package za.co.sindi.sql.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This processor generates a Java Bean using {@link Introspector}. The bean attribute types must match its respective SQL data types.
 * The bean attribute name matches the database column name (case insensitive).
 * 
 * @author Buhake Sindi
 * @since 17 April 2014
 *
 */
public class BeanProcessor<T> {

	private Class<T> beanClass;
	
	/* (non-Javadoc)
	 * @see com.softwareag.data.handler.ResultSetHandler#handle(java.sql.ResultSet)
	 */
	/**
	 * @param clazz
	 */
	public BeanProcessor(Class<T> clazz) {
		super();
		if (clazz == null) {
			throw new IllegalArgumentException("No JavaBean class type has been specified.");
		}
		
		this.beanClass = clazz;
	}
	
	/**
	 * Create a new instance of the bean and populates its attributes from the values matched in the resultset.
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public T createBean(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		T bean = null;
		
		try {
			if (resultSet.next()) {
				bean = createBean(resultSet, getPropertyDescriptors());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Bean introspection failed.", e);
		}
		
		return bean;
	}

	/**
	 * Iterates through the resultset, create the beans and return the list of beans created.
	 * 
	 * @param resultSet
	 * @return a list of created beans.
	 * @throws SQLException
	 */
	public List<T> createBeans(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		List<T> list = null;
		
		try {
			while (resultSet.next()) {
				if (list == null) {
					list = new ArrayList<T>();
				}
				
				list.add(createBean(resultSet, getPropertyDescriptors()));
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Bean introspection failed.", e);
		}
		
		return list;
	}
	
	private PropertyDescriptor[] getPropertyDescriptors() throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		return beanInfo.getPropertyDescriptors();
	}
	
	private T createBean(ResultSet resultSet, PropertyDescriptor[] propertyDescriptors) throws SQLException {
		T bean = null;
		
		try {
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columns = resultSetMetaData.getColumnCount();
			
			//Instantiate...
			bean = beanClass.newInstance();
			
			for (int columnIndex = 1; columnIndex <= columns; columnIndex++) {
				String columnName = resultSetMetaData.getColumnName(columnIndex);
				for (int i = 0; i < propertyDescriptors.length; i++) {
					PropertyDescriptor descriptor = propertyDescriptors[i];
					
					if (columnName.equalsIgnoreCase(descriptor.getName())) {
						Class<?> propertyType = descriptor.getPropertyType();
						Object value = getResultSetValue(resultSet, columnIndex, propertyType);
						setValueToBean(bean, descriptor, value);
					}
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Unable to create a new instance of " + beanClass.getName(), e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Unable to create a new instance of " + beanClass.getName(), e);
		}
		
		return bean;
	}

	private Object getResultSetValue(ResultSet resultSet, int columnIndex, Class<?> propertyType) throws SQLException {
		if (propertyType.equals(String.class)) {
			return resultSet.getString(columnIndex);
		} else if (propertyType.equals(Byte.TYPE) || propertyType.equals(Byte.class)) {
			return resultSet.getByte(columnIndex);
		} else if (propertyType.equals(byte[].class)) {
			return resultSet.getBytes(columnIndex);
		} else if (propertyType.equals(BigDecimal.class)) {
			return resultSet.getBigDecimal(columnIndex);
		} else if (propertyType.equals(Boolean.TYPE) || propertyType.equals(Boolean.class)) {
			return resultSet.getBoolean(columnIndex);
		} else if (propertyType.equals(Double.TYPE) || propertyType.equals(Double.class)) {
			return resultSet.getDouble(columnIndex);
		} else if (propertyType.equals(Float.TYPE) || propertyType.equals(Float.class)) {
			return resultSet.getFloat(columnIndex);
		} else if (propertyType.equals(Integer.TYPE) || propertyType.equals(Integer.class)) {
			return resultSet.getInt(columnIndex);
		} else if (propertyType.equals(Long.TYPE) || propertyType.equals(Long.class)) {
			return resultSet.getLong(columnIndex);
		} else if (propertyType.equals(Short.TYPE) || propertyType.equals(Short.class)) {
			return resultSet.getShort(columnIndex);
		} else if (propertyType.equals(Timestamp.class)) {
			return resultSet.getTimestamp(columnIndex);
		} else if (propertyType.equals(Date.class) || Date.class.isAssignableFrom(propertyType)) {
			return resultSet.getDate(columnIndex);
		} else if (propertyType.equals(Time.class)) {
			return resultSet.getTime(columnIndex);
		}
		
		return resultSet.getObject(columnIndex);
	}
	
	private void setValueToBean(T bean, PropertyDescriptor descriptor, Object value) throws SQLException {
		Method setter = descriptor.getWriteMethod();
		if (setter == null) {
			return ;
		}
		
		try {
			if (value != null) {
				setter.invoke(bean, new Object[] { value });
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Cannot assign value to bean (bean=" + beanClass.getName() + ", value=" + String.valueOf(value) + ").", e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Cannot assign value to bean (bean=" + beanClass.getName() + ", value=" + String.valueOf(value) + ").", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Cannot assign value to bean (bean=" + beanClass.getName() + ", value=" + String.valueOf(value) + ").", e);
		}
	}
}
