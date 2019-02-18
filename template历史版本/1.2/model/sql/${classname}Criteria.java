##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage}.model.sql;

import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

import java.io.Serializable;
import java.util.List;

#parse("/template/java_author.include")
public class ${classname}Criteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static ${classname}Criteria create() {
		return new ${classname}Criteria();
	}
	
	public static ${classname}Criteria create(Column column) {
		${classname}Criteria that = new ${classname}Criteria();
		that.add(column);
        return that;
    }

    public static ${classname}Criteria create(String name, Object value) {
        return (${classname}Criteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("$tablename", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria eq(String name, Object value) {
    	super.eq(name, value);
        return this;
    }

    /**
     * not equals !=
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria ne(String name, Object value) {
    	super.ne(name, value);
        return this;
    }


    /**
     * like
     *
     * @param name
     * @param value
     * @return
     */

    public ${classname}Criteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public ${classname}Criteria notLike(String name, Object value) {
    	super.notLike(name, value);
        return this;
    }

    /**
     * 大于 great than
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria gt(String name, Object value) {
    	super.gt(name, value);
        return this;
    }

    /**
     * 大于等于 great or equal
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria ge(String name, Object value) {
    	super.ge(name, value);
        return this;
    }

    /**
     * 小于 less than
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria lt(String name, Object value) {
    	super.lt(name, value);
        return this;
    }

    /**
     * 小于等于 less or equal
     *
     * @param name
     * @param value
     * @return
     */
    public ${classname}Criteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public ${classname}Criteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public ${classname}Criteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public ${classname}Criteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public ${classname}Criteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public ${classname}Criteria add(Column column) {
    	super.add(column);
    	return this;
    }
    
    /**************************/
	
	public void addCriterion(String name, Object value, ConditionMode logic, String property, String typeHandler, String valueType) {
		 if (value == null) {
			 throw new RuntimeException("Value for " + property + " cannot be null");
		 }
		 add(Column.create(name, value, logic, typeHandler, valueType));
	}
   
	public void addCriterion(String name, Object value1, Object value2, ConditionMode logic, String property, String typeHandler, String valueType) {
		 if (value1 == null || value2 == null) {
			 throw new RuntimeException("Between values for " + property + " cannot be null");
		 }
		 add(Column.create(name, value1, value2, logic, typeHandler, valueType));
	}
		 
#set($valueType = "String")##
#foreach($column in $columns)##
	public ${classname}Criteria and${column.upperName}IsNull() {
		isnull("${column.name}");
		return this;
	}
	
	public ${classname}Criteria and${column.upperName}IsNotNull() {
		notNull("${column.name}");
		return this;
	}
	
	public ${classname}Criteria and${column.upperName}IsEmpty() {
		empty("${column.name}");
		return this;
	}

	public ${classname}Criteria and${column.upperName}IsNotEmpty() {
		notEmpty("${column.name}");
		return this;
	}
 #if(${column.javaType}=="java.lang.String")##       
       public ${classname}Criteria and${column.upperName}Like(${column.javaType} value) {
    	   addCriterion("${column.name}", value, ConditionMode.FUZZY, "${column.attrName}", "${column.javaType}", "$valueType");
    	   return this;
      }

      public ${classname}Criteria and${column.upperName}NotLike(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.NOT_FUZZY, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
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
      public ${classname}Criteria and${column.upperName}EqualTo(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}NotEqualTo(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.NOT_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}GreaterThan(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.GREATER_THEN, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}GreaterThanOrEqualTo(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.GREATER_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}LessThan(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.LESS_THEN, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}LessThanOrEqualTo(${column.javaType} value) {
          addCriterion("${column.name}", value, ConditionMode.LESS_EQUAL, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}Between(${column.javaType} value1, ${column.javaType} value2) {
    	  addCriterion("${column.name}", value1, value2, ConditionMode.BETWEEN, "${column.attrName}", "${column.javaType}", "$valueType");
    	  return this;
      }

      public ${classname}Criteria and${column.upperName}NotBetween(${column.javaType} value1, ${column.javaType} value2) {
          addCriterion("${column.name}", value1, value2, ConditionMode.NOT_BETWEEN, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }
        
      public ${classname}Criteria and${column.upperName}In(List<${column.javaType}> values) {
          addCriterion("${column.name}", values, ConditionMode.IN, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }

      public ${classname}Criteria and${column.upperName}NotIn(List<${column.javaType}> values) {
          addCriterion("${column.name}", values, ConditionMode.NOT_IN, "${column.attrName}", "${column.javaType}", "$valueType");
          return this;
      }
#end##
}