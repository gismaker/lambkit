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
public class MetaFieldMeasureCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldMeasureCriteria create() {
		return new MetaFieldMeasureCriteria();
	}
	
	public static MetaFieldMeasureCriteria create(Column column) {
		MetaFieldMeasureCriteria that = new MetaFieldMeasureCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldMeasureCriteria create(String name, Object value) {
        return (MetaFieldMeasureCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_measure", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldMeasureCriteria eq(String name, Object value) {
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
    public MetaFieldMeasureCriteria ne(String name, Object value) {
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

    public MetaFieldMeasureCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldMeasureCriteria notLike(String name, Object value) {
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
    public MetaFieldMeasureCriteria gt(String name, Object value) {
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
    public MetaFieldMeasureCriteria ge(String name, Object value) {
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
    public MetaFieldMeasureCriteria lt(String name, Object value) {
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
    public MetaFieldMeasureCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldMeasureCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldMeasureCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldMeasureCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldMeasureCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldMeasureCriteria add(Column column) {
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
		 
	public MetaFieldMeasureCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldMeasureCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldMeasureCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldMeasureCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldMeasureCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldMeasureCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldMeasureCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldMeasureCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldMeasureCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldMeasureCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldMeasureCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldMeasureCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldMeasureCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldMeasureCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldMeasureCriteria andNameIsNull() {
		isnull("name");
		return this;
	}
	
	public MetaFieldMeasureCriteria andNameIsNotNull() {
		notNull("name");
		return this;
	}
	
	public MetaFieldMeasureCriteria andNameIsEmpty() {
		empty("name");
		return this;
	}

	public MetaFieldMeasureCriteria andNameIsNotEmpty() {
		notEmpty("name");
		return this;
	}
       public MetaFieldMeasureCriteria andNameEqualTo(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.EQUAL, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameNotEqualTo(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.NOT_EQUAL, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameGreaterThan(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.GREATER_THEN, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.GREATER_EQUAL, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameLessThan(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.LESS_THEN, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("name", value, ConditionMode.LESS_EQUAL, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("name", value1, value2, ConditionMode.BETWEEN, "name", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldMeasureCriteria andNameNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("name", value1, value2, ConditionMode.NOT_BETWEEN, "name", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldMeasureCriteria andNameIn(List<java.lang.Long> values) {
          addCriterion("name", values, ConditionMode.IN, "name", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldMeasureCriteria andNameNotIn(List<java.lang.Long> values) {
          addCriterion("name", values, ConditionMode.NOT_IN, "name", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldMeasureCriteria andTypeIsNull() {
		isnull("type");
		return this;
	}
	
	public MetaFieldMeasureCriteria andTypeIsNotNull() {
		notNull("type");
		return this;
	}
	
	public MetaFieldMeasureCriteria andTypeIsEmpty() {
		empty("type");
		return this;
	}

	public MetaFieldMeasureCriteria andTypeIsNotEmpty() {
		notEmpty("type");
		return this;
	}
        public MetaFieldMeasureCriteria andTypeLike(java.lang.String value) {
    	   addCriterion("type", value, ConditionMode.FUZZY, "type", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldMeasureCriteria andTypeNotLike(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_FUZZY, "type", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldMeasureCriteria andTypeEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeNotEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.NOT_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeGreaterThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.GREATER_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeLessThan(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_THEN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("type", value, ConditionMode.LESS_EQUAL, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("type", value1, value2, ConditionMode.BETWEEN, "type", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMeasureCriteria andTypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("type", value1, value2, ConditionMode.NOT_BETWEEN, "type", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMeasureCriteria andTypeIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.IN, "type", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andTypeNotIn(List<java.lang.String> values) {
          addCriterion("type", values, ConditionMode.NOT_IN, "type", "java.lang.String", "String");
          return this;
      }
	public MetaFieldMeasureCriteria andAggIsNull() {
		isnull("agg");
		return this;
	}
	
	public MetaFieldMeasureCriteria andAggIsNotNull() {
		notNull("agg");
		return this;
	}
	
	public MetaFieldMeasureCriteria andAggIsEmpty() {
		empty("agg");
		return this;
	}

	public MetaFieldMeasureCriteria andAggIsNotEmpty() {
		notEmpty("agg");
		return this;
	}
        public MetaFieldMeasureCriteria andAggLike(java.lang.String value) {
    	   addCriterion("agg", value, ConditionMode.FUZZY, "agg", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldMeasureCriteria andAggNotLike(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.NOT_FUZZY, "agg", "java.lang.String", "String");
          return this;
      }
      public MetaFieldMeasureCriteria andAggEqualTo(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.EQUAL, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggNotEqualTo(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.NOT_EQUAL, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggGreaterThan(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.GREATER_THEN, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.GREATER_EQUAL, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggLessThan(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.LESS_THEN, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggLessThanOrEqualTo(java.lang.String value) {
          addCriterion("agg", value, ConditionMode.LESS_EQUAL, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("agg", value1, value2, ConditionMode.BETWEEN, "agg", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMeasureCriteria andAggNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("agg", value1, value2, ConditionMode.NOT_BETWEEN, "agg", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMeasureCriteria andAggIn(List<java.lang.String> values) {
          addCriterion("agg", values, ConditionMode.IN, "agg", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andAggNotIn(List<java.lang.String> values) {
          addCriterion("agg", values, ConditionMode.NOT_IN, "agg", "java.lang.String", "String");
          return this;
      }
	public MetaFieldMeasureCriteria andFormulaIsNull() {
		isnull("formula");
		return this;
	}
	
	public MetaFieldMeasureCriteria andFormulaIsNotNull() {
		notNull("formula");
		return this;
	}
	
	public MetaFieldMeasureCriteria andFormulaIsEmpty() {
		empty("formula");
		return this;
	}

	public MetaFieldMeasureCriteria andFormulaIsNotEmpty() {
		notEmpty("formula");
		return this;
	}
        public MetaFieldMeasureCriteria andFormulaLike(java.lang.String value) {
    	   addCriterion("formula", value, ConditionMode.FUZZY, "formula", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldMeasureCriteria andFormulaNotLike(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.NOT_FUZZY, "formula", "java.lang.String", "String");
          return this;
      }
      public MetaFieldMeasureCriteria andFormulaEqualTo(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.EQUAL, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaNotEqualTo(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.NOT_EQUAL, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaGreaterThan(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.GREATER_THEN, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.GREATER_EQUAL, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaLessThan(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.LESS_THEN, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaLessThanOrEqualTo(java.lang.String value) {
          addCriterion("formula", value, ConditionMode.LESS_EQUAL, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("formula", value1, value2, ConditionMode.BETWEEN, "formula", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldMeasureCriteria andFormulaNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("formula", value1, value2, ConditionMode.NOT_BETWEEN, "formula", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldMeasureCriteria andFormulaIn(List<java.lang.String> values) {
          addCriterion("formula", values, ConditionMode.IN, "formula", "java.lang.String", "String");
          return this;
      }

      public MetaFieldMeasureCriteria andFormulaNotIn(List<java.lang.String> values) {
          addCriterion("formula", values, ConditionMode.NOT_IN, "formula", "java.lang.String", "String");
          return this;
      }
}