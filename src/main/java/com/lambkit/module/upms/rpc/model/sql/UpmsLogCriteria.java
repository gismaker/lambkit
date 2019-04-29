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
public class UpmsLogCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsLogCriteria create() {
		return new UpmsLogCriteria();
	}
	
	public static UpmsLogCriteria create(Column column) {
		UpmsLogCriteria that = new UpmsLogCriteria();
		that.add(column);
        return that;
    }

    public static UpmsLogCriteria create(String name, Object value) {
        return (UpmsLogCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_log", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsLogCriteria eq(String name, Object value) {
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
    public UpmsLogCriteria ne(String name, Object value) {
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

    public UpmsLogCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsLogCriteria notLike(String name, Object value) {
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
    public UpmsLogCriteria gt(String name, Object value) {
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
    public UpmsLogCriteria ge(String name, Object value) {
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
    public UpmsLogCriteria lt(String name, Object value) {
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
    public UpmsLogCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsLogCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsLogCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsLogCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsLogCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsLogCriteria add(Column column) {
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
		 
	public UpmsLogCriteria andLogIdIsNull() {
		isnull("log_id");
		return this;
	}
	
	public UpmsLogCriteria andLogIdIsNotNull() {
		notNull("log_id");
		return this;
	}
	
	public UpmsLogCriteria andLogIdIsEmpty() {
		empty("log_id");
		return this;
	}

	public UpmsLogCriteria andLogIdIsNotEmpty() {
		notEmpty("log_id");
		return this;
	}
       public UpmsLogCriteria andLogIdEqualTo(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.EQUAL, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdNotEqualTo(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.NOT_EQUAL, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdGreaterThan(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.GREATER_THEN, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.GREATER_EQUAL, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdLessThan(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.LESS_THEN, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("log_id", value, ConditionMode.LESS_EQUAL, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("log_id", value1, value2, ConditionMode.BETWEEN, "logId", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsLogCriteria andLogIdNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("log_id", value1, value2, ConditionMode.NOT_BETWEEN, "logId", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsLogCriteria andLogIdIn(List<java.lang.Integer> values) {
          addCriterion("log_id", values, ConditionMode.IN, "logId", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andLogIdNotIn(List<java.lang.Integer> values) {
          addCriterion("log_id", values, ConditionMode.NOT_IN, "logId", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsLogCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsLogCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsLogCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsLogCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsLogCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsLogCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "Float");
          return this;
      }
      public UpmsLogCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andUsernameIsNull() {
		isnull("username");
		return this;
	}
	
	public UpmsLogCriteria andUsernameIsNotNull() {
		notNull("username");
		return this;
	}
	
	public UpmsLogCriteria andUsernameIsEmpty() {
		empty("username");
		return this;
	}

	public UpmsLogCriteria andUsernameIsNotEmpty() {
		notEmpty("username");
		return this;
	}
        public UpmsLogCriteria andUsernameLike(java.lang.String value) {
    	   addCriterion("username", value, ConditionMode.FUZZY, "username", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andUsernameNotLike(java.lang.String value) {
          addCriterion("username", value, ConditionMode.NOT_FUZZY, "username", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andUsernameEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameNotEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.NOT_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameGreaterThan(java.lang.String value) {
          addCriterion("username", value, ConditionMode.GREATER_THEN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.GREATER_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameLessThan(java.lang.String value) {
          addCriterion("username", value, ConditionMode.LESS_THEN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.LESS_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("username", value1, value2, ConditionMode.BETWEEN, "username", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andUsernameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("username", value1, value2, ConditionMode.NOT_BETWEEN, "username", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andUsernameIn(List<java.lang.String> values) {
          addCriterion("username", values, ConditionMode.IN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUsernameNotIn(List<java.lang.String> values) {
          addCriterion("username", values, ConditionMode.NOT_IN, "username", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andStartTimeIsNull() {
		isnull("start_time");
		return this;
	}
	
	public UpmsLogCriteria andStartTimeIsNotNull() {
		notNull("start_time");
		return this;
	}
	
	public UpmsLogCriteria andStartTimeIsEmpty() {
		empty("start_time");
		return this;
	}

	public UpmsLogCriteria andStartTimeIsNotEmpty() {
		notEmpty("start_time");
		return this;
	}
       public UpmsLogCriteria andStartTimeEqualTo(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.EQUAL, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeNotEqualTo(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.NOT_EQUAL, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeGreaterThan(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.GREATER_THEN, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.GREATER_EQUAL, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeLessThan(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.LESS_THEN, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("start_time", value, ConditionMode.LESS_EQUAL, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("start_time", value1, value2, ConditionMode.BETWEEN, "startTime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsLogCriteria andStartTimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("start_time", value1, value2, ConditionMode.NOT_BETWEEN, "startTime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsLogCriteria andStartTimeIn(List<java.lang.Long> values) {
          addCriterion("start_time", values, ConditionMode.IN, "startTime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsLogCriteria andStartTimeNotIn(List<java.lang.Long> values) {
          addCriterion("start_time", values, ConditionMode.NOT_IN, "startTime", "java.lang.Long", "Float");
          return this;
      }
	public UpmsLogCriteria andSpendTimeIsNull() {
		isnull("spend_time");
		return this;
	}
	
	public UpmsLogCriteria andSpendTimeIsNotNull() {
		notNull("spend_time");
		return this;
	}
	
	public UpmsLogCriteria andSpendTimeIsEmpty() {
		empty("spend_time");
		return this;
	}

	public UpmsLogCriteria andSpendTimeIsNotEmpty() {
		notEmpty("spend_time");
		return this;
	}
       public UpmsLogCriteria andSpendTimeEqualTo(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.EQUAL, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeNotEqualTo(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.NOT_EQUAL, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeGreaterThan(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.GREATER_THEN, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.GREATER_EQUAL, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeLessThan(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.LESS_THEN, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("spend_time", value, ConditionMode.LESS_EQUAL, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("spend_time", value1, value2, ConditionMode.BETWEEN, "spendTime", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsLogCriteria andSpendTimeNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("spend_time", value1, value2, ConditionMode.NOT_BETWEEN, "spendTime", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsLogCriteria andSpendTimeIn(List<java.lang.Integer> values) {
          addCriterion("spend_time", values, ConditionMode.IN, "spendTime", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsLogCriteria andSpendTimeNotIn(List<java.lang.Integer> values) {
          addCriterion("spend_time", values, ConditionMode.NOT_IN, "spendTime", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsLogCriteria andBasePathIsNull() {
		isnull("base_path");
		return this;
	}
	
	public UpmsLogCriteria andBasePathIsNotNull() {
		notNull("base_path");
		return this;
	}
	
	public UpmsLogCriteria andBasePathIsEmpty() {
		empty("base_path");
		return this;
	}

	public UpmsLogCriteria andBasePathIsNotEmpty() {
		notEmpty("base_path");
		return this;
	}
        public UpmsLogCriteria andBasePathLike(java.lang.String value) {
    	   addCriterion("base_path", value, ConditionMode.FUZZY, "basePath", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsLogCriteria andBasePathNotLike(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.NOT_FUZZY, "basePath", "java.lang.String", "Float");
          return this;
      }
      public UpmsLogCriteria andBasePathEqualTo(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.EQUAL, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathNotEqualTo(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.NOT_EQUAL, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathGreaterThan(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.GREATER_THEN, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.GREATER_EQUAL, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathLessThan(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.LESS_THEN, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathLessThanOrEqualTo(java.lang.String value) {
          addCriterion("base_path", value, ConditionMode.LESS_EQUAL, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("base_path", value1, value2, ConditionMode.BETWEEN, "basePath", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andBasePathNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("base_path", value1, value2, ConditionMode.NOT_BETWEEN, "basePath", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andBasePathIn(List<java.lang.String> values) {
          addCriterion("base_path", values, ConditionMode.IN, "basePath", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andBasePathNotIn(List<java.lang.String> values) {
          addCriterion("base_path", values, ConditionMode.NOT_IN, "basePath", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andUriIsNull() {
		isnull("uri");
		return this;
	}
	
	public UpmsLogCriteria andUriIsNotNull() {
		notNull("uri");
		return this;
	}
	
	public UpmsLogCriteria andUriIsEmpty() {
		empty("uri");
		return this;
	}

	public UpmsLogCriteria andUriIsNotEmpty() {
		notEmpty("uri");
		return this;
	}
        public UpmsLogCriteria andUriLike(java.lang.String value) {
    	   addCriterion("uri", value, ConditionMode.FUZZY, "uri", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andUriNotLike(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.NOT_FUZZY, "uri", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andUriEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriNotEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.NOT_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriGreaterThan(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.GREATER_THEN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.GREATER_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriLessThan(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.LESS_THEN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriLessThanOrEqualTo(java.lang.String value) {
          addCriterion("uri", value, ConditionMode.LESS_EQUAL, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("uri", value1, value2, ConditionMode.BETWEEN, "uri", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andUriNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("uri", value1, value2, ConditionMode.NOT_BETWEEN, "uri", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andUriIn(List<java.lang.String> values) {
          addCriterion("uri", values, ConditionMode.IN, "uri", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUriNotIn(List<java.lang.String> values) {
          addCriterion("uri", values, ConditionMode.NOT_IN, "uri", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public UpmsLogCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public UpmsLogCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public UpmsLogCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public UpmsLogCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andMethodIsNull() {
		isnull("method");
		return this;
	}
	
	public UpmsLogCriteria andMethodIsNotNull() {
		notNull("method");
		return this;
	}
	
	public UpmsLogCriteria andMethodIsEmpty() {
		empty("method");
		return this;
	}

	public UpmsLogCriteria andMethodIsNotEmpty() {
		notEmpty("method");
		return this;
	}
        public UpmsLogCriteria andMethodLike(java.lang.String value) {
    	   addCriterion("method", value, ConditionMode.FUZZY, "method", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andMethodNotLike(java.lang.String value) {
          addCriterion("method", value, ConditionMode.NOT_FUZZY, "method", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andMethodEqualTo(java.lang.String value) {
          addCriterion("method", value, ConditionMode.EQUAL, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodNotEqualTo(java.lang.String value) {
          addCriterion("method", value, ConditionMode.NOT_EQUAL, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodGreaterThan(java.lang.String value) {
          addCriterion("method", value, ConditionMode.GREATER_THEN, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("method", value, ConditionMode.GREATER_EQUAL, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodLessThan(java.lang.String value) {
          addCriterion("method", value, ConditionMode.LESS_THEN, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodLessThanOrEqualTo(java.lang.String value) {
          addCriterion("method", value, ConditionMode.LESS_EQUAL, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("method", value1, value2, ConditionMode.BETWEEN, "method", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andMethodNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("method", value1, value2, ConditionMode.NOT_BETWEEN, "method", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andMethodIn(List<java.lang.String> values) {
          addCriterion("method", values, ConditionMode.IN, "method", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andMethodNotIn(List<java.lang.String> values) {
          addCriterion("method", values, ConditionMode.NOT_IN, "method", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andParameterIsNull() {
		isnull("parameter");
		return this;
	}
	
	public UpmsLogCriteria andParameterIsNotNull() {
		notNull("parameter");
		return this;
	}
	
	public UpmsLogCriteria andParameterIsEmpty() {
		empty("parameter");
		return this;
	}

	public UpmsLogCriteria andParameterIsNotEmpty() {
		notEmpty("parameter");
		return this;
	}
        public UpmsLogCriteria andParameterLike(java.lang.String value) {
    	   addCriterion("parameter", value, ConditionMode.FUZZY, "parameter", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andParameterNotLike(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.NOT_FUZZY, "parameter", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andParameterEqualTo(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.EQUAL, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterNotEqualTo(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.NOT_EQUAL, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterGreaterThan(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.GREATER_THEN, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.GREATER_EQUAL, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterLessThan(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.LESS_THEN, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterLessThanOrEqualTo(java.lang.String value) {
          addCriterion("parameter", value, ConditionMode.LESS_EQUAL, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("parameter", value1, value2, ConditionMode.BETWEEN, "parameter", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andParameterNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("parameter", value1, value2, ConditionMode.NOT_BETWEEN, "parameter", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andParameterIn(List<java.lang.String> values) {
          addCriterion("parameter", values, ConditionMode.IN, "parameter", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andParameterNotIn(List<java.lang.String> values) {
          addCriterion("parameter", values, ConditionMode.NOT_IN, "parameter", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andUserAgentIsNull() {
		isnull("user_agent");
		return this;
	}
	
	public UpmsLogCriteria andUserAgentIsNotNull() {
		notNull("user_agent");
		return this;
	}
	
	public UpmsLogCriteria andUserAgentIsEmpty() {
		empty("user_agent");
		return this;
	}

	public UpmsLogCriteria andUserAgentIsNotEmpty() {
		notEmpty("user_agent");
		return this;
	}
        public UpmsLogCriteria andUserAgentLike(java.lang.String value) {
    	   addCriterion("user_agent", value, ConditionMode.FUZZY, "userAgent", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andUserAgentNotLike(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.NOT_FUZZY, "userAgent", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andUserAgentEqualTo(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.EQUAL, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentNotEqualTo(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.NOT_EQUAL, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentGreaterThan(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.GREATER_THEN, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.GREATER_EQUAL, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentLessThan(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.LESS_THEN, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentLessThanOrEqualTo(java.lang.String value) {
          addCriterion("user_agent", value, ConditionMode.LESS_EQUAL, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("user_agent", value1, value2, ConditionMode.BETWEEN, "userAgent", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andUserAgentNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("user_agent", value1, value2, ConditionMode.NOT_BETWEEN, "userAgent", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andUserAgentIn(List<java.lang.String> values) {
          addCriterion("user_agent", values, ConditionMode.IN, "userAgent", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andUserAgentNotIn(List<java.lang.String> values) {
          addCriterion("user_agent", values, ConditionMode.NOT_IN, "userAgent", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andIpIsNull() {
		isnull("ip");
		return this;
	}
	
	public UpmsLogCriteria andIpIsNotNull() {
		notNull("ip");
		return this;
	}
	
	public UpmsLogCriteria andIpIsEmpty() {
		empty("ip");
		return this;
	}

	public UpmsLogCriteria andIpIsNotEmpty() {
		notEmpty("ip");
		return this;
	}
        public UpmsLogCriteria andIpLike(java.lang.String value) {
    	   addCriterion("ip", value, ConditionMode.FUZZY, "ip", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andIpNotLike(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.NOT_FUZZY, "ip", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andIpEqualTo(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.EQUAL, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpNotEqualTo(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.NOT_EQUAL, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpGreaterThan(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.GREATER_THEN, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.GREATER_EQUAL, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpLessThan(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.LESS_THEN, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpLessThanOrEqualTo(java.lang.String value) {
          addCriterion("ip", value, ConditionMode.LESS_EQUAL, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("ip", value1, value2, ConditionMode.BETWEEN, "ip", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andIpNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("ip", value1, value2, ConditionMode.NOT_BETWEEN, "ip", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andIpIn(List<java.lang.String> values) {
          addCriterion("ip", values, ConditionMode.IN, "ip", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andIpNotIn(List<java.lang.String> values) {
          addCriterion("ip", values, ConditionMode.NOT_IN, "ip", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andResultIsNull() {
		isnull("result");
		return this;
	}
	
	public UpmsLogCriteria andResultIsNotNull() {
		notNull("result");
		return this;
	}
	
	public UpmsLogCriteria andResultIsEmpty() {
		empty("result");
		return this;
	}

	public UpmsLogCriteria andResultIsNotEmpty() {
		notEmpty("result");
		return this;
	}
        public UpmsLogCriteria andResultLike(java.lang.String value) {
    	   addCriterion("result", value, ConditionMode.FUZZY, "result", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andResultNotLike(java.lang.String value) {
          addCriterion("result", value, ConditionMode.NOT_FUZZY, "result", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andResultEqualTo(java.lang.String value) {
          addCriterion("result", value, ConditionMode.EQUAL, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultNotEqualTo(java.lang.String value) {
          addCriterion("result", value, ConditionMode.NOT_EQUAL, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultGreaterThan(java.lang.String value) {
          addCriterion("result", value, ConditionMode.GREATER_THEN, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("result", value, ConditionMode.GREATER_EQUAL, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultLessThan(java.lang.String value) {
          addCriterion("result", value, ConditionMode.LESS_THEN, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultLessThanOrEqualTo(java.lang.String value) {
          addCriterion("result", value, ConditionMode.LESS_EQUAL, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("result", value1, value2, ConditionMode.BETWEEN, "result", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andResultNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("result", value1, value2, ConditionMode.NOT_BETWEEN, "result", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andResultIn(List<java.lang.String> values) {
          addCriterion("result", values, ConditionMode.IN, "result", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andResultNotIn(List<java.lang.String> values) {
          addCriterion("result", values, ConditionMode.NOT_IN, "result", "java.lang.String", "String");
          return this;
      }
	public UpmsLogCriteria andPermissionsIsNull() {
		isnull("permissions");
		return this;
	}
	
	public UpmsLogCriteria andPermissionsIsNotNull() {
		notNull("permissions");
		return this;
	}
	
	public UpmsLogCriteria andPermissionsIsEmpty() {
		empty("permissions");
		return this;
	}

	public UpmsLogCriteria andPermissionsIsNotEmpty() {
		notEmpty("permissions");
		return this;
	}
        public UpmsLogCriteria andPermissionsLike(java.lang.String value) {
    	   addCriterion("permissions", value, ConditionMode.FUZZY, "permissions", "java.lang.String", "String");
    	   return this;
      }

      public UpmsLogCriteria andPermissionsNotLike(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.NOT_FUZZY, "permissions", "java.lang.String", "String");
          return this;
      }
      public UpmsLogCriteria andPermissionsEqualTo(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.EQUAL, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsNotEqualTo(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.NOT_EQUAL, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsGreaterThan(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.GREATER_THEN, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.GREATER_EQUAL, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsLessThan(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.LESS_THEN, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsLessThanOrEqualTo(java.lang.String value) {
          addCriterion("permissions", value, ConditionMode.LESS_EQUAL, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("permissions", value1, value2, ConditionMode.BETWEEN, "permissions", "java.lang.String", "String");
    	  return this;
      }

      public UpmsLogCriteria andPermissionsNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("permissions", value1, value2, ConditionMode.NOT_BETWEEN, "permissions", "java.lang.String", "String");
          return this;
      }
        
      public UpmsLogCriteria andPermissionsIn(List<java.lang.String> values) {
          addCriterion("permissions", values, ConditionMode.IN, "permissions", "java.lang.String", "String");
          return this;
      }

      public UpmsLogCriteria andPermissionsNotIn(List<java.lang.String> values) {
          addCriterion("permissions", values, ConditionMode.NOT_IN, "permissions", "java.lang.String", "String");
          return this;
      }
}