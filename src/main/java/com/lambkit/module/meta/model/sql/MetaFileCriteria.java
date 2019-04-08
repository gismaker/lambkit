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
public class MetaFileCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFileCriteria create() {
		return new MetaFileCriteria();
	}
	
	public static MetaFileCriteria create(Column column) {
		MetaFileCriteria that = new MetaFileCriteria();
		that.add(column);
        return that;
    }

    public static MetaFileCriteria create(String name, Object value) {
        return (MetaFileCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_file", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFileCriteria eq(String name, Object value) {
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
    public MetaFileCriteria ne(String name, Object value) {
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

    public MetaFileCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFileCriteria notLike(String name, Object value) {
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
    public MetaFileCriteria gt(String name, Object value) {
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
    public MetaFileCriteria ge(String name, Object value) {
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
    public MetaFileCriteria lt(String name, Object value) {
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
    public MetaFileCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFileCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFileCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFileCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFileCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFileCriteria add(Column column) {
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
		 
	public MetaFileCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFileCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFileCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFileCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFileCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaFileCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaFileCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaFileCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaFileCriteria andSidEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidNotEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidGreaterThan(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidLessThan(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaFileCriteria andSidNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaFileCriteria andSidIn(List<java.lang.Integer> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCriteria andSidNotIn(List<java.lang.Integer> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Integer", "Float");
          return this;
      }
	public MetaFileCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public MetaFileCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public MetaFileCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public MetaFileCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public MetaFileCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public MetaFileCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public MetaFileCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public MetaFileCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public MetaFileCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFileCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "Float");
          return this;
      }
      public MetaFileCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public MetaFileCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public MetaFileCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public MetaFileCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public MetaFileCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andPathIsNull() {
		isnull("path");
		return this;
	}
	
	public MetaFileCriteria andPathIsNotNull() {
		notNull("path");
		return this;
	}
	
	public MetaFileCriteria andPathIsEmpty() {
		empty("path");
		return this;
	}

	public MetaFileCriteria andPathIsNotEmpty() {
		notEmpty("path");
		return this;
	}
        public MetaFileCriteria andPathLike(java.lang.String value) {
    	   addCriterion("path", value, ConditionMode.FUZZY, "path", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andPathNotLike(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_FUZZY, "path", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andPathEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathNotEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.NOT_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathGreaterThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.GREATER_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathLessThan(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_THEN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathLessThanOrEqualTo(java.lang.String value) {
          addCriterion("path", value, ConditionMode.LESS_EQUAL, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("path", value1, value2, ConditionMode.BETWEEN, "path", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andPathNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("path", value1, value2, ConditionMode.NOT_BETWEEN, "path", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andPathIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.IN, "path", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPathNotIn(List<java.lang.String> values) {
          addCriterion("path", values, ConditionMode.NOT_IN, "path", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andMimeTypeIsNull() {
		isnull("mime_type");
		return this;
	}
	
	public MetaFileCriteria andMimeTypeIsNotNull() {
		notNull("mime_type");
		return this;
	}
	
	public MetaFileCriteria andMimeTypeIsEmpty() {
		empty("mime_type");
		return this;
	}

	public MetaFileCriteria andMimeTypeIsNotEmpty() {
		notEmpty("mime_type");
		return this;
	}
        public MetaFileCriteria andMimeTypeLike(java.lang.String value) {
    	   addCriterion("mime_type", value, ConditionMode.FUZZY, "mimeType", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andMimeTypeNotLike(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.NOT_FUZZY, "mimeType", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andMimeTypeEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeNotEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.NOT_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeGreaterThan(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.GREATER_THEN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.GREATER_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeLessThan(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.LESS_THEN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("mime_type", value, ConditionMode.LESS_EQUAL, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("mime_type", value1, value2, ConditionMode.BETWEEN, "mimeType", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andMimeTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("mime_type", value1, value2, ConditionMode.NOT_BETWEEN, "mimeType", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andMimeTypeIn(List<java.lang.String> values) {
          addCriterion("mime_type", values, ConditionMode.IN, "mimeType", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andMimeTypeNotIn(List<java.lang.String> values) {
          addCriterion("mime_type", values, ConditionMode.NOT_IN, "mimeType", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andSuffixIsNull() {
		isnull("suffix");
		return this;
	}
	
	public MetaFileCriteria andSuffixIsNotNull() {
		notNull("suffix");
		return this;
	}
	
	public MetaFileCriteria andSuffixIsEmpty() {
		empty("suffix");
		return this;
	}

	public MetaFileCriteria andSuffixIsNotEmpty() {
		notEmpty("suffix");
		return this;
	}
        public MetaFileCriteria andSuffixLike(java.lang.String value) {
    	   addCriterion("suffix", value, ConditionMode.FUZZY, "suffix", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andSuffixNotLike(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.NOT_FUZZY, "suffix", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andSuffixEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixNotEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.NOT_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixGreaterThan(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.GREATER_THEN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.GREATER_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixLessThan(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.LESS_THEN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixLessThanOrEqualTo(java.lang.String value) {
          addCriterion("suffix", value, ConditionMode.LESS_EQUAL, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("suffix", value1, value2, ConditionMode.BETWEEN, "suffix", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andSuffixNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("suffix", value1, value2, ConditionMode.NOT_BETWEEN, "suffix", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andSuffixIn(List<java.lang.String> values) {
          addCriterion("suffix", values, ConditionMode.IN, "suffix", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andSuffixNotIn(List<java.lang.String> values) {
          addCriterion("suffix", values, ConditionMode.NOT_IN, "suffix", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andFilesizeIsNull() {
		isnull("filesize");
		return this;
	}
	
	public MetaFileCriteria andFilesizeIsNotNull() {
		notNull("filesize");
		return this;
	}
	
	public MetaFileCriteria andFilesizeIsEmpty() {
		empty("filesize");
		return this;
	}

	public MetaFileCriteria andFilesizeIsNotEmpty() {
		notEmpty("filesize");
		return this;
	}
       public MetaFileCriteria andFilesizeEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeNotEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.NOT_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeGreaterThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeLessThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("filesize", value1, value2, ConditionMode.BETWEEN, "filesize", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCriteria andFilesizeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("filesize", value1, value2, ConditionMode.NOT_BETWEEN, "filesize", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCriteria andFilesizeIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.IN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andFilesizeNotIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.NOT_IN, "filesize", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaFileCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaFileCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaFileCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaFileCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFileCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "Float");
          return this;
      }
      public MetaFileCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andFlagIsNull() {
		isnull("flag");
		return this;
	}
	
	public MetaFileCriteria andFlagIsNotNull() {
		notNull("flag");
		return this;
	}
	
	public MetaFileCriteria andFlagIsEmpty() {
		empty("flag");
		return this;
	}

	public MetaFileCriteria andFlagIsNotEmpty() {
		notEmpty("flag");
		return this;
	}
        public MetaFileCriteria andFlagLike(java.lang.String value) {
    	   addCriterion("flag", value, ConditionMode.FUZZY, "flag", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andFlagNotLike(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_FUZZY, "flag", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andFlagEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagNotEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagGreaterThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagLessThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagLessThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("flag", value1, value2, ConditionMode.BETWEEN, "flag", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andFlagNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("flag", value1, value2, ConditionMode.NOT_BETWEEN, "flag", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andFlagIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.IN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andFlagNotIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.NOT_IN, "flag", "java.lang.String", "String");
          return this;
      }
	public MetaFileCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaFileCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaFileCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaFileCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaFileCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaFileCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaFileCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaFileCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public MetaFileCriteria andStatusEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusNotEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusGreaterThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusGreaterThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusLessThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusLessThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Boolean", "String");
    	  return this;
      }

      public MetaFileCriteria andStatusNotBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Boolean", "String");
          return this;
      }
        
      public MetaFileCriteria andStatusIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCriteria andStatusNotIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Boolean", "String");
          return this;
      }
	public MetaFileCriteria andCreatedIsNull() {
		isnull("created");
		return this;
	}
	
	public MetaFileCriteria andCreatedIsNotNull() {
		notNull("created");
		return this;
	}
	
	public MetaFileCriteria andCreatedIsEmpty() {
		empty("created");
		return this;
	}

	public MetaFileCriteria andCreatedIsNotEmpty() {
		notEmpty("created");
		return this;
	}
       public MetaFileCriteria andCreatedEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedNotEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.NOT_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedGreaterThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedLessThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("created", value1, value2, ConditionMode.BETWEEN, "created", "java.util.Date", "String");
    	  return this;
      }

      public MetaFileCriteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("created", value1, value2, ConditionMode.NOT_BETWEEN, "created", "java.util.Date", "String");
          return this;
      }
        
      public MetaFileCriteria andCreatedIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.IN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andCreatedNotIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.NOT_IN, "created", "java.util.Date", "String");
          return this;
      }
	public MetaFileCriteria andModifiedIsNull() {
		isnull("modified");
		return this;
	}
	
	public MetaFileCriteria andModifiedIsNotNull() {
		notNull("modified");
		return this;
	}
	
	public MetaFileCriteria andModifiedIsEmpty() {
		empty("modified");
		return this;
	}

	public MetaFileCriteria andModifiedIsNotEmpty() {
		notEmpty("modified");
		return this;
	}
       public MetaFileCriteria andModifiedEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedNotEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.NOT_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedGreaterThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedLessThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("modified", value1, value2, ConditionMode.BETWEEN, "modified", "java.util.Date", "String");
    	  return this;
      }

      public MetaFileCriteria andModifiedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("modified", value1, value2, ConditionMode.NOT_BETWEEN, "modified", "java.util.Date", "String");
          return this;
      }
        
      public MetaFileCriteria andModifiedIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.IN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCriteria andModifiedNotIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.NOT_IN, "modified", "java.util.Date", "String");
          return this;
      }
	public MetaFileCriteria andPermissionIsNull() {
		isnull("permission");
		return this;
	}
	
	public MetaFileCriteria andPermissionIsNotNull() {
		notNull("permission");
		return this;
	}
	
	public MetaFileCriteria andPermissionIsEmpty() {
		empty("permission");
		return this;
	}

	public MetaFileCriteria andPermissionIsNotEmpty() {
		notEmpty("permission");
		return this;
	}
        public MetaFileCriteria andPermissionLike(java.lang.String value) {
    	   addCriterion("permission", value, ConditionMode.FUZZY, "permission", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCriteria andPermissionNotLike(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_FUZZY, "permission", "java.lang.String", "String");
          return this;
      }
      public MetaFileCriteria andPermissionEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionNotEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionGreaterThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionLessThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permission", value1, value2, ConditionMode.BETWEEN, "permission", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCriteria andPermissionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permission", value1, value2, ConditionMode.NOT_BETWEEN, "permission", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCriteria andPermissionIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.IN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCriteria andPermissionNotIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.NOT_IN, "permission", "java.lang.String", "String");
          return this;
      }
}