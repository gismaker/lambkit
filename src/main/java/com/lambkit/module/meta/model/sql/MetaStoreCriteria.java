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
public class MetaStoreCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaStoreCriteria create() {
		return new MetaStoreCriteria();
	}
	
	public static MetaStoreCriteria create(Column column) {
		MetaStoreCriteria that = new MetaStoreCriteria();
		that.add(column);
        return that;
    }

    public static MetaStoreCriteria create(String name, Object value) {
        return (MetaStoreCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_store", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaStoreCriteria eq(String name, Object value) {
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
    public MetaStoreCriteria ne(String name, Object value) {
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

    public MetaStoreCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaStoreCriteria notLike(String name, Object value) {
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
    public MetaStoreCriteria gt(String name, Object value) {
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
    public MetaStoreCriteria ge(String name, Object value) {
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
    public MetaStoreCriteria lt(String name, Object value) {
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
    public MetaStoreCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaStoreCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaStoreCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaStoreCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaStoreCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaStoreCriteria add(Column column) {
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
		 
	public MetaStoreCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaStoreCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaStoreCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaStoreCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaStoreCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreCriteria andAppidIsNull() {
		isnull("appid");
		return this;
	}
	
	public MetaStoreCriteria andAppidIsNotNull() {
		notNull("appid");
		return this;
	}
	
	public MetaStoreCriteria andAppidIsEmpty() {
		empty("appid");
		return this;
	}

	public MetaStoreCriteria andAppidIsNotEmpty() {
		notEmpty("appid");
		return this;
	}
       public MetaStoreCriteria andAppidEqualTo(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.EQUAL, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidNotEqualTo(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.NOT_EQUAL, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidGreaterThan(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.GREATER_THEN, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.GREATER_EQUAL, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidLessThan(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.LESS_THEN, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("appid", value, ConditionMode.LESS_EQUAL, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("appid", value1, value2, ConditionMode.BETWEEN, "appid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaStoreCriteria andAppidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("appid", value1, value2, ConditionMode.NOT_BETWEEN, "appid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaStoreCriteria andAppidIn(List<java.lang.Long> values) {
          addCriterion("appid", values, ConditionMode.IN, "appid", "java.lang.Long", "Float");
          return this;
      }

      public MetaStoreCriteria andAppidNotIn(List<java.lang.Long> values) {
          addCriterion("appid", values, ConditionMode.NOT_IN, "appid", "java.lang.Long", "Float");
          return this;
      }
	public MetaStoreCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaStoreCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaStoreCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaStoreCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaStoreCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaStoreCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaStoreCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaStoreCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaStoreCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaStoreCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaStoreCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaStoreCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "String");
    	   return this;
      }

      public MetaStoreCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "String");
          return this;
      }
      public MetaStoreCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaStoreCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaStoreCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaStoreCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
}