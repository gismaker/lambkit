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
public class UpmsUserOrganizationCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsUserOrganizationCriteria create() {
		return new UpmsUserOrganizationCriteria();
	}
	
	public static UpmsUserOrganizationCriteria create(Column column) {
		UpmsUserOrganizationCriteria that = new UpmsUserOrganizationCriteria();
		that.add(column);
        return that;
    }

    public static UpmsUserOrganizationCriteria create(String name, Object value) {
        return (UpmsUserOrganizationCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_user_organization", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsUserOrganizationCriteria eq(String name, Object value) {
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
    public UpmsUserOrganizationCriteria ne(String name, Object value) {
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

    public UpmsUserOrganizationCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsUserOrganizationCriteria notLike(String name, Object value) {
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
    public UpmsUserOrganizationCriteria gt(String name, Object value) {
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
    public UpmsUserOrganizationCriteria ge(String name, Object value) {
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
    public UpmsUserOrganizationCriteria lt(String name, Object value) {
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
    public UpmsUserOrganizationCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsUserOrganizationCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsUserOrganizationCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsUserOrganizationCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsUserOrganizationCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsUserOrganizationCriteria add(Column column) {
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
		 
	public UpmsUserOrganizationCriteria andUserOrganizationIdIsNull() {
		isnull("user_organization_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andUserOrganizationIdIsNotNull() {
		notNull("user_organization_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andUserOrganizationIdIsEmpty() {
		empty("user_organization_id");
		return this;
	}

	public UpmsUserOrganizationCriteria andUserOrganizationIdIsNotEmpty() {
		notEmpty("user_organization_id");
		return this;
	}
       public UpmsUserOrganizationCriteria andUserOrganizationIdEqualTo(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.EQUAL, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.NOT_EQUAL, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdGreaterThan(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.GREATER_THEN, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.GREATER_EQUAL, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdLessThan(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.LESS_THEN, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_organization_id", value, ConditionMode.LESS_EQUAL, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_organization_id", value1, value2, ConditionMode.BETWEEN, "userOrganizationId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_organization_id", value1, value2, ConditionMode.NOT_BETWEEN, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserOrganizationCriteria andUserOrganizationIdIn(List<java.lang.Long> values) {
          addCriterion("user_organization_id", values, ConditionMode.IN, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserOrganizationIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_organization_id", values, ConditionMode.NOT_IN, "userOrganizationId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserOrganizationCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsUserOrganizationCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsUserOrganizationCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserOrganizationCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserOrganizationCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserOrganizationCriteria andOrganizationIdIsNull() {
		isnull("organization_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andOrganizationIdIsNotNull() {
		notNull("organization_id");
		return this;
	}
	
	public UpmsUserOrganizationCriteria andOrganizationIdIsEmpty() {
		empty("organization_id");
		return this;
	}

	public UpmsUserOrganizationCriteria andOrganizationIdIsNotEmpty() {
		notEmpty("organization_id");
		return this;
	}
       public UpmsUserOrganizationCriteria andOrganizationIdEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdNotEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.NOT_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdGreaterThan(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.GREATER_THEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.GREATER_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdLessThan(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.LESS_THEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.LESS_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("organization_id", value1, value2, ConditionMode.BETWEEN, "organizationId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("organization_id", value1, value2, ConditionMode.NOT_BETWEEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserOrganizationCriteria andOrganizationIdIn(List<java.lang.Long> values) {
          addCriterion("organization_id", values, ConditionMode.IN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserOrganizationCriteria andOrganizationIdNotIn(List<java.lang.Long> values) {
          addCriterion("organization_id", values, ConditionMode.NOT_IN, "organizationId", "java.lang.Long", "Float");
          return this;
      }
}