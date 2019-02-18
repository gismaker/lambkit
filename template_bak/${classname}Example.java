##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model.sql;

import com.beust.jcommander.internal.Lists;
import com.jfinal.kit.StrKit;
import com.lambkit.common.service.ServiceKit;
import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

import $!{basepackage}.service.${classname}Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

#parse("/template/java_author.include")
public class ${classname}Example implements Example {
	private List<Criteria> oredColumns;
	private String loadColumns = "*";
	private String orderBy;
	private int pageSize = 10;
	private int pageNumber = 1;
	
	public ${classname}Example() {
		oredColumns = Lists.newArrayList();
	}
	
	public Criteria or() {
		Criteria criteria = createColumnsInternal();
        oredColumns.add(criteria);
        return criteria;
    }

    public Criteria createColumns() {
    	Criteria criteria = createColumnsInternal();
        if (oredColumns.size() == 0) {
            oredColumns.add(criteria);
        }
        return criteria;
    }

    protected Criteria createColumnsInternal() {
    	Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredColumns.clear();
        orderBy = null;
    }
    
    @Override
	public QueryParas toQueryParas() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Criteria> getColumnsList() {
		return oredColumns;
	}

	public void or(List<Criteria> orColumns) {
		this.oredColumns = orColumns;
	}

	public List<Criteria> getOredColumns() {
		return oredColumns;
	}

	public void setOredColumns(List<Criteria> oredColumns) {
		this.oredColumns = oredColumns;
	}

	public String getLoadColumns() {
		return loadColumns;
	}

	public void setLoadColumns(String loadColumns) {
		this.loadColumns = loadColumns;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public Example setSelectSql(String sql) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Example setOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		this.orderBy = orderBy;
		return this;
	}

	@Override
	public Example setPageSize(int size) {
		// TODO Auto-generated method stub
		this.pageSize = size;
		return this;
	}

	@Override
	public Example setPageNumber(int number) {
		// TODO Auto-generated method stub
		this.pageNumber = number;
		return this;
	}

	@Override
	public Example addColumns(Columns columns) {
		// TODO Auto-generated method stub
		oredColumns.add(columns);
		return this;
	}

	 public static class Criteria extends Columns implements Serializable {
		 private static final long serialVersionUID = 1L;
		 
		 protected void addCriterion(String name, Object value, ConditionMode logic, String property, String typeHandler, String valueType) {
			 if (value == null) {
				 throw new RuntimeException("Value for " + property + " cannot be null");
			 }
			 add(Column.create(name, value, logic, typeHandler, valueType));
		 }
	       
		 protected void addCriterion(String name, Object value1, Object value2, ConditionMode logic, String property, String typeHandler, String valueType) {
			 if (value1 == null || value2 == null) {
				 throw new RuntimeException("Between values for " + property + " cannot be null");
			 }
			 add(Column.create(name, value1, value2, logic, typeHandler, valueType));
		 }
		 
#set($valueType = "String")##
#foreach($column in $columns)##
        public Criteria and${column.upperName}IsNull() {
            isnull("${column.name}");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}IsNotNull() {
            notNull("${column.name}");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}IsEmpty() {
            empty("${column.name}");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}IsNotEmpty() {
            notEmpty("${column.name}");
            return (Criteria) this;
        }
 #if(${column.javaType}=="java.lang.String")##       
        public Criteria and${column.upperName}Like(${column.javaType} value) {
        	addCriterion("${column.name}", value, ConditionMode.FUZZY, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotLike(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.NOT_FUZZY, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
#end##
#if(${column.javaType}=="java.lang.Integer" || ${column.javaType}=="java.lang.Short" || 
${column.javaType}=="java.lang.Long" || ${column.javaType}=="java.lang.Float" || 
${column.javaType}=="java.lang.Double" || ${column.javaType}=="java.math.BigDecimal")##
#set($valueType = "Float")##
#elseif(${column.javaType}=="java.sql.Date" || ${column.javaType}=="java.sql.Time" ||
${column.javaType}=="java.sql.Timestamp" || ${column.javaType}=="java.util.Calendar")##
#set($valueType = "DateTime")##
#else##
#set($valueType = "String")##
#end##
        public Criteria and${column.upperName}EqualTo(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotEqualTo(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.NOT_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}GreaterThan(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.GREATER_THEN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}GreaterThanOrEqualTo(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.GREATER_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}LessThan(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.LESS_THEN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}LessThanOrEqualTo(${column.javaType} value) {
            addCriterion("${column.name}", value, ConditionMode.LESS_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}Between(${column.javaType} value1, ${column.javaType} value2) {
            addCriterion("${column.name}", value1, value2, ConditionMode.BETWEEN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotBetween(${column.javaType} value1, ${column.javaType} value2) {
            addCriterion("${column.name}", value1, value2, ConditionMode.NOT_BETWEEN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
        
        public Criteria and${column.upperName}In(List<${column.javaType}> values) {
            addCriterion("${column.name}", values, ConditionMode.IN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }

        public Criteria and${column.upperName}NotIn(List<${column.javaType}> values) {
            addCriterion("${column.name}", values, ConditionMode.NOT_IN, "${column.attrName}", "${column.javaType}", "$valueType");
            return (Criteria) this;
        }
#end##
    }
   
}