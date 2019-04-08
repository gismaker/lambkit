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
public class UpmsUserPermissionCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsUserPermissionCriteria create() {
		return new UpmsUserPermissionCriteria();
	}
	
	public static UpmsUserPermissionCriteria create(Column column) {
		UpmsUserPermissionCriteria that = new UpmsUserPermissionCriteria();
		that.add(column);
        return that;
    }

    public static UpmsUserPermissionCriteria create(String name, Object value) {
        return (UpmsUserPermissionCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_user_permission", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsUserPermissionCriteria eq(String name, Object value) {
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
    public UpmsUserPermissionCriteria ne(String name, Object value) {
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

    public UpmsUserPermissionCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsUserPermissionCriteria notLike(String name, Object value) {
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
    public UpmsUserPermissionCriteria gt(String name, Object value) {
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
    public UpmsUserPermissionCriteria ge(String name, Object value) {
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
    public UpmsUserPermissionCriteria lt(String name, Object value) {
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
    public UpmsUserPermissionCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsUserPermissionCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsUserPermissionCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsUserPermissionCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsUserPermissionCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsUserPermissionCriteria add(Column column) {
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
		 
	public UpmsUserPermissionCriteria andUserPermissionIdIsNull() {
		isnull("user_permission_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andUserPermissionIdIsNotNull() {
		notNull("user_permission_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andUserPermissionIdIsEmpty() {
		empty("user_permission_id");
		return this;
	}

	public UpmsUserPermissionCriteria andUserPermissionIdIsNotEmpty() {
		notEmpty("user_permission_id");
		return this;
	}
       public UpmsUserPermissionCriteria andUserPermissionIdEqualTo(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.EQUAL, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.NOT_EQUAL, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdGreaterThan(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.GREATER_THEN, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.GREATER_EQUAL, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdLessThan(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.LESS_THEN, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_permission_id", value, ConditionMode.LESS_EQUAL, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_permission_id", value1, value2, ConditionMode.BETWEEN, "userPermissionId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_permission_id", value1, value2, ConditionMode.NOT_BETWEEN, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserPermissionCriteria andUserPermissionIdIn(List<java.lang.Long> values) {
          addCriterion("user_permission_id", values, ConditionMode.IN, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserPermissionIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_permission_id", values, ConditionMode.NOT_IN, "userPermissionId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserPermissionCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsUserPermissionCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsUserPermissionCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserPermissionCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserPermissionCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserPermissionCriteria andPermissionIdIsNull() {
		isnull("permission_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andPermissionIdIsNotNull() {
		notNull("permission_id");
		return this;
	}
	
	public UpmsUserPermissionCriteria andPermissionIdIsEmpty() {
		empty("permission_id");
		return this;
	}

	public UpmsUserPermissionCriteria andPermissionIdIsNotEmpty() {
		notEmpty("permission_id");
		return this;
	}
       public UpmsUserPermissionCriteria andPermissionIdEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdNotEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.NOT_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdGreaterThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdLessThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("permission_id", value1, value2, ConditionMode.BETWEEN, "permissionId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("permission_id", value1, value2, ConditionMode.NOT_BETWEEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserPermissionCriteria andPermissionIdIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andPermissionIdNotIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.NOT_IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserPermissionCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public UpmsUserPermissionCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public UpmsUserPermissionCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public UpmsUserPermissionCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
       public UpmsUserPermissionCriteria andTypeEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeNotEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeGreaterThan(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeLessThan(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsUserPermissionCriteria andTypeNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsUserPermissionCriteria andTypeIn(List<java.lang.Integer> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserPermissionCriteria andTypeNotIn(List<java.lang.Integer> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.Integer", "Float");
          return this;
      }
}