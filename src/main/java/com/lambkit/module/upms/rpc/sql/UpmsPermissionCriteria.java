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
public class UpmsPermissionCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsPermissionCriteria create() {
		return new UpmsPermissionCriteria();
	}
	
	public static UpmsPermissionCriteria create(Column column) {
		UpmsPermissionCriteria that = new UpmsPermissionCriteria();
		that.add(column);
        return that;
    }

    public static UpmsPermissionCriteria create(String name, Object value) {
        return (UpmsPermissionCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_permission", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsPermissionCriteria eq(String name, Object value) {
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
    public UpmsPermissionCriteria ne(String name, Object value) {
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

    public UpmsPermissionCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsPermissionCriteria notLike(String name, Object value) {
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
    public UpmsPermissionCriteria gt(String name, Object value) {
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
    public UpmsPermissionCriteria ge(String name, Object value) {
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
    public UpmsPermissionCriteria lt(String name, Object value) {
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
    public UpmsPermissionCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsPermissionCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsPermissionCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsPermissionCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsPermissionCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsPermissionCriteria add(Column column) {
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
		 
	public UpmsPermissionCriteria andPermissionIdIsNull() {
		isnull("permission_id");
		return this;
	}
	
	public UpmsPermissionCriteria andPermissionIdIsNotNull() {
		notNull("permission_id");
		return this;
	}
	
	public UpmsPermissionCriteria andPermissionIdIsEmpty() {
		empty("permission_id");
		return this;
	}

	public UpmsPermissionCriteria andPermissionIdIsNotEmpty() {
		notEmpty("permission_id");
		return this;
	}
       public UpmsPermissionCriteria andPermissionIdEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdNotEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.NOT_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdGreaterThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.GREATER_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdLessThan(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_THEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("permission_id", value, ConditionMode.LESS_EQUAL, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("permission_id", value1, value2, ConditionMode.BETWEEN, "permissionId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andPermissionIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("permission_id", value1, value2, ConditionMode.NOT_BETWEEN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andPermissionIdIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPermissionIdNotIn(List<java.lang.Long> values) {
          addCriterion("permission_id", values, ConditionMode.NOT_IN, "permissionId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsPermissionCriteria andSystemIdIsNull() {
		isnull("system_id");
		return this;
	}
	
	public UpmsPermissionCriteria andSystemIdIsNotNull() {
		notNull("system_id");
		return this;
	}
	
	public UpmsPermissionCriteria andSystemIdIsEmpty() {
		empty("system_id");
		return this;
	}

	public UpmsPermissionCriteria andSystemIdIsNotEmpty() {
		notEmpty("system_id");
		return this;
	}
       public UpmsPermissionCriteria andSystemIdEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdNotEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.NOT_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdGreaterThan(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.GREATER_THEN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.GREATER_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdLessThan(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.LESS_THEN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.LESS_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("system_id", value1, value2, ConditionMode.BETWEEN, "systemId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andSystemIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("system_id", value1, value2, ConditionMode.NOT_BETWEEN, "systemId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andSystemIdIn(List<java.lang.Long> values) {
          addCriterion("system_id", values, ConditionMode.IN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andSystemIdNotIn(List<java.lang.Long> values) {
          addCriterion("system_id", values, ConditionMode.NOT_IN, "systemId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsPermissionCriteria andPidIsNull() {
		isnull("pid");
		return this;
	}
	
	public UpmsPermissionCriteria andPidIsNotNull() {
		notNull("pid");
		return this;
	}
	
	public UpmsPermissionCriteria andPidIsEmpty() {
		empty("pid");
		return this;
	}

	public UpmsPermissionCriteria andPidIsNotEmpty() {
		notEmpty("pid");
		return this;
	}
       public UpmsPermissionCriteria andPidEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidNotEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.NOT_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidGreaterThan(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.GREATER_THEN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.GREATER_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidLessThan(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.LESS_THEN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.LESS_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("pid", value1, value2, ConditionMode.BETWEEN, "pid", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andPidNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("pid", value1, value2, ConditionMode.NOT_BETWEEN, "pid", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andPidIn(List<java.lang.Integer> values) {
          addCriterion("pid", values, ConditionMode.IN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andPidNotIn(List<java.lang.Integer> values) {
          addCriterion("pid", values, ConditionMode.NOT_IN, "pid", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsPermissionCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public UpmsPermissionCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public UpmsPermissionCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public UpmsPermissionCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public UpmsPermissionCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsPermissionCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public UpmsPermissionCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public UpmsPermissionCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public UpmsPermissionCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public UpmsPermissionCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public UpmsPermissionCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public UpmsPermissionCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public UpmsPermissionCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
       public UpmsPermissionCriteria andTypeEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeNotEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeGreaterThan(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeLessThan(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andTypeNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andTypeIn(List<java.lang.Integer> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andTypeNotIn(List<java.lang.Integer> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsPermissionCriteria andPermissionValueIsNull() {
		isnull("permission_value");
		return this;
	}
	
	public UpmsPermissionCriteria andPermissionValueIsNotNull() {
		notNull("permission_value");
		return this;
	}
	
	public UpmsPermissionCriteria andPermissionValueIsEmpty() {
		empty("permission_value");
		return this;
	}

	public UpmsPermissionCriteria andPermissionValueIsNotEmpty() {
		notEmpty("permission_value");
		return this;
	}
        public UpmsPermissionCriteria andPermissionValueLike(java.lang.String value) {
    	   addCriterion("permission_value", value, ConditionMode.FUZZY, "permissionValue", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsPermissionCriteria andPermissionValueNotLike(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.NOT_FUZZY, "permissionValue", "java.lang.String", "Float");
          return this;
      }
      public UpmsPermissionCriteria andPermissionValueEqualTo(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.EQUAL, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueNotEqualTo(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.NOT_EQUAL, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueGreaterThan(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.GREATER_THEN, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.GREATER_EQUAL, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueLessThan(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.LESS_THEN, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permission_value", value, ConditionMode.LESS_EQUAL, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permission_value", value1, value2, ConditionMode.BETWEEN, "permissionValue", "java.lang.String", "String");
    	  return this;
      }

      public UpmsPermissionCriteria andPermissionValueNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permission_value", value1, value2, ConditionMode.NOT_BETWEEN, "permissionValue", "java.lang.String", "String");
          return this;
      }
        
      public UpmsPermissionCriteria andPermissionValueIn(List<java.lang.String> values) {
          addCriterion("permission_value", values, ConditionMode.IN, "permissionValue", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andPermissionValueNotIn(List<java.lang.String> values) {
          addCriterion("permission_value", values, ConditionMode.NOT_IN, "permissionValue", "java.lang.String", "String");
          return this;
      }
	public UpmsPermissionCriteria andUriIsNull() {
		isnull("uri");
		return this;
	}
	
	public UpmsPermissionCriteria andUriIsNotNull() {
		notNull("uri");
		return this;
	}
	
	public UpmsPermissionCriteria andUriIsEmpty() {
		empty("uri");
		return this;
	}

	public UpmsPermissionCriteria andUriIsNotEmpty() {
		notEmpty("uri");
		return this;
	}
        public UpmsPermissionCriteria andUriLike(java.lang.String value) {
    	   addCriterion("uri", value, ConditionMode.FUZZY, "uri", "java.lang.String", "String");
    	   return this;
      }

      public UpmsPermissionCriteria andUriNotLike(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.NOT_FUZZY, "uri", "java.lang.String", "String");
          return this;
      }
      public UpmsPermissionCriteria andUriEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriNotEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.NOT_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriGreaterThan(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.GREATER_THEN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.GREATER_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriLessThan(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.LESS_THEN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriLessThanOrEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.LESS_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("uri", value1, value2, ConditionMode.BETWEEN, "uri", "java.lang.String", "String");
    	  return this;
      }

      public UpmsPermissionCriteria andUriNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("uri", value1, value2, ConditionMode.NOT_BETWEEN, "uri", "java.lang.String", "String");
          return this;
      }
        
      public UpmsPermissionCriteria andUriIn(List<java.lang.String> values) {
          addCriterion("uri", values, ConditionMode.IN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andUriNotIn(List<java.lang.String> values) {
          addCriterion("uri", values, ConditionMode.NOT_IN, "uri", "java.lang.String", "String");
          return this;
      }
	public UpmsPermissionCriteria andIconIsNull() {
		isnull("icon");
		return this;
	}
	
	public UpmsPermissionCriteria andIconIsNotNull() {
		notNull("icon");
		return this;
	}
	
	public UpmsPermissionCriteria andIconIsEmpty() {
		empty("icon");
		return this;
	}

	public UpmsPermissionCriteria andIconIsNotEmpty() {
		notEmpty("icon");
		return this;
	}
        public UpmsPermissionCriteria andIconLike(java.lang.String value) {
    	   addCriterion("icon", value, ConditionMode.FUZZY, "icon", "java.lang.String", "String");
    	   return this;
      }

      public UpmsPermissionCriteria andIconNotLike(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.NOT_FUZZY, "icon", "java.lang.String", "String");
          return this;
      }
      public UpmsPermissionCriteria andIconEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconNotEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.NOT_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconGreaterThan(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.GREATER_THEN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.GREATER_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconLessThan(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.LESS_THEN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconLessThanOrEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.LESS_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("icon", value1, value2, ConditionMode.BETWEEN, "icon", "java.lang.String", "String");
    	  return this;
      }

      public UpmsPermissionCriteria andIconNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("icon", value1, value2, ConditionMode.NOT_BETWEEN, "icon", "java.lang.String", "String");
          return this;
      }
        
      public UpmsPermissionCriteria andIconIn(List<java.lang.String> values) {
          addCriterion("icon", values, ConditionMode.IN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsPermissionCriteria andIconNotIn(List<java.lang.String> values) {
          addCriterion("icon", values, ConditionMode.NOT_IN, "icon", "java.lang.String", "String");
          return this;
      }
	public UpmsPermissionCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public UpmsPermissionCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public UpmsPermissionCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public UpmsPermissionCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public UpmsPermissionCriteria andStatusEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusNotEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusGreaterThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusLessThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andStatusNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andStatusIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsPermissionCriteria andStatusNotIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsPermissionCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsPermissionCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsPermissionCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsPermissionCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsPermissionCriteria andCtimeEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeNotEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeGreaterThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeLessThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andCtimeIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andCtimeNotIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.lang.Long", "Float");
          return this;
      }
	public UpmsPermissionCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public UpmsPermissionCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public UpmsPermissionCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public UpmsPermissionCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public UpmsPermissionCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsPermissionCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsPermissionCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsPermissionCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
}