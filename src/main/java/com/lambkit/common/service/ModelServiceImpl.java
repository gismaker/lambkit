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

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.db.dialect.IModelDialect;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.Query;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

/**
 * 实现LambkitService抽象类
 */
public abstract class ModelServiceImpl<M extends Model<M>> implements LambkitService<M> {
	
	public abstract M dao();
	
	public abstract String configName();
	
	public abstract String getTableName();
	
	public M findById(Object idValue) {
		return dao().findById(idValue);
	}
	
	@Override
	public M findByPrimaryKey(Object id) {
		return dao().findById(id);
	}
	
	public M findFirst(QueryParas query) {
		if(query==null) return null;
		if(query.getParas()==null) {
			return dao().findFirst(query.getSql());
		}
		return dao().findFirst(query.getSql(), query.getParas());
	}
	
	@Override
	public M findFirst(Example example) {
		// TODO Auto-generated method stub
		return dao().findFirst(exampleToSqlPara(example, null));
	}
	
	@Override
	public M findFirst(Columns columns) {
		// TODO Auto-generated method stub
		return findFirst(Example.create(getTableName(), columns));
	}

	
	@Override
	public M findFirst(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return findFirst(Example.create(getTableName(), columns).setOrderBy(orderby));
	}
	
	@Override
	public M findFirstByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return findFirst(Example.create(getTableName(), columns));
	}
	
	@Override
	public M findFirstByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return findFirst(Example.create(getTableName(), columns).setOrderBy(orderby));
	}	
	
	public List<M> find(QueryParas query, Integer count) {
		if(query==null) return null;
    	if(count==null) return find(query);
		if(query.getParas()==null) {
			return dao().find(query.getSql());
		}
		return dao().find(query.getSql(), query.getParas());
	}
	
	public List<M> find(QueryParas query) {
		if(query==null) return null;
		if(query.getParas()==null) {
			return dao().find(query.getSql());
		}
		return dao().find(query.getSql(), query.getParas());
	}
	
	@Override
	public List<M> find(Example example) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(example, null));
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
	public List<M> find(Example example, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(example, count));
	}
	
	@Override
	public List<M> find(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(Example.create(getTableName(), columns), count));
	}

	@Override
	public List<M> find(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(Example.create(getTableName(), columns).setOrderBy(orderby), count));
	}
	
	@Override
	public List<M> findListByColumns(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(Example.create(getTableName(), columns), count));
	}
	
	@Override
	public List<M> findListByColumns(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(exampleToSqlPara(Example.create(getTableName(), columns).setOrderBy(orderby), count));
	}
	
	 /**
     * 分页查询数据
     * @return
     */
	public Page<M> paginate(QueryParas query) { 
		if(query==null) return null;
		Object[] paras = query.getParas();
		if(paras==null) {
			return dao().paginate(query.getPageNumber(), query.getPageSize(), query.getSelect(), query.getSqlExceptSelect());
		}
		return dao().paginate(query.getPageNumber(), query.getPageSize(), query.getSelect(), query.getSqlExceptSelect(), paras);
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Example example) {
		// TODO Auto-generated method stub
		return dao().paginate(pageNumber, pageSize, exampleToSqlParaForPaginate(example));
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return dao().paginate(pageNumber, pageSize, exampleToSqlParaForPaginate(Example.create(getTableName(), columns)));
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

    /**
     * offet page
     * @param query
     * @param offset
     * @param limit
     * @return
     */
	public Page<M> paginate(QueryParas query, Integer offset, Integer limit) { 
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		query.setPageSize(pageSize);
		query.setPageNumber(pageNumber);
		return paginate(query);
	}
	
	@Override
	public Page<M> paginate(Example example, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		return paginate(pageNumber, pageSize, example);
	}
	
	@Override
	public Page<M> paginate(Columns columns, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		return paginate(pageNumber, pageSize, Example.create(getTableName(), columns));
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
    
    /**
     * findFirst or Find or paginate
     * @param query
     * @return
     */
	public Object query(QueryParas query) {
		if(query.getPageNumber()==0) {
			return findFirst(query);
		} else {
			if(query.getPageSize()==0) {
				return find(query);
			} else {
				return paginate(query);
			}
		}
	}
    
	/**
	 * 获取数量	
	 * @param query
	 * @return
	 */
	public Long count(QueryParas queryParas) {
		queryParas.setSelect("select count(*) ");
		return Query.count(configName(), queryParas);
	}
	
	public Long count(Example example) {
		example.setSelectSql(" count(*) ");
		SqlPara sqlPara = exampleToSqlPara(example, null);
		DbPro dp;
		if(StrKit.isBlank(configName())) {
			dp = Db.use();
		} else {
			dp = Db.use(configName());
		}
		if(sqlPara.getPara()==null) {
			return dp.queryLong(sqlPara.getSql());
		}
		return dp.queryLong(sqlPara.getSql(), sqlPara.getPara());
	}
	
	public Long count(Columns columns) {
		return count(Example.create(getTableName(), columns));
	}
	
	public boolean insert(Record record) { 
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).save(getTableName(), record);
		} else {
			return Db.save(getTableName(), record);
		}
	}
	
	public boolean insert(String primaryKey, Record record) { 
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).save(getTableName(), primaryKey, record);
		} else {
			return Db.save(getTableName(), primaryKey, record);
		}
	}

	public boolean deleteById(Object idValue) {
		return dao().deleteById(idValue);
	}
	
	public int deleteByPrimaryKey(Object id) {
		return dao().deleteById(id) ? 1 : -1;
	}
	
	public int deleteByPrimaryKeys(String ids) {
		if (StringUtils.isBlank(ids)) {
			return 0;
		}
		String[] idArray = ids.split(",");
		int count = 0;
		for (String idStr : idArray) {
			if (StringUtils.isBlank(idStr)) {
				continue;
			}
			Long id = Long.parseLong(idStr);
			int result = deleteByPrimaryKey(id);
			count += result;
		}
		return count;
	}
	
	public int delete(QueryParas query) {
		return Query.delete(configName(), query);
	}
	
	@Override
	public int delete(Example example) {
		SqlPara sqlPara = exampleToDeleteSqlPara(example);
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).delete(sqlPara.getSql(), sqlPara.getPara());
		} else {
			return Db.delete(sqlPara.getSql(), sqlPara.getPara());
		}
	}
	
	@Override
	public int delete(Columns columns) {
		return delete(Example.create(getTableName(), columns));
	}

	@Override
	public int update(Record record, QueryParas query) {
		SqlPara sqlPara = getDialect().forUpdate(record, getTableName(), query);
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).update(sqlPara);
		} else {
			return Db.update(sqlPara);
		}
	}
	
	@Override
	public int update(Record record, Example example) {
		SqlPara sqlPara = getDialect().forUpdateByExample(record, example);
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).update(sqlPara);
		} else {
			return Db.update(sqlPara);
		}
	}
	
	@Override
	public int update(Record record, Columns columns) {
		// TODO Auto-generated method stub
		return update(record, Example.create(getTableName(), columns));
	}
	
	@Override
	public boolean update(Record record) {
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).update(getTableName(), record);
		} else {
			return Db.update(getTableName(), record);
		}
	}
	
	@Override
	public boolean updateByPrimaryKey(String primaryKey, Record record) {
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).update(getTableName(), primaryKey, record);
		} else {
			return Db.update(getTableName(), primaryKey, record);
		}
	};
	
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

	public void join(Page<? extends Model> page, String joinOnField) {
        join(page.getList(), joinOnField);
    }

    public void join(Page<? extends Model> page, String joinOnField, String[] attrs) {
        join(page.getList(), joinOnField, attrs);
    }


    public void join(List<? extends Model> models, String joinOnField) {
        if (ArrayUtils.isNotEmpty(models)) {
            for (Model m : models) {
                join(m, joinOnField);
            }
        }
    }


    public void join(List<? extends Model> models, String joinOnField, String[] attrs) {
        if (ArrayUtils.isNotEmpty(models)) {
            for (Model m : models) {
                join(m, joinOnField, attrs);
            }
        }
    }


    public void join(Page<? extends Model> page, String joinOnField, String joinName) {
        join(page.getList(), joinOnField, joinName);
    }


    public void join(List<? extends Model> models, String joinOnField, String joinName) {
        if (ArrayUtils.isNotEmpty(models)) {
            for (Model m : models) {
                join(m, joinOnField, joinName);
            }
        }
    }

    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs) {
        join(page.getList(), joinOnField, joinName, attrs);
    }


    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs) {
        if (ArrayUtils.isNotEmpty(models)) {
            for (Model m : models) {
                join(m, joinOnField, joinName, attrs);
            }
        }
    }

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model       要添加到的model
     * @param joinOnField model对于的关联字段
     */
    public void join(Model model, String joinOnField) {
        if (model == null)
            return;
        String id = model.getStr(joinOnField);
        if (id == null) {
            return;
        }
        Model m = findById(id);
        if (m != null) {
            model.put(StrKit.firstCharToLowerCase(m.getClass().getSimpleName()), m);
        }
    }

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param attrs
     */
    public void join(Model model, String joinOnField, String[] attrs) {
        if (model == null)
            return;
        String id = model.getStr(joinOnField);
        if (id == null) {
            return;
        }
        Model m = findById(id);
        if (m != null) {
            m = copy(m);
            m.keep(attrs);
            model.put(StrKit.firstCharToLowerCase(m.getClass().getSimpleName()), m);
        }
    }


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     */
    public void join(Model model, String joinOnField, String joinName) {
        if (model == null)
            return;
        String id = model.getStr(joinOnField);
        if (id == null) {
            return;
        }
        Model m = findById(id);
        if (m != null) {
            model.put(joinName, m);
        }
    }


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     * @param attrs
     */
    public void join(Model model, String joinOnField, String joinName, String[] attrs) {
        if (model == null)
            return;
        String id = model.getStr(joinOnField);
        if (id == null) {
            return;
        }
        Model m = findById(id);
        if (m != null) {
            m = copy(m);
            m.keep(attrs);
            model.put(joinName, m);
        }

    }


    public void keep(Model model, String... attrs) {
        if (model == null) {
            return;
        }

        model.keep(attrs);
    }

    public void keep(List<? extends Model> models, String... attrs) {
        if (ArrayUtils.isNotEmpty(models)) {
            for (Model m : models) {
                keep(m, attrs);
            }
        }
    }
    
    public Model copy(Model model) {
    	Model m = null;
        try {
            m = getModelClass().newInstance();
            m._setAttrs(model);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return m;
    }
    
    /**
	 * 获取类泛型class
	 * @return
	 */
	public Class<M> getModelClass() {
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private SqlPara exampleToDeleteSqlPara(Example example) {
    	return getDialect().forDeleteByExample(example);
	}
    
    private SqlPara exampleToSqlPara(Example example, Object limit) {
    	return getDialect().forFindByExample(example, limit);
	}
	
	private SqlPara exampleToSqlParaForPaginate(Example example) {
		return getDialect().forPaginateByExample(example);
	}
	
	private IModelDialect getDialect() {
    	if(StrKit.notBlank(configName())) {
    		return (IModelDialect) DbKit.getConfig(configName()).getDialect();
    	} else {
    		return (IModelDialect) DbKit.getConfig().getDialect();
    	}
	}
}
