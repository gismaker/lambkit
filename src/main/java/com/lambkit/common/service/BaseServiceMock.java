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
 * 降级实现LambkitService抽象类 
 */
public abstract class BaseServiceMock<M> implements LambkitService<M> {

	@Override
	public M dao() {
		return null;
	};
	
	@Override
	public String getTableName() {
		return null;
	}

	@Override
	public M findById(Object id) {
		return null;
	}

	@Override
	public M findByPrimaryKey(Object id) {
		return null;
	}

	@Override
	public M findFirst(QueryParas queryParas) {
		return null;
	}

	@Override
	public List<M> find(QueryParas queryParas, Integer count) {
		return null;
	}

	@Override
	public List<M> find(QueryParas queryParas) {
		return null;
	}

	@Override
	public Page<M> paginate(QueryParas queryParas) {
		return null;
	}

	@Override
	public Page<M> paginate(QueryParas queryParas, Integer offset, Integer limit) {
		return null;
	}

	@Override
	public Object query(QueryParas queryParas) {
		return null;
	}

	@Override
	public Long count(QueryParas queryParas) {
		return null;
	}
	
	@Override
	public Long count(Example example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long count(Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Record record) {
		return false;
	}
	
	@Override
	public boolean insert(String primaryKey, Record record) {
		return false;
	}

	@Override
	public boolean deleteById(Object id) {
		return false;
	}

	@Override
	public int deleteByPrimaryKey(Object id) {
		return -1;
	}

	@Override
	public int deleteByPrimaryKeys(String ids) {
		return -1;
	}

	@Override
	public int delete(QueryParas queryParas) {
		return -1;
	}

	@Override
	public int update(Record record, QueryParas queryParas) {
		return -1;
	}

	@Override
	public boolean updateByPrimaryKey(String primaryKey, Record record) {
		return false;
	}

	@Override
	public M findFirst(IQuery queryParas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(IQuery queryParas, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(IQuery queryParas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(IQuery queryParas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(IQuery queryParas, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object query(IQuery queryParas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(IQuery queryParas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(IQuery queryParas) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int update(Record record, IQuery queryParas) {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public boolean update(Record Record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void join(Page<? extends Model> page, String joinOnField) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Page<? extends Model> page, String joinOnField, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(List<? extends Model> models, String joinOnField) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(List<? extends Model> models, String joinOnField, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Page<? extends Model> page, String joinOnField, String joinName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(List<? extends Model> models, String joinOnField, String joinName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Model model, String joinOnField) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Model model, String joinOnField, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Model model, String joinOnField, String joinName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void join(Model model, String joinOnField, String joinName, String[] attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keep(Model model, String... attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keep(List<? extends Model> models, String... attrs) {
		// TODO Auto-generated method stub
	}

	@Override
	public M findFirst(Example example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public M findFirst(Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<M> find(Example example, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<M> find(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(Example example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Example example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Example example, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Columns columns, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Example example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Columns columns) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Record record, Example example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Record record, Columns columns) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public M findFirst(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public M findFirstByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public M findFirstByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> find(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> findListByColumns(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> findListByColumns(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> findListByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M> findListByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginate(Columns colums, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginateByColumns(Columns colums, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<M> paginateByColumns(Columns colums, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

}