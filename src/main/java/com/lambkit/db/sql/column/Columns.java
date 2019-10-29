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
package com.lambkit.db.sql.column;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lambkit.db.sql.ConditionMode;

/**
 * Column 的工具类，用于方便组装sql
 */
public class Columns implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3093668879029947796L;
	
	private List<Column> cols = new ArrayList<>();


    public static Columns create() {
        return new Columns();
    }

    public static Columns create(Column column) {
        Columns that = new Columns();
        that.cols.add(column);
        return that;

    }

    public static Columns create(String name, Object value) {
        return create().eq(name, value);
    }

    /**
     * equals
     *
     * @param name
     * @param value
     * @return
     */
    public Columns eq(String name, Object value) {
        cols.add(Column.create(name, value));
        return this;
    }
    
    public Columns add(String name, Object value)
    {
      return eq(name, value);
    }

    /**
     * not equals !=
     *
     * @param name
     * @param value
     * @return
     */
    public Columns ne(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.NOT_EQUAL));
        return this;
    }


    /**
     * like
     *
     * @param name
     * @param value
     * @return
     */

    public Columns like(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.FUZZY));
        return this;
    }
    
    public Columns notLike(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.NOT_FUZZY));
        return this;
    }

    /**
     * 大于 great than
     *
     * @param name
     * @param value
     * @return
     */
    public Columns gt(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.GREATER_THEN));
        return this;
    }

    /**
     * 大于等于 great or equal
     *
     * @param name
     * @param value
     * @return
     */
    public Columns ge(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.GREATER_EQUAL));
        return this;
    }

    /**
     * 小于 less than
     *
     * @param name
     * @param value
     * @return
     */
    public Columns lt(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.LESS_THEN));
        return this;
    }

    /**
     * 小于等于 less or equal
     *
     * @param name
     * @param value
     * @return
     */
    public Columns le(String name, Object value) {
        cols.add(Column.create(name, value, ConditionMode.LESS_EQUAL));
        return this;
    }
    
    public Columns isnull(String name) {
        cols.add(Column.create(name, ConditionMode.ISNULL));
        return this;
    } 

    public Columns notNull(String name) {
        cols.add(Column.create(name, ConditionMode.NOT_NULL));
        return this;
    } 
    
    public Columns empty(String name) {
        cols.add(Column.create(name, ConditionMode.EMPTY));
        return this;
    } 
    
    public Columns notEmpty(String name) {
        cols.add(Column.create(name, ConditionMode.NOT_EMPTY));
        return this;
    } 
    
    public Columns in(String name, Object arrays) {
        cols.add(Column.create(name, arrays, ConditionMode.IN));
        return this;
    } 
    
    public Columns notIn(String name, Object arrays) {
        cols.add(Column.create(name, arrays, ConditionMode.NOT_IN));
        return this;
    } 
    
    public Columns between(String name, Object start, Object end)
    {
      add(Column.create(name, start, end, ConditionMode.BETWEEN));
      return this;
    }
    
    public Columns add(Column column) {
    	cols.add(column);
    	return this;
    }
    
    public Columns addAll(List<Column> columns) {
    	cols.addAll(columns);
    	return this;
    }

    public List<Column> getList() {
        return cols;
    }

}
