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
public class MetaStoreResourceCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaStoreResourceCriteria create() {
		return new MetaStoreResourceCriteria();
	}
	
	public static MetaStoreResourceCriteria create(Column column) {
		MetaStoreResourceCriteria that = new MetaStoreResourceCriteria();
		that.add(column);
        return that;
    }

    public static MetaStoreResourceCriteria create(String name, Object value) {
        return (MetaStoreResourceCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_store_resource", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaStoreResourceCriteria eq(String name, Object value) {
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
    public MetaStoreResourceCriteria ne(String name, Object value) {
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

    public MetaStoreResourceCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaStoreResourceCriteria notLike(String name, Object value) {
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
    public MetaStoreResourceCriteria gt(String name, Object value) {
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
    public MetaStoreResourceCriteria ge(String name, Object value) {
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
    public MetaStoreResourceCriteria lt(String name, Object value) {
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
    public MetaStoreResourceCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaStoreResourceCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaStoreResourceCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaStoreResourceCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaStoreResourceCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaStoreResourceCriteria add(Column column) {
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
		 
	public MetaStoreResourceCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaStoreResourceCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaStoreResourceCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaStoreResourceCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaStoreResourceCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreResourceCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreResourceCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreResourceCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaStoreResourceCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaStoreResourceCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaStoreResourceCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaStoreResourceCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreResourceCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreResourceCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreResourceCriteria andPidIsNull() {
		isnull("pid");
		return this;
	}
	
	public MetaStoreResourceCriteria andPidIsNotNull() {
		notNull("pid");
		return this;
	}
	
	public MetaStoreResourceCriteria andPidIsEmpty() {
		empty("pid");
		return this;
	}

	public MetaStoreResourceCriteria andPidIsNotEmpty() {
		notEmpty("pid");
		return this;
	}
       public MetaStoreResourceCriteria andPidEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidNotEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.NOT_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidGreaterThan(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.GREATER_THEN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.GREATER_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidLessThan(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.LESS_THEN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.LESS_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("pid", value1, value2, ConditionMode.BETWEEN, "pid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreResourceCriteria andPidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("pid", value1, value2, ConditionMode.NOT_BETWEEN, "pid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreResourceCriteria andPidIn(List<java.lang.Long> values) {
          addCriterion("pid", values, ConditionMode.IN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreResourceCriteria andPidNotIn(List<java.lang.Long> values) {
          addCriterion("pid", values, ConditionMode.NOT_IN, "pid", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreResourceCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaStoreResourceCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaStoreResourceCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaStoreResourceCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaStoreResourceCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaStoreResourceCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaStoreResourceCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreResourceCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreResourceCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaStoreResourceCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public MetaStoreResourceCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public MetaStoreResourceCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public MetaStoreResourceCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public MetaStoreResourceCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreResourceCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public MetaStoreResourceCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreResourceCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreResourceCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public MetaStoreResourceCriteria andSummaryIsNull() {
		isnull("summary");
		return this;
	}
	
	public MetaStoreResourceCriteria andSummaryIsNotNull() {
		notNull("summary");
		return this;
	}
	
	public MetaStoreResourceCriteria andSummaryIsEmpty() {
		empty("summary");
		return this;
	}

	public MetaStoreResourceCriteria andSummaryIsNotEmpty() {
		notEmpty("summary");
		return this;
	}
        public MetaStoreResourceCriteria andSummaryLike(java.lang.String value) {
    	   addCriterion("summary", value, ConditionMode.FUZZY, "summary", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreResourceCriteria andSummaryNotLike(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.NOT_FUZZY, "summary", "java.lang.String", "String");
          return this;
      }
      public MetaStoreResourceCriteria andSummaryEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryNotEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.NOT_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryGreaterThan(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.GREATER_THEN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.GREATER_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryLessThan(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.LESS_THEN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryLessThanOrEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.LESS_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("summary", value1, value2, ConditionMode.BETWEEN, "summary", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreResourceCriteria andSummaryNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("summary", value1, value2, ConditionMode.NOT_BETWEEN, "summary", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreResourceCriteria andSummaryIn(List<java.lang.String> values) {
          addCriterion("summary", values, ConditionMode.IN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andSummaryNotIn(List<java.lang.String> values) {
          addCriterion("summary", values, ConditionMode.NOT_IN, "summary", "java.lang.String", "String");
          return this;
      }
	public MetaStoreResourceCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaStoreResourceCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaStoreResourceCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaStoreResourceCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
        public MetaStoreResourceCriteria andStatusLike(java.lang.String value) {
    	   addCriterion("status", value, ConditionMode.FUZZY, "status", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreResourceCriteria andStatusNotLike(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_FUZZY, "status", "java.lang.String", "String");
          return this;
      }
      public MetaStoreResourceCriteria andStatusEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusNotEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusGreaterThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusLessThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusLessThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreResourceCriteria andStatusNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreResourceCriteria andStatusIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreResourceCriteria andStatusNotIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.String", "String");
          return this;
      }
}