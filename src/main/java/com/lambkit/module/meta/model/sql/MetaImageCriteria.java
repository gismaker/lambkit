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
public class MetaImageCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaImageCriteria create() {
		return new MetaImageCriteria();
	}
	
	public static MetaImageCriteria create(Column column) {
		MetaImageCriteria that = new MetaImageCriteria();
		that.add(column);
        return that;
    }

    public static MetaImageCriteria create(String name, Object value) {
        return (MetaImageCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_image", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaImageCriteria eq(String name, Object value) {
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
    public MetaImageCriteria ne(String name, Object value) {
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

    public MetaImageCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaImageCriteria notLike(String name, Object value) {
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
    public MetaImageCriteria gt(String name, Object value) {
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
    public MetaImageCriteria ge(String name, Object value) {
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
    public MetaImageCriteria lt(String name, Object value) {
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
    public MetaImageCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaImageCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaImageCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaImageCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaImageCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaImageCriteria add(Column column) {
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
		 
	public MetaImageCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaImageCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaImageCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaImageCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaImageCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaImageCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaImageCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaImageCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaImageCriteria andSidEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidNotEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidGreaterThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidLessThan(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageCriteria andSidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageCriteria andSidIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andSidNotIn(List<java.lang.Long> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public MetaImageCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public MetaImageCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public MetaImageCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public MetaImageCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaImageCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaImageCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaImageCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaImageCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaImageCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaImageCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaImageCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public MetaImageCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public MetaImageCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public MetaImageCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public MetaImageCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public MetaImageCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public MetaImageCriteria andPathIsNull() {
		isnull("path");
		return this;
	}
	
	public MetaImageCriteria andPathIsNotNull() {
		notNull("path");
		return this;
	}
	
	public MetaImageCriteria andPathIsEmpty() {
		empty("path");
		return this;
	}

	public MetaImageCriteria andPathIsNotEmpty() {
		notEmpty("path");
		return this;
	}
        public MetaImageCriteria andPathLike(java.lang.String value) {
    	   addCriterion("path", value, ConditionMode.FUZZY, "path", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageCriteria andPathNotLike(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_FUZZY, "path", "java.lang.String", "String");
          return this;
      }
      public MetaImageCriteria andPathEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathNotEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathGreaterThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathLessThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathLessThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("path", value1, value2, ConditionMode.BETWEEN, "path", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageCriteria andPathNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("path", value1, value2, ConditionMode.NOT_BETWEEN, "path", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageCriteria andPathIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.IN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andPathNotIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.NOT_IN, "path", "java.lang.String", "String");
          return this;
      }
	public MetaImageCriteria andMimeTypeIsNull() {
		isnull("mime_type");
		return this;
	}
	
	public MetaImageCriteria andMimeTypeIsNotNull() {
		notNull("mime_type");
		return this;
	}
	
	public MetaImageCriteria andMimeTypeIsEmpty() {
		empty("mime_type");
		return this;
	}

	public MetaImageCriteria andMimeTypeIsNotEmpty() {
		notEmpty("mime_type");
		return this;
	}
        public MetaImageCriteria andMimeTypeLike(java.lang.String value) {
    	   addCriterion("mime_type", value, ConditionMode.FUZZY, "mimeType", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageCriteria andMimeTypeNotLike(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.NOT_FUZZY, "mimeType", "java.lang.String", "String");
          return this;
      }
      public MetaImageCriteria andMimeTypeEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeNotEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.NOT_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeGreaterThan(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.GREATER_THEN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.GREATER_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeLessThan(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.LESS_THEN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.LESS_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("mime_type", value1, value2, ConditionMode.BETWEEN, "mimeType", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageCriteria andMimeTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("mime_type", value1, value2, ConditionMode.NOT_BETWEEN, "mimeType", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageCriteria andMimeTypeIn(List<java.lang.String> values) {
          addCriterion("mime_type", values, ConditionMode.IN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andMimeTypeNotIn(List<java.lang.String> values) {
          addCriterion("mime_type", values, ConditionMode.NOT_IN, "mimeType", "java.lang.String", "String");
          return this;
      }
	public MetaImageCriteria andSuffixIsNull() {
		isnull("suffix");
		return this;
	}
	
	public MetaImageCriteria andSuffixIsNotNull() {
		notNull("suffix");
		return this;
	}
	
	public MetaImageCriteria andSuffixIsEmpty() {
		empty("suffix");
		return this;
	}

	public MetaImageCriteria andSuffixIsNotEmpty() {
		notEmpty("suffix");
		return this;
	}
        public MetaImageCriteria andSuffixLike(java.lang.String value) {
    	   addCriterion("suffix", value, ConditionMode.FUZZY, "suffix", "java.lang.String", "String");
    	   return this;
      }

      public MetaImageCriteria andSuffixNotLike(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.NOT_FUZZY, "suffix", "java.lang.String", "String");
          return this;
      }
      public MetaImageCriteria andSuffixEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixNotEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.NOT_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixGreaterThan(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.GREATER_THEN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.GREATER_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixLessThan(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.LESS_THEN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixLessThanOrEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.LESS_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("suffix", value1, value2, ConditionMode.BETWEEN, "suffix", "java.lang.String", "String");
    	  return this;
      }

      public MetaImageCriteria andSuffixNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("suffix", value1, value2, ConditionMode.NOT_BETWEEN, "suffix", "java.lang.String", "String");
          return this;
      }
        
      public MetaImageCriteria andSuffixIn(List<java.lang.String> values) {
          addCriterion("suffix", values, ConditionMode.IN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaImageCriteria andSuffixNotIn(List<java.lang.String> values) {
          addCriterion("suffix", values, ConditionMode.NOT_IN, "suffix", "java.lang.String", "String");
          return this;
      }
	public MetaImageCriteria andFilesizeIsNull() {
		isnull("filesize");
		return this;
	}
	
	public MetaImageCriteria andFilesizeIsNotNull() {
		notNull("filesize");
		return this;
	}
	
	public MetaImageCriteria andFilesizeIsEmpty() {
		empty("filesize");
		return this;
	}

	public MetaImageCriteria andFilesizeIsNotEmpty() {
		notEmpty("filesize");
		return this;
	}
       public MetaImageCriteria andFilesizeEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeNotEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.NOT_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeGreaterThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeLessThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("filesize", value1, value2, ConditionMode.BETWEEN, "filesize", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageCriteria andFilesizeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("filesize", value1, value2, ConditionMode.NOT_BETWEEN, "filesize", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageCriteria andFilesizeIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.IN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andFilesizeNotIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.NOT_IN, "filesize", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageCriteria andWidthIsNull() {
		isnull("width");
		return this;
	}
	
	public MetaImageCriteria andWidthIsNotNull() {
		notNull("width");
		return this;
	}
	
	public MetaImageCriteria andWidthIsEmpty() {
		empty("width");
		return this;
	}

	public MetaImageCriteria andWidthIsNotEmpty() {
		notEmpty("width");
		return this;
	}
       public MetaImageCriteria andWidthEqualTo(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.EQUAL, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthNotEqualTo(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.NOT_EQUAL, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthGreaterThan(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.GREATER_THEN, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.GREATER_EQUAL, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthLessThan(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.LESS_THEN, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("width", value, ConditionMode.LESS_EQUAL, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("width", value1, value2, ConditionMode.BETWEEN, "width", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaImageCriteria andWidthNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("width", value1, value2, ConditionMode.NOT_BETWEEN, "width", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaImageCriteria andWidthIn(List<java.lang.Integer> values) {
          addCriterion("width", values, ConditionMode.IN, "width", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andWidthNotIn(List<java.lang.Integer> values) {
          addCriterion("width", values, ConditionMode.NOT_IN, "width", "java.lang.Integer", "Float");
          return this;
      }
	public MetaImageCriteria andHeightIsNull() {
		isnull("height");
		return this;
	}
	
	public MetaImageCriteria andHeightIsNotNull() {
		notNull("height");
		return this;
	}
	
	public MetaImageCriteria andHeightIsEmpty() {
		empty("height");
		return this;
	}

	public MetaImageCriteria andHeightIsNotEmpty() {
		notEmpty("height");
		return this;
	}
       public MetaImageCriteria andHeightEqualTo(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.EQUAL, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightNotEqualTo(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.NOT_EQUAL, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightGreaterThan(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.GREATER_THEN, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.GREATER_EQUAL, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightLessThan(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.LESS_THEN, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("height", value, ConditionMode.LESS_EQUAL, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("height", value1, value2, ConditionMode.BETWEEN, "height", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaImageCriteria andHeightNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("height", value1, value2, ConditionMode.NOT_BETWEEN, "height", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaImageCriteria andHeightIn(List<java.lang.Integer> values) {
          addCriterion("height", values, ConditionMode.IN, "height", "java.lang.Integer", "Float");
          return this;
      }

      public MetaImageCriteria andHeightNotIn(List<java.lang.Integer> values) {
          addCriterion("height", values, ConditionMode.NOT_IN, "height", "java.lang.Integer", "Float");
          return this;
      }
	public MetaImageCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaImageCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaImageCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaImageCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaImageCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaImageCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaImageCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaImageCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaImageCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaImageCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaImageCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaImageCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public MetaImageCriteria andStatusEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusNotEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusGreaterThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusGreaterThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusLessThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusLessThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Boolean", "String");
    	  return this;
      }

      public MetaImageCriteria andStatusNotBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Boolean", "String");
          return this;
      }
        
      public MetaImageCriteria andStatusIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaImageCriteria andStatusNotIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Boolean", "String");
          return this;
      }
	public MetaImageCriteria andCreatedIsNull() {
		isnull("created");
		return this;
	}
	
	public MetaImageCriteria andCreatedIsNotNull() {
		notNull("created");
		return this;
	}
	
	public MetaImageCriteria andCreatedIsEmpty() {
		empty("created");
		return this;
	}

	public MetaImageCriteria andCreatedIsNotEmpty() {
		notEmpty("created");
		return this;
	}
       public MetaImageCriteria andCreatedEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedNotEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.NOT_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedGreaterThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedLessThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("created", value1, value2, ConditionMode.BETWEEN, "created", "java.util.Date", "String");
    	  return this;
      }

      public MetaImageCriteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("created", value1, value2, ConditionMode.NOT_BETWEEN, "created", "java.util.Date", "String");
          return this;
      }
        
      public MetaImageCriteria andCreatedIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.IN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaImageCriteria andCreatedNotIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.NOT_IN, "created", "java.util.Date", "String");
          return this;
      }
}