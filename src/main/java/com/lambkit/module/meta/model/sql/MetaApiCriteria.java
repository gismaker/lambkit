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
public class MetaApiCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaApiCriteria create() {
		return new MetaApiCriteria();
	}
	
	public static MetaApiCriteria create(Column column) {
		MetaApiCriteria that = new MetaApiCriteria();
		that.add(column);
        return that;
    }

    public static MetaApiCriteria create(String name, Object value) {
        return (MetaApiCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_api", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaApiCriteria eq(String name, Object value) {
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
    public MetaApiCriteria ne(String name, Object value) {
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

    public MetaApiCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaApiCriteria notLike(String name, Object value) {
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
    public MetaApiCriteria gt(String name, Object value) {
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
    public MetaApiCriteria ge(String name, Object value) {
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
    public MetaApiCriteria lt(String name, Object value) {
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
    public MetaApiCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaApiCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaApiCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaApiCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaApiCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaApiCriteria add(Column column) {
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
		 
	public MetaApiCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaApiCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaApiCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaApiCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaApiCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaApiCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaApiCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaApiCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaApiCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaApiCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaApiCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaApiCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaApiCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaApiCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaApiCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaApiCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaApiCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaApiCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaApiCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaApiCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaApiCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaApiCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaApiCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaApiCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaApiCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public MetaApiCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public MetaApiCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public MetaApiCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public MetaApiCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public MetaApiCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public MetaApiCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public MetaApiCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public MetaApiCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public MetaApiCriteria andActionIsNull() {
		isnull("action");
		return this;
	}
	
	public MetaApiCriteria andActionIsNotNull() {
		notNull("action");
		return this;
	}
	
	public MetaApiCriteria andActionIsEmpty() {
		empty("action");
		return this;
	}

	public MetaApiCriteria andActionIsNotEmpty() {
		notEmpty("action");
		return this;
	}
        public MetaApiCriteria andActionLike(java.lang.String value) {
    	   addCriterion("action", value, ConditionMode.FUZZY, "action", "java.lang.String", "String");
    	   return this;
      }

      public MetaApiCriteria andActionNotLike(java.lang.String value) {
          addCriterion("action", value, ConditionMode.NOT_FUZZY, "action", "java.lang.String", "String");
          return this;
      }
      public MetaApiCriteria andActionEqualTo(java.lang.String value) {
          addCriterion("action", value, ConditionMode.EQUAL, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionNotEqualTo(java.lang.String value) {
          addCriterion("action", value, ConditionMode.NOT_EQUAL, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionGreaterThan(java.lang.String value) {
          addCriterion("action", value, ConditionMode.GREATER_THEN, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("action", value, ConditionMode.GREATER_EQUAL, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionLessThan(java.lang.String value) {
          addCriterion("action", value, ConditionMode.LESS_THEN, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("action", value, ConditionMode.LESS_EQUAL, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("action", value1, value2, ConditionMode.BETWEEN, "action", "java.lang.String", "String");
    	  return this;
      }

      public MetaApiCriteria andActionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("action", value1, value2, ConditionMode.NOT_BETWEEN, "action", "java.lang.String", "String");
          return this;
      }
        
      public MetaApiCriteria andActionIn(List<java.lang.String> values) {
          addCriterion("action", values, ConditionMode.IN, "action", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andActionNotIn(List<java.lang.String> values) {
          addCriterion("action", values, ConditionMode.NOT_IN, "action", "java.lang.String", "String");
          return this;
      }
	public MetaApiCriteria andFormatIsNull() {
		isnull("format");
		return this;
	}
	
	public MetaApiCriteria andFormatIsNotNull() {
		notNull("format");
		return this;
	}
	
	public MetaApiCriteria andFormatIsEmpty() {
		empty("format");
		return this;
	}

	public MetaApiCriteria andFormatIsNotEmpty() {
		notEmpty("format");
		return this;
	}
        public MetaApiCriteria andFormatLike(java.lang.String value) {
    	   addCriterion("format", value, ConditionMode.FUZZY, "format", "java.lang.String", "String");
    	   return this;
      }

      public MetaApiCriteria andFormatNotLike(java.lang.String value) {
          addCriterion("format", value, ConditionMode.NOT_FUZZY, "format", "java.lang.String", "String");
          return this;
      }
      public MetaApiCriteria andFormatEqualTo(java.lang.String value) {
          addCriterion("format", value, ConditionMode.EQUAL, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatNotEqualTo(java.lang.String value) {
          addCriterion("format", value, ConditionMode.NOT_EQUAL, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatGreaterThan(java.lang.String value) {
          addCriterion("format", value, ConditionMode.GREATER_THEN, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("format", value, ConditionMode.GREATER_EQUAL, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatLessThan(java.lang.String value) {
          addCriterion("format", value, ConditionMode.LESS_THEN, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatLessThanOrEqualTo(java.lang.String value) {
          addCriterion("format", value, ConditionMode.LESS_EQUAL, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("format", value1, value2, ConditionMode.BETWEEN, "format", "java.lang.String", "String");
    	  return this;
      }

      public MetaApiCriteria andFormatNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("format", value1, value2, ConditionMode.NOT_BETWEEN, "format", "java.lang.String", "String");
          return this;
      }
        
      public MetaApiCriteria andFormatIn(List<java.lang.String> values) {
          addCriterion("format", values, ConditionMode.IN, "format", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andFormatNotIn(List<java.lang.String> values) {
          addCriterion("format", values, ConditionMode.NOT_IN, "format", "java.lang.String", "String");
          return this;
      }
	public MetaApiCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaApiCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaApiCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaApiCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
        public MetaApiCriteria andStatusLike(java.lang.String value) {
    	   addCriterion("status", value, ConditionMode.FUZZY, "status", "java.lang.String", "String");
    	   return this;
      }

      public MetaApiCriteria andStatusNotLike(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_FUZZY, "status", "java.lang.String", "String");
          return this;
      }
      public MetaApiCriteria andStatusEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusNotEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusGreaterThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusLessThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusLessThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.String", "String");
    	  return this;
      }

      public MetaApiCriteria andStatusNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.String", "String");
          return this;
      }
        
      public MetaApiCriteria andStatusIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaApiCriteria andStatusNotIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.String", "String");
          return this;
      }
	public MetaApiCriteria andViewCountIsNull() {
		isnull("view_count");
		return this;
	}
	
	public MetaApiCriteria andViewCountIsNotNull() {
		notNull("view_count");
		return this;
	}
	
	public MetaApiCriteria andViewCountIsEmpty() {
		empty("view_count");
		return this;
	}

	public MetaApiCriteria andViewCountIsNotEmpty() {
		notEmpty("view_count");
		return this;
	}
       public MetaApiCriteria andViewCountEqualTo(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.EQUAL, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountNotEqualTo(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.NOT_EQUAL, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountGreaterThan(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.GREATER_THEN, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.GREATER_EQUAL, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountLessThan(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.LESS_THEN, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("view_count", value, ConditionMode.LESS_EQUAL, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("view_count", value1, value2, ConditionMode.BETWEEN, "viewCount", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaApiCriteria andViewCountNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("view_count", value1, value2, ConditionMode.NOT_BETWEEN, "viewCount", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaApiCriteria andViewCountIn(List<java.lang.Integer> values) {
          addCriterion("view_count", values, ConditionMode.IN, "viewCount", "java.lang.Integer", "Float");
          return this;
      }

      public MetaApiCriteria andViewCountNotIn(List<java.lang.Integer> values) {
          addCriterion("view_count", values, ConditionMode.NOT_IN, "viewCount", "java.lang.Integer", "Float");
          return this;
      }
}