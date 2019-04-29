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
public class UpmsSystemCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static UpmsSystemCriteria create() {
		return new UpmsSystemCriteria();
	}
	
	public static UpmsSystemCriteria create(Column column) {
		UpmsSystemCriteria that = new UpmsSystemCriteria();
		that.add(column);
        return that;
    }

    public static UpmsSystemCriteria create(String name, Object value) {
        return (UpmsSystemCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("upms_system", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public UpmsSystemCriteria eq(String name, Object value) {
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
    public UpmsSystemCriteria ne(String name, Object value) {
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

    public UpmsSystemCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public UpmsSystemCriteria notLike(String name, Object value) {
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
    public UpmsSystemCriteria gt(String name, Object value) {
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
    public UpmsSystemCriteria ge(String name, Object value) {
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
    public UpmsSystemCriteria lt(String name, Object value) {
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
    public UpmsSystemCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public UpmsSystemCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public UpmsSystemCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public UpmsSystemCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public UpmsSystemCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public UpmsSystemCriteria add(Column column) {
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
		 
	public UpmsSystemCriteria andSystemIdIsNull() {
		isnull("system_id");
		return this;
	}
	
	public UpmsSystemCriteria andSystemIdIsNotNull() {
		notNull("system_id");
		return this;
	}
	
	public UpmsSystemCriteria andSystemIdIsEmpty() {
		empty("system_id");
		return this;
	}

	public UpmsSystemCriteria andSystemIdIsNotEmpty() {
		notEmpty("system_id");
		return this;
	}
       public UpmsSystemCriteria andSystemIdEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdNotEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.NOT_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdGreaterThan(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.GREATER_THEN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.GREATER_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdLessThan(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.LESS_THEN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("system_id", value, ConditionMode.LESS_EQUAL, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("system_id", value1, value2, ConditionMode.BETWEEN, "systemId", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsSystemCriteria andSystemIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("system_id", value1, value2, ConditionMode.NOT_BETWEEN, "systemId", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsSystemCriteria andSystemIdIn(List<java.lang.Long> values) {
          addCriterion("system_id", values, ConditionMode.IN, "systemId", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andSystemIdNotIn(List<java.lang.Long> values) {
          addCriterion("system_id", values, ConditionMode.NOT_IN, "systemId", "java.lang.Long", "Float");
          return this;
      }
	public UpmsSystemCriteria andIconIsNull() {
		isnull("icon");
		return this;
	}
	
	public UpmsSystemCriteria andIconIsNotNull() {
		notNull("icon");
		return this;
	}
	
	public UpmsSystemCriteria andIconIsEmpty() {
		empty("icon");
		return this;
	}

	public UpmsSystemCriteria andIconIsNotEmpty() {
		notEmpty("icon");
		return this;
	}
        public UpmsSystemCriteria andIconLike(java.lang.String value) {
    	   addCriterion("icon", value, ConditionMode.FUZZY, "icon", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsSystemCriteria andIconNotLike(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.NOT_FUZZY, "icon", "java.lang.String", "Float");
          return this;
      }
      public UpmsSystemCriteria andIconEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconNotEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.NOT_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconGreaterThan(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.GREATER_THEN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.GREATER_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconLessThan(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.LESS_THEN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconLessThanOrEqualTo(java.lang.String value) {
          addCriterion("icon", value, ConditionMode.LESS_EQUAL, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("icon", value1, value2, ConditionMode.BETWEEN, "icon", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andIconNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("icon", value1, value2, ConditionMode.NOT_BETWEEN, "icon", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andIconIn(List<java.lang.String> values) {
          addCriterion("icon", values, ConditionMode.IN, "icon", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andIconNotIn(List<java.lang.String> values) {
          addCriterion("icon", values, ConditionMode.NOT_IN, "icon", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andBannerIsNull() {
		isnull("banner");
		return this;
	}
	
	public UpmsSystemCriteria andBannerIsNotNull() {
		notNull("banner");
		return this;
	}
	
	public UpmsSystemCriteria andBannerIsEmpty() {
		empty("banner");
		return this;
	}

	public UpmsSystemCriteria andBannerIsNotEmpty() {
		notEmpty("banner");
		return this;
	}
        public UpmsSystemCriteria andBannerLike(java.lang.String value) {
    	   addCriterion("banner", value, ConditionMode.FUZZY, "banner", "java.lang.String", "String");
    	   return this;
      }

      public UpmsSystemCriteria andBannerNotLike(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.NOT_FUZZY, "banner", "java.lang.String", "String");
          return this;
      }
      public UpmsSystemCriteria andBannerEqualTo(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.EQUAL, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerNotEqualTo(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.NOT_EQUAL, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerGreaterThan(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.GREATER_THEN, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.GREATER_EQUAL, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerLessThan(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.LESS_THEN, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerLessThanOrEqualTo(java.lang.String value) {
          addCriterion("banner", value, ConditionMode.LESS_EQUAL, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("banner", value1, value2, ConditionMode.BETWEEN, "banner", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andBannerNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("banner", value1, value2, ConditionMode.NOT_BETWEEN, "banner", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andBannerIn(List<java.lang.String> values) {
          addCriterion("banner", values, ConditionMode.IN, "banner", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBannerNotIn(List<java.lang.String> values) {
          addCriterion("banner", values, ConditionMode.NOT_IN, "banner", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andThemeIsNull() {
		isnull("theme");
		return this;
	}
	
	public UpmsSystemCriteria andThemeIsNotNull() {
		notNull("theme");
		return this;
	}
	
	public UpmsSystemCriteria andThemeIsEmpty() {
		empty("theme");
		return this;
	}

	public UpmsSystemCriteria andThemeIsNotEmpty() {
		notEmpty("theme");
		return this;
	}
        public UpmsSystemCriteria andThemeLike(java.lang.String value) {
    	   addCriterion("theme", value, ConditionMode.FUZZY, "theme", "java.lang.String", "String");
    	   return this;
      }

      public UpmsSystemCriteria andThemeNotLike(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.NOT_FUZZY, "theme", "java.lang.String", "String");
          return this;
      }
      public UpmsSystemCriteria andThemeEqualTo(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.EQUAL, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeNotEqualTo(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.NOT_EQUAL, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeGreaterThan(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.GREATER_THEN, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.GREATER_EQUAL, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeLessThan(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.LESS_THEN, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("theme", value, ConditionMode.LESS_EQUAL, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("theme", value1, value2, ConditionMode.BETWEEN, "theme", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andThemeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("theme", value1, value2, ConditionMode.NOT_BETWEEN, "theme", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andThemeIn(List<java.lang.String> values) {
          addCriterion("theme", values, ConditionMode.IN, "theme", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andThemeNotIn(List<java.lang.String> values) {
          addCriterion("theme", values, ConditionMode.NOT_IN, "theme", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andBasepathIsNull() {
		isnull("basepath");
		return this;
	}
	
	public UpmsSystemCriteria andBasepathIsNotNull() {
		notNull("basepath");
		return this;
	}
	
	public UpmsSystemCriteria andBasepathIsEmpty() {
		empty("basepath");
		return this;
	}

	public UpmsSystemCriteria andBasepathIsNotEmpty() {
		notEmpty("basepath");
		return this;
	}
        public UpmsSystemCriteria andBasepathLike(java.lang.String value) {
    	   addCriterion("basepath", value, ConditionMode.FUZZY, "basepath", "java.lang.String", "String");
    	   return this;
      }

      public UpmsSystemCriteria andBasepathNotLike(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.NOT_FUZZY, "basepath", "java.lang.String", "String");
          return this;
      }
      public UpmsSystemCriteria andBasepathEqualTo(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.EQUAL, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathNotEqualTo(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.NOT_EQUAL, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathGreaterThan(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.GREATER_THEN, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.GREATER_EQUAL, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathLessThan(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.LESS_THEN, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathLessThanOrEqualTo(java.lang.String value) {
          addCriterion("basepath", value, ConditionMode.LESS_EQUAL, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("basepath", value1, value2, ConditionMode.BETWEEN, "basepath", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andBasepathNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("basepath", value1, value2, ConditionMode.NOT_BETWEEN, "basepath", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andBasepathIn(List<java.lang.String> values) {
          addCriterion("basepath", values, ConditionMode.IN, "basepath", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andBasepathNotIn(List<java.lang.String> values) {
          addCriterion("basepath", values, ConditionMode.NOT_IN, "basepath", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andStatusIsNull() {
		isnull("status");
		return this;
	}
	
	public UpmsSystemCriteria andStatusIsNotNull() {
		notNull("status");
		return this;
	}
	
	public UpmsSystemCriteria andStatusIsEmpty() {
		empty("status");
		return this;
	}

	public UpmsSystemCriteria andStatusIsNotEmpty() {
		notEmpty("status");
		return this;
	}
       public UpmsSystemCriteria andStatusEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusNotEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.NOT_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusGreaterThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusGreaterThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.GREATER_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusLessThan(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_THEN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusLessThanOrEqualTo(java.lang.Integer value) {
          addCriterion("status", value, ConditionMode.LESS_EQUAL, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusBetween(java.lang.Integer value1, java.lang.Integer value2) {
    	  addCriterion("status", value1, value2, ConditionMode.BETWEEN, "status", "java.lang.Integer", "Float");
    	  return this;
      }

      public UpmsSystemCriteria andStatusNotBetween(java.lang.Integer value1, java.lang.Integer value2) {
          addCriterion("status", value1, value2, ConditionMode.NOT_BETWEEN, "status", "java.lang.Integer", "Float");
          return this;
      }
        
      public UpmsSystemCriteria andStatusIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.IN, "status", "java.lang.Integer", "Float");
          return this;
      }

      public UpmsSystemCriteria andStatusNotIn(List<java.lang.Integer> values) {
          addCriterion("status", values, ConditionMode.NOT_IN, "status", "java.lang.Integer", "Float");
          return this;
      }
	public UpmsSystemCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public UpmsSystemCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public UpmsSystemCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public UpmsSystemCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
        public UpmsSystemCriteria andNameLike(java.lang.String value) {
    	   addCriterion("name", value, ConditionMode.FUZZY, "name", "java.lang.String", "Float");
    	   return this;
      }

      public UpmsSystemCriteria andNameNotLike(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_FUZZY, "name", "java.lang.String", "Float");
          return this;
      }
      public UpmsSystemCriteria andNameEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameNotEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameGreaterThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameLessThan(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andNameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andNameIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andNameNotIn(List<java.lang.String> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andTitleIsNull() {
		isnull("title");
		return this;
	}
	
	public UpmsSystemCriteria andTitleIsNotNull() {
		notNull("title");
		return this;
	}
	
	public UpmsSystemCriteria andTitleIsEmpty() {
		empty("title");
		return this;
	}

	public UpmsSystemCriteria andTitleIsNotEmpty() {
		notEmpty("title");
		return this;
	}
        public UpmsSystemCriteria andTitleLike(java.lang.String value) {
    	   addCriterion("title", value, ConditionMode.FUZZY, "title", "java.lang.String", "String");
    	   return this;
      }

      public UpmsSystemCriteria andTitleNotLike(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_FUZZY, "title", "java.lang.String", "String");
          return this;
      }
      public UpmsSystemCriteria andTitleEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleNotEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.NOT_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleGreaterThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.GREATER_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleLessThan(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_THEN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleLessThanOrEqualTo(java.lang.String value) {
          addCriterion("title", value, ConditionMode.LESS_EQUAL, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("title", value1, value2, ConditionMode.BETWEEN, "title", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andTitleNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("title", value1, value2, ConditionMode.NOT_BETWEEN, "title", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andTitleIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.IN, "title", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andTitleNotIn(List<java.lang.String> values) {
          addCriterion("title", values, ConditionMode.NOT_IN, "title", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andDescriptionIsNull() {
		isnull("description");
		return this;
	}
	
	public UpmsSystemCriteria andDescriptionIsNotNull() {
		notNull("description");
		return this;
	}
	
	public UpmsSystemCriteria andDescriptionIsEmpty() {
		empty("description");
		return this;
	}

	public UpmsSystemCriteria andDescriptionIsNotEmpty() {
		notEmpty("description");
		return this;
	}
        public UpmsSystemCriteria andDescriptionLike(java.lang.String value) {
    	   addCriterion("description", value, ConditionMode.FUZZY, "description", "java.lang.String", "String");
    	   return this;
      }

      public UpmsSystemCriteria andDescriptionNotLike(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_FUZZY, "description", "java.lang.String", "String");
          return this;
      }
      public UpmsSystemCriteria andDescriptionEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionNotEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.NOT_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionGreaterThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.GREATER_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionLessThan(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_THEN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionLessThanOrEqualTo(java.lang.String value) {
          addCriterion("description", value, ConditionMode.LESS_EQUAL, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("description", value1, value2, ConditionMode.BETWEEN, "description", "java.lang.String", "String");
    	  return this;
      }

      public UpmsSystemCriteria andDescriptionNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("description", value1, value2, ConditionMode.NOT_BETWEEN, "description", "java.lang.String", "String");
          return this;
      }
        
      public UpmsSystemCriteria andDescriptionIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.IN, "description", "java.lang.String", "String");
          return this;
      }

      public UpmsSystemCriteria andDescriptionNotIn(List<java.lang.String> values) {
          addCriterion("description", values, ConditionMode.NOT_IN, "description", "java.lang.String", "String");
          return this;
      }
	public UpmsSystemCriteria andCtimeIsNull() {
		isnull("ctime");
		return this;
	}
	
	public UpmsSystemCriteria andCtimeIsNotNull() {
		notNull("ctime");
		return this;
	}
	
	public UpmsSystemCriteria andCtimeIsEmpty() {
		empty("ctime");
		return this;
	}

	public UpmsSystemCriteria andCtimeIsNotEmpty() {
		notEmpty("ctime");
		return this;
	}
       public UpmsSystemCriteria andCtimeEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeNotEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.NOT_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeGreaterThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.GREATER_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeLessThan(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_THEN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("ctime", value, ConditionMode.LESS_EQUAL, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("ctime", value1, value2, ConditionMode.BETWEEN, "ctime", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsSystemCriteria andCtimeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("ctime", value1, value2, ConditionMode.NOT_BETWEEN, "ctime", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsSystemCriteria andCtimeIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.IN, "ctime", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andCtimeNotIn(List<java.lang.Long> values) {
          addCriterion("ctime", values, ConditionMode.NOT_IN, "ctime", "java.lang.Long", "Float");
          return this;
      }
	public UpmsSystemCriteria andOrdersIsNull() {
		isnull("orders");
		return this;
	}
	
	public UpmsSystemCriteria andOrdersIsNotNull() {
		notNull("orders");
		return this;
	}
	
	public UpmsSystemCriteria andOrdersIsEmpty() {
		empty("orders");
		return this;
	}

	public UpmsSystemCriteria andOrdersIsNotEmpty() {
		notEmpty("orders");
		return this;
	}
       public UpmsSystemCriteria andOrdersEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersNotEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.NOT_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersGreaterThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.GREATER_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersLessThan(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_THEN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("orders", value, ConditionMode.LESS_EQUAL, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("orders", value1, value2, ConditionMode.BETWEEN, "orders", "java.lang.Long", "Float");
    	  return this;
      }

      public UpmsSystemCriteria andOrdersNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("orders", value1, value2, ConditionMode.NOT_BETWEEN, "orders", "java.lang.Long", "Float");
          return this;
      }
        
      public UpmsSystemCriteria andOrdersIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.IN, "orders", "java.lang.Long", "Float");
          return this;
      }

      public UpmsSystemCriteria andOrdersNotIn(List<java.lang.Long> values) {
          addCriterion("orders", values, ConditionMode.NOT_IN, "orders", "java.lang.Long", "Float");
          return this;
      }
}