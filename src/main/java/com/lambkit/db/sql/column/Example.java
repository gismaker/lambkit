package com.lambkit.db.sql.column;

import java.util.List;

import com.beust.jcommander.internal.Lists;

public class Example implements IExample{
	private List<Columns> oredColumns;
	private String loadColumns = "*";
	private String tableName;
	private SqlJoinOn joinOn;
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
    
    public SqlJoinOn createJoinOn(String tableName) {
    	if(joinOn!=null) joinOn=null;
    	joinOn = new SqlJoinOn(tableName);
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
	
	public SqlJoinOn getJoinOn() {
		return joinOn;
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
	
	public Example setJoinOn(SqlJoinOn joinOn) {
		this.joinOn = joinOn;
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
}
