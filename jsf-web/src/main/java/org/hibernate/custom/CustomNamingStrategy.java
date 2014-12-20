package org.hibernate.custom;

import org.hibernate.AssertionFailure;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.internal.util.StringHelper;

/**
 * 自定义建表策略，留待需要自定义的时候使用,更改表和字段的命名规则如TableName为TABLE_NAME
 * @author Hui
 *
 */
public class CustomNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy { 
	private static final long serialVersionUID = -4071069487437953913L;
    
    public String foreignKeyColumnName(
			String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName
	) {
    	String header = propertyName != null ? StringHelper.unqualify( propertyName ) : propertyTableName;
		if ( header == null ) throw new AssertionFailure( "NamingStrategy not properly filled" );
		return columnName( header + "_" + referencedColumnName );
	}
} 