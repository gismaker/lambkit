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
public class MetaFieldEditCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldEditCriteria create() {
		return new MetaFieldEditCriteria();
	}
	
	public static MetaFieldEditCriteria create(Column column) {
		MetaFieldEditCriteria that = new MetaFieldEditCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldEditCriteria create(String name, Object value) {
        return (MetaFieldEditCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_edit", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldEditCriteria eq(String name, Object value) {
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
    public MetaFieldEditCriteria ne(String name, Object value) {
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

    public MetaFieldEditCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldEditCriteria notLike(String name, Object value) {
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
    public MetaFieldEditCriteria gt(String name, Object value) {
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
    public MetaFieldEditCriteria ge(String name, Object value) {
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
    public MetaFieldEditCriteria lt(String name, Object value) {
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
    public MetaFieldEditCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldEditCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldEditCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldEditCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldEditCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldEditCriteria add(Column column) {
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
		 
	public MetaFieldEditCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldEditCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldEditCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldEditCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldEditCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldEditCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldEditCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldEditCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldEditCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andTmidIsNull() {
		isnull("tmid");
		return this;
	}
	
	public MetaFieldEditCriteria andTmidIsNotNull() {
		notNull("tmid");
		return this;
	}
	
	public MetaFieldEditCriteria andTmidIsEmpty() {
		empty("tmid");
		return this;
	}

	public MetaFieldEditCriteria andTmidIsNotEmpty() {
		notEmpty("tmid");
		return this;
	}
       public MetaFieldEditCriteria andTmidEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidNotEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.NOT_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidGreaterThan(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.GREATER_THEN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.GREATER_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidLessThan(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.LESS_THEN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.LESS_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tmid", value1, value2, ConditionMode.BETWEEN, "tmid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andTmidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tmid", value1, value2, ConditionMode.NOT_BETWEEN, "tmid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andTmidIn(List<java.lang.Long> values) {
          addCriterion("tmid", values, ConditionMode.IN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andTmidNotIn(List<java.lang.Long> values) {
          addCriterion("tmid", values, ConditionMode.NOT_IN, "tmid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andChecktypeIsNull() {
		isnull("checktype");
		return this;
	}
	
	public MetaFieldEditCriteria andChecktypeIsNotNull() {
		notNull("checktype");
		return this;
	}
	
	public MetaFieldEditCriteria andChecktypeIsEmpty() {
		empty("checktype");
		return this;
	}

	public MetaFieldEditCriteria andChecktypeIsNotEmpty() {
		notEmpty("checktype");
		return this;
	}
       public MetaFieldEditCriteria andChecktypeEqualTo(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.EQUAL, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeNotEqualTo(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.NOT_EQUAL, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeGreaterThan(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.GREATER_THEN, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.GREATER_EQUAL, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeLessThan(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.LESS_THEN, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("checktype", value, ConditionMode.LESS_EQUAL, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("checktype", value1, value2, ConditionMode.BETWEEN, "checktype", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andChecktypeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("checktype", value1, value2, ConditionMode.NOT_BETWEEN, "checktype", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andChecktypeIn(List<java.lang.Long> values) {
          addCriterion("checktype", values, ConditionMode.IN, "checktype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andChecktypeNotIn(List<java.lang.Long> values) {
          addCriterion("checktype", values, ConditionMode.NOT_IN, "checktype", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andEdittypeIsNull() {
		isnull("edittype");
		return this;
	}
	
	public MetaFieldEditCriteria andEdittypeIsNotNull() {
		notNull("edittype");
		return this;
	}
	
	public MetaFieldEditCriteria andEdittypeIsEmpty() {
		empty("edittype");
		return this;
	}

	public MetaFieldEditCriteria andEdittypeIsNotEmpty() {
		notEmpty("edittype");
		return this;
	}
       public MetaFieldEditCriteria andEdittypeEqualTo(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.EQUAL, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeNotEqualTo(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.NOT_EQUAL, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeGreaterThan(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.GREATER_THEN, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.GREATER_EQUAL, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeLessThan(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.LESS_THEN, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("edittype", value, ConditionMode.LESS_EQUAL, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("edittype", value1, value2, ConditionMode.BETWEEN, "edittype", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andEdittypeNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("edittype", value1, value2, ConditionMode.NOT_BETWEEN, "edittype", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andEdittypeIn(List<java.lang.Long> values) {
          addCriterion("edittype", values, ConditionMode.IN, "edittype", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEdittypeNotIn(List<java.lang.Long> values) {
          addCriterion("edittype", values, ConditionMode.NOT_IN, "edittype", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andEditidIsNull() {
		isnull("editid");
		return this;
	}
	
	public MetaFieldEditCriteria andEditidIsNotNull() {
		notNull("editid");
		return this;
	}
	
	public MetaFieldEditCriteria andEditidIsEmpty() {
		empty("editid");
		return this;
	}

	public MetaFieldEditCriteria andEditidIsNotEmpty() {
		notEmpty("editid");
		return this;
	}
       public MetaFieldEditCriteria andEditidEqualTo(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.EQUAL, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidNotEqualTo(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.NOT_EQUAL, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidGreaterThan(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.GREATER_THEN, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.GREATER_EQUAL, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidLessThan(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.LESS_THEN, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("editid", value, ConditionMode.LESS_EQUAL, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("editid", value1, value2, ConditionMode.BETWEEN, "editid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andEditidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("editid", value1, value2, ConditionMode.NOT_BETWEEN, "editid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andEditidIn(List<java.lang.Long> values) {
          addCriterion("editid", values, ConditionMode.IN, "editid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditidNotIn(List<java.lang.Long> values) {
          addCriterion("editid", values, ConditionMode.NOT_IN, "editid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andEditminlenIsNull() {
		isnull("editminlen");
		return this;
	}
	
	public MetaFieldEditCriteria andEditminlenIsNotNull() {
		notNull("editminlen");
		return this;
	}
	
	public MetaFieldEditCriteria andEditminlenIsEmpty() {
		empty("editminlen");
		return this;
	}

	public MetaFieldEditCriteria andEditminlenIsNotEmpty() {
		notEmpty("editminlen");
		return this;
	}
       public MetaFieldEditCriteria andEditminlenEqualTo(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.EQUAL, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenNotEqualTo(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.NOT_EQUAL, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenGreaterThan(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.GREATER_THEN, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.GREATER_EQUAL, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenLessThan(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.LESS_THEN, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("editminlen", value, ConditionMode.LESS_EQUAL, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("editminlen", value1, value2, ConditionMode.BETWEEN, "editminlen", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andEditminlenNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("editminlen", value1, value2, ConditionMode.NOT_BETWEEN, "editminlen", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andEditminlenIn(List<java.lang.Long> values) {
          addCriterion("editminlen", values, ConditionMode.IN, "editminlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditminlenNotIn(List<java.lang.Long> values) {
          addCriterion("editminlen", values, ConditionMode.NOT_IN, "editminlen", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andEditmaxlenIsNull() {
		isnull("editmaxlen");
		return this;
	}
	
	public MetaFieldEditCriteria andEditmaxlenIsNotNull() {
		notNull("editmaxlen");
		return this;
	}
	
	public MetaFieldEditCriteria andEditmaxlenIsEmpty() {
		empty("editmaxlen");
		return this;
	}

	public MetaFieldEditCriteria andEditmaxlenIsNotEmpty() {
		notEmpty("editmaxlen");
		return this;
	}
       public MetaFieldEditCriteria andEditmaxlenEqualTo(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.EQUAL, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenNotEqualTo(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.NOT_EQUAL, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenGreaterThan(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.GREATER_THEN, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.GREATER_EQUAL, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenLessThan(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.LESS_THEN, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("editmaxlen", value, ConditionMode.LESS_EQUAL, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("editmaxlen", value1, value2, ConditionMode.BETWEEN, "editmaxlen", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andEditmaxlenNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("editmaxlen", value1, value2, ConditionMode.NOT_BETWEEN, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andEditmaxlenIn(List<java.lang.Long> values) {
          addCriterion("editmaxlen", values, ConditionMode.IN, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditmaxlenNotIn(List<java.lang.Long> values) {
          addCriterion("editmaxlen", values, ConditionMode.NOT_IN, "editmaxlen", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldEditCriteria andEditorderIsNull() {
		isnull("editorder");
		return this;
	}
	
	public MetaFieldEditCriteria andEditorderIsNotNull() {
		notNull("editorder");
		return this;
	}
	
	public MetaFieldEditCriteria andEditorderIsEmpty() {
		empty("editorder");
		return this;
	}

	public MetaFieldEditCriteria andEditorderIsNotEmpty() {
		notEmpty("editorder");
		return this;
	}
       public MetaFieldEditCriteria andEditorderEqualTo(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.EQUAL, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderNotEqualTo(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.NOT_EQUAL, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderGreaterThan(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.GREATER_THEN, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.GREATER_EQUAL, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderLessThan(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.LESS_THEN, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("editorder", value, ConditionMode.LESS_EQUAL, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("editorder", value1, value2, ConditionMode.BETWEEN, "editorder", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldEditCriteria andEditorderNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("editorder", value1, value2, ConditionMode.NOT_BETWEEN, "editorder", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldEditCriteria andEditorderIn(List<java.lang.Long> values) {
          addCriterion("editorder", values, ConditionMode.IN, "editorder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldEditCriteria andEditorderNotIn(List<java.lang.Long> values) {
          addCriterion("editorder", values, ConditionMode.NOT_IN, "editorder", "java.lang.Long", "Float");
          return this;
      }
}