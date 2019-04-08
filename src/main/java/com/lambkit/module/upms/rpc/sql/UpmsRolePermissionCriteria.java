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
public class UpmsRolePermissionCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsRolePermissionCriteria create() {
		return new UpmsRolePermissionCriteria();
	}
	
	public static UpmsRolePermissionCriteria create(Column column) {
		UpmsRolePermissionCriteria that = new UpmsRolePermissionCriteria();
		that.add(column);
        return that;
    }

    public static UpmsRolePermissionCriteria create(String name, Object value) {
        return (UpmsRolePermissionCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_role_permission", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsRolePermissionCriteria eq(String name, Object value) {
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
    public UpmsRolePermissionCriteria ne(String name, Object value) {
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

    public UpmsRolePermissionCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsRolePermissionCriteria notLike(String name, Object value) {
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
    public UpmsRolePermissionCriteria gt(String name, Object value) {
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
    public UpmsRolePermissionCriteria ge(String name, Object value) {
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
    public UpmsRolePermissionCriteria lt(String name, Object value) {
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
    public UpmsRolePermissionCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsRolePermissionCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsRolePermissionCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsRolePermissionCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsRolePermissionCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsRolePermissionCriteria add(Column column) {
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
		 
	public UpmsRolePermissionCriteria andRolePermissionIdIsNull() {
		isnull("role_permission_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andRolePermissionIdIsNotNull() {
		notNull("role_permission_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andRolePermissionIdIsEmpty() {
		empty("role_permission_id");
		return this;
	}

	public UpmsRolePermissionCriteria andRolePermissionIdIsNotEmpty() {
		notEmpty("role_permission_id");
		return this;
	}
       public UpmsRolePermissionCriteria andRolePermissionIdEqualTo(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.EQUAL, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdNotEqualTo(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.NOT_EQUAL, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdGreaterThan(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.GREATER_THEN, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.GREATER_EQUAL, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdLessThan(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.LESS_THEN, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_permission_id", value, ConditionMode.LESS_EQUAL, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("role_permission_id", value1, value2, ConditionMode.BETWEEN, "rolePermissionId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("role_permission_id", value1, value2, ConditionMode.NOT_BETWEEN, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRolePermissionCriteria andRolePermissionIdIn(List<java.lang.Long> values) {
          addCriterion("role_permission_id", values, ConditionMode.IN, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRolePermissionIdNotIn(List<java.lang.Long> values) {
          addCriterion("role_permission_id", values, ConditionMode.NOT_IN, "rolePermissionId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsRolePermissionCriteria andRoleIdIsNull() {
		isnull("role_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andRoleIdIsNotNull() {
		notNull("role_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andRoleIdIsEmpty() {
		empty("role_id");
		return this;
	}

	public UpmsRolePermissionCriteria andRoleIdIsNotEmpty() {
		notEmpty("role_id");
		return this;
	}
       public UpmsRolePermissionCriteria andRoleIdEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdNotEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.NOT_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdGreaterThan(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.GREATER_THEN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.GREATER_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdLessThan(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.LESS_THEN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("role_id", value, ConditionMode.LESS_EQUAL, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("role_id", value1, value2, ConditionMode.BETWEEN, "roleId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRolePermissionCriteria andRoleIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("role_id", value1, value2, ConditionMode.NOT_BETWEEN, "roleId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRolePermissionCriteria andRoleIdIn(List<java.lang.Long> values) {
          addCriterion("role_id", values, ConditionMode.IN, "roleId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andRoleIdNotIn(List<java.lang.Long> values) {
          addCriterion("role_id", values, ConditionMode.NOT_IN, "roleId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsRolePermissionCriteria andPermissionIdIsNull() {
		isnull("permission_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andPermissionIdIsNotNull() {
		notNull("permission_id");
		return this;
	}
	
	public UpmsRolePermissionCriteria andPermissionIdIsEmpty() {
		empty("permission_id");
		return this;
	}

	public UpmsRolePermissionCriteria andPermissionIdIsNotEmpty() {
		notEmpty("permission_id");
		return this;
	}
       public UpmsRolePermissionCriteria andPermissionIdEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdNotEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.NOT_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdGreaterThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdLessThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("permission_id", value1, value2, ConditionMode.BETWEEN, "permissionId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("permission_id", value1, value2, ConditionMode.NOT_BETWEEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsRolePermissionCriteria andPermissionIdIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsRolePermissionCriteria andPermissionIdNotIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.NOT_IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
}