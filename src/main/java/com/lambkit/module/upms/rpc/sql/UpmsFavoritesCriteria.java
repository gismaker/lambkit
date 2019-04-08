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
public class UpmsFavoritesCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsFavoritesCriteria create() {
		return new UpmsFavoritesCriteria();
	}
	
	public static UpmsFavoritesCriteria create(Column column) {
		UpmsFavoritesCriteria that = new UpmsFavoritesCriteria();
		that.add(column);
        return that;
    }

    public static UpmsFavoritesCriteria create(String name, Object value) {
        return (UpmsFavoritesCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_favorites", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsFavoritesCriteria eq(String name, Object value) {
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
    public UpmsFavoritesCriteria ne(String name, Object value) {
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

    public UpmsFavoritesCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsFavoritesCriteria notLike(String name, Object value) {
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
    public UpmsFavoritesCriteria gt(String name, Object value) {
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
    public UpmsFavoritesCriteria ge(String name, Object value) {
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
    public UpmsFavoritesCriteria lt(String name, Object value) {
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
    public UpmsFavoritesCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsFavoritesCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsFavoritesCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsFavoritesCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsFavoritesCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsFavoritesCriteria add(Column column) {
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
		 
	public UpmsFavoritesCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public UpmsFavoritesCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public UpmsFavoritesCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public UpmsFavoritesCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public UpmsFavoritesCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsFavoritesCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsFavoritesCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public UpmsFavoritesCriteria andUserIdIsNull() {
		isnull("user_id");
		return this;
	}
	
	public UpmsFavoritesCriteria andUserIdIsNotNull() {
		notNull("user_id");
		return this;
	}
	
	public UpmsFavoritesCriteria andUserIdIsEmpty() {
		empty("user_id");
		return this;
	}

	public UpmsFavoritesCriteria andUserIdIsNotEmpty() {
		notEmpty("user_id");
		return this;
	}
       public UpmsFavoritesCriteria andUserIdEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdNotEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.NOT_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdGreaterThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.GREATER_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdLessThan(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_THEN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("user_id", value, ConditionMode.LESS_EQUAL, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("user_id", value1, value2, ConditionMode.BETWEEN, "userId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsFavoritesCriteria andUserIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("user_id", value1, value2, ConditionMode.NOT_BETWEEN, "userId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsFavoritesCriteria andUserIdIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.IN, "userId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsFavoritesCriteria andUserIdNotIn(List<java.lang.Long> values) {
          addCriterion("user_id", values, ConditionMode.NOT_IN, "userId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsFavoritesCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public UpmsFavoritesCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public UpmsFavoritesCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public UpmsFavoritesCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public UpmsFavoritesCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsFavoritesCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "Float");
          return this;
      }
      public UpmsFavoritesCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public UpmsFavoritesCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public UpmsFavoritesCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public UpmsFavoritesCriteria andUrlIsNull() {
		isnull("url");
		return this;
	}
	
	public UpmsFavoritesCriteria andUrlIsNotNull() {
		notNull("url");
		return this;
	}
	
	public UpmsFavoritesCriteria andUrlIsEmpty() {
		empty("url");
		return this;
	}

	public UpmsFavoritesCriteria andUrlIsNotEmpty() {
		notEmpty("url");
		return this;
	}
        public UpmsFavoritesCriteria andUrlLike(java.lang.String value) {
    	   addCriterion("url", value, ConditionMode.FUZZY, "url", "java.lang.String", "String");
    	   return this;
      }

      public UpmsFavoritesCriteria andUrlNotLike(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_FUZZY, "url", "java.lang.String", "String");
          return this;
      }
      public UpmsFavoritesCriteria andUrlEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlNotEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.NOT_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlGreaterThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.GREATER_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlLessThan(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_THEN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlLessThanOrEqualTo(java.lang.String value) {
          addCriterion("url", value, ConditionMode.LESS_EQUAL, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("url", value1, value2, ConditionMode.BETWEEN, "url", "java.lang.String", "String");
    	  return this;
      }

      public UpmsFavoritesCriteria andUrlNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("url", value1, value2, ConditionMode.NOT_BETWEEN, "url", "java.lang.String", "String");
          return this;
      }
        
      public UpmsFavoritesCriteria andUrlIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.IN, "url", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andUrlNotIn(List<java.lang.String> values) {
          addCriterion("url", values, ConditionMode.NOT_IN, "url", "java.lang.String", "String");
          return this;
      }
	public UpmsFavoritesCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsFavoritesCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsFavoritesCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsFavoritesCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsFavoritesCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public UpmsFavoritesCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public UpmsFavoritesCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsFavoritesCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsFavoritesCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsFavoritesCriteria andTagsIsNull() {
		isnull("tags");
		return this;
	}
	
	public UpmsFavoritesCriteria andTagsIsNotNull() {
		notNull("tags");
		return this;
	}
	
	public UpmsFavoritesCriteria andTagsIsEmpty() {
		empty("tags");
		return this;
	}

	public UpmsFavoritesCriteria andTagsIsNotEmpty() {
		notEmpty("tags");
		return this;
	}
        public UpmsFavoritesCriteria andTagsLike(java.lang.String value) {
    	   addCriterion("tags", value, ConditionMode.FUZZY, "tags", "java.lang.String", "String");
    	   return this;
      }

      public UpmsFavoritesCriteria andTagsNotLike(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.NOT_FUZZY, "tags", "java.lang.String", "String");
          return this;
      }
      public UpmsFavoritesCriteria andTagsEqualTo(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.EQUAL, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsNotEqualTo(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.NOT_EQUAL, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsGreaterThan(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.GREATER_THEN, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.GREATER_EQUAL, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsLessThan(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.LESS_THEN, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsLessThanOrEqualTo(java.lang.String value) {
          addCriterion("tags", value, ConditionMode.LESS_EQUAL, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("tags", value1, value2, ConditionMode.BETWEEN, "tags", "java.lang.String", "String");
    	  return this;
      }

      public UpmsFavoritesCriteria andTagsNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("tags", value1, value2, ConditionMode.NOT_BETWEEN, "tags", "java.lang.String", "String");
          return this;
      }
        
      public UpmsFavoritesCriteria andTagsIn(List<java.lang.String> values) {
          addCriterion("tags", values, ConditionMode.IN, "tags", "java.lang.String", "String");
          return this;
      }

      public UpmsFavoritesCriteria andTagsNotIn(List<java.lang.String> values) {
          addCriterion("tags", values, ConditionMode.NOT_IN, "tags", "java.lang.String", "String");
          return this;
      }
	public UpmsFavoritesCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsFavoritesCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsFavoritesCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsFavoritesCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsFavoritesCriteria andCtimeEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeNotEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeGreaterThan(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeGreaterThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeLessThan(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeLessThanOrEqualTo(java.math.BigInteger value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.math.BigInteger", "String");
    	  return this;
      }

      public UpmsFavoritesCriteria andCtimeNotBetween(java.math.BigInteger value1, java.math.BigInteger value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.math.BigInteger", "String");
          return this;
      }
        
      public UpmsFavoritesCriteria andCtimeIn(List<java.math.BigInteger> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.math.BigInteger", "String");
          return this;
      }

      public UpmsFavoritesCriteria andCtimeNotIn(List<java.math.BigInteger> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.math.BigInteger", "String");
          return this;
      }
}