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
public class MetaFileCatalogCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFileCatalogCriteria create() {
		return new MetaFileCatalogCriteria();
	}
	
	public static MetaFileCatalogCriteria create(Column column) {
		MetaFileCatalogCriteria that = new MetaFileCatalogCriteria();
		that.add(column);
        return that;
    }

    public static MetaFileCatalogCriteria create(String name, Object value) {
        return (MetaFileCatalogCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_file_catalog", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFileCatalogCriteria eq(String name, Object value) {
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
    public MetaFileCatalogCriteria ne(String name, Object value) {
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

    public MetaFileCatalogCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFileCatalogCriteria notLike(String name, Object value) {
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
    public MetaFileCatalogCriteria gt(String name, Object value) {
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
    public MetaFileCatalogCriteria ge(String name, Object value) {
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
    public MetaFileCatalogCriteria lt(String name, Object value) {
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
    public MetaFileCatalogCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFileCatalogCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFileCatalogCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFileCatalogCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFileCatalogCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFileCatalogCriteria add(Column column) {
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
		 
	public MetaFileCatalogCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFileCatalogCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFileCatalogCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFileCatalogCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFileCatalogCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andSidIsNull() {
		isnull("sid");
		return this;
	}
	
	public MetaFileCatalogCriteria andSidIsNotNull() {
		notNull("sid");
		return this;
	}
	
	public MetaFileCatalogCriteria andSidIsEmpty() {
		empty("sid");
		return this;
	}

	public MetaFileCatalogCriteria andSidIsNotEmpty() {
		notEmpty("sid");
		return this;
	}
       public MetaFileCatalogCriteria andSidEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidNotEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.NOT_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidGreaterThan(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.GREATER_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.GREATER_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidLessThan(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.LESS_THEN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sid", value, ConditionMode.LESS_EQUAL, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("sid", value1, value2, ConditionMode.BETWEEN, "sid", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andSidNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("sid", value1, value2, ConditionMode.NOT_BETWEEN, "sid", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andSidIn(List<java.lang.Integer> values) {
          addCriterion("sid", values, ConditionMode.IN, "sid", "java.lang.Integer", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andSidNotIn(List<java.lang.Integer> values) {
          addCriterion("sid", values, ConditionMode.NOT_IN, "sid", "java.lang.Integer", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public MetaFileCatalogCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public MetaFileCatalogCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public MetaFileCatalogCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public MetaFileCatalogCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andPidIsNull() {
		isnull("pid");
		return this;
	}
	
	public MetaFileCatalogCriteria andPidIsNotNull() {
		notNull("pid");
		return this;
	}
	
	public MetaFileCatalogCriteria andPidIsEmpty() {
		empty("pid");
		return this;
	}

	public MetaFileCatalogCriteria andPidIsNotEmpty() {
		notEmpty("pid");
		return this;
	}
       public MetaFileCatalogCriteria andPidEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidNotEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.NOT_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidGreaterThan(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.GREATER_THEN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.GREATER_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidLessThan(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.LESS_THEN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("pid", value, ConditionMode.LESS_EQUAL, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("pid", value1, value2, ConditionMode.BETWEEN, "pid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andPidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("pid", value1, value2, ConditionMode.NOT_BETWEEN, "pid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andPidIn(List<java.lang.Long> values) {
          addCriterion("pid", values, ConditionMode.IN, "pid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andPidNotIn(List<java.lang.Long> values) {
          addCriterion("pid", values, ConditionMode.NOT_IN, "pid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public MetaFileCatalogCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public MetaFileCatalogCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public MetaFileCatalogCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public MetaFileCatalogCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFileCatalogCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "Float");
          return this;
      }
      public MetaFileCatalogCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public MetaFileCatalogCriteria andFilesizeIsNull() {
		isnull("filesize");
		return this;
	}
	
	public MetaFileCatalogCriteria andFilesizeIsNotNull() {
		notNull("filesize");
		return this;
	}
	
	public MetaFileCatalogCriteria andFilesizeIsEmpty() {
		empty("filesize");
		return this;
	}

	public MetaFileCatalogCriteria andFilesizeIsNotEmpty() {
		notEmpty("filesize");
		return this;
	}
       public MetaFileCatalogCriteria andFilesizeEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeNotEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.NOT_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeGreaterThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.GREATER_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeLessThan(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_THEN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("filesize", value, ConditionMode.LESS_EQUAL, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("filesize", value1, value2, ConditionMode.BETWEEN, "filesize", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andFilesizeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("filesize", value1, value2, ConditionMode.NOT_BETWEEN, "filesize", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andFilesizeIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.IN, "filesize", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andFilesizeNotIn(List<java.lang.Long> values) {
          addCriterion("filesize", values, ConditionMode.NOT_IN, "filesize", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaFileCatalogCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaFileCatalogCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaFileCatalogCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaFileCatalogCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFileCatalogCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "Float");
          return this;
      }
      public MetaFileCatalogCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
	public MetaFileCatalogCriteria andFlagIsNull() {
		isnull("flag");
		return this;
	}
	
	public MetaFileCatalogCriteria andFlagIsNotNull() {
		notNull("flag");
		return this;
	}
	
	public MetaFileCatalogCriteria andFlagIsEmpty() {
		empty("flag");
		return this;
	}

	public MetaFileCatalogCriteria andFlagIsNotEmpty() {
		notEmpty("flag");
		return this;
	}
        public MetaFileCatalogCriteria andFlagLike(java.lang.String value) {
    	   addCriterion("flag", value, ConditionMode.FUZZY, "flag", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCatalogCriteria andFlagNotLike(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_FUZZY, "flag", "java.lang.String", "String");
          return this;
      }
      public MetaFileCatalogCriteria andFlagEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagNotEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.NOT_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagGreaterThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.GREATER_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagLessThan(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_THEN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagLessThanOrEqualTo(java.lang.String value) {
          addCriterion("flag", value, ConditionMode.LESS_EQUAL, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("flag", value1, value2, ConditionMode.BETWEEN, "flag", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andFlagNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("flag", value1, value2, ConditionMode.NOT_BETWEEN, "flag", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andFlagIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.IN, "flag", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andFlagNotIn(List<java.lang.String> values) {
          addCriterion("flag", values, ConditionMode.NOT_IN, "flag", "java.lang.String", "String");
          return this;
      }
	public MetaFileCatalogCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaFileCatalogCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaFileCatalogCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaFileCatalogCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaFileCatalogCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public MetaFileCatalogCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public MetaFileCatalogCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public MetaFileCatalogCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public MetaFileCatalogCriteria andStatusEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusNotEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusGreaterThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusGreaterThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusLessThan(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusLessThanOrEqualTo(java.lang.Boolean value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Boolean", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andStatusNotBetween(java.lang.Boolean value1, java.lang.Boolean value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Boolean", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andStatusIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Boolean", "String");
          return this;
      }

      public MetaFileCatalogCriteria andStatusNotIn(List<java.lang.Boolean> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Boolean", "String");
          return this;
      }
	public MetaFileCatalogCriteria andCreatedIsNull() {
		isnull("created");
		return this;
	}
	
	public MetaFileCatalogCriteria andCreatedIsNotNull() {
		notNull("created");
		return this;
	}
	
	public MetaFileCatalogCriteria andCreatedIsEmpty() {
		empty("created");
		return this;
	}

	public MetaFileCatalogCriteria andCreatedIsNotEmpty() {
		notEmpty("created");
		return this;
	}
       public MetaFileCatalogCriteria andCreatedEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedNotEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.NOT_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedGreaterThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.GREATER_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedLessThan(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_THEN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("created", value, ConditionMode.LESS_EQUAL, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("created", value1, value2, ConditionMode.BETWEEN, "created", "java.util.Date", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andCreatedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("created", value1, value2, ConditionMode.NOT_BETWEEN, "created", "java.util.Date", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andCreatedIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.IN, "created", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andCreatedNotIn(List<java.util.Date> values) {
          addCriterion("created", values, ConditionMode.NOT_IN, "created", "java.util.Date", "String");
          return this;
      }
	public MetaFileCatalogCriteria andModifiedIsNull() {
		isnull("modified");
		return this;
	}
	
	public MetaFileCatalogCriteria andModifiedIsNotNull() {
		notNull("modified");
		return this;
	}
	
	public MetaFileCatalogCriteria andModifiedIsEmpty() {
		empty("modified");
		return this;
	}

	public MetaFileCatalogCriteria andModifiedIsNotEmpty() {
		notEmpty("modified");
		return this;
	}
       public MetaFileCatalogCriteria andModifiedEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedNotEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.NOT_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedGreaterThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedGreaterThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.GREATER_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedLessThan(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_THEN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedLessThanOrEqualTo(java.util.Date value) {
          addCriterion("modified", value, ConditionMode.LESS_EQUAL, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedBetween(java.util.Date value1, java.util.Date value2) {
    	  addCriterion("modified", value1, value2, ConditionMode.BETWEEN, "modified", "java.util.Date", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andModifiedNotBetween(java.util.Date value1, java.util.Date value2) {
          addCriterion("modified", value1, value2, ConditionMode.NOT_BETWEEN, "modified", "java.util.Date", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andModifiedIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.IN, "modified", "java.util.Date", "String");
          return this;
      }

      public MetaFileCatalogCriteria andModifiedNotIn(List<java.util.Date> values) {
          addCriterion("modified", values, ConditionMode.NOT_IN, "modified", "java.util.Date", "String");
          return this;
      }
	public MetaFileCatalogCriteria andPermissionIsNull() {
		isnull("permission");
		return this;
	}
	
	public MetaFileCatalogCriteria andPermissionIsNotNull() {
		notNull("permission");
		return this;
	}
	
	public MetaFileCatalogCriteria andPermissionIsEmpty() {
		empty("permission");
		return this;
	}

	public MetaFileCatalogCriteria andPermissionIsNotEmpty() {
		notEmpty("permission");
		return this;
	}
        public MetaFileCatalogCriteria andPermissionLike(java.lang.String value) {
    	   addCriterion("permission", value, ConditionMode.FUZZY, "permission", "java.lang.String", "String");
    	   return this;
      }

      public MetaFileCatalogCriteria andPermissionNotLike(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_FUZZY, "permission", "java.lang.String", "String");
          return this;
      }
      public MetaFileCatalogCriteria andPermissionEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionNotEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionGreaterThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionLessThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permission", value1, value2, ConditionMode.BETWEEN, "permission", "java.lang.String", "String");
    	  return this;
      }

      public MetaFileCatalogCriteria andPermissionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permission", value1, value2, ConditionMode.NOT_BETWEEN, "permission", "java.lang.String", "String");
          return this;
      }
        
      public MetaFileCatalogCriteria andPermissionIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.IN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFileCatalogCriteria andPermissionNotIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.NOT_IN, "permission", "java.lang.String", "String");
          return this;
      }
}