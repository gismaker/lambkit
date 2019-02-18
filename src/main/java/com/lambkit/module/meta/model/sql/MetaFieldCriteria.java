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
public class MetaFieldCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldCriteria create() {
		return new MetaFieldCriteria();
	}
	
	public static MetaFieldCriteria create(Column column) {
		MetaFieldCriteria that = new MetaFieldCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldCriteria create(String name, Object value) {
        return (MetaFieldCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldCriteria eq(String name, Object value) {
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
    public MetaFieldCriteria ne(String name, Object value) {
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

    public MetaFieldCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldCriteria notLike(String name, Object value) {
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
    public MetaFieldCriteria gt(String name, Object value) {
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
    public MetaFieldCriteria ge(String name, Object value) {
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
    public MetaFieldCriteria lt(String name, Object value) {
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
    public MetaFieldCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldCriteria add(Column column) {
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
		 
	public MetaFieldCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldCriteria andTbidIsNull() {
		isnull("tbid");
		return this;
	}
	
	public MetaFieldCriteria andTbidIsNotNull() {
		notNull("tbid");
		return this;
	}
	
	public MetaFieldCriteria andTbidIsEmpty() {
		empty("tbid");
		return this;
	}

	public MetaFieldCriteria andTbidIsNotEmpty() {
		notEmpty("tbid");
		return this;
	}
       public MetaFieldCriteria andTbidEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidNotEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.NOT_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidGreaterThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidLessThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tbid", value1, value2, ConditionMode.BETWEEN, "tbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldCriteria andTbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tbid", value1, value2, ConditionMode.NOT_BETWEEN, "tbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldCriteria andTbidIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.IN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andTbidNotIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.NOT_IN, "tbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaFieldCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaFieldCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaFieldCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaFieldCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public MetaFieldCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public MetaFieldCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public MetaFieldCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public MetaFieldCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andDatatypeIsNull() {
		isnull("datatype");
		return this;
	}
	
	public MetaFieldCriteria andDatatypeIsNotNull() {
		notNull("datatype");
		return this;
	}
	
	public MetaFieldCriteria andDatatypeIsEmpty() {
		empty("datatype");
		return this;
	}

	public MetaFieldCriteria andDatatypeIsNotEmpty() {
		notEmpty("datatype");
		return this;
	}
        public MetaFieldCriteria andDatatypeLike(java.lang.String value) {
    	   addCriterion("datatype", value, ConditionMode.FUZZY, "datatype", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andDatatypeNotLike(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.NOT_FUZZY, "datatype", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andDatatypeEqualTo(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.EQUAL, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeNotEqualTo(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.NOT_EQUAL, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeGreaterThan(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.GREATER_THEN, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.GREATER_EQUAL, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeLessThan(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.LESS_THEN, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("datatype", value, ConditionMode.LESS_EQUAL, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("datatype", value1, value2, ConditionMode.BETWEEN, "datatype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andDatatypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("datatype", value1, value2, ConditionMode.NOT_BETWEEN, "datatype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andDatatypeIn(List<java.lang.String> values) {
          addCriterion("datatype", values, ConditionMode.IN, "datatype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDatatypeNotIn(List<java.lang.String> values) {
          addCriterion("datatype", values, ConditionMode.NOT_IN, "datatype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andClasstypeIsNull() {
		isnull("classtype");
		return this;
	}
	
	public MetaFieldCriteria andClasstypeIsNotNull() {
		notNull("classtype");
		return this;
	}
	
	public MetaFieldCriteria andClasstypeIsEmpty() {
		empty("classtype");
		return this;
	}

	public MetaFieldCriteria andClasstypeIsNotEmpty() {
		notEmpty("classtype");
		return this;
	}
        public MetaFieldCriteria andClasstypeLike(java.lang.String value) {
    	   addCriterion("classtype", value, ConditionMode.FUZZY, "classtype", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andClasstypeNotLike(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.NOT_FUZZY, "classtype", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andClasstypeEqualTo(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.EQUAL, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeNotEqualTo(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.NOT_EQUAL, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeGreaterThan(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.GREATER_THEN, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.GREATER_EQUAL, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeLessThan(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.LESS_THEN, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("classtype", value, ConditionMode.LESS_EQUAL, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("classtype", value1, value2, ConditionMode.BETWEEN, "classtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andClasstypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("classtype", value1, value2, ConditionMode.NOT_BETWEEN, "classtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andClasstypeIn(List<java.lang.String> values) {
          addCriterion("classtype", values, ConditionMode.IN, "classtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andClasstypeNotIn(List<java.lang.String> values) {
          addCriterion("classtype", values, ConditionMode.NOT_IN, "classtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIskeyIsNull() {
		isnull("iskey");
		return this;
	}
	
	public MetaFieldCriteria andIskeyIsNotNull() {
		notNull("iskey");
		return this;
	}
	
	public MetaFieldCriteria andIskeyIsEmpty() {
		empty("iskey");
		return this;
	}

	public MetaFieldCriteria andIskeyIsNotEmpty() {
		notEmpty("iskey");
		return this;
	}
        public MetaFieldCriteria andIskeyLike(java.lang.String value) {
    	   addCriterion("iskey", value, ConditionMode.FUZZY, "iskey", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIskeyNotLike(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.NOT_FUZZY, "iskey", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIskeyEqualTo(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.EQUAL, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyNotEqualTo(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.NOT_EQUAL, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyGreaterThan(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.GREATER_THEN, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.GREATER_EQUAL, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyLessThan(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.LESS_THEN, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyLessThanOrEqualTo(java.lang.String value) {
          addCriterion("iskey", value, ConditionMode.LESS_EQUAL, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("iskey", value1, value2, ConditionMode.BETWEEN, "iskey", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIskeyNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("iskey", value1, value2, ConditionMode.NOT_BETWEEN, "iskey", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIskeyIn(List<java.lang.String> values) {
          addCriterion("iskey", values, ConditionMode.IN, "iskey", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIskeyNotIn(List<java.lang.String> values) {
          addCriterion("iskey", values, ConditionMode.NOT_IN, "iskey", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsunsignedIsNull() {
		isnull("isunsigned");
		return this;
	}
	
	public MetaFieldCriteria andIsunsignedIsNotNull() {
		notNull("isunsigned");
		return this;
	}
	
	public MetaFieldCriteria andIsunsignedIsEmpty() {
		empty("isunsigned");
		return this;
	}

	public MetaFieldCriteria andIsunsignedIsNotEmpty() {
		notEmpty("isunsigned");
		return this;
	}
        public MetaFieldCriteria andIsunsignedLike(java.lang.String value) {
    	   addCriterion("isunsigned", value, ConditionMode.FUZZY, "isunsigned", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsunsignedNotLike(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.NOT_FUZZY, "isunsigned", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsunsignedEqualTo(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.EQUAL, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedNotEqualTo(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.NOT_EQUAL, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedGreaterThan(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.GREATER_THEN, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.GREATER_EQUAL, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedLessThan(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.LESS_THEN, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isunsigned", value, ConditionMode.LESS_EQUAL, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isunsigned", value1, value2, ConditionMode.BETWEEN, "isunsigned", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsunsignedNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isunsigned", value1, value2, ConditionMode.NOT_BETWEEN, "isunsigned", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsunsignedIn(List<java.lang.String> values) {
          addCriterion("isunsigned", values, ConditionMode.IN, "isunsigned", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsunsignedNotIn(List<java.lang.String> values) {
          addCriterion("isunsigned", values, ConditionMode.NOT_IN, "isunsigned", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsnullableIsNull() {
		isnull("isnullable");
		return this;
	}
	
	public MetaFieldCriteria andIsnullableIsNotNull() {
		notNull("isnullable");
		return this;
	}
	
	public MetaFieldCriteria andIsnullableIsEmpty() {
		empty("isnullable");
		return this;
	}

	public MetaFieldCriteria andIsnullableIsNotEmpty() {
		notEmpty("isnullable");
		return this;
	}
        public MetaFieldCriteria andIsnullableLike(java.lang.String value) {
    	   addCriterion("isnullable", value, ConditionMode.FUZZY, "isnullable", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsnullableNotLike(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.NOT_FUZZY, "isnullable", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsnullableEqualTo(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.EQUAL, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableNotEqualTo(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.NOT_EQUAL, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableGreaterThan(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.GREATER_THEN, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.GREATER_EQUAL, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableLessThan(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.LESS_THEN, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isnullable", value, ConditionMode.LESS_EQUAL, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isnullable", value1, value2, ConditionMode.BETWEEN, "isnullable", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsnullableNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isnullable", value1, value2, ConditionMode.NOT_BETWEEN, "isnullable", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsnullableIn(List<java.lang.String> values) {
          addCriterion("isnullable", values, ConditionMode.IN, "isnullable", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsnullableNotIn(List<java.lang.String> values) {
          addCriterion("isnullable", values, ConditionMode.NOT_IN, "isnullable", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsaiIsNull() {
		isnull("isai");
		return this;
	}
	
	public MetaFieldCriteria andIsaiIsNotNull() {
		notNull("isai");
		return this;
	}
	
	public MetaFieldCriteria andIsaiIsEmpty() {
		empty("isai");
		return this;
	}

	public MetaFieldCriteria andIsaiIsNotEmpty() {
		notEmpty("isai");
		return this;
	}
        public MetaFieldCriteria andIsaiLike(java.lang.String value) {
    	   addCriterion("isai", value, ConditionMode.FUZZY, "isai", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsaiNotLike(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.NOT_FUZZY, "isai", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsaiEqualTo(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.EQUAL, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiNotEqualTo(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.NOT_EQUAL, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiGreaterThan(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.GREATER_THEN, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.GREATER_EQUAL, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiLessThan(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.LESS_THEN, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isai", value, ConditionMode.LESS_EQUAL, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isai", value1, value2, ConditionMode.BETWEEN, "isai", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsaiNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isai", value1, value2, ConditionMode.NOT_BETWEEN, "isai", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsaiIn(List<java.lang.String> values) {
          addCriterion("isai", values, ConditionMode.IN, "isai", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsaiNotIn(List<java.lang.String> values) {
          addCriterion("isai", values, ConditionMode.NOT_IN, "isai", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andFlddefaultIsNull() {
		isnull("flddefault");
		return this;
	}
	
	public MetaFieldCriteria andFlddefaultIsNotNull() {
		notNull("flddefault");
		return this;
	}
	
	public MetaFieldCriteria andFlddefaultIsEmpty() {
		empty("flddefault");
		return this;
	}

	public MetaFieldCriteria andFlddefaultIsNotEmpty() {
		notEmpty("flddefault");
		return this;
	}
        public MetaFieldCriteria andFlddefaultLike(java.lang.String value) {
    	   addCriterion("flddefault", value, ConditionMode.FUZZY, "flddefault", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andFlddefaultNotLike(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.NOT_FUZZY, "flddefault", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andFlddefaultEqualTo(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.EQUAL, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultNotEqualTo(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.NOT_EQUAL, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultGreaterThan(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.GREATER_THEN, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.GREATER_EQUAL, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultLessThan(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.LESS_THEN, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultLessThanOrEqualTo(java.lang.String value) {
          addCriterion("flddefault", value, ConditionMode.LESS_EQUAL, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("flddefault", value1, value2, ConditionMode.BETWEEN, "flddefault", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andFlddefaultNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("flddefault", value1, value2, ConditionMode.NOT_BETWEEN, "flddefault", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andFlddefaultIn(List<java.lang.String> values) {
          addCriterion("flddefault", values, ConditionMode.IN, "flddefault", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andFlddefaultNotIn(List<java.lang.String> values) {
          addCriterion("flddefault", values, ConditionMode.NOT_IN, "flddefault", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andDescriptIsNull() {
		isnull("descript");
		return this;
	}
	
	public MetaFieldCriteria andDescriptIsNotNull() {
		notNull("descript");
		return this;
	}
	
	public MetaFieldCriteria andDescriptIsEmpty() {
		empty("descript");
		return this;
	}

	public MetaFieldCriteria andDescriptIsNotEmpty() {
		notEmpty("descript");
		return this;
	}
        public MetaFieldCriteria andDescriptLike(java.lang.String value) {
    	   addCriterion("descript", value, ConditionMode.FUZZY, "descript", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andDescriptNotLike(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.NOT_FUZZY, "descript", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andDescriptEqualTo(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.EQUAL, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptNotEqualTo(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.NOT_EQUAL, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptGreaterThan(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.GREATER_THEN, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.GREATER_EQUAL, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptLessThan(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.LESS_THEN, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptLessThanOrEqualTo(java.lang.String value) {
          addCriterion("descript", value, ConditionMode.LESS_EQUAL, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("descript", value1, value2, ConditionMode.BETWEEN, "descript", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andDescriptNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("descript", value1, value2, ConditionMode.NOT_BETWEEN, "descript", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andDescriptIn(List<java.lang.String> values) {
          addCriterion("descript", values, ConditionMode.IN, "descript", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andDescriptNotIn(List<java.lang.String> values) {
          addCriterion("descript", values, ConditionMode.NOT_IN, "descript", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsfkIsNull() {
		isnull("isfk");
		return this;
	}
	
	public MetaFieldCriteria andIsfkIsNotNull() {
		notNull("isfk");
		return this;
	}
	
	public MetaFieldCriteria andIsfkIsEmpty() {
		empty("isfk");
		return this;
	}

	public MetaFieldCriteria andIsfkIsNotEmpty() {
		notEmpty("isfk");
		return this;
	}
        public MetaFieldCriteria andIsfkLike(java.lang.String value) {
    	   addCriterion("isfk", value, ConditionMode.FUZZY, "isfk", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsfkNotLike(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.NOT_FUZZY, "isfk", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsfkEqualTo(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.EQUAL, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkNotEqualTo(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.NOT_EQUAL, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkGreaterThan(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.GREATER_THEN, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.GREATER_EQUAL, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkLessThan(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.LESS_THEN, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isfk", value, ConditionMode.LESS_EQUAL, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isfk", value1, value2, ConditionMode.BETWEEN, "isfk", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsfkNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isfk", value1, value2, ConditionMode.NOT_BETWEEN, "isfk", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsfkIn(List<java.lang.String> values) {
          addCriterion("isfk", values, ConditionMode.IN, "isfk", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsfkNotIn(List<java.lang.String> values) {
          addCriterion("isfk", values, ConditionMode.NOT_IN, "isfk", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andFktbidIsNull() {
		isnull("fktbid");
		return this;
	}
	
	public MetaFieldCriteria andFktbidIsNotNull() {
		notNull("fktbid");
		return this;
	}
	
	public MetaFieldCriteria andFktbidIsEmpty() {
		empty("fktbid");
		return this;
	}

	public MetaFieldCriteria andFktbidIsNotEmpty() {
		notEmpty("fktbid");
		return this;
	}
       public MetaFieldCriteria andFktbidEqualTo(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.EQUAL, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidNotEqualTo(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.NOT_EQUAL, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidGreaterThan(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.GREATER_THEN, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.GREATER_EQUAL, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidLessThan(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.LESS_THEN, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fktbid", value, ConditionMode.LESS_EQUAL, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fktbid", value1, value2, ConditionMode.BETWEEN, "fktbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldCriteria andFktbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fktbid", value1, value2, ConditionMode.NOT_BETWEEN, "fktbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldCriteria andFktbidIn(List<java.lang.Long> values) {
          addCriterion("fktbid", values, ConditionMode.IN, "fktbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andFktbidNotIn(List<java.lang.Long> values) {
          addCriterion("fktbid", values, ConditionMode.NOT_IN, "fktbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldCriteria andIsviewIsNull() {
		isnull("isview");
		return this;
	}
	
	public MetaFieldCriteria andIsviewIsNotNull() {
		notNull("isview");
		return this;
	}
	
	public MetaFieldCriteria andIsviewIsEmpty() {
		empty("isview");
		return this;
	}

	public MetaFieldCriteria andIsviewIsNotEmpty() {
		notEmpty("isview");
		return this;
	}
        public MetaFieldCriteria andIsviewLike(java.lang.String value) {
    	   addCriterion("isview", value, ConditionMode.FUZZY, "isview", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldCriteria andIsviewNotLike(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.NOT_FUZZY, "isview", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldCriteria andIsviewEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewNotEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.NOT_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewGreaterThan(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.GREATER_THEN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.GREATER_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewLessThan(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.LESS_THEN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.LESS_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isview", value1, value2, ConditionMode.BETWEEN, "isview", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsviewNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isview", value1, value2, ConditionMode.NOT_BETWEEN, "isview", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsviewIn(List<java.lang.String> values) {
          addCriterion("isview", values, ConditionMode.IN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsviewNotIn(List<java.lang.String> values) {
          addCriterion("isview", values, ConditionMode.NOT_IN, "isview", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsselectIsNull() {
		isnull("isselect");
		return this;
	}
	
	public MetaFieldCriteria andIsselectIsNotNull() {
		notNull("isselect");
		return this;
	}
	
	public MetaFieldCriteria andIsselectIsEmpty() {
		empty("isselect");
		return this;
	}

	public MetaFieldCriteria andIsselectIsNotEmpty() {
		notEmpty("isselect");
		return this;
	}
        public MetaFieldCriteria andIsselectLike(java.lang.String value) {
    	   addCriterion("isselect", value, ConditionMode.FUZZY, "isselect", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsselectNotLike(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.NOT_FUZZY, "isselect", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsselectEqualTo(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.EQUAL, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectNotEqualTo(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.NOT_EQUAL, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectGreaterThan(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.GREATER_THEN, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.GREATER_EQUAL, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectLessThan(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.LESS_THEN, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isselect", value, ConditionMode.LESS_EQUAL, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isselect", value1, value2, ConditionMode.BETWEEN, "isselect", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsselectNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isselect", value1, value2, ConditionMode.NOT_BETWEEN, "isselect", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsselectIn(List<java.lang.String> values) {
          addCriterion("isselect", values, ConditionMode.IN, "isselect", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsselectNotIn(List<java.lang.String> values) {
          addCriterion("isselect", values, ConditionMode.NOT_IN, "isselect", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIseditIsNull() {
		isnull("isedit");
		return this;
	}
	
	public MetaFieldCriteria andIseditIsNotNull() {
		notNull("isedit");
		return this;
	}
	
	public MetaFieldCriteria andIseditIsEmpty() {
		empty("isedit");
		return this;
	}

	public MetaFieldCriteria andIseditIsNotEmpty() {
		notEmpty("isedit");
		return this;
	}
        public MetaFieldCriteria andIseditLike(java.lang.String value) {
    	   addCriterion("isedit", value, ConditionMode.FUZZY, "isedit", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIseditNotLike(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.NOT_FUZZY, "isedit", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIseditEqualTo(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.EQUAL, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditNotEqualTo(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.NOT_EQUAL, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditGreaterThan(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.GREATER_THEN, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.GREATER_EQUAL, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditLessThan(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.LESS_THEN, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isedit", value, ConditionMode.LESS_EQUAL, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isedit", value1, value2, ConditionMode.BETWEEN, "isedit", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIseditNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isedit", value1, value2, ConditionMode.NOT_BETWEEN, "isedit", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIseditIn(List<java.lang.String> values) {
          addCriterion("isedit", values, ConditionMode.IN, "isedit", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIseditNotIn(List<java.lang.String> values) {
          addCriterion("isedit", values, ConditionMode.NOT_IN, "isedit", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsmustfldIsNull() {
		isnull("ismustfld");
		return this;
	}
	
	public MetaFieldCriteria andIsmustfldIsNotNull() {
		notNull("ismustfld");
		return this;
	}
	
	public MetaFieldCriteria andIsmustfldIsEmpty() {
		empty("ismustfld");
		return this;
	}

	public MetaFieldCriteria andIsmustfldIsNotEmpty() {
		notEmpty("ismustfld");
		return this;
	}
        public MetaFieldCriteria andIsmustfldLike(java.lang.String value) {
    	   addCriterion("ismustfld", value, ConditionMode.FUZZY, "ismustfld", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsmustfldNotLike(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.NOT_FUZZY, "ismustfld", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsmustfldEqualTo(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.EQUAL, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldNotEqualTo(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.NOT_EQUAL, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldGreaterThan(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.GREATER_THEN, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.GREATER_EQUAL, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldLessThan(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.LESS_THEN, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldLessThanOrEqualTo(java.lang.String value) {
          addCriterion("ismustfld", value, ConditionMode.LESS_EQUAL, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("ismustfld", value1, value2, ConditionMode.BETWEEN, "ismustfld", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsmustfldNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("ismustfld", value1, value2, ConditionMode.NOT_BETWEEN, "ismustfld", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsmustfldIn(List<java.lang.String> values) {
          addCriterion("ismustfld", values, ConditionMode.IN, "ismustfld", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmustfldNotIn(List<java.lang.String> values) {
          addCriterion("ismustfld", values, ConditionMode.NOT_IN, "ismustfld", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andIsmapIsNull() {
		isnull("ismap");
		return this;
	}
	
	public MetaFieldCriteria andIsmapIsNotNull() {
		notNull("ismap");
		return this;
	}
	
	public MetaFieldCriteria andIsmapIsEmpty() {
		empty("ismap");
		return this;
	}

	public MetaFieldCriteria andIsmapIsNotEmpty() {
		notEmpty("ismap");
		return this;
	}
        public MetaFieldCriteria andIsmapLike(java.lang.String value) {
    	   addCriterion("ismap", value, ConditionMode.FUZZY, "ismap", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andIsmapNotLike(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.NOT_FUZZY, "ismap", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andIsmapEqualTo(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.EQUAL, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapNotEqualTo(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.NOT_EQUAL, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapGreaterThan(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.GREATER_THEN, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.GREATER_EQUAL, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapLessThan(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.LESS_THEN, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapLessThanOrEqualTo(java.lang.String value) {
          addCriterion("ismap", value, ConditionMode.LESS_EQUAL, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("ismap", value1, value2, ConditionMode.BETWEEN, "ismap", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andIsmapNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("ismap", value1, value2, ConditionMode.NOT_BETWEEN, "ismap", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andIsmapIn(List<java.lang.String> values) {
          addCriterion("ismap", values, ConditionMode.IN, "ismap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andIsmapNotIn(List<java.lang.String> values) {
          addCriterion("ismap", values, ConditionMode.NOT_IN, "ismap", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andOlapIsNull() {
		isnull("olap");
		return this;
	}
	
	public MetaFieldCriteria andOlapIsNotNull() {
		notNull("olap");
		return this;
	}
	
	public MetaFieldCriteria andOlapIsEmpty() {
		empty("olap");
		return this;
	}

	public MetaFieldCriteria andOlapIsNotEmpty() {
		notEmpty("olap");
		return this;
	}
        public MetaFieldCriteria andOlapLike(java.lang.String value) {
    	   addCriterion("olap", value, ConditionMode.FUZZY, "olap", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldCriteria andOlapNotLike(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.NOT_FUZZY, "olap", "java.lang.String", "String");
          return this;
      }
      public MetaFieldCriteria andOlapEqualTo(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.EQUAL, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapNotEqualTo(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.NOT_EQUAL, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapGreaterThan(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.GREATER_THEN, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.GREATER_EQUAL, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapLessThan(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.LESS_THEN, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapLessThanOrEqualTo(java.lang.String value) {
          addCriterion("olap", value, ConditionMode.LESS_EQUAL, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("olap", value1, value2, ConditionMode.BETWEEN, "olap", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andOlapNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("olap", value1, value2, ConditionMode.NOT_BETWEEN, "olap", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andOlapIn(List<java.lang.String> values) {
          addCriterion("olap", values, ConditionMode.IN, "olap", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andOlapNotIn(List<java.lang.String> values) {
          addCriterion("olap", values, ConditionMode.NOT_IN, "olap", "java.lang.String", "String");
          return this;
      }
	public MetaFieldCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public MetaFieldCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public MetaFieldCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public MetaFieldCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public MetaFieldCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldCriteria andPermissionIsNull() {
		isnull("permission");
		return this;
	}
	
	public MetaFieldCriteria andPermissionIsNotNull() {
		notNull("permission");
		return this;
	}
	
	public MetaFieldCriteria andPermissionIsEmpty() {
		empty("permission");
		return this;
	}

	public MetaFieldCriteria andPermissionIsNotEmpty() {
		notEmpty("permission");
		return this;
	}
        public MetaFieldCriteria andPermissionLike(java.lang.String value) {
    	   addCriterion("permission", value, ConditionMode.FUZZY, "permission", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldCriteria andPermissionNotLike(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_FUZZY, "permission", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldCriteria andPermissionEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionNotEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.NOT_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionGreaterThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.GREATER_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionLessThan(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_THEN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permission", value, ConditionMode.LESS_EQUAL, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permission", value1, value2, ConditionMode.BETWEEN, "permission", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldCriteria andPermissionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permission", value1, value2, ConditionMode.NOT_BETWEEN, "permission", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldCriteria andPermissionIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.IN, "permission", "java.lang.String", "String");
          return this;
      }

      public MetaFieldCriteria andPermissionNotIn(List<java.lang.String> values) {
          addCriterion("permission", values, ConditionMode.NOT_IN, "permission", "java.lang.String", "String");
          return this;
      }
}