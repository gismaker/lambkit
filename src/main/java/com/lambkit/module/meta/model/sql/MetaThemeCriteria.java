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
public class MetaThemeCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaThemeCriteria create() {
		return new MetaThemeCriteria();
	}
	
	public static MetaThemeCriteria create(Column column) {
		MetaThemeCriteria that = new MetaThemeCriteria();
		that.add(column);
        return that;
    }

    public static MetaThemeCriteria create(String name, Object value) {
        return (MetaThemeCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_theme", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaThemeCriteria eq(String name, Object value) {
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
    public MetaThemeCriteria ne(String name, Object value) {
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

    public MetaThemeCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaThemeCriteria notLike(String name, Object value) {
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
    public MetaThemeCriteria gt(String name, Object value) {
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
    public MetaThemeCriteria ge(String name, Object value) {
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
    public MetaThemeCriteria lt(String name, Object value) {
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
    public MetaThemeCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaThemeCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaThemeCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaThemeCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaThemeCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaThemeCriteria add(Column column) {
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
		 
	public MetaThemeCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaThemeCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaThemeCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaThemeCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaThemeCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaThemeCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaThemeCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaThemeCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaThemeCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaThemeCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaThemeCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public MetaThemeCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public MetaThemeCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public MetaThemeCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public MetaThemeCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public MetaThemeCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public MetaThemeCriteria andUseridIsNull() {
		isnull("userid");
		return this;
	}
	
	public MetaThemeCriteria andUseridIsNotNull() {
		notNull("userid");
		return this;
	}
	
	public MetaThemeCriteria andUseridIsEmpty() {
		empty("userid");
		return this;
	}

	public MetaThemeCriteria andUseridIsNotEmpty() {
		notEmpty("userid");
		return this;
	}
       public MetaThemeCriteria andUseridEqualTo(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.EQUAL, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridNotEqualTo(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.NOT_EQUAL, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridGreaterThan(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.GREATER_THEN, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.GREATER_EQUAL, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridLessThan(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.LESS_THEN, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("userid", value, ConditionMode.LESS_EQUAL, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("userid", value1, value2, ConditionMode.BETWEEN, "userid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaThemeCriteria andUseridNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("userid", value1, value2, ConditionMode.NOT_BETWEEN, "userid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaThemeCriteria andUseridIn(List<java.lang.Long> values) {
          addCriterion("userid", values, ConditionMode.IN, "userid", "java.lang.Long", "Float");
          return this;
      }

      public MetaThemeCriteria andUseridNotIn(List<java.lang.Long> values) {
          addCriterion("userid", values, ConditionMode.NOT_IN, "userid", "java.lang.Long", "Float");
          return this;
      }
	public MetaThemeCriteria andTmtypeIsNull() {
		isnull("tmtype");
		return this;
	}
	
	public MetaThemeCriteria andTmtypeIsNotNull() {
		notNull("tmtype");
		return this;
	}
	
	public MetaThemeCriteria andTmtypeIsEmpty() {
		empty("tmtype");
		return this;
	}

	public MetaThemeCriteria andTmtypeIsNotEmpty() {
		notEmpty("tmtype");
		return this;
	}
        public MetaThemeCriteria andTmtypeLike(java.lang.String value) {
    	   addCriterion("tmtype", value, ConditionMode.FUZZY, "tmtype", "java.lang.String", "Float");
    	   return this;
      }

      public MetaThemeCriteria andTmtypeNotLike(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.NOT_FUZZY, "tmtype", "java.lang.String", "Float");
          return this;
      }
      public MetaThemeCriteria andTmtypeEqualTo(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.EQUAL, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeNotEqualTo(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.NOT_EQUAL, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeGreaterThan(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.GREATER_THEN, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.GREATER_EQUAL, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeLessThan(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.LESS_THEN, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("tmtype", value, ConditionMode.LESS_EQUAL, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("tmtype", value1, value2, ConditionMode.BETWEEN, "tmtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaThemeCriteria andTmtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("tmtype", value1, value2, ConditionMode.NOT_BETWEEN, "tmtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaThemeCriteria andTmtypeIn(List<java.lang.String> values) {
          addCriterion("tmtype", values, ConditionMode.IN, "tmtype", "java.lang.String", "String");
          return this;
      }

      public MetaThemeCriteria andTmtypeNotIn(List<java.lang.String> values) {
          addCriterion("tmtype", values, ConditionMode.NOT_IN, "tmtype", "java.lang.String", "String");
          return this;
      }
	public MetaThemeCriteria andActiveIsNull() {
		isnull("active");
		return this;
	}
	
	public MetaThemeCriteria andActiveIsNotNull() {
		notNull("active");
		return this;
	}
	
	public MetaThemeCriteria andActiveIsEmpty() {
		empty("active");
		return this;
	}

	public MetaThemeCriteria andActiveIsNotEmpty() {
		notEmpty("active");
		return this;
	}
       public MetaThemeCriteria andActiveEqualTo(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.EQUAL, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveNotEqualTo(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.NOT_EQUAL, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveGreaterThan(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.GREATER_THEN, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.GREATER_EQUAL, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveLessThan(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.LESS_THEN, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("active", value, ConditionMode.LESS_EQUAL, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("active", value1, value2, ConditionMode.BETWEEN, "active", "java.lang.Integer", "Float");
    	  return this;
      }

      public MetaThemeCriteria andActiveNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("active", value1, value2, ConditionMode.NOT_BETWEEN, "active", "java.lang.Integer", "Float");
          return this;
      }
        
      public MetaThemeCriteria andActiveIn(List<java.lang.Integer> values) {
          addCriterion("active", values, ConditionMode.IN, "active", "java.lang.Integer", "Float");
          return this;
      }

      public MetaThemeCriteria andActiveNotIn(List<java.lang.Integer> values) {
          addCriterion("active", values, ConditionMode.NOT_IN, "active", "java.lang.Integer", "Float");
          return this;
      }
}