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
package com.lambkit.common.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;

public interface BaseQuery<M extends Model<M>> {

	Class<M> getModelClass();
	
	M dao();
	
	String getTableName();
	
	M findById(Object idValue); 
	
	M findByPrimaryKey(Object id); 
	
	M findFirst(QueryParas queryParas);
	M findFirst(IQuery queryParas);
	
	List<M> find(QueryParas queryParas, Integer count);
	List<M> find(IQuery queryParas, Integer count);
	
	List<M> find(QueryParas queryParas);
	List<M> find(IQuery queryParas);

	 /**
     * 分页查询数据
     * @return
     */
    Page<M> paginate(QueryParas queryParas);
    Page<M> paginate(IQuery queryParas);
    
    /**
     * offet page
     * @param query
     * @param offset
     * @param limit
     * @return
     */
    Page<M> paginate(QueryParas queryParas, Integer offset, Integer limit);
    Page<M> paginate(IQuery queryParas, Integer offset, Integer limit);
	
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
	int count(QueryParas queryParas);
	
	int count(IQuery queryParas);

	int insert(Record M);

	boolean deleteById(Object idValue);
	
	int deleteByPrimaryKey(Object id);
	
	int deleteByPrimaryKeys(String ids);
	
	int delete(QueryParas queryParas);
	int delete(IQuery queryParas);

	boolean update(M M);
	boolean updateByPrimaryKey(Record M);
	int updateSelective(QueryParas queryParas);
	int updateSelective(IQuery queryParas);
	
	
}
