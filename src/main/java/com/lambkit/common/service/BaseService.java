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
package com.lambkit.common.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

/**
 * BaseService接口
 */
public interface BaseService<M> {

	M dao();
	
	String getTableName();
	
	M findById(Object idValue); 
	M findByPrimaryKey(Object id); 
	
	M findFirst(QueryParas queryParas);
	M findFirst(IQuery queryParas);
	M findFirst(Example example);
	M findFirst(Columns columns);

	List<M> find(QueryParas queryParas, Integer count);
	List<M> find(IQuery queryParas, Integer count);
	List<M> find(Example example, Integer count);
	List<M> find(Columns columns, Integer count);
	
	List<M> find(QueryParas queryParas);
	List<M> find(IQuery queryParas);
	List<M> find(Example example);
	List<M> find(Columns columns);

	 /**
     * 分页查询数据
     * @return
     */
    Page<M> paginate(QueryParas queryParas);
    Page<M> paginate(IQuery queryParas);
    Page<M> paginate(Integer pageNumber, Integer pageSize, Example example);
    Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns);
    /**
     * offet page
     * @param query
     * @param offset
     * @param limit
     * @return
     */
    Page<M> paginate(QueryParas queryParas, Integer offset, Integer limit);
    Page<M> paginate(IQuery queryParas, Integer offset, Integer limit);
    Page<M> paginate(Example example, Integer offset, Integer limit);
    Page<M> paginate(Columns colums, Integer offset, Integer limit);
	
    /**
     * findFirst or Find or paginate
     * @param query
     * @return
     */
    Object query(QueryParas queryParas);
    Object query(IQuery queryParas);
    
	/**
	 * 获取数量	
	 * @param query
	 * @return
	 */
    Long count(QueryParas queryParas);
    Long count(IQuery queryParas);
    Long count(Example example);
    Long count(Columns columns);

	boolean insert(Record record);
	boolean insert(String primaryKey, Record record);
	
	boolean deleteById(Object idValue);
	int deleteByPrimaryKey(Object id);
	/**
	 * ids逗号分隔
	 * @param ids
	 * @return
	 */
	int deleteByPrimaryKeys(String ids);
	int delete(QueryParas queryParas);
	int delete(IQuery queryParas);
	int delete(Example example);
	int delete(Columns columns);

	boolean update(Record record);
	boolean updateByPrimaryKey(String primaryKey, Record record);
	int update(Record record, QueryParas queryParas);
	int update(Record record, IQuery queryParas);
	int update(Record record, Example example);
	int update(Record record, Columns columns);

	void join(Page<? extends Model> page, String joinOnField);
	void join(Page<? extends Model> page, String joinOnField, String[] attrs);
	void join(List<? extends Model> models, String joinOnField);
	void join(List<? extends Model> models, String joinOnField, String[] attrs);
	void join(Page<? extends Model> page, String joinOnField, String joinName);
	void join(List<? extends Model> models, String joinOnField, String joinName);
	void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);
	void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);
	void join(Model model, String joinOnField);
	void join(Model model, String joinOnField, String[] attrs);
	void join(Model model, String joinOnField, String joinName);
	void join(Model model, String joinOnField, String joinName, String[] attrs);
	void keep(Model model, String... attrs);
	void keep(List<? extends Model> models, String... attrs);
}