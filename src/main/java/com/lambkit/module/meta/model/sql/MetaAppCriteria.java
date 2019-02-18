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
public class MetaAppCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaAppCriteria create() {
		return new MetaAppCriteria();
	}
	
	public static MetaAppCriteria create(Column column) {
		MetaAppCriteria that = new MetaAppCriteria();
		that.add(column);
        return that;
    }

    public static MetaAppCriteria create(String name, Object value) {
        return (MetaAppCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_app", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaAppCriteria eq(String name, Object value) {
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
    public MetaAppCriteria ne(String name, Object value) {
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

    public MetaAppCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaAppCriteria notLike(String name, Object value) {
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
    public MetaAppCriteria gt(String name, Object value) {
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
    public MetaAppCriteria ge(String name, Object value) {
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
    public MetaAppCriteria lt(String name, Object value) {
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
    public MetaAppCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaAppCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaAppCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaAppCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaAppCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaAppCriteria add(Column column) {
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
		 
	public MetaAppCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaAppCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaAppCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaAppCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaAppCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaAppCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaAppCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaAppCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaAppCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaAppCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaAppCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaAppCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaAppCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaAppCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaAppCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaAppCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaAppCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaAppCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public MetaAppCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public MetaAppCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public MetaAppCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public MetaAppCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public MetaAppCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public MetaAppCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public MetaAppCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public MetaAppCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public MetaAppCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaAppCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaAppCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaAppCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
        public MetaAppCriteria andStatusLike(java.lang.String value) {
    	   addCriterion("status", value, ConditionMode.FUZZY, "status", "java.lang.String", "String");
    	   return this;
      }

      public MetaAppCriteria andStatusNotLike(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_FUZZY, "status", "java.lang.String", "String");
          return this;
      }
      public MetaAppCriteria andStatusEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusNotEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusGreaterThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusLessThan(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusLessThanOrEqualTo(java.lang.String value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.String", "String");
    	  return this;
      }

      public MetaAppCriteria andStatusNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.String", "String");
          return this;
      }
        
      public MetaAppCriteria andStatusIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.String", "String");
          return this;
      }

      public MetaAppCriteria andStatusNotIn(List<java.lang.String> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.String", "String");
          return this;
      }
}