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
public class MetaStoreRouteCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaStoreRouteCriteria create() {
		return new MetaStoreRouteCriteria();
	}
	
	public static MetaStoreRouteCriteria create(Column column) {
		MetaStoreRouteCriteria that = new MetaStoreRouteCriteria();
		that.add(column);
        return that;
    }

    public static MetaStoreRouteCriteria create(String name, Object value) {
        return (MetaStoreRouteCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_store_route", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaStoreRouteCriteria eq(String name, Object value) {
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
    public MetaStoreRouteCriteria ne(String name, Object value) {
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

    public MetaStoreRouteCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaStoreRouteCriteria notLike(String name, Object value) {
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
    public MetaStoreRouteCriteria gt(String name, Object value) {
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
    public MetaStoreRouteCriteria ge(String name, Object value) {
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
    public MetaStoreRouteCriteria lt(String name, Object value) {
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
    public MetaStoreRouteCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaStoreRouteCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaStoreRouteCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaStoreRouteCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaStoreRouteCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaStoreRouteCriteria add(Column column) {
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
		 
	public MetaStoreRouteCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaStoreRouteCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaStoreRouteCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaStoreRouteCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaStoreRouteCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreRouteCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreRouteCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreRouteCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaStoreRouteCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaStoreRouteCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaStoreRouteCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaStoreRouteCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreRouteCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreRouteCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreRouteCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreRouteCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaStoreRouteCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaStoreRouteCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaStoreRouteCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaStoreRouteCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaStoreRouteCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaStoreRouteCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreRouteCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreRouteCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaStoreRouteCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public MetaStoreRouteCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public MetaStoreRouteCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public MetaStoreRouteCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public MetaStoreRouteCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreRouteCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public MetaStoreRouteCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreRouteCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreRouteCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public MetaStoreRouteCriteria andSummaryIsNull() {
		isnull("summary");
		return this;
	}
	
	public MetaStoreRouteCriteria andSummaryIsNotNull() {
		notNull("summary");
		return this;
	}
	
	public MetaStoreRouteCriteria andSummaryIsEmpty() {
		empty("summary");
		return this;
	}

	public MetaStoreRouteCriteria andSummaryIsNotEmpty() {
		notEmpty("summary");
		return this;
	}
        public MetaStoreRouteCriteria andSummaryLike(java.lang.String value) {
    	   addCriterion("summary", value, ConditionMode.FUZZY, "summary", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreRouteCriteria andSummaryNotLike(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.NOT_FUZZY, "summary", "java.lang.String", "String");
          return this;
      }
      public MetaStoreRouteCriteria andSummaryEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryNotEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.NOT_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryGreaterThan(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.GREATER_THEN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.GREATER_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryLessThan(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.LESS_THEN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryLessThanOrEqualTo(java.lang.String value) {
          addCriterion("summary", value, ConditionMode.LESS_EQUAL, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("summary", value1, value2, ConditionMode.BETWEEN, "summary", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreRouteCriteria andSummaryNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("summary", value1, value2, ConditionMode.NOT_BETWEEN, "summary", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreRouteCriteria andSummaryIn(List<java.lang.String> values) {
          addCriterion("summary", values, ConditionMode.IN, "summary", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andSummaryNotIn(List<java.lang.String> values) {
          addCriterion("summary", values, ConditionMode.NOT_IN, "summary", "java.lang.String", "String");
          return this;
      }
	public MetaStoreRouteCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaStoreRouteCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaStoreRouteCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaStoreRouteCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
        public MetaStoreRouteCriteria andStatusLike(java.lang.String value) {
    	   addCriterion("status", value, ConditionMode.FUZZY, "status", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreRouteCriteria andStatusNotLike(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_FUZZY, "status", "java.lang.String", "String");
          return this;
      }
      public MetaStoreRouteCriteria andStatusEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusNotEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusGreaterThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusLessThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusLessThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreRouteCriteria andStatusNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreRouteCriteria andStatusIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaStoreRouteCriteria andStatusNotIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.String", "String");
          return this;
      }
}