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

import org.apache.commons.lang.StringUtils;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.common.model.LambkitModel;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.db.dialect.LambkitDialect;
import com.lambkit.db.sql.IQuery;
import com.lambkit.db.sql.Query;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;

/**
 * 实现LambkitService抽象类
 */
public abstract class LambkitModelServiceImpl<M extends LambkitModel<M>> implements LambkitService<M> {

	public abstract M dao();
	
	public String getTableName() {
		return dao().tableName();
	}
	
	public M findById(Object idValue) {
		return dao().findById(idValue);
	}
	
	@Override
	public M findByPrimaryKey(Object id) {
		return dao().findById(id);
	}
	
	public M findFirst(QueryParas queryParas) {
		return dao().findFirst(queryParas);
	}
	
	@Override
	public M findFirst(Example example) {
		return dao().findFirst(example);
	}
	
	@Override
	public M findFirst(Columns columns) {
		// TODO Auto-generated method stub
		return dao().findFirstByColumns(columns);
	}
	
	@Override
	public M findFirst(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().findFirstByColumns(columns, orderby);
	}
	
	@Override
	public M findFirstByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return dao().findFirstByColumns(columns);
	}
	
	@Override
	public M findFirstByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().findFirstByColumns(columns, orderby);
	}
	
	public List<M> find(QueryParas queryParas, Integer count) {
		return dao().find(queryParas, count);
	}
	
	@Override
	public List<M> find(Example example, Integer count) {
		// TODO Auto-generated method stub
		return dao().find(example, count);
	}
	
	@Override
	public List<M> find(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, count);
	}
	
	@Override
	public List<M> find(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, orderby, count);
	}
	
	@Override
	public List<M> findListByColumns(Columns columns, Integer count) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, count);
	}
	
	@Override
	public List<M> findListByColumns(Columns columns, String orderby, Integer count) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, orderby, count);
	}
	
	public List<M> find(QueryParas queryParas) {
		return dao().find(queryParas);
	}
	
	@Override
	public List<M> find(Example example) {
		// TODO Auto-generated method stub
		return dao().find(example);
	}
	
	@Override
	public List<M> find(Columns columns) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns);
	}
	
	@Override
	public List<M> find(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, orderby);
	}
	
	@Override
	public List<M> findListByColumns(Columns columns) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns);
	}
	
	@Override
	public List<M> findListByColumns(Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().findListByColumns(columns, orderby);
	}

	 /**
     * 分页查询数据
     * @return
     */
	public Page<M> paginate(QueryParas queryParas) { 
		return dao().paginate(queryParas);
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Example example) {
		// TODO Auto-generated method stub
		return dao().paginate(pageNumber, pageSize, example);
	}
	
	@Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return dao().paginateByColumns(pageNumber, pageSize, columns.getList());
	}
	

    @Override
	public Page<M> paginate(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().paginateByColumns(pageNumber, pageSize, columns.getList(), orderby);
	}
    
    @Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns) {
		// TODO Auto-generated method stub
		return dao().paginateByColumns(pageNumber, pageSize, columns.getList());
	}
    
    @Override
	public Page<M> paginateByColumns(Integer pageNumber, Integer pageSize, Columns columns, String orderby) {
		// TODO Auto-generated method stub
		return dao().paginateByColumns(pageNumber, pageSize, columns.getList(), orderby);
	}
    

    /**
     * offet page
     * @param queryParas
     * @param offset
     * @param limit
     * @return
     */
	public Page<M> paginate(QueryParas queryParas, Integer offset, Integer limit) { 
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		queryParas.setPageSize(pageSize);
		queryParas.setPageNumber(pageNumber);
		return dao().paginate(queryParas);
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
		return paginate(pageNumber, pageSize, columns);
	}
	

    @Override
	public Page<M> paginate(Columns columns, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		return paginate(pageNumber, pageSize, columns, orderby);
	}
    
    @Override
	public Page<M> paginateByColumns(Columns columns, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		return paginate(pageNumber, pageSize, columns);
	}
    
    @Override
	public Page<M> paginateByColumns(Columns columns, String orderby, Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		int pageSize = limit;
		int pageNumber = offset / pageSize + 1;
		return paginate(pageNumber, pageSize, columns, orderby);
	}
    
    
    /**
     * findFirst or Find or paginate
     * @param queryParas
     * @return
     */
	public Object query(QueryParas queryParas) {
		if(queryParas.getPageNumber()==0) {
			return findFirst(queryParas);
		} else {
			if(queryParas.getPageSize()==0) {
				return find(queryParas);
			} else {
				return paginate(queryParas);
			}
		}
	}
    
	/**
	 * 获取数量	
	 * @param queryParas
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
	
	public int delete(QueryParas queryParas) {
		return Query.delete(configName(), queryParas);
	}
	
	@Override
	public int delete(Example example) {
		SqlPara sqlPara = exampleToDeleteSqlPara(example);
		if(StrKit.notBlank(dao().configName())) {
			return Db.use(dao().configName()).delete(sqlPara.getSql(), sqlPara.getPara());
		} else {
			return Db.delete(sqlPara.getSql(), sqlPara.getPara());
		}
	}
	
	@Override
	public int delete(Columns columns) {
		return delete(Example.create(getTableName(), columns));
	}

	@Override
	public int update(Record record, QueryParas queryParas) {
		SqlPara sqlPara = getDialect().forUpdate(record, getTableName(), queryParas);
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
		return update(record, Example.create(getTableName(), columns));
	}

	@Override
	public boolean updateByPrimaryKey(String primaryKey, Record record) { 
		if(StrKit.notBlank(configName())) {
			return Db.use(configName()).update(getTableName(), primaryKey, record);
		} else {
			return Db.update(getTableName(), primaryKey, record);
		}
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
        LambkitModel m = findById(id);
        if (m != null) {
            m = m.copy();
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
        LambkitModel m = findById(id);
        if (m != null) {
            m = m.copy();
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
    
    private String configName() {
    	return dao().configName();
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
	
	private LambkitDialect getDialect() {
    	if(StrKit.notBlank(dao().configName())) {
    		return (LambkitDialect) DbKit.getConfig(dao().configName()).getDialect();
    	} else {
    		return (LambkitDialect) DbKit.getConfig().getDialect();
    	}
	}
}