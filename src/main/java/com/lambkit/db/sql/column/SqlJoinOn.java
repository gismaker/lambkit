package com.lambkit.db.sql.column;

import com.lambkit.db.sql.SqlJoinMode;

public class SqlJoinOn extends Columns {
	private static final long serialVersionUID = -8250834881745500656L;
	
	private SqlJoinMode type = SqlJoinMode.INNER_JOIN;
	private String tableName;
	
	public SqlJoinOn(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public SqlJoinMode getType() {
		return type;
	}

	public void setType(SqlJoinMode type) {
		this.type = type;
	}
}
