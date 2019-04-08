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
public class MetaFileCatalogMappingCriteria extends Columns implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static MetaFileCatalogMappingCriteria create() {
		return new MetaFileCatalogMappingCriteria();
	}
	
	public static MetaFileCatalogMappingCriteria create(Column column) {
		MetaFileCatalogMappingCriteria that = new MetaFileCatalogMappingCriteria();
		that.add(column);
        return that;
    }

    public static MetaFileCatalogMappingCriteria create(String name, Object value) {
        return (MetaFileCatalogMappingCriteria) create().eq(name, value);
    }
    
    public Example example() {
    	return Example.create("meta_file_catalog_mapping", this);
    }
    
    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public MetaFileCatalogMappingCriteria eq(String name, Object value) {
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
    public MetaFileCatalogMappingCriteria ne(String name, Object value) {
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

    public MetaFileCatalogMappingCriteria like(String name, Object value) {
    	super.like(name, value);
        return this;
    }
    
    public MetaFileCatalogMappingCriteria notLike(String name, Object value) {
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
    public MetaFileCatalogMappingCriteria gt(String name, Object value) {
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
    public MetaFileCatalogMappingCriteria ge(String name, Object value) {
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
    public MetaFileCatalogMappingCriteria lt(String name, Object value) {
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
    public MetaFileCatalogMappingCriteria le(String name, Object value) {
    	super.le(name, value);
        return this;
    }
    
    public MetaFileCatalogMappingCriteria isnull(String name) {
    	super.isnull(name);
        return this;
    } 

    public MetaFileCatalogMappingCriteria notNull(String name) {
    	super.notNull(name);
        return this;
    } 
    
    public MetaFileCatalogMappingCriteria empty(String name) {
    	super.empty(name);
        return this;
    } 
    
    public MetaFileCatalogMappingCriteria notEmpty(String name) {
    	super.notEmpty(name);
        return this;
    } 
    
    public MetaFileCatalogMappingCriteria add(Column column) {
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
		 
	public MetaFileCatalogMappingCriteria andCatalogIdIsNull() {
		isnull("catalog_id");
		return this;
	}
	
	public MetaFileCatalogMappingCriteria andCatalogIdIsNotNull() {
		notNull("catalog_id");
		return this;
	}
	
	public MetaFileCatalogMappingCriteria andCatalogIdIsEmpty() {
		empty("catalog_id");
		return this;
	}

	public MetaFileCatalogMappingCriteria andCatalogIdIsNotEmpty() {
		notEmpty("catalog_id");
		return this;
	}
       public MetaFileCatalogMappingCriteria andCatalogIdEqualTo(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.EQUAL, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdNotEqualTo(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.NOT_EQUAL, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdGreaterThan(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.GREATER_THEN, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.GREATER_EQUAL, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdLessThan(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.LESS_THEN, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("catalog_id", value, ConditionMode.LESS_EQUAL, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("catalog_id", value1, value2, ConditionMode.BETWEEN, "catalogId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("catalog_id", value1, value2, ConditionMode.NOT_BETWEEN, "catalogId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogMappingCriteria andCatalogIdIn(List<java.lang.Long> values) {
          addCriterion("catalog_id", values, ConditionMode.IN, "catalogId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andCatalogIdNotIn(List<java.lang.Long> values) {
          addCriterion("catalog_id", values, ConditionMode.NOT_IN, "catalogId", "java.lang.Long", "Float");
          return this;
      }
	public MetaFileCatalogMappingCriteria andFileIdIsNull() {
		isnull("file_id");
		return this;
	}
	
	public MetaFileCatalogMappingCriteria andFileIdIsNotNull() {
		notNull("file_id");
		return this;
	}
	
	public MetaFileCatalogMappingCriteria andFileIdIsEmpty() {
		empty("file_id");
		return this;
	}

	public MetaFileCatalogMappingCriteria andFileIdIsNotEmpty() {
		notEmpty("file_id");
		return this;
	}
       public MetaFileCatalogMappingCriteria andFileIdEqualTo(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.EQUAL, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdNotEqualTo(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.NOT_EQUAL, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdGreaterThan(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.GREATER_THEN, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdGreaterThanOrEqualTo(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.GREATER_EQUAL, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdLessThan(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.LESS_THEN, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdLessThanOrEqualTo(java.lang.Long value) {
          addCriterion("file_id", value, ConditionMode.LESS_EQUAL, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdBetween(java.lang.Long value1, java.lang.Long value2) {
    	  addCriterion("file_id", value1, value2, ConditionMode.BETWEEN, "fileId", "java.lang.Long", "Float");
    	  return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdNotBetween(java.lang.Long value1, java.lang.Long value2) {
          addCriterion("file_id", value1, value2, ConditionMode.NOT_BETWEEN, "fileId", "java.lang.Long", "Float");
          return this;
      }
        
      public MetaFileCatalogMappingCriteria andFileIdIn(List<java.lang.Long> values) {
          addCriterion("file_id", values, ConditionMode.IN, "fileId", "java.lang.Long", "Float");
          return this;
      }

      public MetaFileCatalogMappingCriteria andFileIdNotIn(List<java.lang.Long> values) {
          addCriterion("file_id", values, ConditionMode.NOT_IN, "fileId", "java.lang.Long", "Float");
          return this;
      }
}