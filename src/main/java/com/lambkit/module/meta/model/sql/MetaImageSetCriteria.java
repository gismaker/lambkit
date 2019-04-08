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
public class MetaImageSetCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaImageSetCriteria create() {
		return new MetaImageSetCriteria();
	}
	
	public static MetaImageSetCriteria create(Column column) {
		MetaImageSetCriteria that = new MetaImageSetCriteria();
		that.add(column);
        return that;
    }

    public static MetaImageSetCriteria create(String name, Object value) {
        return (MetaImageSetCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_image_set", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaImageSetCriteria eq(String name, Object value) {
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
    public MetaImageSetCriteria ne(String name, Object value) {
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

    public MetaImageSetCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaImageSetCriteria notLike(String name, Object value) {
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
    public MetaImageSetCriteria gt(String name, Object value) {
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
    public MetaImageSetCriteria ge(String name, Object value) {
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
    public MetaImageSetCriteria lt(String name, Object value) {
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
    public MetaImageSetCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaImageSetCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaImageSetCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaImageSetCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaImageSetCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaImageSetCriteria add(Column column) {
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
		 
	public MetaImageSetCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaImageSetCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaImageSetCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaImageSetCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaImageSetCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageSetCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageSetCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageSetCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaImageSetCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaImageSetCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaImageSetCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaImageSetCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageSetCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageSetCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageSetCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public MetaImageSetCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public MetaImageSetCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public MetaImageSetCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public MetaImageSetCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageSetCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageSetCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageSetCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaImageSetCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaImageSetCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaImageSetCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaImageSetCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaImageSetCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaImageSetCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageSetCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageSetCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaImageSetCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public MetaImageSetCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public MetaImageSetCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public MetaImageSetCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public MetaImageSetCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageSetCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public MetaImageSetCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageSetCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageSetCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public MetaImageSetCriteria andPathIsNull() {
		isnull("path");
		return this;
	}
	
	public MetaImageSetCriteria andPathIsNotNull() {
		notNull("path");
		return this;
	}
	
	public MetaImageSetCriteria andPathIsEmpty() {
		empty("path");
		return this;
	}

	public MetaImageSetCriteria andPathIsNotEmpty() {
		notEmpty("path");
		return this;
	}
        public MetaImageSetCriteria andPathLike(java.lang.String value) {
    	   addCriterion("path", value, ConditionMode.FUZZY, "path", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageSetCriteria andPathNotLike(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_FUZZY, "path", "java.lang.String", "String");
          return this;
      }
      public MetaImageSetCriteria andPathEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathNotEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathGreaterThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathLessThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathLessThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("path", value1, value2, ConditionMode.BETWEEN, "path", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageSetCriteria andPathNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("path", value1, value2, ConditionMode.NOT_BETWEEN, "path", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageSetCriteria andPathIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.IN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andPathNotIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.NOT_IN, "path", "java.lang.String", "String");
          return this;
      }
	public MetaImageSetCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaImageSetCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaImageSetCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaImageSetCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaImageSetCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageSetCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "String");
          return this;
      }
      public MetaImageSetCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageSetCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageSetCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
	public MetaImageSetCriteria andFlagIsNull() {
		isnull("flag");
		return this;
	}
	
	public MetaImageSetCriteria andFlagIsNotNull() {
		notNull("flag");
		return this;
	}
	
	public MetaImageSetCriteria andFlagIsEmpty() {
		empty("flag");
		return this;
	}

	public MetaImageSetCriteria andFlagIsNotEmpty() {
		notEmpty("flag");
		return this;
	}
        public MetaImageSetCriteria andFlagLike(java.lang.String value) {
    	   addCriterion("flag", value, ConditionMode.FUZZY, "flag", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageSetCriteria andFlagNotLike(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_FUZZY, "flag", "java.lang.String", "String");
          return this;
      }
      public MetaImageSetCriteria andFlagEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagNotEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagGreaterThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagLessThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagLessThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("flag", value1, value2, ConditionMode.BETWEEN, "flag", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageSetCriteria andFlagNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("flag", value1, value2, ConditionMode.NOT_BETWEEN, "flag", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageSetCriteria andFlagIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.IN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaImageSetCriteria andFlagNotIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.NOT_IN, "flag", "java.lang.String", "String");
          return this;
      }
	public MetaImageSetCriteria andNumIsNull() {
		isnull("num");
		return this;
	}
	
	public MetaImageSetCriteria andNumIsNotNull() {
		notNull("num");
		return this;
	}
	
	public MetaImageSetCriteria andNumIsEmpty() {
		empty("num");
		return this;
	}

	public MetaImageSetCriteria andNumIsNotEmpty() {
		notEmpty("num");
		return this;
	}
       public MetaImageSetCriteria andNumEqualTo(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.EQUAL, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumNotEqualTo(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.NOT_EQUAL, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumGreaterThan(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.GREATER_THEN, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.GREATER_EQUAL, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumLessThan(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.LESS_THEN, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("num", value, ConditionMode.LESS_EQUAL, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("num", value1, value2, ConditionMode.BETWEEN, "num", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaImageSetCriteria andNumNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("num", value1, value2, ConditionMode.NOT_BETWEEN, "num", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaImageSetCriteria andNumIn(List<java.lang.Integer> values) {
          addCriterion("num", values, ConditionMode.IN, "num", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageSetCriteria andNumNotIn(List<java.lang.Integer> values) {
          addCriterion("num", values, ConditionMode.NOT_IN, "num", "java.lang.Integer", "Float");
          return this;
      }
	public MetaImageSetCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaImageSetCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaImageSetCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaImageSetCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaImageSetCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageSetCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageSetCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageSetCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageSetCriteria andCreatedIsNull() {
		isnull("created");
		return this;
	}
	
	public MetaImageSetCriteria andCreatedIsNotNull() {
		notNull("created");
		return this;
	}
	
	public MetaImageSetCriteria andCreatedIsEmpty() {
		empty("created");
		return this;
	}

	public MetaImageSetCriteria andCreatedIsNotEmpty() {
		notEmpty("created");
		return this;
	}
       public MetaImageSetCriteria andCreatedEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedNotEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.NOT_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedGreaterThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedLessThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("created", value1, value2, ConditionMode.BETWEEN, "created", "java.util.Date", "String");
    	  return this;
      }

      public MetaImageSetCriteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("created", value1, value2, ConditionMode.NOT_BETWEEN, "created", "java.util.Date", "String");
          return this;
      }
        
      public MetaImageSetCriteria andCreatedIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.IN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andCreatedNotIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.NOT_IN, "created", "java.util.Date", "String");
          return this;
      }
	public MetaImageSetCriteria andModifiedIsNull() {
		isnull("modified");
		return this;
	}
	
	public MetaImageSetCriteria andModifiedIsNotNull() {
		notNull("modified");
		return this;
	}
	
	public MetaImageSetCriteria andModifiedIsEmpty() {
		empty("modified");
		return this;
	}

	public MetaImageSetCriteria andModifiedIsNotEmpty() {
		notEmpty("modified");
		return this;
	}
       public MetaImageSetCriteria andModifiedEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedNotEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.NOT_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedGreaterThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedLessThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("modified", value1, value2, ConditionMode.BETWEEN, "modified", "java.util.Date", "String");
    	  return this;
      }

      public MetaImageSetCriteria andModifiedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("modified", value1, value2, ConditionMode.NOT_BETWEEN, "modified", "java.util.Date", "String");
          return this;
      }
        
      public MetaImageSetCriteria andModifiedIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.IN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaImageSetCriteria andModifiedNotIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.NOT_IN, "modified", "java.util.Date", "String");
          return this;
      }
	public MetaImageSetCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaImageSetCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaImageSetCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaImageSetCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public MetaImageSetCriteria andStatusEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusNotEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusGreaterThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusGreaterThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusLessThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusLessThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Boolean", "String");
    	  return this;
      }

      public MetaImageSetCriteria andStatusNotBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Boolean", "String");
          return this;
      }
        
      public MetaImageSetCriteria andStatusIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageSetCriteria andStatusNotIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Boolean", "String");
          return this;
      }
}