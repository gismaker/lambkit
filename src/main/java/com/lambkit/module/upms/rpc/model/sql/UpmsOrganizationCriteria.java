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
package com.lambkit.module.upms.rpc.model.sql;

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
public class UpmsOrganizationCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsOrganizationCriteria create() {
		return new UpmsOrganizationCriteria();
	}
	
	public static UpmsOrganizationCriteria create(Column column) {
		UpmsOrganizationCriteria that = new UpmsOrganizationCriteria();
		that.add(column);
        return that;
    }

    public static UpmsOrganizationCriteria create(String name, Object value) {
        return (UpmsOrganizationCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_organization", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsOrganizationCriteria eq(String name, Object value) {
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
    public UpmsOrganizationCriteria ne(String name, Object value) {
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

    public UpmsOrganizationCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsOrganizationCriteria notLike(String name, Object value) {
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
    public UpmsOrganizationCriteria gt(String name, Object value) {
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
    public UpmsOrganizationCriteria ge(String name, Object value) {
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
    public UpmsOrganizationCriteria lt(String name, Object value) {
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
    public UpmsOrganizationCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsOrganizationCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsOrganizationCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsOrganizationCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsOrganizationCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsOrganizationCriteria add(Column column) {
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
		 
	public UpmsOrganizationCriteria andOrganizationIdIsNull() {
		isnull("organization_id");
		return this;
	}
	
	public UpmsOrganizationCriteria andOrganizationIdIsNotNull() {
		notNull("organization_id");
		return this;
	}
	
	public UpmsOrganizationCriteria andOrganizationIdIsEmpty() {
		empty("organization_id");
		return this;
	}

	public UpmsOrganizationCriteria andOrganizationIdIsNotEmpty() {
		notEmpty("organization_id");
		return this;
	}
       public UpmsOrganizationCriteria andOrganizationIdEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdNotEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.NOT_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdGreaterThan(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.GREATER_THEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.GREATER_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdLessThan(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.LESS_THEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("organization_id", value, ConditionMode.LESS_EQUAL, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("organization_id", value1, value2, ConditionMode.BETWEEN, "organizationId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("organization_id", value1, value2, ConditionMode.NOT_BETWEEN, "organizationId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsOrganizationCriteria andOrganizationIdIn(List<java.lang.Long> values) {
          addCriterion("organization_id", values, ConditionMode.IN, "organizationId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andOrganizationIdNotIn(List<java.lang.Long> values) {
          addCriterion("organization_id", values, ConditionMode.NOT_IN, "organizationId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsOrganizationCriteria andPidIsNull() {
		isnull("pid");
		return this;
	}
	
	public UpmsOrganizationCriteria andPidIsNotNull() {
		notNull("pid");
		return this;
	}
	
	public UpmsOrganizationCriteria andPidIsEmpty() {
		empty("pid");
		return this;
	}

	public UpmsOrganizationCriteria andPidIsNotEmpty() {
		notEmpty("pid");
		return this;
	}
       public UpmsOrganizationCriteria andPidEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidNotEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.NOT_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidGreaterThan(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.GREATER_THEN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.GREATER_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidLessThan(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.LESS_THEN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("pid", value, ConditionMode.LESS_EQUAL, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("pid", value1, value2, ConditionMode.BETWEEN, "pid", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsOrganizationCriteria andPidNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("pid", value1, value2, ConditionMode.NOT_BETWEEN, "pid", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsOrganizationCriteria andPidIn(List<java.lang.Integer> values) {
          addCriterion("pid", values, ConditionMode.IN, "pid", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andPidNotIn(List<java.lang.Integer> values) {
          addCriterion("pid", values, ConditionMode.NOT_IN, "pid", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsOrganizationCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public UpmsOrganizationCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public UpmsOrganizationCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public UpmsOrganizationCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public UpmsOrganizationCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsOrganizationCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public UpmsOrganizationCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public UpmsOrganizationCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public UpmsOrganizationCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public UpmsOrganizationCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsOrganizationCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsOrganizationCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsOrganizationCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsOrganizationCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public UpmsOrganizationCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public UpmsOrganizationCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsOrganizationCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsOrganizationCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsOrganizationCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsOrganizationCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsOrganizationCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsOrganizationCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsOrganizationCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsOrganizationCriteria andCtimeEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeNotEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeGreaterThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeLessThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsOrganizationCriteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsOrganizationCriteria andCtimeIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsOrganizationCriteria andCtimeNotIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.lang.Long", "Float");
          return this;
      }
}