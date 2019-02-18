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
public class UpmsUserRoleCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsUserRoleCriteria create() {
		return new UpmsUserRoleCriteria();
	}
	
	public static UpmsUserRoleCriteria create(Column column) {
		UpmsUserRoleCriteria that = new UpmsUserRoleCriteria();
		that.add(column);
        return that;
    }

    public static UpmsUserRoleCriteria create(String name, Object value) {
        return (UpmsUserRoleCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_user_role", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsUserRoleCriteria eq(String name, Object value) {
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
    public UpmsUserRoleCriteria ne(String name, Object value) {
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

    public UpmsUserRoleCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsUserRoleCriteria notLike(String name, Object value) {
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
    public UpmsUserRoleCriteria gt(String name, Object value) {
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
    public UpmsUserRoleCriteria ge(String name, Object value) {
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
    public UpmsUserRoleCriteria lt(String name, Object value) {
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
    public UpmsUserRoleCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsUserRoleCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsUserRoleCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsUserRoleCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsUserRoleCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsUserRoleCriteria add(Column column) {
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
		 
	public UpmsUserRoleCriteria andUserRoleIdIsNull() {
		isnull("user_role_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andUserRoleIdIsNotNull() {
		notNull("user_role_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andUserRoleIdIsEmpty() {
		empty("user_role_id");
		return this;
	}

	public UpmsUserRoleCriteria andUserRoleIdIsNotEmpty() {
		notEmpty("user_role_id");
		return this;
	}
       public UpmsUserRoleCriteria andUserRoleIdEqualTo(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.EQUAL, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.NOT_EQUAL, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdGreaterThan(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.GREATER_THEN, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.GREATER_EQUAL, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdLessThan(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.LESS_THEN, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_role_id", value, ConditionMode.LESS_EQUAL, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_role_id", value1, value2, ConditionMode.BETWEEN, "userRoleId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_role_id", value1, value2, ConditionMode.NOT_BETWEEN, "userRoleId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserRoleCriteria andUserRoleIdIn(List<java.lang.Long> values) {
          addCriterion("user_role_id", values, ConditionMode.IN, "userRoleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserRoleIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_role_id", values, ConditionMode.NOT_IN, "userRoleId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserRoleCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsUserRoleCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsUserRoleCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserRoleCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserRoleCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserRoleCriteria andRoleIdIsNull() {
		isnull("role_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andRoleIdIsNotNull() {
		notNull("role_id");
		return this;
	}
	
	public UpmsUserRoleCriteria andRoleIdIsEmpty() {
		empty("role_id");
		return this;
	}

	public UpmsUserRoleCriteria andRoleIdIsNotEmpty() {
		notEmpty("role_id");
		return this;
	}
       public UpmsUserRoleCriteria andRoleIdEqualTo(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.EQUAL, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdNotEqualTo(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.NOT_EQUAL, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdGreaterThan(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.GREATER_THEN, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.GREATER_EQUAL, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdLessThan(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.LESS_THEN, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("role_id", value, ConditionMode.LESS_EQUAL, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("role_id", value1, value2, ConditionMode.BETWEEN, "roleId", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsUserRoleCriteria andRoleIdNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("role_id", value1, value2, ConditionMode.NOT_BETWEEN, "roleId", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsUserRoleCriteria andRoleIdIn(List<java.lang.Integer> values) {
          addCriterion("role_id", values, ConditionMode.IN, "roleId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserRoleCriteria andRoleIdNotIn(List<java.lang.Integer> values) {
          addCriterion("role_id", values, ConditionMode.NOT_IN, "roleId", "java.lang.Integer", "Float");
          return this;
      }
}