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

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 实现LambkitService抽象类
 */
public abstract class BaseServiceImpl<Query, M> implements LambkitService<M> {

	public Query query;
	
	protected abstract void initQuery();

	public Query getQuery() {
		return query;
	}
	
	/**
	 * 获取类泛型class
	 * @return
	 */
	public Class<Query> getQueryClass() {
		return (Class<Query>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public M findById(Object id) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method findByPrimaryKey = query.getClass().getDeclaredMethod("findById", id.getClass());
			Object result = findByPrimaryKey.invoke(query, id);
			return (M) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public M findByPrimaryKey(Object id) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method findByPrimaryKey = query.getClass().getDeclaredMethod("findByPrimaryKey", id.getClass());
			Object result = findByPrimaryKey.invoke(query, id);
			return (M) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public M findFirst(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("findFirst", queryParas.getClass());
			List<M> result = (List<M>) select.invoke(query, queryParas);
			if (null != result && result.size() > 0) {
				return result.get(0);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public M findFirst(Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("findFirst", example.getClass());
			List<M> result = (List<M>) select.invoke(query, example);
			if (null != result && result.size() > 0) {
				return result.get(0);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public M findFirst(Columns columns) {
		return findFirst(Example.create(getTableName(), columns));
	}
	

	@Override
	public M findFirst(Columns columns, String orderby) {
		return findFirst(Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	
	@Override
	public M findFirstByColumns(Columns columns) {
		return findFirst(Example.create(getTableName(), columns));
	}
	
	@Override
	public M findFirstByColumns(Columns columns, String orderby) {
		return findFirst(Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	

	@Override
	public List<M> find(QueryParas queryParas, Integer count) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("find", queryParas.getClass(), Integer.class);
			Object result = select.invoke(query, queryParas);
			return (List<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public List<M> find(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("find", queryParas.getClass());
			Object result = select.invoke(query, queryParas);
			return (List<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public List<M> find(Example example, Integer count) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("find", example.getClass());
			Object result = select.invoke(query, example);
			return (List<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public List<M> find(Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method select = query.getClass().getDeclaredMethod("find", example.getClass());
			Object result = select.invoke(query, example);
			return (List<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public List<M> find(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns), count);
	}
	

	@Override
	public List<M> find(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns).setOrderBy(orderby), count);
	}
	@Override
	public List<M> findListByColumns(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns), count);
	}
	@Override
	public List<M> findListByColumns(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns).setOrderBy(orderby), count);
	}

	@Override
	public List<M> find(Columns columns) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns));
	}

	@Override
	public List<M> find(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	@Override
	public List<M> findListByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns));
	}
	@Override
	public List<M> findListByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return find(Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	
	
	@Override
	public Page<M> paginate(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectWithBLOBs = query.getClass().getDeclaredMethod("paginate", queryParas.getClass());
			//PageHelper.startPage(pageNum, pageSize, false);
			Object result = selectWithBLOBs.invoke(query, queryParas);
			return (Page<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectWithBLOBs = query.getClass().getDeclaredMethod("paginate", Integer.class, Integer.class, example.getClass());
			//PageHelper.startPage(pageNum, pageSize, false);
			Object result = selectWithBLOBs.invoke(query, pageNumber, pageSize, example);
			return (Page<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return paginate(pageNumber, pageSize, Example.create(getTableName(), columns));
	}

	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return paginate(pageNumber, pageSize, Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	@Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return paginate(pageNumber, pageSize, Example.create(getTableName(), columns));
	}
	@Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return paginate(pageNumber, pageSize, Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	
	
	@Override
	public Page<M> paginate(QueryParas queryParas, Integer offset, Integer limit) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method paginate = query.getClass().getDeclaredMethod("paginate", queryParas.getClass(), Integer.class, Integer.class);
			//PageHelper.offsetPage(offset, limit, false);
			Object result = paginate.invoke(query, offset/limit+1, limit, queryParas);
			return (Page<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Page<M> paginate(Example example, Integer offset, Integer limit) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method paginate = query.getClass().getDeclaredMethod("paginate", example.getClass(), Integer.class, Integer.class);
			//PageHelper.offsetPage(offset, limit, false);
			Object result = paginate.invoke(query, offset/limit+1, limit, example);
			return (Page<M>) result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Page<M> paginate(Columns columns, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return paginate(Example.create(getTableName(), columns), offset, limit);
	}

	@Override
	public Page<M> paginate(Columns columns, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return paginate(Example.create(getTableName(), columns).setOrderBy(orderby), offset, limit);
	}
	
	@Override
	public Page<M> paginateByColumns(Columns columns, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return paginate(Example.create(getTableName(), columns), offset, limit);
	}
	
	@Override
	public Page<M> paginateByColumns(Columns columns, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return paginate(Example.create(getTableName(), columns).setOrderBy(orderby), offset, limit);
	}
	
	@Override
	public Object query(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method selectWithBLOBs = query.getClass().getDeclaredMethod("query", queryParas.getClass());
			Object result = selectWithBLOBs.invoke(query, queryParas);
			return result;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}

	@Override
	public Long count(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method count = query.getClass().getDeclaredMethod("count", queryParas.getClass());
			Object result = count.invoke(query, queryParas);
			return Long.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Long count(Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
			Method count = query.getClass().getDeclaredMethod("count", example.getClass());
			Object result = count.invoke(query, example);
			return Long.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return null;
	}
	
	@Override
	public Long count(Columns columns) {
		return count(Example.create(getTableName(), columns));
	}

	@Override
	public boolean insert(Record record) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method insert = query.getClass().getDeclaredMethod("insert", record.getClass());
			Object result = insert.invoke(query, record);
			return Boolean.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return false;
	}
	
	@Override
	public boolean insert(String primaryKey, Record record) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method insert = query.getClass().getDeclaredMethod("insert", String.class, record.getClass());
			Object result = insert.invoke(query, primaryKey, record);
			return Boolean.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return false;
	}

	@Override
	public boolean deleteById(Object id) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method deleteByPrimaryKey = query.getClass().getDeclaredMethod("deleteById", id.getClass());
			Object result = deleteByPrimaryKey.invoke(query, id);
			return Boolean.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return false;
	}

	@Override
	public int deleteByPrimaryKey(Object id) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method deleteByPrimaryKey = query.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
			Object result = deleteByPrimaryKey.invoke(query, id);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}
	

	@Override
	public int deleteByPrimaryKeys(String ids) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method deleteByPrimaryKey = query.getClass().getDeclaredMethod("deleteByPrimaryKeys", String.class);
			Object result = deleteByPrimaryKey.invoke(query, ids);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}

	@Override
	public int delete(QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method delete = query.getClass().getDeclaredMethod("delete", queryParas.getClass());
			Object result = delete.invoke(query, queryParas);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}
	
	@Override
	public int delete(Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method delete = query.getClass().getDeclaredMethod("delete", example.getClass());
			Object result = delete.invoke(query, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}
	
	@Override
	public int delete(Columns columns) {
		// TODO Auto-generated method stub
		return delete(Example.create(getTableName(), columns));
	}

	@Override
	public int update(Record record, QueryParas queryParas) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method update = query.getClass().getDeclaredMethod("update", record.getClass(), queryParas.getClass());
			Object result = update.invoke(query, queryParas);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}
	
	@Override
	public int update(Record record, Example example) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method update = query.getClass().getDeclaredMethod("update", record.getClass(), example.getClass());
			Object result = update.invoke(query, example);
			return Integer.parseInt(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return 0;
	}
	
	@Override
	public int update(Record record, Columns columns) {
		// TODO Auto-generated method stub
		return update(record, Example.create(getTableName(), columns));
	}

	@Override
	public boolean updateByPrimaryKey(String primaryKey, Record record) {
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByPrimaryKey = query.getClass().getDeclaredMethod("updateByPrimaryKey", String.class, record.getClass());
			Object result = updateByPrimaryKey.invoke(query, primaryKey, record);
			return Boolean.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return false;
	}
	
	@Override
	public boolean update(Record record) {
		// TODO Auto-generated method stub
		try {
			//DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
			Method updateByPrimaryKey = query.getClass().getDeclaredMethod("update", record.getClass());
			Object result = updateByPrimaryKey.invoke(query, record);
			return Boolean.valueOf(String.valueOf(result));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		//DynamicDataSource.clearDataSource();
		return false;
	}

	@Override
	public M findFirst(IQuery queryParas) {
		// TODO Auto-generated method stub
		return findFirst(queryParas.toQueryParas());
	}

	@Override
	public List<M> find(IQuery queryParas, Integer count) {
		// TODO Auto-generated method stub
		return find(queryParas.toQueryParas(), count);
	}

	@Override
	public List<M> find(IQuery queryParas) {
		// TODO Auto-generated method stub
		return find(queryParas.toQueryParas());
	}

	@Override
	public Page<M> paginate(IQuery queryParas) {
		// TODO Auto-generated method stub
		return paginate(queryParas.toQueryParas());
	}

	@Override
	public Page<M> paginate(IQuery queryParas, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return paginate(queryParas.toQueryParas(), offset, limit);
	}

	@Override
	public Object query(IQuery queryParas) {
		// TODO Auto-generated method stub
		return query(queryParas.toQueryParas());
	}

	@Override
	public Long count(IQuery queryParas) {
		// TODO Auto-generated method stub
		return count(queryParas.toQueryParas());
	}

	@Override
	public int delete(IQuery queryParas) {
		// TODO Auto-generated method stub
		return delete(queryParas.toQueryParas());
	}

	@Override
	public int update(Record record, IQuery queryParas) {
		// TODO Auto-generated method stub
		return update(record, queryParas.toQueryParas());
	}
	
	
}