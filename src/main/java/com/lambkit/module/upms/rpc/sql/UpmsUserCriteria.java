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
public class UpmsUserCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsUserCriteria create() {
		return new UpmsUserCriteria();
	}
	
	public static UpmsUserCriteria create(Column column) {
		UpmsUserCriteria that = new UpmsUserCriteria();
		that.add(column);
        return that;
    }

    public static UpmsUserCriteria create(String name, Object value) {
        return (UpmsUserCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_user", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsUserCriteria eq(String name, Object value) {
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
    public UpmsUserCriteria ne(String name, Object value) {
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

    public UpmsUserCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsUserCriteria notLike(String name, Object value) {
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
    public UpmsUserCriteria gt(String name, Object value) {
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
    public UpmsUserCriteria ge(String name, Object value) {
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
    public UpmsUserCriteria lt(String name, Object value) {
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
    public UpmsUserCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsUserCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsUserCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsUserCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsUserCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsUserCriteria add(Column column) {
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
		 
	public UpmsUserCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsUserCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsUserCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsUserCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsUserCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsUserCriteria andUsernameIsNull() {
		isnull("username");
		return this;
	}
	
	public UpmsUserCriteria andUsernameIsNotNull() {
		notNull("username");
		return this;
	}
	
	public UpmsUserCriteria andUsernameIsEmpty() {
		empty("username");
		return this;
	}

	public UpmsUserCriteria andUsernameIsNotEmpty() {
		notEmpty("username");
		return this;
	}
        public UpmsUserCriteria andUsernameLike(java.lang.String value) {
    	   addCriterion("username", value, ConditionMode.FUZZY, "username", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsUserCriteria andUsernameNotLike(java.lang.String value) {
          addCriterion("username", value, ConditionMode.NOT_FUZZY, "username", "java.lang.String", "Float");
          return this;
      }
      public UpmsUserCriteria andUsernameEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameNotEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.NOT_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameGreaterThan(java.lang.String value) {
          addCriterion("username", value, ConditionMode.GREATER_THEN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.GREATER_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameLessThan(java.lang.String value) {
          addCriterion("username", value, ConditionMode.LESS_THEN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("username", value, ConditionMode.LESS_EQUAL, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("username", value1, value2, ConditionMode.BETWEEN, "username", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andUsernameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("username", value1, value2, ConditionMode.NOT_BETWEEN, "username", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andUsernameIn(List<java.lang.String> values) {
          addCriterion("username", values, ConditionMode.IN, "username", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andUsernameNotIn(List<java.lang.String> values) {
          addCriterion("username", values, ConditionMode.NOT_IN, "username", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andPasswordIsNull() {
		isnull("password");
		return this;
	}
	
	public UpmsUserCriteria andPasswordIsNotNull() {
		notNull("password");
		return this;
	}
	
	public UpmsUserCriteria andPasswordIsEmpty() {
		empty("password");
		return this;
	}

	public UpmsUserCriteria andPasswordIsNotEmpty() {
		notEmpty("password");
		return this;
	}
        public UpmsUserCriteria andPasswordLike(java.lang.String value) {
    	   addCriterion("password", value, ConditionMode.FUZZY, "password", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andPasswordNotLike(java.lang.String value) {
          addCriterion("password", value, ConditionMode.NOT_FUZZY, "password", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andPasswordEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordNotEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.NOT_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordGreaterThan(java.lang.String value) {
          addCriterion("password", value, ConditionMode.GREATER_THEN, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.GREATER_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordLessThan(java.lang.String value) {
          addCriterion("password", value, ConditionMode.LESS_THEN, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordLessThanOrEqualTo(java.lang.String value) {
          addCriterion("password", value, ConditionMode.LESS_EQUAL, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("password", value1, value2, ConditionMode.BETWEEN, "password", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andPasswordNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("password", value1, value2, ConditionMode.NOT_BETWEEN, "password", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andPasswordIn(List<java.lang.String> values) {
          addCriterion("password", values, ConditionMode.IN, "password", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPasswordNotIn(List<java.lang.String> values) {
          addCriterion("password", values, ConditionMode.NOT_IN, "password", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andSaltIsNull() {
		isnull("salt");
		return this;
	}
	
	public UpmsUserCriteria andSaltIsNotNull() {
		notNull("salt");
		return this;
	}
	
	public UpmsUserCriteria andSaltIsEmpty() {
		empty("salt");
		return this;
	}

	public UpmsUserCriteria andSaltIsNotEmpty() {
		notEmpty("salt");
		return this;
	}
        public UpmsUserCriteria andSaltLike(java.lang.String value) {
    	   addCriterion("salt", value, ConditionMode.FUZZY, "salt", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andSaltNotLike(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.NOT_FUZZY, "salt", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andSaltEqualTo(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.EQUAL, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltNotEqualTo(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.NOT_EQUAL, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltGreaterThan(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.GREATER_THEN, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.GREATER_EQUAL, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltLessThan(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.LESS_THEN, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltLessThanOrEqualTo(java.lang.String value) {
          addCriterion("salt", value, ConditionMode.LESS_EQUAL, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("salt", value1, value2, ConditionMode.BETWEEN, "salt", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andSaltNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("salt", value1, value2, ConditionMode.NOT_BETWEEN, "salt", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andSaltIn(List<java.lang.String> values) {
          addCriterion("salt", values, ConditionMode.IN, "salt", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andSaltNotIn(List<java.lang.String> values) {
          addCriterion("salt", values, ConditionMode.NOT_IN, "salt", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andRealnameIsNull() {
		isnull("realname");
		return this;
	}
	
	public UpmsUserCriteria andRealnameIsNotNull() {
		notNull("realname");
		return this;
	}
	
	public UpmsUserCriteria andRealnameIsEmpty() {
		empty("realname");
		return this;
	}

	public UpmsUserCriteria andRealnameIsNotEmpty() {
		notEmpty("realname");
		return this;
	}
        public UpmsUserCriteria andRealnameLike(java.lang.String value) {
    	   addCriterion("realname", value, ConditionMode.FUZZY, "realname", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andRealnameNotLike(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.NOT_FUZZY, "realname", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andRealnameEqualTo(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.EQUAL, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameNotEqualTo(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.NOT_EQUAL, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameGreaterThan(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.GREATER_THEN, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.GREATER_EQUAL, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameLessThan(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.LESS_THEN, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("realname", value, ConditionMode.LESS_EQUAL, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("realname", value1, value2, ConditionMode.BETWEEN, "realname", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andRealnameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("realname", value1, value2, ConditionMode.NOT_BETWEEN, "realname", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andRealnameIn(List<java.lang.String> values) {
          addCriterion("realname", values, ConditionMode.IN, "realname", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andRealnameNotIn(List<java.lang.String> values) {
          addCriterion("realname", values, ConditionMode.NOT_IN, "realname", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andAvatarIsNull() {
		isnull("avatar");
		return this;
	}
	
	public UpmsUserCriteria andAvatarIsNotNull() {
		notNull("avatar");
		return this;
	}
	
	public UpmsUserCriteria andAvatarIsEmpty() {
		empty("avatar");
		return this;
	}

	public UpmsUserCriteria andAvatarIsNotEmpty() {
		notEmpty("avatar");
		return this;
	}
        public UpmsUserCriteria andAvatarLike(java.lang.String value) {
    	   addCriterion("avatar", value, ConditionMode.FUZZY, "avatar", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andAvatarNotLike(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.NOT_FUZZY, "avatar", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andAvatarEqualTo(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.EQUAL, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarNotEqualTo(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.NOT_EQUAL, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarGreaterThan(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.GREATER_THEN, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.GREATER_EQUAL, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarLessThan(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.LESS_THEN, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarLessThanOrEqualTo(java.lang.String value) {
          addCriterion("avatar", value, ConditionMode.LESS_EQUAL, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("avatar", value1, value2, ConditionMode.BETWEEN, "avatar", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andAvatarNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("avatar", value1, value2, ConditionMode.NOT_BETWEEN, "avatar", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andAvatarIn(List<java.lang.String> values) {
          addCriterion("avatar", values, ConditionMode.IN, "avatar", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andAvatarNotIn(List<java.lang.String> values) {
          addCriterion("avatar", values, ConditionMode.NOT_IN, "avatar", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andPhoneIsNull() {
		isnull("phone");
		return this;
	}
	
	public UpmsUserCriteria andPhoneIsNotNull() {
		notNull("phone");
		return this;
	}
	
	public UpmsUserCriteria andPhoneIsEmpty() {
		empty("phone");
		return this;
	}

	public UpmsUserCriteria andPhoneIsNotEmpty() {
		notEmpty("phone");
		return this;
	}
        public UpmsUserCriteria andPhoneLike(java.lang.String value) {
    	   addCriterion("phone", value, ConditionMode.FUZZY, "phone", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andPhoneNotLike(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.NOT_FUZZY, "phone", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andPhoneEqualTo(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.EQUAL, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneNotEqualTo(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.NOT_EQUAL, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneGreaterThan(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.GREATER_THEN, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.GREATER_EQUAL, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneLessThan(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.LESS_THEN, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneLessThanOrEqualTo(java.lang.String value) {
          addCriterion("phone", value, ConditionMode.LESS_EQUAL, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("phone", value1, value2, ConditionMode.BETWEEN, "phone", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andPhoneNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("phone", value1, value2, ConditionMode.NOT_BETWEEN, "phone", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andPhoneIn(List<java.lang.String> values) {
          addCriterion("phone", values, ConditionMode.IN, "phone", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andPhoneNotIn(List<java.lang.String> values) {
          addCriterion("phone", values, ConditionMode.NOT_IN, "phone", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andEmailIsNull() {
		isnull("email");
		return this;
	}
	
	public UpmsUserCriteria andEmailIsNotNull() {
		notNull("email");
		return this;
	}
	
	public UpmsUserCriteria andEmailIsEmpty() {
		empty("email");
		return this;
	}

	public UpmsUserCriteria andEmailIsNotEmpty() {
		notEmpty("email");
		return this;
	}
        public UpmsUserCriteria andEmailLike(java.lang.String value) {
    	   addCriterion("email", value, ConditionMode.FUZZY, "email", "java.lang.String", "String");
    	   return this;
      }

      public UpmsUserCriteria andEmailNotLike(java.lang.String value) {
          addCriterion("email", value, ConditionMode.NOT_FUZZY, "email", "java.lang.String", "String");
          return this;
      }
      public UpmsUserCriteria andEmailEqualTo(java.lang.String value) {
          addCriterion("email", value, ConditionMode.EQUAL, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailNotEqualTo(java.lang.String value) {
          addCriterion("email", value, ConditionMode.NOT_EQUAL, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailGreaterThan(java.lang.String value) {
          addCriterion("email", value, ConditionMode.GREATER_THEN, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("email", value, ConditionMode.GREATER_EQUAL, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailLessThan(java.lang.String value) {
          addCriterion("email", value, ConditionMode.LESS_THEN, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailLessThanOrEqualTo(java.lang.String value) {
          addCriterion("email", value, ConditionMode.LESS_EQUAL, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("email", value1, value2, ConditionMode.BETWEEN, "email", "java.lang.String", "String");
    	  return this;
      }

      public UpmsUserCriteria andEmailNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("email", value1, value2, ConditionMode.NOT_BETWEEN, "email", "java.lang.String", "String");
          return this;
      }
        
      public UpmsUserCriteria andEmailIn(List<java.lang.String> values) {
          addCriterion("email", values, ConditionMode.IN, "email", "java.lang.String", "String");
          return this;
      }

      public UpmsUserCriteria andEmailNotIn(List<java.lang.String> values) {
          addCriterion("email", values, ConditionMode.NOT_IN, "email", "java.lang.String", "String");
          return this;
      }
	public UpmsUserCriteria andSexIsNull() {
		isnull("sex");
		return this;
	}
	
	public UpmsUserCriteria andSexIsNotNull() {
		notNull("sex");
		return this;
	}
	
	public UpmsUserCriteria andSexIsEmpty() {
		empty("sex");
		return this;
	}

	public UpmsUserCriteria andSexIsNotEmpty() {
		notEmpty("sex");
		return this;
	}
       public UpmsUserCriteria andSexEqualTo(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.EQUAL, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexNotEqualTo(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.NOT_EQUAL, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexGreaterThan(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.GREATER_THEN, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.GREATER_EQUAL, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexLessThan(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.LESS_THEN, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("sex", value, ConditionMode.LESS_EQUAL, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("sex", value1, value2, ConditionMode.BETWEEN, "sex", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsUserCriteria andSexNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("sex", value1, value2, ConditionMode.NOT_BETWEEN, "sex", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsUserCriteria andSexIn(List<java.lang.Integer> values) {
          addCriterion("sex", values, ConditionMode.IN, "sex", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andSexNotIn(List<java.lang.Integer> values) {
          addCriterion("sex", values, ConditionMode.NOT_IN, "sex", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsUserCriteria andLockedIsNull() {
		isnull("locked");
		return this;
	}
	
	public UpmsUserCriteria andLockedIsNotNull() {
		notNull("locked");
		return this;
	}
	
	public UpmsUserCriteria andLockedIsEmpty() {
		empty("locked");
		return this;
	}

	public UpmsUserCriteria andLockedIsNotEmpty() {
		notEmpty("locked");
		return this;
	}
       public UpmsUserCriteria andLockedEqualTo(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.EQUAL, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedNotEqualTo(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.NOT_EQUAL, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedGreaterThan(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.GREATER_THEN, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.GREATER_EQUAL, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedLessThan(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.LESS_THEN, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("locked", value, ConditionMode.LESS_EQUAL, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("locked", value1, value2, ConditionMode.BETWEEN, "locked", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsUserCriteria andLockedNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("locked", value1, value2, ConditionMode.NOT_BETWEEN, "locked", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsUserCriteria andLockedIn(List<java.lang.Integer> values) {
          addCriterion("locked", values, ConditionMode.IN, "locked", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsUserCriteria andLockedNotIn(List<java.lang.Integer> values) {
          addCriterion("locked", values, ConditionMode.NOT_IN, "locked", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsUserCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsUserCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsUserCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsUserCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsUserCriteria andCtimeEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeNotEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeGreaterThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeLessThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsUserCriteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsUserCriteria andCtimeIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsUserCriteria andCtimeNotIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.lang.Long", "Float");
          return this;
      }
}