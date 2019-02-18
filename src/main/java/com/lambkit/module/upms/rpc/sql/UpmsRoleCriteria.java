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
public class UpmsRoleCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsRoleCriteria create() {
		return new UpmsRoleCriteria();
	}
	
	public static UpmsRoleCriteria create(Column column) {
		UpmsRoleCriteria that = new UpmsRoleCriteria();
		that.add(column);
        return that;
    }

    public static UpmsRoleCriteria create(String name, Object value) {
        return (UpmsRoleCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_role", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsRoleCriteria eq(String name, Object value) {
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
    public UpmsRoleCriteria ne(String name, Object value) {
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

    public UpmsRoleCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsRoleCriteria notLike(String name, Object value) {
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
    public UpmsRoleCriteria gt(String name, Object value) {
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
    public UpmsRoleCriteria ge(String name, Object value) {
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
    public UpmsRoleCriteria lt(String name, Object value) {
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
    public UpmsRoleCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsRoleCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsRoleCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsRoleCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsRoleCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsRoleCriteria add(Column column) {
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
		 
	public UpmsRoleCriteria andRoleIdIsNull() {
		isnull("role_id");
		return this;
	}
	
	public UpmsRoleCriteria andRoleIdIsNotNull() {
		notNull("role_id");
		return this;
	}
	
	public UpmsRoleCriteria andRoleIdIsEmpty() {
		empty("role_id");
		return this;
	}

	public UpmsRoleCriteria andRoleIdIsNotEmpty() {
		notEmpty("role_id");
		return this;
	}
       public UpmsRoleCriteria andRoleIdEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdNotEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.NOT_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdGreaterThan(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.GREATER_THEN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.GREATER_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdLessThan(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.LESS_THEN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.LESS_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("role_id", value1, value2, ConditionMode.BETWEEN, "roleId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRoleCriteria andRoleIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("role_id", value1, value2, ConditionMode.NOT_BETWEEN, "roleId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRoleCriteria andRoleIdIn(List<java.lang.Long> values) {
          addCriterion("role_id", values, ConditionMode.IN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andRoleIdNotIn(List<java.lang.Long> values) {
          addCriterion("role_id", values, ConditionMode.NOT_IN, "roleId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsRoleCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public UpmsRoleCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public UpmsRoleCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public UpmsRoleCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public UpmsRoleCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsRoleCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public UpmsRoleCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public UpmsRoleCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public UpmsRoleCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public UpmsRoleCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public UpmsRoleCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public UpmsRoleCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public UpmsRoleCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public UpmsRoleCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "String");
    	   return this;
      }

      public UpmsRoleCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "String");
          return this;
      }
      public UpmsRoleCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public UpmsRoleCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public UpmsRoleCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public UpmsRoleCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsRoleCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsRoleCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsRoleCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsRoleCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public UpmsRoleCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public UpmsRoleCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsRoleCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsRoleCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsRoleCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsRoleCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsRoleCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsRoleCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsRoleCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsRoleCriteria andCtimeEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeNotEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeGreaterThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeLessThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRoleCriteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRoleCriteria andCtimeIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andCtimeNotIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.lang.Long", "Float");
          return this;
      }
	public UpmsRoleCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public UpmsRoleCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public UpmsRoleCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public UpmsRoleCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public UpmsRoleCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRoleCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRoleCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRoleCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
}