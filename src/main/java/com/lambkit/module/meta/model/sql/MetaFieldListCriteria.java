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
public class MetaFieldListCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldListCriteria create() {
		return new MetaFieldListCriteria();
	}
	
	public static MetaFieldListCriteria create(Column column) {
		MetaFieldListCriteria that = new MetaFieldListCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldListCriteria create(String name, Object value) {
        return (MetaFieldListCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_list", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldListCriteria eq(String name, Object value) {
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
    public MetaFieldListCriteria ne(String name, Object value) {
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

    public MetaFieldListCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldListCriteria notLike(String name, Object value) {
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
    public MetaFieldListCriteria gt(String name, Object value) {
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
    public MetaFieldListCriteria ge(String name, Object value) {
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
    public MetaFieldListCriteria lt(String name, Object value) {
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
    public MetaFieldListCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldListCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldListCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldListCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldListCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldListCriteria add(Column column) {
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
		 
	public MetaFieldListCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldListCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldListCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldListCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldListCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldListCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldListCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldListCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldListCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldListCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldListCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldListCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldListCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldListCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldListCriteria andTmidIsNull() {
		isnull("tmid");
		return this;
	}
	
	public MetaFieldListCriteria andTmidIsNotNull() {
		notNull("tmid");
		return this;
	}
	
	public MetaFieldListCriteria andTmidIsEmpty() {
		empty("tmid");
		return this;
	}

	public MetaFieldListCriteria andTmidIsNotEmpty() {
		notEmpty("tmid");
		return this;
	}
       public MetaFieldListCriteria andTmidEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidNotEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.NOT_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidGreaterThan(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.GREATER_THEN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.GREATER_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidLessThan(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.LESS_THEN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tmid", value, ConditionMode.LESS_EQUAL, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tmid", value1, value2, ConditionMode.BETWEEN, "tmid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldListCriteria andTmidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tmid", value1, value2, ConditionMode.NOT_BETWEEN, "tmid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldListCriteria andTmidIn(List<java.lang.Long> values) {
          addCriterion("tmid", values, ConditionMode.IN, "tmid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andTmidNotIn(List<java.lang.Long> values) {
          addCriterion("tmid", values, ConditionMode.NOT_IN, "tmid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldListCriteria andViewnameIsNull() {
		isnull("viewname");
		return this;
	}
	
	public MetaFieldListCriteria andViewnameIsNotNull() {
		notNull("viewname");
		return this;
	}
	
	public MetaFieldListCriteria andViewnameIsEmpty() {
		empty("viewname");
		return this;
	}

	public MetaFieldListCriteria andViewnameIsNotEmpty() {
		notEmpty("viewname");
		return this;
	}
        public MetaFieldListCriteria andViewnameLike(java.lang.String value) {
    	   addCriterion("viewname", value, ConditionMode.FUZZY, "viewname", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldListCriteria andViewnameNotLike(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.NOT_FUZZY, "viewname", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldListCriteria andViewnameEqualTo(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.EQUAL, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameNotEqualTo(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.NOT_EQUAL, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameGreaterThan(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.GREATER_THEN, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.GREATER_EQUAL, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameLessThan(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.LESS_THEN, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameLessThanOrEqualTo(java.lang.String value) {
          addCriterion("viewname", value, ConditionMode.LESS_EQUAL, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("viewname", value1, value2, ConditionMode.BETWEEN, "viewname", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andViewnameNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("viewname", value1, value2, ConditionMode.NOT_BETWEEN, "viewname", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andViewnameIn(List<java.lang.String> values) {
          addCriterion("viewname", values, ConditionMode.IN, "viewname", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewnameNotIn(List<java.lang.String> values) {
          addCriterion("viewname", values, ConditionMode.NOT_IN, "viewname", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andIsviewIsNull() {
		isnull("isview");
		return this;
	}
	
	public MetaFieldListCriteria andIsviewIsNotNull() {
		notNull("isview");
		return this;
	}
	
	public MetaFieldListCriteria andIsviewIsEmpty() {
		empty("isview");
		return this;
	}

	public MetaFieldListCriteria andIsviewIsNotEmpty() {
		notEmpty("isview");
		return this;
	}
        public MetaFieldListCriteria andIsviewLike(java.lang.String value) {
    	   addCriterion("isview", value, ConditionMode.FUZZY, "isview", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldListCriteria andIsviewNotLike(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.NOT_FUZZY, "isview", "java.lang.String", "String");
          return this;
      }
      public MetaFieldListCriteria andIsviewEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewNotEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.NOT_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewGreaterThan(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.GREATER_THEN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.GREATER_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewLessThan(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.LESS_THEN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isview", value, ConditionMode.LESS_EQUAL, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isview", value1, value2, ConditionMode.BETWEEN, "isview", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andIsviewNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isview", value1, value2, ConditionMode.NOT_BETWEEN, "isview", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andIsviewIn(List<java.lang.String> values) {
          addCriterion("isview", values, ConditionMode.IN, "isview", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsviewNotIn(List<java.lang.String> values) {
          addCriterion("isview", values, ConditionMode.NOT_IN, "isview", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andIsorderIsNull() {
		isnull("isorder");
		return this;
	}
	
	public MetaFieldListCriteria andIsorderIsNotNull() {
		notNull("isorder");
		return this;
	}
	
	public MetaFieldListCriteria andIsorderIsEmpty() {
		empty("isorder");
		return this;
	}

	public MetaFieldListCriteria andIsorderIsNotEmpty() {
		notEmpty("isorder");
		return this;
	}
        public MetaFieldListCriteria andIsorderLike(java.lang.String value) {
    	   addCriterion("isorder", value, ConditionMode.FUZZY, "isorder", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldListCriteria andIsorderNotLike(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.NOT_FUZZY, "isorder", "java.lang.String", "String");
          return this;
      }
      public MetaFieldListCriteria andIsorderEqualTo(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.EQUAL, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderNotEqualTo(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.NOT_EQUAL, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderGreaterThan(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.GREATER_THEN, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.GREATER_EQUAL, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderLessThan(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.LESS_THEN, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderLessThanOrEqualTo(java.lang.String value) {
          addCriterion("isorder", value, ConditionMode.LESS_EQUAL, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("isorder", value1, value2, ConditionMode.BETWEEN, "isorder", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andIsorderNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("isorder", value1, value2, ConditionMode.NOT_BETWEEN, "isorder", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andIsorderIn(List<java.lang.String> values) {
          addCriterion("isorder", values, ConditionMode.IN, "isorder", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIsorderNotIn(List<java.lang.String> values) {
          addCriterion("isorder", values, ConditionMode.NOT_IN, "isorder", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andViewmaxlenIsNull() {
		isnull("viewmaxlen");
		return this;
	}
	
	public MetaFieldListCriteria andViewmaxlenIsNotNull() {
		notNull("viewmaxlen");
		return this;
	}
	
	public MetaFieldListCriteria andViewmaxlenIsEmpty() {
		empty("viewmaxlen");
		return this;
	}

	public MetaFieldListCriteria andViewmaxlenIsNotEmpty() {
		notEmpty("viewmaxlen");
		return this;
	}
       public MetaFieldListCriteria andViewmaxlenEqualTo(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.EQUAL, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenNotEqualTo(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.NOT_EQUAL, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenGreaterThan(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.GREATER_THEN, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.GREATER_EQUAL, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenLessThan(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.LESS_THEN, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("viewmaxlen", value, ConditionMode.LESS_EQUAL, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("viewmaxlen", value1, value2, ConditionMode.BETWEEN, "viewmaxlen", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldListCriteria andViewmaxlenNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("viewmaxlen", value1, value2, ConditionMode.NOT_BETWEEN, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldListCriteria andViewmaxlenIn(List<java.lang.Long> values) {
          addCriterion("viewmaxlen", values, ConditionMode.IN, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andViewmaxlenNotIn(List<java.lang.Long> values) {
          addCriterion("viewmaxlen", values, ConditionMode.NOT_IN, "viewmaxlen", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldListCriteria andViewtypeIsNull() {
		isnull("viewtype");
		return this;
	}
	
	public MetaFieldListCriteria andViewtypeIsNotNull() {
		notNull("viewtype");
		return this;
	}
	
	public MetaFieldListCriteria andViewtypeIsEmpty() {
		empty("viewtype");
		return this;
	}

	public MetaFieldListCriteria andViewtypeIsNotEmpty() {
		notEmpty("viewtype");
		return this;
	}
        public MetaFieldListCriteria andViewtypeLike(java.lang.String value) {
    	   addCriterion("viewtype", value, ConditionMode.FUZZY, "viewtype", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldListCriteria andViewtypeNotLike(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.NOT_FUZZY, "viewtype", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldListCriteria andViewtypeEqualTo(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.EQUAL, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeNotEqualTo(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.NOT_EQUAL, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeGreaterThan(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.GREATER_THEN, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.GREATER_EQUAL, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeLessThan(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.LESS_THEN, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("viewtype", value, ConditionMode.LESS_EQUAL, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("viewtype", value1, value2, ConditionMode.BETWEEN, "viewtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andViewtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("viewtype", value1, value2, ConditionMode.NOT_BETWEEN, "viewtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andViewtypeIn(List<java.lang.String> values) {
          addCriterion("viewtype", values, ConditionMode.IN, "viewtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andViewtypeNotIn(List<java.lang.String> values) {
          addCriterion("viewtype", values, ConditionMode.NOT_IN, "viewtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andVieworderIsNull() {
		isnull("vieworder");
		return this;
	}
	
	public MetaFieldListCriteria andVieworderIsNotNull() {
		notNull("vieworder");
		return this;
	}
	
	public MetaFieldListCriteria andVieworderIsEmpty() {
		empty("vieworder");
		return this;
	}

	public MetaFieldListCriteria andVieworderIsNotEmpty() {
		notEmpty("vieworder");
		return this;
	}
       public MetaFieldListCriteria andVieworderEqualTo(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.EQUAL, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderNotEqualTo(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.NOT_EQUAL, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderGreaterThan(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.GREATER_THEN, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.GREATER_EQUAL, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderLessThan(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.LESS_THEN, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("vieworder", value, ConditionMode.LESS_EQUAL, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("vieworder", value1, value2, ConditionMode.BETWEEN, "vieworder", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldListCriteria andVieworderNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("vieworder", value1, value2, ConditionMode.NOT_BETWEEN, "vieworder", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldListCriteria andVieworderIn(List<java.lang.Long> values) {
          addCriterion("vieworder", values, ConditionMode.IN, "vieworder", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldListCriteria andVieworderNotIn(List<java.lang.Long> values) {
          addCriterion("vieworder", values, ConditionMode.NOT_IN, "vieworder", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldListCriteria andIssearchIsNull() {
		isnull("issearch");
		return this;
	}
	
	public MetaFieldListCriteria andIssearchIsNotNull() {
		notNull("issearch");
		return this;
	}
	
	public MetaFieldListCriteria andIssearchIsEmpty() {
		empty("issearch");
		return this;
	}

	public MetaFieldListCriteria andIssearchIsNotEmpty() {
		notEmpty("issearch");
		return this;
	}
        public MetaFieldListCriteria andIssearchLike(java.lang.String value) {
    	   addCriterion("issearch", value, ConditionMode.FUZZY, "issearch", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldListCriteria andIssearchNotLike(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.NOT_FUZZY, "issearch", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldListCriteria andIssearchEqualTo(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.EQUAL, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchNotEqualTo(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.NOT_EQUAL, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchGreaterThan(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.GREATER_THEN, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.GREATER_EQUAL, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchLessThan(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.LESS_THEN, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchLessThanOrEqualTo(java.lang.String value) {
          addCriterion("issearch", value, ConditionMode.LESS_EQUAL, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("issearch", value1, value2, ConditionMode.BETWEEN, "issearch", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andIssearchNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("issearch", value1, value2, ConditionMode.NOT_BETWEEN, "issearch", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andIssearchIn(List<java.lang.String> values) {
          addCriterion("issearch", values, ConditionMode.IN, "issearch", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andIssearchNotIn(List<java.lang.String> values) {
          addCriterion("issearch", values, ConditionMode.NOT_IN, "issearch", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andSearchtypeIsNull() {
		isnull("searchtype");
		return this;
	}
	
	public MetaFieldListCriteria andSearchtypeIsNotNull() {
		notNull("searchtype");
		return this;
	}
	
	public MetaFieldListCriteria andSearchtypeIsEmpty() {
		empty("searchtype");
		return this;
	}

	public MetaFieldListCriteria andSearchtypeIsNotEmpty() {
		notEmpty("searchtype");
		return this;
	}
        public MetaFieldListCriteria andSearchtypeLike(java.lang.String value) {
    	   addCriterion("searchtype", value, ConditionMode.FUZZY, "searchtype", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldListCriteria andSearchtypeNotLike(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.NOT_FUZZY, "searchtype", "java.lang.String", "String");
          return this;
      }
      public MetaFieldListCriteria andSearchtypeEqualTo(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.EQUAL, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeNotEqualTo(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.NOT_EQUAL, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeGreaterThan(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.GREATER_THEN, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.GREATER_EQUAL, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeLessThan(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.LESS_THEN, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeLessThanOrEqualTo(java.lang.String value) {
          addCriterion("searchtype", value, ConditionMode.LESS_EQUAL, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("searchtype", value1, value2, ConditionMode.BETWEEN, "searchtype", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andSearchtypeNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("searchtype", value1, value2, ConditionMode.NOT_BETWEEN, "searchtype", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andSearchtypeIn(List<java.lang.String> values) {
          addCriterion("searchtype", values, ConditionMode.IN, "searchtype", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchtypeNotIn(List<java.lang.String> values) {
          addCriterion("searchtype", values, ConditionMode.NOT_IN, "searchtype", "java.lang.String", "String");
          return this;
      }
	public MetaFieldListCriteria andSearchinfoIsNull() {
		isnull("searchinfo");
		return this;
	}
	
	public MetaFieldListCriteria andSearchinfoIsNotNull() {
		notNull("searchinfo");
		return this;
	}
	
	public MetaFieldListCriteria andSearchinfoIsEmpty() {
		empty("searchinfo");
		return this;
	}

	public MetaFieldListCriteria andSearchinfoIsNotEmpty() {
		notEmpty("searchinfo");
		return this;
	}
        public MetaFieldListCriteria andSearchinfoLike(java.lang.String value) {
    	   addCriterion("searchinfo", value, ConditionMode.FUZZY, "searchinfo", "java.lang.String", "String");
    	   return this;
      }

      public MetaFieldListCriteria andSearchinfoNotLike(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.NOT_FUZZY, "searchinfo", "java.lang.String", "String");
          return this;
      }
      public MetaFieldListCriteria andSearchinfoEqualTo(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.EQUAL, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoNotEqualTo(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.NOT_EQUAL, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoGreaterThan(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.GREATER_THEN, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.GREATER_EQUAL, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoLessThan(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.LESS_THEN, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoLessThanOrEqualTo(java.lang.String value) {
          addCriterion("searchinfo", value, ConditionMode.LESS_EQUAL, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("searchinfo", value1, value2, ConditionMode.BETWEEN, "searchinfo", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldListCriteria andSearchinfoNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("searchinfo", value1, value2, ConditionMode.NOT_BETWEEN, "searchinfo", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldListCriteria andSearchinfoIn(List<java.lang.String> values) {
          addCriterion("searchinfo", values, ConditionMode.IN, "searchinfo", "java.lang.String", "String");
          return this;
      }

      public MetaFieldListCriteria andSearchinfoNotIn(List<java.lang.String> values) {
          addCriterion("searchinfo", values, ConditionMode.NOT_IN, "searchinfo", "java.lang.String", "String");
          return this;
      }
}