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
package com.lambkit.module.upms.rpc.sql;

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
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
public class UpmsTagCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsTagCriteria create() {
		return new UpmsTagCriteria();
	}
	
	public static UpmsTagCriteria create(Column column) {
		UpmsTagCriteria that = new UpmsTagCriteria();
		that.add(column);
        return that;
    }

    public static UpmsTagCriteria create(String name, Object value) {
        return (UpmsTagCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_tag", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsTagCriteria eq(String name, Object value) {
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
    public UpmsTagCriteria ne(String name, Object value) {
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

    public UpmsTagCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsTagCriteria notLike(String name, Object value) {
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
    public UpmsTagCriteria gt(String name, Object value) {
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
    public UpmsTagCriteria ge(String name, Object value) {
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
    public UpmsTagCriteria lt(String name, Object value) {
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
    public UpmsTagCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsTagCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsTagCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsTagCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsTagCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsTagCriteria add(Column column) {
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
		 
	public UpmsTagCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public UpmsTagCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public UpmsTagCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public UpmsTagCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public UpmsTagCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsTagCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsTagCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public UpmsTagCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsTagCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsTagCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsTagCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsTagCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsTagCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsTagCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsTagCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsTagCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public UpmsTagCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public UpmsTagCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public UpmsTagCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public UpmsTagCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsTagCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public UpmsTagCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public UpmsTagCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public UpmsTagCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public UpmsTagCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsTagCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsTagCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsTagCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsTagCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public UpmsTagCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public UpmsTagCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsTagCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsTagCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsTagCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsTagCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public UpmsTagCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public UpmsTagCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public UpmsTagCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public UpmsTagCriteria andOrdersEqualTo(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersNotEqualTo(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersGreaterThan(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersGreaterThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersLessThan(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersLessThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.math.BigInteger", "String");
    	  return this;
      }

      public UpmsTagCriteria andOrdersNotBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.math.BigInteger", "String");
          return this;
      }
        
      public UpmsTagCriteria andOrdersIn(List<java.math.BigInteger> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andOrdersNotIn(List<java.math.BigInteger> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.math.BigInteger", "String");
          return this;
      }
	public UpmsTagCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsTagCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsTagCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsTagCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsTagCriteria andCtimeEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeNotEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeGreaterThan(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeGreaterThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeLessThan(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeLessThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.math.BigInteger", "String");
    	  return this;
      }

      public UpmsTagCriteria andCtimeNotBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }
        
      public UpmsTagCriteria andCtimeIn(List<java.math.BigInteger> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsTagCriteria andCtimeNotIn(List<java.math.BigInteger> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.math.BigInteger", "String");
          return this;
      }
}