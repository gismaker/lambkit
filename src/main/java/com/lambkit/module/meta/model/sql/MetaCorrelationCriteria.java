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
public class MetaCorrelationCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaCorrelationCriteria create() {
		return new MetaCorrelationCriteria();
	}
	
	public static MetaCorrelationCriteria create(Column column) {
		MetaCorrelationCriteria that = new MetaCorrelationCriteria();
		that.add(column);
        return that;
    }

    public static MetaCorrelationCriteria create(String name, Object value) {
        return (MetaCorrelationCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_correlation", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaCorrelationCriteria eq(String name, Object value) {
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
    public MetaCorrelationCriteria ne(String name, Object value) {
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

    public MetaCorrelationCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaCorrelationCriteria notLike(String name, Object value) {
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
    public MetaCorrelationCriteria gt(String name, Object value) {
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
    public MetaCorrelationCriteria ge(String name, Object value) {
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
    public MetaCorrelationCriteria lt(String name, Object value) {
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
    public MetaCorrelationCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaCorrelationCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaCorrelationCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaCorrelationCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaCorrelationCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaCorrelationCriteria add(Column column) {
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
		 
	public MetaCorrelationCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaCorrelationCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaCorrelationCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaCorrelationCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaCorrelationCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaCorrelationCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaCorrelationCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaCorrelationCriteria andTbidIsNull() {
		isnull("tbid");
		return this;
	}
	
	public MetaCorrelationCriteria andTbidIsNotNull() {
		notNull("tbid");
		return this;
	}
	
	public MetaCorrelationCriteria andTbidIsEmpty() {
		empty("tbid");
		return this;
	}

	public MetaCorrelationCriteria andTbidIsNotEmpty() {
		notEmpty("tbid");
		return this;
	}
       public MetaCorrelationCriteria andTbidEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidNotEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.NOT_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidGreaterThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidLessThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tbid", value1, value2, ConditionMode.BETWEEN, "tbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaCorrelationCriteria andTbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tbid", value1, value2, ConditionMode.NOT_BETWEEN, "tbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaCorrelationCriteria andTbidIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.IN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaCorrelationCriteria andTbidNotIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.NOT_IN, "tbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaCorrelationCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaCorrelationCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaCorrelationCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaCorrelationCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaCorrelationCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaCorrelationCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaCorrelationCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaCorrelationCriteria andCodeIsNull() {
		isnull("code");
		return this;
	}
	
	public MetaCorrelationCriteria andCodeIsNotNull() {
		notNull("code");
		return this;
	}
	
	public MetaCorrelationCriteria andCodeIsEmpty() {
		empty("code");
		return this;
	}

	public MetaCorrelationCriteria andCodeIsNotEmpty() {
		notEmpty("code");
		return this;
	}
        public MetaCorrelationCriteria andCodeLike(java.lang.String value) {
    	   addCriterion("code", value, ConditionMode.FUZZY, "code", "java.lang.String", "String");
    	   return this;
      }

      public MetaCorrelationCriteria andCodeNotLike(java.lang.String value) {
          addCriterion("code", value, ConditionMode.NOT_FUZZY, "code", "java.lang.String", "String");
          return this;
      }
      public MetaCorrelationCriteria andCodeEqualTo(java.lang.String value) {
          addCriterion("code", value, ConditionMode.EQUAL, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeNotEqualTo(java.lang.String value) {
          addCriterion("code", value, ConditionMode.NOT_EQUAL, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeGreaterThan(java.lang.String value) {
          addCriterion("code", value, ConditionMode.GREATER_THEN, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("code", value, ConditionMode.GREATER_EQUAL, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeLessThan(java.lang.String value) {
          addCriterion("code", value, ConditionMode.LESS_THEN, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("code", value, ConditionMode.LESS_EQUAL, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("code", value1, value2, ConditionMode.BETWEEN, "code", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andCodeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("code", value1, value2, ConditionMode.NOT_BETWEEN, "code", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andCodeIn(List<java.lang.String> values) {
          addCriterion("code", values, ConditionMode.IN, "code", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andCodeNotIn(List<java.lang.String> values) {
          addCriterion("code", values, ConditionMode.NOT_IN, "code", "java.lang.String", "String");
          return this;
      }
	public MetaCorrelationCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public MetaCorrelationCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public MetaCorrelationCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public MetaCorrelationCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public MetaCorrelationCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "String");
    	   return this;
      }

      public MetaCorrelationCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "String");
          return this;
      }
      public MetaCorrelationCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public MetaCorrelationCriteria andUpidIsNull() {
		isnull("upid");
		return this;
	}
	
	public MetaCorrelationCriteria andUpidIsNotNull() {
		notNull("upid");
		return this;
	}
	
	public MetaCorrelationCriteria andUpidIsEmpty() {
		empty("upid");
		return this;
	}

	public MetaCorrelationCriteria andUpidIsNotEmpty() {
		notEmpty("upid");
		return this;
	}
        public MetaCorrelationCriteria andUpidLike(java.lang.String value) {
    	   addCriterion("upid", value, ConditionMode.FUZZY, "upid", "java.lang.String", "String");
    	   return this;
      }

      public MetaCorrelationCriteria andUpidNotLike(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.NOT_FUZZY, "upid", "java.lang.String", "String");
          return this;
      }
      public MetaCorrelationCriteria andUpidEqualTo(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.EQUAL, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidNotEqualTo(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.NOT_EQUAL, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidGreaterThan(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.GREATER_THEN, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.GREATER_EQUAL, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidLessThan(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.LESS_THEN, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidLessThanOrEqualTo(java.lang.String value) {
          addCriterion("upid", value, ConditionMode.LESS_EQUAL, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("upid", value1, value2, ConditionMode.BETWEEN, "upid", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andUpidNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("upid", value1, value2, ConditionMode.NOT_BETWEEN, "upid", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andUpidIn(List<java.lang.String> values) {
          addCriterion("upid", values, ConditionMode.IN, "upid", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUpidNotIn(List<java.lang.String> values) {
          addCriterion("upid", values, ConditionMode.NOT_IN, "upid", "java.lang.String", "String");
          return this;
      }
	public MetaCorrelationCriteria andUplinkIsNull() {
		isnull("uplink");
		return this;
	}
	
	public MetaCorrelationCriteria andUplinkIsNotNull() {
		notNull("uplink");
		return this;
	}
	
	public MetaCorrelationCriteria andUplinkIsEmpty() {
		empty("uplink");
		return this;
	}

	public MetaCorrelationCriteria andUplinkIsNotEmpty() {
		notEmpty("uplink");
		return this;
	}
        public MetaCorrelationCriteria andUplinkLike(java.lang.String value) {
    	   addCriterion("uplink", value, ConditionMode.FUZZY, "uplink", "java.lang.String", "String");
    	   return this;
      }

      public MetaCorrelationCriteria andUplinkNotLike(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.NOT_FUZZY, "uplink", "java.lang.String", "String");
          return this;
      }
      public MetaCorrelationCriteria andUplinkEqualTo(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.EQUAL, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkNotEqualTo(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.NOT_EQUAL, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkGreaterThan(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.GREATER_THEN, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.GREATER_EQUAL, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkLessThan(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.LESS_THEN, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkLessThanOrEqualTo(java.lang.String value) {
          addCriterion("uplink", value, ConditionMode.LESS_EQUAL, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("uplink", value1, value2, ConditionMode.BETWEEN, "uplink", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andUplinkNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("uplink", value1, value2, ConditionMode.NOT_BETWEEN, "uplink", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andUplinkIn(List<java.lang.String> values) {
          addCriterion("uplink", values, ConditionMode.IN, "uplink", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andUplinkNotIn(List<java.lang.String> values) {
          addCriterion("uplink", values, ConditionMode.NOT_IN, "uplink", "java.lang.String", "String");
          return this;
      }
	public MetaCorrelationCriteria andJoinsIsNull() {
		isnull("joins");
		return this;
	}
	
	public MetaCorrelationCriteria andJoinsIsNotNull() {
		notNull("joins");
		return this;
	}
	
	public MetaCorrelationCriteria andJoinsIsEmpty() {
		empty("joins");
		return this;
	}

	public MetaCorrelationCriteria andJoinsIsNotEmpty() {
		notEmpty("joins");
		return this;
	}
        public MetaCorrelationCriteria andJoinsLike(java.lang.String value) {
    	   addCriterion("joins", value, ConditionMode.FUZZY, "joins", "java.lang.String", "String");
    	   return this;
      }

      public MetaCorrelationCriteria andJoinsNotLike(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.NOT_FUZZY, "joins", "java.lang.String", "String");
          return this;
      }
      public MetaCorrelationCriteria andJoinsEqualTo(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.EQUAL, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsNotEqualTo(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.NOT_EQUAL, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsGreaterThan(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.GREATER_THEN, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.GREATER_EQUAL, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsLessThan(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.LESS_THEN, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsLessThanOrEqualTo(java.lang.String value) {
          addCriterion("joins", value, ConditionMode.LESS_EQUAL, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("joins", value1, value2, ConditionMode.BETWEEN, "joins", "java.lang.String", "String");
    	  return this;
      }

      public MetaCorrelationCriteria andJoinsNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("joins", value1, value2, ConditionMode.NOT_BETWEEN, "joins", "java.lang.String", "String");
          return this;
      }
        
      public MetaCorrelationCriteria andJoinsIn(List<java.lang.String> values) {
          addCriterion("joins", values, ConditionMode.IN, "joins", "java.lang.String", "String");
          return this;
      }

      public MetaCorrelationCriteria andJoinsNotIn(List<java.lang.String> values) {
          addCriterion("joins", values, ConditionMode.NOT_IN, "joins", "java.lang.String", "String");
          return this;
      }
}