package com.lambkit.db.sql.column;

public interface IExample {
	
	public Columns or();

    public Columns createColumns();
   
    public void clear();
    
    public IExample setSelectSql(String sql);
    
    public IExample setOrderBy(String orderBy);
    
    //public IExample setPageSize(int size);
    
    //public IExample setPageNumber(int number);
		
    public IExample addColumns(Columns columns);
}
