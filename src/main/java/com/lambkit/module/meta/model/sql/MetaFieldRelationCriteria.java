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
public class MetaFieldRelationCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFieldRelationCriteria create() {
		return new MetaFieldRelationCriteria();
	}
	
	public static MetaFieldRelationCriteria create(Column column) {
		MetaFieldRelationCriteria that = new MetaFieldRelationCriteria();
		that.add(column);
        return that;
    }

    public static MetaFieldRelationCriteria create(String name, Object value) {
        return (MetaFieldRelationCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_field_relation", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFieldRelationCriteria eq(String name, Object value) {
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
    public MetaFieldRelationCriteria ne(String name, Object value) {
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

    public MetaFieldRelationCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFieldRelationCriteria notLike(String name, Object value) {
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
    public MetaFieldRelationCriteria gt(String name, Object value) {
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
    public MetaFieldRelationCriteria ge(String name, Object value) {
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
    public MetaFieldRelationCriteria lt(String name, Object value) {
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
    public MetaFieldRelationCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFieldRelationCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFieldRelationCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFieldRelationCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFieldRelationCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFieldRelationCriteria add(Column column) {
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
		 
	public MetaFieldRelationCriteria andIdIsNull() {
		isnull("id");
		return this;
	}
	
	public MetaFieldRelationCriteria andIdIsNotNull() {
		notNull("id");
		return this;
	}
	
	public MetaFieldRelationCriteria andIdIsEmpty() {
		empty("id");
		return this;
	}

	public MetaFieldRelationCriteria andIdIsNotEmpty() {
		notEmpty("id");
		return this;
	}
       public MetaFieldRelationCriteria andIdEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdNotEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.NOT_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdGreaterThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.GREATER_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdLessThan(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_THEN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("id", value, ConditionMode.LESS_EQUAL, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("id", value1, value2, ConditionMode.BETWEEN, "id", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldRelationCriteria andIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("id", value1, value2, ConditionMode.NOT_BETWEEN, "id", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldRelationCriteria andIdIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.IN, "id", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andIdNotIn(List<java.lang.Long> values) {
          addCriterion("id", values, ConditionMode.NOT_IN, "id", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldRelationCriteria andTbidIsNull() {
		isnull("tbid");
		return this;
	}
	
	public MetaFieldRelationCriteria andTbidIsNotNull() {
		notNull("tbid");
		return this;
	}
	
	public MetaFieldRelationCriteria andTbidIsEmpty() {
		empty("tbid");
		return this;
	}

	public MetaFieldRelationCriteria andTbidIsNotEmpty() {
		notEmpty("tbid");
		return this;
	}
       public MetaFieldRelationCriteria andTbidEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidNotEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.NOT_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidGreaterThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.GREATER_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidLessThan(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_THEN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("tbid", value, ConditionMode.LESS_EQUAL, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("tbid", value1, value2, ConditionMode.BETWEEN, "tbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldRelationCriteria andTbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("tbid", value1, value2, ConditionMode.NOT_BETWEEN, "tbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldRelationCriteria andTbidIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.IN, "tbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andTbidNotIn(List<java.lang.Long> values) {
          addCriterion("tbid", values, ConditionMode.NOT_IN, "tbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldRelationCriteria andFldidIsNull() {
		isnull("fldid");
		return this;
	}
	
	public MetaFieldRelationCriteria andFldidIsNotNull() {
		notNull("fldid");
		return this;
	}
	
	public MetaFieldRelationCriteria andFldidIsEmpty() {
		empty("fldid");
		return this;
	}

	public MetaFieldRelationCriteria andFldidIsNotEmpty() {
		notEmpty("fldid");
		return this;
	}
       public MetaFieldRelationCriteria andFldidEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidNotEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.NOT_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidGreaterThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.GREATER_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidLessThan(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_THEN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("fldid", value, ConditionMode.LESS_EQUAL, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("fldid", value1, value2, ConditionMode.BETWEEN, "fldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldRelationCriteria andFldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("fldid", value1, value2, ConditionMode.NOT_BETWEEN, "fldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldRelationCriteria andFldidIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.IN, "fldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andFldidNotIn(List<java.lang.Long> values) {
          addCriterion("fldid", values, ConditionMode.NOT_IN, "fldid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldRelationCriteria andRelationIsNull() {
		isnull("relation");
		return this;
	}
	
	public MetaFieldRelationCriteria andRelationIsNotNull() {
		notNull("relation");
		return this;
	}
	
	public MetaFieldRelationCriteria andRelationIsEmpty() {
		empty("relation");
		return this;
	}

	public MetaFieldRelationCriteria andRelationIsNotEmpty() {
		notEmpty("relation");
		return this;
	}
        public MetaFieldRelationCriteria andRelationLike(java.lang.String value) {
    	   addCriterion("relation", value, ConditionMode.FUZZY, "relation", "java.lang.String", "Float");
    	   return this;
      }

      public MetaFieldRelationCriteria andRelationNotLike(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.NOT_FUZZY, "relation", "java.lang.String", "Float");
          return this;
      }
      public MetaFieldRelationCriteria andRelationEqualTo(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.EQUAL, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationNotEqualTo(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.NOT_EQUAL, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationGreaterThan(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.GREATER_THEN, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationGreaterThanOrEqualTo(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.GREATER_EQUAL, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationLessThan(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.LESS_THEN, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationLessThanOrEqualTo(java.lang.String value) {
          addCriterion("relation", value, ConditionMode.LESS_EQUAL, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationBetween(java.lang.String value1, java.lang.String value2) {
    	  addCriterion("relation", value1, value2, ConditionMode.BETWEEN, "relation", "java.lang.String", "String");
    	  return this;
      }

      public MetaFieldRelationCriteria andRelationNotBetween(java.lang.String value1, java.lang.String value2) {
          addCriterion("relation", value1, value2, ConditionMode.NOT_BETWEEN, "relation", "java.lang.String", "String");
          return this;
      }
        
      public MetaFieldRelationCriteria andRelationIn(List<java.lang.String> values) {
          addCriterion("relation", values, ConditionMode.IN, "relation", "java.lang.String", "String");
          return this;
      }

      public MetaFieldRelationCriteria andRelationNotIn(List<java.lang.String> values) {
          addCriterion("relation", values, ConditionMode.NOT_IN, "relation", "java.lang.String", "String");
          return this;
      }
	public MetaFieldRelationCriteria andRtbidIsNull() {
		isnull("rtbid");
		return this;
	}
	
	public MetaFieldRelationCriteria andRtbidIsNotNull() {
		notNull("rtbid");
		return this;
	}
	
	public MetaFieldRelationCriteria andRtbidIsEmpty() {
		empty("rtbid");
		return this;
	}

	public MetaFieldRelationCriteria andRtbidIsNotEmpty() {
		notEmpty("rtbid");
		return this;
	}
       public MetaFieldRelationCriteria andRtbidEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidNotEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.NOT_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidGreaterThan(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.GREATER_THEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.GREATER_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidLessThan(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.LESS_THEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("rtbid", value, ConditionMode.LESS_EQUAL, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("rtbid", value1, value2, ConditionMode.BETWEEN, "rtbid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldRelationCriteria andRtbidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("rtbid", value1, value2, ConditionMode.NOT_BETWEEN, "rtbid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldRelationCriteria andRtbidIn(List<java.lang.Long> values) {
          addCriterion("rtbid", values, ConditionMode.IN, "rtbid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRtbidNotIn(List<java.lang.Long> values) {
          addCriterion("rtbid", values, ConditionMode.NOT_IN, "rtbid", "java.lang.Long", "Float");
          return this;
      }
	public MetaFieldRelationCriteria andRfldidIsNull() {
		isnull("rfldid");
		return this;
	}
	
	public MetaFieldRelationCriteria andRfldidIsNotNull() {
		notNull("rfldid");
		return this;
	}
	
	public MetaFieldRelationCriteria andRfldidIsEmpty() {
		empty("rfldid");
		return this;
	}

	public MetaFieldRelationCriteria andRfldidIsNotEmpty() {
		notEmpty("rfldid");
		return this;
	}
       public MetaFieldRelationCriteria andRfldidEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidNotEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.NOT_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidGreaterThan(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.GREATER_THEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.GREATER_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidLessThan(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.LESS_THEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("rfldid", value, ConditionMode.LESS_EQUAL, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("rfldid", value1, value2, ConditionMode.BETWEEN, "rfldid", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFieldRelationCriteria andRfldidNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("rfldid", value1, value2, ConditionMode.NOT_BETWEEN, "rfldid", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFieldRelationCriteria andRfldidIn(List<java.lang.Long> values) {
          addCriterion("rfldid", values, ConditionMode.IN, "rfldid", "java.lang.Long", "Float");
          return this;
      }

      public MetaFieldRelationCriteria andRfldidNotIn(List<java.lang.Long> values) {
          addCriterion("rfldid", values, ConditionMode.NOT_IN, "rfldid", "java.lang.Long", "Float");
          return this;
      }
}