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
 */
package com.lambkit.module.meta.model.sql;

import com.lambkit.db.sql.ConditionMode;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaStoreDbCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaStoreDbCriteria create() {
		return new MetaStoreDbCriteria();
	}
	
	public static MetaStoreDbCriteria create(Column column) {
		MetaStoreDbCriteria that = new MetaStoreDbCriteria();
		that.add(column);
        return that;
    }

    public static MetaStoreDbCriteria create(String name, Object value) {
        return (MetaStoreDbCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_store_db", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaStoreDbCriteria eq(String name, Object value) {
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
    public MetaStoreDbCriteria ne(String name, Object value) {
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

    public MetaStoreDbCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaStoreDbCriteria notLike(String name, Object value) {
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
    public MetaStoreDbCriteria gt(String name, Object value) {
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
    public MetaStoreDbCriteria ge(String name, Object value) {
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
    public MetaStoreDbCriteria lt(String name, Object value) {
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
    public MetaStoreDbCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaStoreDbCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaStoreDbCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaStoreDbCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaStoreDbCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaStoreDbCriteria add(Column column) {
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
		 
	public MetaStoreDbCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaStoreDbCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaStoreDbCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaStoreDbCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaStoreDbCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreDbCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreDbCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreDbCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreDbCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaStoreDbCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaStoreDbCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaStoreDbCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaStoreDbCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaStoreDbCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaStoreDbCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaStoreDbCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Integer", "Float");
          return this;
      }
	public MetaStoreDbCriteria andDbnameIsNull() {
		isnull("dbname");
		return this;
	}
	
	public MetaStoreDbCriteria andDbnameIsNotNull() {
		notNull("dbname");
		return this;
	}
	
	public MetaStoreDbCriteria andDbnameIsEmpty() {
		empty("dbname");
		return this;
	}

	public MetaStoreDbCriteria andDbnameIsNotEmpty() {
		notEmpty("dbname");
		return this;
	}
        public MetaStoreDbCriteria andDbnameLike(java.lang.String value) {
    	   addCriterion("dbname", value, ConditionMode.FUZZY, "dbname", "java.lang.String", "Float");
    	   return this;
      }

      public MetaStoreDbCriteria andDbnameNotLike(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.NOT_FUZZY, "dbname", "java.lang.String", "Float");
          return this;
      }
      public MetaStoreDbCriteria andDbnameEqualTo(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.EQUAL, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameNotEqualTo(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.NOT_EQUAL, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameGreaterThan(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.GREATER_THEN, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.GREATER_EQUAL, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameLessThan(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.LESS_THEN, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("dbname", value, ConditionMode.LESS_EQUAL, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("dbname", value1, value2, ConditionMode.BETWEEN, "dbname", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreDbCriteria andDbnameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("dbname", value1, value2, ConditionMode.NOT_BETWEEN, "dbname", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreDbCriteria andDbnameIn(List<java.lang.String> values) {
          addCriterion("dbname", values, ConditionMode.IN, "dbname", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbnameNotIn(List<java.lang.String> values) {
          addCriterion("dbname", values, ConditionMode.NOT_IN, "dbname", "java.lang.String", "String");
          return this;
      }
	public MetaStoreDbCriteria andDbtypeIsNull() {
		isnull("dbtype");
		return this;
	}
	
	public MetaStoreDbCriteria andDbtypeIsNotNull() {
		notNull("dbtype");
		return this;
	}
	
	public MetaStoreDbCriteria andDbtypeIsEmpty() {
		empty("dbtype");
		return this;
	}

	public MetaStoreDbCriteria andDbtypeIsNotEmpty() {
		notEmpty("dbtype");
		return this;
	}
        public MetaStoreDbCriteria andDbtypeLike(java.lang.String value) {
    	   addCriterion("dbtype", value, ConditionMode.FUZZY, "dbtype", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreDbCriteria andDbtypeNotLike(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.NOT_FUZZY, "dbtype", "java.lang.String", "String");
          return this;
      }
      public MetaStoreDbCriteria andDbtypeEqualTo(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.EQUAL, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeNotEqualTo(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.NOT_EQUAL, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeGreaterThan(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.GREATER_THEN, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.GREATER_EQUAL, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeLessThan(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.LESS_THEN, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("dbtype", value, ConditionMode.LESS_EQUAL, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("dbtype", value1, value2, ConditionMode.BETWEEN, "dbtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreDbCriteria andDbtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("dbtype", value1, value2, ConditionMode.NOT_BETWEEN, "dbtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreDbCriteria andDbtypeIn(List<java.lang.String> values) {
          addCriterion("dbtype", values, ConditionMode.IN, "dbtype", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDbtypeNotIn(List<java.lang.String> values) {
          addCriterion("dbtype", values, ConditionMode.NOT_IN, "dbtype", "java.lang.String", "String");
          return this;
      }
	public MetaStoreDbCriteria andDburlIsNull() {
		isnull("dburl");
		return this;
	}
	
	public MetaStoreDbCriteria andDburlIsNotNull() {
		notNull("dburl");
		return this;
	}
	
	public MetaStoreDbCriteria andDburlIsEmpty() {
		empty("dburl");
		return this;
	}

	public MetaStoreDbCriteria andDburlIsNotEmpty() {
		notEmpty("dburl");
		return this;
	}
        public MetaStoreDbCriteria andDburlLike(java.lang.String value) {
    	   addCriterion("dburl", value, ConditionMode.FUZZY, "dburl", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreDbCriteria andDburlNotLike(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.NOT_FUZZY, "dburl", "java.lang.String", "String");
          return this;
      }
      public MetaStoreDbCriteria andDburlEqualTo(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.EQUAL, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlNotEqualTo(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.NOT_EQUAL, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlGreaterThan(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.GREATER_THEN, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.GREATER_EQUAL, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlLessThan(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.LESS_THEN, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("dburl", value, ConditionMode.LESS_EQUAL, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("dburl", value1, value2, ConditionMode.BETWEEN, "dburl", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreDbCriteria andDburlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("dburl", value1, value2, ConditionMode.NOT_BETWEEN, "dburl", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreDbCriteria andDburlIn(List<java.lang.String> values) {
          addCriterion("dburl", values, ConditionMode.IN, "dburl", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andDburlNotIn(List<java.lang.String> values) {
          addCriterion("dburl", values, ConditionMode.NOT_IN, "dburl", "java.lang.String", "String");
          return this;
      }
	public MetaStoreDbCriteria andUserIsNull() {
		isnull("user");
		return this;
	}
	
	public MetaStoreDbCriteria andUserIsNotNull() {
		notNull("user");
		return this;
	}
	
	public MetaStoreDbCriteria andUserIsEmpty() {
		empty("user");
		return this;
	}

	public MetaStoreDbCriteria andUserIsNotEmpty() {
		notEmpty("user");
		return this;
	}
        public MetaStoreDbCriteria andUserLike(java.lang.String value) {
    	   addCriterion("user", value, ConditionMode.FUZZY, "user", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreDbCriteria andUserNotLike(java.lang.String value) {
          addCriterion("user", value, ConditionMode.NOT_FUZZY, "user", "java.lang.String", "String");
          return this;
      }
      public MetaStoreDbCriteria andUserEqualTo(java.lang.String value) {
          addCriterion("user", value, ConditionMode.EQUAL, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserNotEqualTo(java.lang.String value) {
          addCriterion("user", value, ConditionMode.NOT_EQUAL, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserGreaterThan(java.lang.String value) {
          addCriterion("user", value, ConditionMode.GREATER_THEN, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("user", value, ConditionMode.GREATER_EQUAL, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserLessThan(java.lang.String value) {
          addCriterion("user", value, ConditionMode.LESS_THEN, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserLessThanOrEqualTo(java.lang.String value) {
          addCriterion("user", value, ConditionMode.LESS_EQUAL, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("user", value1, value2, ConditionMode.BETWEEN, "user", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreDbCriteria andUserNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("user", value1, value2, ConditionMode.NOT_BETWEEN, "user", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreDbCriteria andUserIn(List<java.lang.String> values) {
          addCriterion("user", values, ConditionMode.IN, "user", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andUserNotIn(List<java.lang.String> values) {
          addCriterion("user", values, ConditionMode.NOT_IN, "user", "java.lang.String", "String");
          return this;
      }
	public MetaStoreDbCriteria andPasswordIsNull() {
		isnull("password");
		return this;
	}
	
	public MetaStoreDbCriteria andPasswordIsNotNull() {
		notNull("password");
		return this;
	}
	
	public MetaStoreDbCriteria andPasswordIsEmpty() {
		empty("password");
		return this;
	}

	public MetaStoreDbCriteria andPasswordIsNotEmpty() {
		notEmpty("password");
		return this;
	}
        public MetaStoreDbCriteria andPasswordLike(java.lang.String value) {
    	   addCriterion("password", value, ConditionMode.FUZZY, "password", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreDbCriteria andPasswordNotLike(java.lang.String value) {
          addCriterion("password", value, ConditionMode.NOT_FUZZY, "password", "java.lang.String", "String");
          return this;
      }
      public MetaStoreDbCriteria andPasswordEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordNotEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.NOT_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordGreaterThan(java.lang.String value) {
          addCriterion("password", value, ConditionMode.GREATER_THEN, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.GREATER_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordLessThan(java.lang.String value) {
          addCriterion("password", value, ConditionMode.LESS_THEN, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordLessThanOrEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.LESS_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("password", value1, value2, ConditionMode.BETWEEN, "password", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreDbCriteria andPasswordNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("password", value1, value2, ConditionMode.NOT_BETWEEN, "password", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreDbCriteria andPasswordIn(List<java.lang.String> values) {
          addCriterion("password", values, ConditionMode.IN, "password", "java.lang.String", "String");
          return this;
      }

      public MetaStoreDbCriteria andPasswordNotIn(List<java.lang.String> values) {
          addCriterion("password", values, ConditionMode.NOT_IN, "password", "java.lang.String", "String");
          return this;
      }
}