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
public class MetaTableCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaTableCriteria create() {
		return new MetaTableCriteria();
	}
	
	public static MetaTableCriteria create(Column column) {
		MetaTableCriteria that = new MetaTableCriteria();
		that.add(column);
        return that;
    }

    public static MetaTableCriteria create(String name, Object value) {
        return (MetaTableCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_table", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaTableCriteria eq(String name, Object value) {
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
    public MetaTableCriteria ne(String name, Object value) {
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

    public MetaTableCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaTableCriteria notLike(String name, Object value) {
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
    public MetaTableCriteria gt(String name, Object value) {
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
    public MetaTableCriteria ge(String name, Object value) {
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
    public MetaTableCriteria lt(String name, Object value) {
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
    public MetaTableCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaTableCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaTableCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaTableCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaTableCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaTableCriteria add(Column column) {
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
		 
	public MetaTableCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaTableCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaTableCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaTableCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaTableCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaTableCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaTableCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaTableCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaTableCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaTableCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaTableCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaTableCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaTableCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaTableCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaTableCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public MetaTableCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public MetaTableCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public MetaTableCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public MetaTableCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaTableCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaTableCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public MetaTableCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaTableCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaTableCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaTableCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaTableCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaTableCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaTableCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public MetaTableCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public MetaTableCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public MetaTableCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public MetaTableCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "String");
    	   return this;
      }

      public MetaTableCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "String");
          return this;
      }
      public MetaTableCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andKeynameIsNull() {
		isnull("keyname");
		return this;
	}
	
	public MetaTableCriteria andKeynameIsNotNull() {
		notNull("keyname");
		return this;
	}
	
	public MetaTableCriteria andKeynameIsEmpty() {
		empty("keyname");
		return this;
	}

	public MetaTableCriteria andKeynameIsNotEmpty() {
		notEmpty("keyname");
		return this;
	}
        public MetaTableCriteria andKeynameLike(java.lang.String value) {
    	   addCriterion("keyname", value, ConditionMode.FUZZY, "keyname", "java.lang.String", "String");
    	   return this;
      }

      public MetaTableCriteria andKeynameNotLike(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.NOT_FUZZY, "keyname", "java.lang.String", "String");
          return this;
      }
      public MetaTableCriteria andKeynameEqualTo(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.EQUAL, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameNotEqualTo(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.NOT_EQUAL, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameGreaterThan(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.GREATER_THEN, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.GREATER_EQUAL, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameLessThan(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.LESS_THEN, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("keyname", value, ConditionMode.LESS_EQUAL, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("keyname", value1, value2, ConditionMode.BETWEEN, "keyname", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andKeynameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("keyname", value1, value2, ConditionMode.NOT_BETWEEN, "keyname", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andKeynameIn(List<java.lang.String> values) {
          addCriterion("keyname", values, ConditionMode.IN, "keyname", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andKeynameNotIn(List<java.lang.String> values) {
          addCriterion("keyname", values, ConditionMode.NOT_IN, "keyname", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andNamefldIsNull() {
		isnull("namefld");
		return this;
	}
	
	public MetaTableCriteria andNamefldIsNotNull() {
		notNull("namefld");
		return this;
	}
	
	public MetaTableCriteria andNamefldIsEmpty() {
		empty("namefld");
		return this;
	}

	public MetaTableCriteria andNamefldIsNotEmpty() {
		notEmpty("namefld");
		return this;
	}
        public MetaTableCriteria andNamefldLike(java.lang.String value) {
    	   addCriterion("namefld", value, ConditionMode.FUZZY, "namefld", "java.lang.String", "String");
    	   return this;
      }

      public MetaTableCriteria andNamefldNotLike(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.NOT_FUZZY, "namefld", "java.lang.String", "String");
          return this;
      }
      public MetaTableCriteria andNamefldEqualTo(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.EQUAL, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldNotEqualTo(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.NOT_EQUAL, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldGreaterThan(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.GREATER_THEN, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.GREATER_EQUAL, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldLessThan(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.LESS_THEN, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldLessThanOrEqualTo(java.lang.String value) {
          addCriterion("namefld", value, ConditionMode.LESS_EQUAL, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("namefld", value1, value2, ConditionMode.BETWEEN, "namefld", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andNamefldNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("namefld", value1, value2, ConditionMode.NOT_BETWEEN, "namefld", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andNamefldIn(List<java.lang.String> values) {
          addCriterion("namefld", values, ConditionMode.IN, "namefld", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andNamefldNotIn(List<java.lang.String> values) {
          addCriterion("namefld", values, ConditionMode.NOT_IN, "namefld", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andOlapTypeIsNull() {
		isnull("olap_type");
		return this;
	}
	
	public MetaTableCriteria andOlapTypeIsNotNull() {
		notNull("olap_type");
		return this;
	}
	
	public MetaTableCriteria andOlapTypeIsEmpty() {
		empty("olap_type");
		return this;
	}

	public MetaTableCriteria andOlapTypeIsNotEmpty() {
		notEmpty("olap_type");
		return this;
	}
        public MetaTableCriteria andOlapTypeLike(java.lang.String value) {
    	   addCriterion("olap_type", value, ConditionMode.FUZZY, "olapType", "java.lang.String", "String");
    	   return this;
      }

      public MetaTableCriteria andOlapTypeNotLike(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.NOT_FUZZY, "olapType", "java.lang.String", "String");
          return this;
      }
      public MetaTableCriteria andOlapTypeEqualTo(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.EQUAL, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeNotEqualTo(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.NOT_EQUAL, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeGreaterThan(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.GREATER_THEN, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.GREATER_EQUAL, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeLessThan(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.LESS_THEN, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("olap_type", value, ConditionMode.LESS_EQUAL, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("olap_type", value1, value2, ConditionMode.BETWEEN, "olapType", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andOlapTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("olap_type", value1, value2, ConditionMode.NOT_BETWEEN, "olapType", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andOlapTypeIn(List<java.lang.String> values) {
          addCriterion("olap_type", values, ConditionMode.IN, "olapType", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andOlapTypeNotIn(List<java.lang.String> values) {
          addCriterion("olap_type", values, ConditionMode.NOT_IN, "olapType", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaTableCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaTableCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaTableCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaTableCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "String");
    	   return this;
      }

      public MetaTableCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "String");
          return this;
      }
      public MetaTableCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
	public MetaTableCriteria andCreatedIsNull() {
		isnull("created");
		return this;
	}
	
	public MetaTableCriteria andCreatedIsNotNull() {
		notNull("created");
		return this;
	}
	
	public MetaTableCriteria andCreatedIsEmpty() {
		empty("created");
		return this;
	}

	public MetaTableCriteria andCreatedIsNotEmpty() {
		notEmpty("created");
		return this;
	}
       public MetaTableCriteria andCreatedEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedNotEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.NOT_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedGreaterThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedLessThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("created", value1, value2, ConditionMode.BETWEEN, "created", "java.util.Date", "String");
    	  return this;
      }

      public MetaTableCriteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("created", value1, value2, ConditionMode.NOT_BETWEEN, "created", "java.util.Date", "String");
          return this;
      }
        
      public MetaTableCriteria andCreatedIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.IN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andCreatedNotIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.NOT_IN, "created", "java.util.Date", "String");
          return this;
      }
	public MetaTableCriteria andModifiedIsNull() {
		isnull("modified");
		return this;
	}
	
	public MetaTableCriteria andModifiedIsNotNull() {
		notNull("modified");
		return this;
	}
	
	public MetaTableCriteria andModifiedIsEmpty() {
		empty("modified");
		return this;
	}

	public MetaTableCriteria andModifiedIsNotEmpty() {
		notEmpty("modified");
		return this;
	}
       public MetaTableCriteria andModifiedEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedNotEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.NOT_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedGreaterThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedLessThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("modified", value1, value2, ConditionMode.BETWEEN, "modified", "java.util.Date", "String");
    	  return this;
      }

      public MetaTableCriteria andModifiedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("modified", value1, value2, ConditionMode.NOT_BETWEEN, "modified", "java.util.Date", "String");
          return this;
      }
        
      public MetaTableCriteria andModifiedIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.IN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaTableCriteria andModifiedNotIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.NOT_IN, "modified", "java.util.Date", "String");
          return this;
      }
	public MetaTableCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaTableCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaTableCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaTableCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public MetaTableCriteria andStatusEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusNotEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusGreaterThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusLessThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaTableCriteria andStatusNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaTableCriteria andStatusIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public MetaTableCriteria andStatusNotIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Integer", "Float");
          return this;
      }
	public MetaTableCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaTableCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaTableCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaTableCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaTableCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaTableCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaTableCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaTableCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaTableCriteria andPermissionIsNull() {
		isnull("permission");
		return this;
	}
	
	public MetaTableCriteria andPermissionIsNotNull() {
		notNull("permission");
		return this;
	}
	
	public MetaTableCriteria andPermissionIsEmpty() {
		empty("permission");
		return this;
	}

	public MetaTableCriteria andPermissionIsNotEmpty() {
		notEmpty("permission");
		return this;
	}
        public MetaTableCriteria andPermissionLike(java.lang.String value) {
    	   addCriterion("permission", value, ConditionMode.FUZZY, "permission", "java.lang.String", "Float");
    	   return this;
      }

      public MetaTableCriteria andPermissionNotLike(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_FUZZY, "permission", "java.lang.String", "Float");
          return this;
      }
      public MetaTableCriteria andPermissionEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionNotEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionGreaterThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionLessThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permission", value1, value2, ConditionMode.BETWEEN, "permission", "java.lang.String", "String");
    	  return this;
      }

      public MetaTableCriteria andPermissionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permission", value1, value2, ConditionMode.NOT_BETWEEN, "permission", "java.lang.String", "String");
          return this;
      }
        
      public MetaTableCriteria andPermissionIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.IN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaTableCriteria andPermissionNotIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.NOT_IN, "permission", "java.lang.String", "String");
          return this;
      }
}