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
package com.lambkit.module.meta.model.sql;

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
 * @date 2019-01-07
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldDimessionCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldDimessionCriteria create() {
		return new MetaFieldDimessionCriteria();
	}
	
	public static MetaFieldDimessionCriteria create(Column column) {
		MetaFieldDimessionCriteria that = new MetaFieldDimessionCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldDimessionCriteria create(String name, Object value) {
        return (MetaFieldDimessionCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_dimession", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldDimessionCriteria eq(String name, Object value) {
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
    public MetaFieldDimessionCriteria ne(String name, Object value) {
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

    public MetaFieldDimessionCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldDimessionCriteria notLike(String name, Object value) {
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
    public MetaFieldDimessionCriteria gt(String name, Object value) {
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
    public MetaFieldDimessionCriteria ge(String name, Object value) {
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
    public MetaFieldDimessionCriteria lt(String name, Object value) {
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
    public MetaFieldDimessionCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldDimessionCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldDimessionCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldDimessionCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldDimessionCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldDimessionCriteria add(Column column) {
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
		 
	public MetaFieldDimessionCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldDimessionCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldDimessionCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldDimessionCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldDimessionCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andTbidIsNull() {
		isnull("tbid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andTbidIsNotNull() {
		notNull("tbid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andTbidIsEmpty() {
		empty("tbid");
		return this;
	}

	public MetaFieldDimessionCriteria andTbidIsNotEmpty() {
		notEmpty("tbid");
		return this;
	}
       public MetaFieldDimessionCriteria andTbidEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidNotEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.NOT_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidGreaterThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidLessThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tbid", value1, value2, ConditionMode.BETWEEN, "tbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andTbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tbid", value1, value2, ConditionMode.NOT_BETWEEN, "tbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andTbidIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.IN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andTbidNotIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.NOT_IN, "tbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldDimessionCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldDimessionCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andRtbidIsNull() {
		isnull("rtbid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRtbidIsNotNull() {
		notNull("rtbid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRtbidIsEmpty() {
		empty("rtbid");
		return this;
	}

	public MetaFieldDimessionCriteria andRtbidIsNotEmpty() {
		notEmpty("rtbid");
		return this;
	}
       public MetaFieldDimessionCriteria andRtbidEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidNotEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.NOT_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidGreaterThan(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.GREATER_THEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.GREATER_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidLessThan(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.LESS_THEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.LESS_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("rtbid", value1, value2, ConditionMode.BETWEEN, "rtbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andRtbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("rtbid", value1, value2, ConditionMode.NOT_BETWEEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andRtbidIn(List<java.lang.Long> values) {
          addCriterion("rtbid", values, ConditionMode.IN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRtbidNotIn(List<java.lang.Long> values) {
          addCriterion("rtbid", values, ConditionMode.NOT_IN, "rtbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andRtypeIsNull() {
		isnull("rtype");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRtypeIsNotNull() {
		notNull("rtype");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRtypeIsEmpty() {
		empty("rtype");
		return this;
	}

	public MetaFieldDimessionCriteria andRtypeIsNotEmpty() {
		notEmpty("rtype");
		return this;
	}
        public MetaFieldDimessionCriteria andRtypeLike(java.lang.String value) {
    	   addCriterion("rtype", value, ConditionMode.FUZZY, "rtype", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldDimessionCriteria andRtypeNotLike(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.NOT_FUZZY, "rtype", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldDimessionCriteria andRtypeEqualTo(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.EQUAL, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeNotEqualTo(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.NOT_EQUAL, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeGreaterThan(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.GREATER_THEN, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.GREATER_EQUAL, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeLessThan(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.LESS_THEN, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("rtype", value, ConditionMode.LESS_EQUAL, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("rtype", value1, value2, ConditionMode.BETWEEN, "rtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldDimessionCriteria andRtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("rtype", value1, value2, ConditionMode.NOT_BETWEEN, "rtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldDimessionCriteria andRtypeIn(List<java.lang.String> values) {
          addCriterion("rtype", values, ConditionMode.IN, "rtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andRtypeNotIn(List<java.lang.String> values) {
          addCriterion("rtype", values, ConditionMode.NOT_IN, "rtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldDimessionCriteria andRfldidIsNull() {
		isnull("rfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRfldidIsNotNull() {
		notNull("rfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andRfldidIsEmpty() {
		empty("rfldid");
		return this;
	}

	public MetaFieldDimessionCriteria andRfldidIsNotEmpty() {
		notEmpty("rfldid");
		return this;
	}
       public MetaFieldDimessionCriteria andRfldidEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidNotEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.NOT_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidGreaterThan(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.GREATER_THEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.GREATER_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidLessThan(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.LESS_THEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.LESS_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("rfldid", value1, value2, ConditionMode.BETWEEN, "rfldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andRfldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("rfldid", value1, value2, ConditionMode.NOT_BETWEEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andRfldidIn(List<java.lang.Long> values) {
          addCriterion("rfldid", values, ConditionMode.IN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andRfldidNotIn(List<java.lang.Long> values) {
          addCriterion("rfldid", values, ConditionMode.NOT_IN, "rfldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andDtypeIsNull() {
		isnull("dtype");
		return this;
	}
	
	public MetaFieldDimessionCriteria andDtypeIsNotNull() {
		notNull("dtype");
		return this;
	}
	
	public MetaFieldDimessionCriteria andDtypeIsEmpty() {
		empty("dtype");
		return this;
	}

	public MetaFieldDimessionCriteria andDtypeIsNotEmpty() {
		notEmpty("dtype");
		return this;
	}
        public MetaFieldDimessionCriteria andDtypeLike(java.lang.String value) {
    	   addCriterion("dtype", value, ConditionMode.FUZZY, "dtype", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldDimessionCriteria andDtypeNotLike(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.NOT_FUZZY, "dtype", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldDimessionCriteria andDtypeEqualTo(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.EQUAL, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeNotEqualTo(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.NOT_EQUAL, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeGreaterThan(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.GREATER_THEN, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.GREATER_EQUAL, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeLessThan(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.LESS_THEN, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("dtype", value, ConditionMode.LESS_EQUAL, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("dtype", value1, value2, ConditionMode.BETWEEN, "dtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldDimessionCriteria andDtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("dtype", value1, value2, ConditionMode.NOT_BETWEEN, "dtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldDimessionCriteria andDtypeIn(List<java.lang.String> values) {
          addCriterion("dtype", values, ConditionMode.IN, "dtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andDtypeNotIn(List<java.lang.String> values) {
          addCriterion("dtype", values, ConditionMode.NOT_IN, "dtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldDimessionCriteria andLevelTypeIsNull() {
		isnull("level_type");
		return this;
	}
	
	public MetaFieldDimessionCriteria andLevelTypeIsNotNull() {
		notNull("level_type");
		return this;
	}
	
	public MetaFieldDimessionCriteria andLevelTypeIsEmpty() {
		empty("level_type");
		return this;
	}

	public MetaFieldDimessionCriteria andLevelTypeIsNotEmpty() {
		notEmpty("level_type");
		return this;
	}
        public MetaFieldDimessionCriteria andLevelTypeLike(java.lang.String value) {
    	   addCriterion("level_type", value, ConditionMode.FUZZY, "levelType", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeNotLike(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.NOT_FUZZY, "levelType", "java.lang.String", "String");
          return this;
      }
      public MetaFieldDimessionCriteria andLevelTypeEqualTo(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.EQUAL, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeNotEqualTo(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.NOT_EQUAL, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeGreaterThan(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.GREATER_THEN, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.GREATER_EQUAL, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeLessThan(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.LESS_THEN, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("level_type", value, ConditionMode.LESS_EQUAL, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("level_type", value1, value2, ConditionMode.BETWEEN, "levelType", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("level_type", value1, value2, ConditionMode.NOT_BETWEEN, "levelType", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldDimessionCriteria andLevelTypeIn(List<java.lang.String> values) {
          addCriterion("level_type", values, ConditionMode.IN, "levelType", "java.lang.String", "String");
          return this;
      }

      public MetaFieldDimessionCriteria andLevelTypeNotIn(List<java.lang.String> values) {
          addCriterion("level_type", values, ConditionMode.NOT_IN, "levelType", "java.lang.String", "String");
          return this;
      }
	public MetaFieldDimessionCriteria andParentfldidIsNull() {
		isnull("parentfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andParentfldidIsNotNull() {
		notNull("parentfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andParentfldidIsEmpty() {
		empty("parentfldid");
		return this;
	}

	public MetaFieldDimessionCriteria andParentfldidIsNotEmpty() {
		notEmpty("parentfldid");
		return this;
	}
       public MetaFieldDimessionCriteria andParentfldidEqualTo(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.EQUAL, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidNotEqualTo(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.NOT_EQUAL, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidGreaterThan(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.GREATER_THEN, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.GREATER_EQUAL, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidLessThan(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.LESS_THEN, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("parentfldid", value, ConditionMode.LESS_EQUAL, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("parentfldid", value1, value2, ConditionMode.BETWEEN, "parentfldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andParentfldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("parentfldid", value1, value2, ConditionMode.NOT_BETWEEN, "parentfldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andParentfldidIn(List<java.lang.Long> values) {
          addCriterion("parentfldid", values, ConditionMode.IN, "parentfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andParentfldidNotIn(List<java.lang.Long> values) {
          addCriterion("parentfldid", values, ConditionMode.NOT_IN, "parentfldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldDimessionCriteria andChildfldidIsNull() {
		isnull("childfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andChildfldidIsNotNull() {
		notNull("childfldid");
		return this;
	}
	
	public MetaFieldDimessionCriteria andChildfldidIsEmpty() {
		empty("childfldid");
		return this;
	}

	public MetaFieldDimessionCriteria andChildfldidIsNotEmpty() {
		notEmpty("childfldid");
		return this;
	}
       public MetaFieldDimessionCriteria andChildfldidEqualTo(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.EQUAL, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidNotEqualTo(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.NOT_EQUAL, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidGreaterThan(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.GREATER_THEN, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.GREATER_EQUAL, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidLessThan(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.LESS_THEN, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("childfldid", value, ConditionMode.LESS_EQUAL, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("childfldid", value1, value2, ConditionMode.BETWEEN, "childfldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldDimessionCriteria andChildfldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("childfldid", value1, value2, ConditionMode.NOT_BETWEEN, "childfldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldDimessionCriteria andChildfldidIn(List<java.lang.Long> values) {
          addCriterion("childfldid", values, ConditionMode.IN, "childfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldDimessionCriteria andChildfldidNotIn(List<java.lang.Long> values) {
          addCriterion("childfldid", values, ConditionMode.NOT_IN, "childfldid", "java.lang.Long", "Float");
          return this;
      }
}