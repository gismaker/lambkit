/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package com.lambkit.db.sql.column;

import java.util.List;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.db.sql.SqlJoinMode;

public class Example implements IExample{
	private List<Columns> oredColumns;
	private String loadColumns = "*";
	private String tableName;
	//暂未实现，等待完善
	private List<SqlJoinOn> joinOns;
	private String orderBy;
	//private int pageSize = 0;
	//private int pageNumber = 0;
	
	public Example() {
		oredColumns = Lists.newArrayList();
	}
	
	public static Example create(String table) {
		Example example = new Example();
		example.setTableName(table);
		return example;
	}
	
	public static Example create(String table, Columns columns) {
		Example example = new Example();
		example.setTableName(table).addColumns(columns);
		return example;
	}
	
	public Columns or() {
        Columns criteria = createColumnsInternal();
        oredColumns.add(criteria);
        return criteria;
    }

    public Columns createColumns() {
        Columns criteria = createColumnsInternal();
        if (oredColumns.size() == 0) {
            oredColumns.add(criteria);
        }
        return criteria;
    }

    protected Columns createColumnsInternal() {
        Columns criteria = new Columns();
        return criteria;
    }
    
    public Example join(String mainField, String joinTableName, String joinField, SqlJoinMode type, Columns cols) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField, type, cols);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainField, String joinTableName, String joinField, SqlJoinMode type) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField, type);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainField, String joinTableName, String joinField, Columns cols) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField, cols);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainField, String joinTableName, String joinField) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainTableName, String mainField, String joinTableName, String joinField, SqlJoinMode type, Columns cols) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(mainTableName, mainField, joinTableName, joinField, type, cols);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainTableName, String mainField, String joinTableName, String joinField, SqlJoinMode type) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(mainTableName, mainField, joinTableName, joinField, type);
    	joinOns.add(joinOn);
    	return this;
	}
    
    public Example join(String mainTableName, String mainField, String joinTableName, String joinField) {
    	if(joinOns==null) joinOns = Lists.newArrayList();
    	SqlJoinOn joinOn = new SqlJoinOn(mainTableName, mainField, joinTableName, joinField);
    	joinOns.add(joinOn);
    	return this;
	}
	
	public SqlJoinOn createJoinOn(String mainField, String joinTableName, String joinField, SqlJoinMode type) {
		SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField, type);
    	joinOns.add(joinOn);
		return joinOn;
	}
	
	public SqlJoinOn createJoinOn(String mainField, String joinTableName, String joinField) {
		SqlJoinOn joinOn = new SqlJoinOn(tableName, mainField, joinTableName, joinField);
    	joinOns.add(joinOn);
		return joinOn;
	}

    public void add(Columns columns) {
    	this.oredColumns.add(columns);
    }
    
    public void clear() {
        oredColumns.clear();
        orderBy = null;
    }
    
	public List<Columns> getColumnsList() {
		return oredColumns;
	}

	public String getLoadColumns() {
		return loadColumns;
	}
	
	public String getSelectSql() {
		return loadColumns;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getTableName() {
		return tableName;
	}
	
	public List<SqlJoinOn> getJoinOnList() {
		return joinOns;
	}
	
	public Example addColumns(Columns columns) {
		// TODO Auto-generated method stub
		oredColumns.add(columns);
		return this;
	}
	
	public Example setColumns(List<Columns> oredColumns) {
		this.oredColumns = oredColumns;
		return this;
	}
	
	public Example setLoadColumns(String loadColumns) {
		this.loadColumns = loadColumns;
		return this;
	}

	public Example setSelectSql(String sql) {
		// TODO Auto-generated method stub
		this.loadColumns = sql;
		return this;
	}

	public Example setOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		this.orderBy = orderBy;
		return this;
	}

	public Example setTableName(String table) {
		this.tableName = table;
		return this;
	}
	
	/*
	public int getPageSize() {
		return pageSize;
	}
	
	public int getCount() {
		return pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	
	public Example setPageSize(int size) {
		// TODO Auto-generated method stub
		this.pageSize = size > 0 ? size : 0;
		return this;
	}
	
	public Example setCount(int size) {
		// TODO Auto-generated method stub
		this.pageSize = size > 0 ? size : 0;
		return this;
	}

	public Example setPageNumber(int number) {
		// TODO Auto-generated method stub
		this.pageNumber = number;
		return this;
	}
	*/
	
	public void addValueToParam(SqlPara sqlPara) {
        if (ArrayUtils.isNotEmpty(getColumnsList())) {
            for (Columns columns : getColumnsList()) {
            	if (ArrayUtils.isNotEmpty(columns.getList())) {
                    for (Column column : columns.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
            }
        }
        if (ArrayUtils.isNotEmpty(getJoinOnList())) {
        	for (SqlJoinOn jon : getJoinOnList()) {
        		if (ArrayUtils.isNotEmpty(jon.getList())) {
                    for (Column column : jon.getList()) {
                        column.addValueToParam(sqlPara);
                    }
                }
        	}
        }
	}
}
