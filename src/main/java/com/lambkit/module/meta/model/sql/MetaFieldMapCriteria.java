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
public class MetaFieldMapCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldMapCriteria create() {
		return new MetaFieldMapCriteria();
	}
	
	public static MetaFieldMapCriteria create(Column column) {
		MetaFieldMapCriteria that = new MetaFieldMapCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldMapCriteria create(String name, Object value) {
        return (MetaFieldMapCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_map", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldMapCriteria eq(String name, Object value) {
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
    public MetaFieldMapCriteria ne(String name, Object value) {
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

    public MetaFieldMapCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldMapCriteria notLike(String name, Object value) {
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
    public MetaFieldMapCriteria gt(String name, Object value) {
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
    public MetaFieldMapCriteria ge(String name, Object value) {
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
    public MetaFieldMapCriteria lt(String name, Object value) {
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
    public MetaFieldMapCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldMapCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldMapCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldMapCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldMapCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldMapCriteria add(Column column) {
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
		 
	public MetaFieldMapCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldMapCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldMapCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldMapCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldMapCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldMapCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldMapCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldMapCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldMapCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldMapCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldMapCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldMapCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldMapCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldMapCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMapCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldMapCriteria andMtypeIsNull() {
		isnull("mtype");
		return this;
	}
	
	public MetaFieldMapCriteria andMtypeIsNotNull() {
		notNull("mtype");
		return this;
	}
	
	public MetaFieldMapCriteria andMtypeIsEmpty() {
		empty("mtype");
		return this;
	}

	public MetaFieldMapCriteria andMtypeIsNotEmpty() {
		notEmpty("mtype");
		return this;
	}
        public MetaFieldMapCriteria andMtypeLike(java.lang.String value) {
    	   addCriterion("mtype", value, ConditionMode.FUZZY, "mtype", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldMapCriteria andMtypeNotLike(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.NOT_FUZZY, "mtype", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldMapCriteria andMtypeEqualTo(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.EQUAL, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeNotEqualTo(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.NOT_EQUAL, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeGreaterThan(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.GREATER_THEN, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.GREATER_EQUAL, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeLessThan(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.LESS_THEN, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("mtype", value, ConditionMode.LESS_EQUAL, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("mtype", value1, value2, ConditionMode.BETWEEN, "mtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMapCriteria andMtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("mtype", value1, value2, ConditionMode.NOT_BETWEEN, "mtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMapCriteria andMtypeIn(List<java.lang.String> values) {
          addCriterion("mtype", values, ConditionMode.IN, "mtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andMtypeNotIn(List<java.lang.String> values) {
          addCriterion("mtype", values, ConditionMode.NOT_IN, "mtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldMapCriteria andSridIsNull() {
		isnull("srid");
		return this;
	}
	
	public MetaFieldMapCriteria andSridIsNotNull() {
		notNull("srid");
		return this;
	}
	
	public MetaFieldMapCriteria andSridIsEmpty() {
		empty("srid");
		return this;
	}

	public MetaFieldMapCriteria andSridIsNotEmpty() {
		notEmpty("srid");
		return this;
	}
        public MetaFieldMapCriteria andSridLike(java.lang.String value) {
    	   addCriterion("srid", value, ConditionMode.FUZZY, "srid", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldMapCriteria andSridNotLike(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.NOT_FUZZY, "srid", "java.lang.String", "String");
          return this;
      }
      public MetaFieldMapCriteria andSridEqualTo(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.EQUAL, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridNotEqualTo(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.NOT_EQUAL, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridGreaterThan(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.GREATER_THEN, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.GREATER_EQUAL, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridLessThan(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.LESS_THEN, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridLessThanOrEqualTo(java.lang.String value) {
          addCriterion("srid", value, ConditionMode.LESS_EQUAL, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("srid", value1, value2, ConditionMode.BETWEEN, "srid", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMapCriteria andSridNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("srid", value1, value2, ConditionMode.NOT_BETWEEN, "srid", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMapCriteria andSridIn(List<java.lang.String> values) {
          addCriterion("srid", values, ConditionMode.IN, "srid", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andSridNotIn(List<java.lang.String> values) {
          addCriterion("srid", values, ConditionMode.NOT_IN, "srid", "java.lang.String", "String");
          return this;
      }
	public MetaFieldMapCriteria andGeotypeIsNull() {
		isnull("geotype");
		return this;
	}
	
	public MetaFieldMapCriteria andGeotypeIsNotNull() {
		notNull("geotype");
		return this;
	}
	
	public MetaFieldMapCriteria andGeotypeIsEmpty() {
		empty("geotype");
		return this;
	}

	public MetaFieldMapCriteria andGeotypeIsNotEmpty() {
		notEmpty("geotype");
		return this;
	}
        public MetaFieldMapCriteria andGeotypeLike(java.lang.String value) {
    	   addCriterion("geotype", value, ConditionMode.FUZZY, "geotype", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldMapCriteria andGeotypeNotLike(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.NOT_FUZZY, "geotype", "java.lang.String", "String");
          return this;
      }
      public MetaFieldMapCriteria andGeotypeEqualTo(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.EQUAL, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeNotEqualTo(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.NOT_EQUAL, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeGreaterThan(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.GREATER_THEN, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.GREATER_EQUAL, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeLessThan(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.LESS_THEN, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("geotype", value, ConditionMode.LESS_EQUAL, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("geotype", value1, value2, ConditionMode.BETWEEN, "geotype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMapCriteria andGeotypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("geotype", value1, value2, ConditionMode.NOT_BETWEEN, "geotype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMapCriteria andGeotypeIn(List<java.lang.String> values) {
          addCriterion("geotype", values, ConditionMode.IN, "geotype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMapCriteria andGeotypeNotIn(List<java.lang.String> values) {
          addCriterion("geotype", values, ConditionMode.NOT_IN, "geotype", "java.lang.String", "String");
          return this;
      }
}