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
package com.lambkit.common.model;

import com.jfinal.core.JFinal;
import com.jfinal.core.converter.TypeConverter;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.lambkit.common.exception.LambkitAssert;
import com.lambkit.common.exception.LambkitException;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.cache.CacheManager;
import com.lambkit.core.event.EventKit;
import com.lambkit.db.dialect.IModelDialect;
import com.lambkit.db.sql.QueryParas;
import com.lambkit.db.sql.column.Column;
import com.lambkit.db.sql.column.Columns;
import com.lambkit.db.sql.column.Example;
import com.lambkit.db.sql.condition.Conditions;
import com.lambkit.db.sql.condition.ConditionBuilder;
import com.lambkit.db.sql.condition.ConditionQuery;

import io.zbus.kit.JsonKit;

import java.text.ParseException;
import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LambkitModel<M extends LambkitModel<M>> extends Model<M> implements IBean {

	private static final long serialVersionUID = 1002554886565298306L;

	public static final String AUTO_COPY_MODEL = "_auto_copy_model_";
	
    private static final String COLUMN_CREATED = "created";
    private static final String COLUMN_MODIFIED = "modified";

    /**
     * 是否启用自动缓存
     */
    private boolean cacheEnable = true;
    private transient int cacheTime = 60 * 60 * 24; // 1day

    /**
     * 添加数据到缓存
     *
     * @param key
     * @param value
     */
    public void putCache(Object key, Object value) {
    	CacheManager.me().getCache().put(tableName(), key, value);
    }

    /**
     * 获取缓存中的数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCache(Object key) {
        return CacheManager.me().getCache().get(tableName(), key);
    }

    /**
     * 获取缓存中的数据 ， 如果缓存不存在，则通过dataloader 去加载
     *
     * @param key
     * @param dataloader
     * @param <T>
     * @return
     */
    public <T> T getCache(Object key, IDataLoader dataloader) {
        return CacheManager.me().getCache().get(tableName(), key, dataloader);
    }
    
    /**
     * 移除缓存数据
     *
     * @param key
     */
    public void removeCache(Object key) {
        if (key == null) return;
        CacheManager.me().getCache().remove(tableName(), key);
    }


    /**
     * 复制一个新的model
     * 主要是用在 从缓存取出数据的时候，如果直接修改，在ehcache会抛异常
     * 如果要对model进行修改，可以先copy一份新的，然后再修改
     *
     * @return
     */
	public M copy() {
        M m = null;
        try {
            m = (M) _getUsefulClass().newInstance();
            m._setAttrs(this._getAttrs());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return m;
    }
    
    /**
     * 在 RPC 传输的时候，通过 Controller 传入到Service
     * 不同的序列化方案 可能导致 getModifyFlag 并未设置，可能造成无法保存到数据库
     * 因此需要 通过这个方法 拷贝数据库对于字段，然后再进行更新或保存
     *
     * @return
     */
    public M copyModel() {
        M m = null;
        try {
            m = (M) _getUsefulClass().newInstance();
            Table table = TableMapping.me().getTable(_getUsefulClass());
            if (table == null) {
                throw new LambkitException("can't get table of " + _getUsefulClass() + " , maybe config incorrect");
            }
            Set<String> attrKeys = table.getColumnTypeMap().keySet();
            for (String attrKey : attrKeys) {
                Object o = this.get(attrKey);
                if (o != null) {
                    m.set(attrKey, o);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return m;
    }


    /**
     * 可以再DAO中调用此方法使用proxy数据源进行连接数据库
     * 例如：DAO.useProxy().findById("123")
     * 注意：使用此方法，需要配置名称为 proxy 的数据源
     *
     * @return
     */
    public M useProxy() {
        M proxy = get("__proxy__");
        if (proxy != null) {
            return proxy;
        }

        proxy = copy().use("proxy").cacheEnable(this.cacheEnable);

        if (proxy._getConfig() == null) {
            proxy.use(null);
        }

        set("__proxy__", proxy);
        return proxy;
    }


    /**
     * 同 useProxy
     *
     * @return
     */
    public M useSlave() {
        M proxy = get("__slave__");
        if (proxy != null) {
            return proxy;
        }

        proxy = copy().use("slave").cacheEnable(this.cacheEnable);

        if (proxy._getConfig() == null) {
            proxy.use(null);
        }

        set("__slave__", proxy);
        return proxy;
    }

    /**
     * 同 useProxy
     *
     * @return
     */
    public M useMaster() {
        M proxy = get("__master__");
        if (proxy != null) {
            return proxy;
        }

        proxy = copy().use("master").cacheEnable(this.cacheEnable);

        if (proxy._getConfig() == null) {
            proxy.use(null);
        }

        set("__master__", proxy);
        return proxy;
    }

    /**
     * 是否启用自动缓存
     *
     * @param enable
     * @return
     */
    public M cacheEnable(boolean enable) {
        this.cacheEnable = enable;
        return (M) this;
    }

    public boolean cacheEnable() {
        return cacheEnable;
    }

    /**
     * 设置默认的缓存时间
     *
     * @param time 缓存时间，单位：秒
     * @return
     */
    public M cacheTime(int time) {
        this.cacheTime = time;
        return (M) this;
    }

    public int cacheTime() {
        return cacheTime;
    }

	public LambkitModel setAttrs(Kv para, String modelName) {
    	modelName = StrKit.notBlank(modelName) ? modelName + "." : "";
    	if (table == null) {
            table = TableMapping.me().getTable(_getUsefulClass());
            if (table == null) {
                throw new LambkitException(String.format("table for class[%s] is null! \n maybe cannot connection to database，please check your propertie files.", _getUsefulClass()));
            }
        }
		Set<String> attrs = table.getColumnTypeMap().keySet();
		TypeConverter converter = TypeConverter.me();
		try {
			for (String attr : attrs) {
				String paraValue = para.getStr(modelName + attr);
				Class<?> colType = table.getColumnType(attr);
				Object value = paraValue != null ? converter.convert(colType, paraValue) : null;
				set(attr, value);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this;
    }
    
	public LambkitModel setAttrs(String jsonString) {
    	return (LambkitModel) JsonKit.parseObject(jsonString, _getUsefulClass());
    }
    
    /*
    public LambkitModel create(Record record) {
    }
    */

    /**
     * 更新或者保存
     * 有主键就更新，没有就保存
     *
     * @return
     */
    public boolean saveOrUpdate() {
        if (null == getPrimaryKeyValue()/*get(getPrimaryKey())*/) {
            return this.save();
        }
        return this.update();
    }


    /**
     * 保存数据
     *
     * @return
     */
    @Override
    public boolean save() {
        if (hasColumn(COLUMN_CREATED) && get(COLUMN_CREATED) == null) {
            set(COLUMN_CREATED, new Date());
        }

        if (getPrimaryKeyCount() ==1 && null == get(getPrimaryKey()) && String.class == getPrimaryType()) {
            set(getPrimaryKey(), StringUtils.uuid());
        }
        Boolean autoCopyModel = get(AUTO_COPY_MODEL);
        boolean saveSuccess = autoCopyModel!=null && autoCopyModel == true ? copyModel().saveNormal() : saveNormal();
        if (saveSuccess) {
            EventKit.sendEvent(addAction(), this);
        }
        return saveSuccess;
    }

    public boolean saveNormal() {
        return super.save();
    }

    /**
     * 删除
     *
     * @return
     */
    @Override
    public boolean delete() {
        boolean deleted = super.delete();
        if (deleted) {
            if (cacheEnable) {
                removeCache(getPrimaryKeyValue()/*get(getPrimaryKey())*/);
            }
            EventKit.sendEvent(deleteAction(), this);
        }
        return deleted;
    }


    /**
     * 根据ID删除
     *
     * @param idValue the id value of the model
     * @return
     */
    @Override
    public boolean deleteById(Object idValue) {
    	boolean deleted = super.deleteById(idValue);
    	 if (deleted) {
             if (cacheEnable) {
                 removeCache(getPrimaryKeyValue()/*get(getPrimaryKey())*/);
             }
             EventKit.sendEvent(deleteAction(), this);
         }
        return deleted;
    }
    
    /**
     * 根据ID删除
     *
     * @param idValue the id value of the model
     * @return
     */
    public boolean deleteById(Object... idValue) {
    	boolean deleted = super.deleteById(idValue);
    	 if (deleted) {
             if (cacheEnable) {
                 removeCache(getPrimaryKeyValue()/*get(getPrimaryKey())*/);
             }
             EventKit.sendEvent(deleteAction(), this);
         }
        return deleted;
    }


    /**
     * 更新
     *
     * @return
     */
    @Override
    public boolean update() {
        if (hasColumn(COLUMN_MODIFIED)) {
            set(COLUMN_MODIFIED, new Date());
        }
        Boolean autoCopyModel = get(AUTO_COPY_MODEL);
        boolean updateSuccess = autoCopyModel!=null && autoCopyModel == true ? copyModel().updateNormal() : updateNormal();
        if (updateSuccess) {
            if (cacheEnable) {
                removeCache(getPrimaryKeyValue());
            }
            if(getPrimaryKeyCount()==1) {
                Object id = get(getPrimaryKey());
                EventKit.sendEvent(updateAction(), findById(id));
            } else {
            	EventKit.sendEvent(updateAction(), this);
            }
        }
        return updateSuccess;
    }
    
    public boolean updateNormal() {
        return super.update();
    }

    protected String addAction() {
        return tableName() + ":add";
    }

    protected String deleteAction() {
        return tableName() + ":delete";
    }

    protected String updateAction() {
        return tableName() + ":update";
    }

    /**
     * 根据ID查找model
     *
     * @param idValue the id value of the model
     * @return
     */
	@Override
    public M findById(final Object idValue) {
        return (M) (cacheEnable ? getCache(idValue, new IDataLoader() {
            @Override
            public Object load() {
                return findByIdWithoutCache(idValue);
            }
        }) : findByIdWithoutCache(idValue));
    }


    public M findByIdWithoutCache(Object idValue) {
        return super.findById(idValue);
    }
    
    public String configName() {
    	return _getConfig().getName();
    }
    
    private IModelDialect getDialect() {
        return (IModelDialect) _getConfig().getDialect();
    }

    /**
     * 根据列名和值，查找1条数据
     *
     * @param column
     * @param value
     * @return
     */
    public M findFirstByCondition(String column, Object value) {
    	return findFirstByCondition(column, value, Conditions.EQUAL);
    }
    
    public M findFirstByCondition(String column, Object value, String queryType) {
    	ConditionBuilder cb = new ConditionBuilder();
    	cb.appendQuery(column, queryType, value);
    	ConditionQuery qb = new ConditionQuery();
    	qb.setBaseTable(tableName());
    	qb.setConditions(cb);
        return findFirst(qb.toQueryParas());
    }
    
    /**
     * 查找全部数据
     *
     * @return
     */
    public List<M> findAllBySql() {
    	ConditionQuery qb = new ConditionQuery();
    	qb.setBaseTable(tableName());
        return find(qb.toQueryParas());
    }
    
    /**
     * 根据列名和值，查找1条数据
     *
     * @param column
     * @param value
     * @return
     */
    public M findFirstByColumn(String column, Object value) {
        String sql = getDialect().forFindByColumns(tableName(), "*", Columns.create(column, value).getList(), null, 1);
        return findFirst(sql, value);
    }
    
    /**
     * 根据 列和值 查询1条数据
     *
     * @param column
     * @return
     */
    public M findFirstByColumn(Column column) {
        String sql = getDialect().forFindByColumns(tableName(), "*", Columns.create(column).getList(), null, 1);
        //return findFirst(sql, column.getValue());
        LinkedList<Object> params = new LinkedList<Object>();
        column.addValueToParam(params);
        return findFirst(sql, params.toArray());
    }

    /**
     * 根据 多列和值，查询1条数据
     *
     * @param columns
     * @return
     */
    public M findFirstByColumns(Columns columns) {
        String sql = getDialect().forFindByColumns(tableName(), "*", columns.getList(), null, 1);
        LinkedList<Object> params = new LinkedList<Object>();

        if (ArrayUtils.isNotEmpty(columns.getList())) {
            for (Column column : columns.getList()) {
                column.addValueToParam(params);
            }
        }
        return findFirst(sql, params.toArray());
    }
    
    public M findFirstByColumns(Columns columns, String orderby) {
        String sql = getDialect().forFindByColumns(tableName(), "*", columns.getList(), orderby, 1);
        LinkedList<Object> params = new LinkedList<Object>();

        if (ArrayUtils.isNotEmpty(columns.getList())) {
            for (Column column : columns.getList()) {
                column.addValueToParam(params);
            }
        }
        return findFirst(sql, params.toArray());
    }


    /**
     * 查找全部数据
     *
     * @return
     */
    public List<M> findAll() {
        String sql = getDialect().forFindByColumns(tableName(), "*", null, null, null);
        return find(sql);
    }
    

    /**
     * 根据列名和值 查询一个列表
     *
     * @param column
     * @param value
     * @param count  最多查询多少条数据
     * @return
     */
    public List<M> findListByCondition(String column, Object value, Integer count) {
        return findListByCondition(column, value, Conditions.EQUAL, count);
    }
    
    public List<M> findListByCondition(String column, Object value, String queryType, Integer count) {
    	ConditionBuilder cb = new ConditionBuilder();
    	cb.appendQuery(column, queryType, value);
    	ConditionQuery qb = new ConditionQuery();
    	qb.setBaseTable(tableName());
    	qb.setConditions(cb);
        return find(qb.toQueryParas(), count);
    }
    
    /**
     * 根据 列信息 查找数据列表
     *
     * @param column
     * @param count
     * @return
     */
    public List<M> findListByCondition(String column, Object value) {
        return findListByCondition(column, value, null);
    }
    
    /**
     * 根据列名和值 查询一个列表
     *
     * @param column
     * @param value
     * @param count  最多查询多少条数据
     * @return
     */
    public List<M> findListByColumn(String column, Object value, Integer count) {
        List<Column> columns = new ArrayList<>();
        columns.add(Column.create(column, value));
        return findListByColumns(columns, count);
    }


    /**
     * 根据 列信息 查找数据列表
     *
     * @param column
     * @param count
     * @return
     */
    public List<M> findListByColumn(Column column, Integer count) {
        return findListByColumns(Columns.create(column).getList(), count);
    }


    public List<M> findListByColumn(String column, Object value) {
        return findListByColumn(column, value, null);
    }

    public List<M> findListByColumn(Column column) {
        return findListByColumn(column, null);
    }

    public List<M> findListByColumns(List<Column> columns) {
        return findListByColumns(columns, null, null);
    }

    public List<M> findListByColumns(List<Column> columns, String orderBy) {
        return findListByColumns(columns, orderBy, null);
    }

    public List<M> findListByColumns(List<Column> columns, Integer count) {
        return findListByColumns(columns, null, count);
    }


    public List<M> findListByColumns(Columns columns) {
        return findListByColumns(columns.getList());
    }

    public List<M> findListByColumns(Columns columns, String orderBy) {
        return findListByColumns(columns.getList(), orderBy);
    }

    public List<M> findListByColumns(Columns columns, Integer count) {
        return findListByColumns(columns.getList(), count);
    }

    public List<M> findListByColumns(Columns columns, String orderBy, Integer count) {
        return findListByColumns(columns.getList(), orderBy, count);
    }


    /**
     * 根据列信心查询列表
     *
     * @param columns
     * @param orderBy
     * @param count
     * @return
     */
    public List<M> findListByColumns(List<Column> columns, String orderBy, Integer count) {
        LinkedList<Object> params = new LinkedList<Object>();

        if (ArrayUtils.isNotEmpty(columns)) {
            for (Column column : columns) {
                column.addValueToParam(params);
            }
        }

        String sql = getDialect().forFindByColumns(tableName(), "*", columns, orderBy, count);
        return params.isEmpty() ? find(sql) : find(sql, params.toArray());
    }

    /**
     * 根据某列信息，分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @param column
     * @return
     */
    public Page<M> paginateByColumn(int pageNumber, int pageSize, Column column) {
        return paginateByColumns(pageNumber, pageSize, Columns.create(column).getList(), null);
    }


    /**
     * 根据某列信息，分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @param column
     * @return
     */
    public Page<M> paginateByColumn(int pageNumber, int pageSize, Column column, String orderBy) {
        return paginateByColumns(pageNumber, pageSize, Columns.create(column).getList(), orderBy);
    }


    /**
     * 根据列信息，分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @param columns
     * @return
     */
    public Page<M> paginateByColumns(int pageNumber, int pageSize, List<Column> columns) {
        return paginateByColumns(pageNumber, pageSize, columns, null);
    }


    /**
     * 根据列信息，分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @param columns
     * @param orderBy
     * @return
     */
    public Page<M> paginateByColumns(int pageNumber, int pageSize, List<Column> columns, String orderBy) {
        String selectPartSql = getDialect().forPaginateSelect("*");
        String fromPartSql = getDialect().forPaginateFrom(tableName(), columns, orderBy);

        LinkedList<Object> params = new LinkedList<Object>();

        if (ArrayUtils.isNotEmpty(columns)) {
            for (Column column : columns) {
                column.addValueToParam(params);
            }
        }
        return params.isEmpty() ? paginate(pageNumber, pageSize, selectPartSql, fromPartSql)
                : paginate(pageNumber, pageSize, selectPartSql, fromPartSql, params.toArray());
    }
    
    
    /**
     * 分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<M> paginate(int pageNumber, int pageSize, String orderBy) {
        return paginateByColumns(pageNumber, pageSize, null, orderBy);
    }
    
    
    public M findFirst(Example example) {
    	 SqlPara sqlPara = getDialect().forFindByExample(example, null);
         return findFirst(sqlPara);
    }
    
    public List<M> find(Example example) {
    	SqlPara sqlPara = getDialect().forFindByExample(example, null);
        return find(sqlPara);
    }
    
    public List<M> find(Example example, Integer count) {
    	SqlPara sqlPara = getDialect().forFindByExample(example, count);
        return find(sqlPara);
    }
    
    public Page<M> paginate(int pageNumber, int pageSize, Example example) {
    	SqlPara sqlPara = getDialect().forPaginateByExample(example);
        return paginate(pageNumber, pageSize, sqlPara);
    }
    
    /**
     * 分页查询数据
     *
     * @param pageNumber
     * @param pageSize
     * @return
     
    public Page<M> paginate(int pageNumber, int pageSize, String orderBy) {
    	QueryBuilder qb = new QueryBuilder();
    	qb.setBaseTable(getTableName());
    	qb.setOrderBy(orderBy);
    	qb.setPageNumber(pageNumber);
    	qb.setPageSize(pageSize);
        return paginate(qb.build());
    }
*/
    /**
     * 分页查询数据
     * @return
     */
    public Page<M> paginate(QueryParas q) {
		if(q==null) return null;
		Object[] paras = q.getParas();
		if(paras==null) {
			return paginate(q.getPageNumber(), q.getPageSize(), q.getSelect(), q.getSqlExceptSelect());
		}
		/*
		System.out.print("[");
		for (Object object : paras) {
			System.out.print(object);
			System.out.print(",");
		}
		System.out.println("]");
		*/
		return paginate(q.getPageNumber(), q.getPageSize(), q.getSelect(), q.getSqlExceptSelect(), paras);
	}
    
    public List<M> find(QueryParas q, Integer count) {
    	if(q==null) return null;
    	if(count==null) return find(q);
		if(q.getParas()==null) {
			return find(q.getSql());
		}
		return find(q.getSql(), q.getParas());
    }
	
	public List<M> find(QueryParas q) {
		if(q==null) return null;
		if(q.getParas()==null) {
			return find(q.getSql());
		}
		return find(q.getSql(), q.getParas());
	}
	
	public M findFirst(QueryParas q) {
		if(q==null) return null;
		if(q.getParas()==null) {
			return findFirst(q.getSql());
		}
		return findFirst(q.getSql(), q.getParas());
	}
	
	public Object query(QueryParas q) {
		if(q.getPageNumber()==0) {
			return findFirst(q);
		} else {
			if(q.getPageSize()==0) {
				return find(q);
			} else {
				return paginate(q);
			}
		}
	}

    public String cacheName() {
        return tableName();
    }

    private transient Table table;

    public String tableName() {
        if (table == null) {
            table = TableMapping.me().getTable(_getUsefulClass());
            if (table == null) {
                throw new LambkitException(String.format("table for class[%s] is null! \n maybe cannot connection to database，please check your propertie files.", _getUsefulClass()));
            }
        }
        return table.getName();
    }

    private transient String primaryKey;

    protected int getPrimaryKeyCount() {
        return getPrimaryKeys().length;
    }
    
    protected Object getPrimaryKeyValue() {
        if(getPrimaryKeyCount()==1) {
        	return get(getPrimaryKey());
        } else if(getPrimaryKeyCount()>1) {
        	String res="";
        	String[] primaryKeys = getPrimaryKeys();
            for (int i = 0; i < primaryKeys.length; i++) {
            	res += ","+get(primaryKeys[0]);
			}
            res = res.substring(1);
            return res;
        } else {
        	return primaryKey;
        }
    }
    
    protected String getPrimaryKey() {
        if (primaryKey != null) {
            return primaryKey;
        }
        String[] primaryKeys = getPrimaryKeys();
        if (null != primaryKeys && primaryKeys.length == 1) {
            primaryKey = primaryKeys[0];
        }
        
        LambkitAssert.assertTrue(primaryKey != null, String.format("get PrimaryKey is error in[%s]", getClass()));
        return primaryKey;
    }

    private transient Class<?> primaryType;

    public Class<?> getPrimaryType() {
        if (primaryType == null) {
            primaryType = TableMapping.me().getTable(_getUsefulClass()).getColumnType(getPrimaryKey());
        }
        return primaryType;
    }

    protected String[] getPrimaryKeys() {
        Table t = TableMapping.me().getTable(_getUsefulClass());
        if (t == null) {
            throw new RuntimeException("can't get table of " + _getUsefulClass() + " , maybe install incorrect");
        }
        return t.getPrimaryKey();
    }

    protected boolean hasColumn(String columnLabel) {
        return TableMapping.me().getTable(_getUsefulClass()).hasColumnLabel(columnLabel);
    }

    // -----------------------------Override----------------------------
    /*
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Class<? extends LambkitModel> _getUsefulClass() {
		Class c = getClass();
		return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();
	}
    */
    @Override
    public Page<M> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect) {
        return super.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    @Override
    public Page<M> paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras) {
        return super.paginate(pageNumber, pageSize, select, sqlExceptSelect, paras);
    }

    @Override
    public Page<M> paginate(int pageNumber, int pageSize, boolean isGroupBySql, String select, String sqlExceptSelect,
                            Object... paras) {
        return super.paginate(pageNumber, pageSize, isGroupBySql, select, sqlExceptSelect, paras);
    }

    @Override
    public List<M> find(String sql, Object... paras) {
        debugPrintParas(paras);
        return super.find(sql, paras);
    }

    @Override
    public M findFirst(String sql, Object... paras) {
        debugPrintParas(paras);
        return super.findFirst(sql, paras);
    }

    @Override
    public List<M> findByCache(String cacheName, Object key, String sql, Object... paras) {
        return super.findByCache(cacheName, key, sql, paras);
    }


    @Override
    public M findFirstByCache(String cacheName, Object key, String sql, Object... paras) {
        return super.findFirstByCache(cacheName, key, sql, paras);
    }

    @Override
    public Page<M> paginateByCache(String cacheName, Object key, int pageNumber, int pageSize, String select,
                                   String sqlExceptSelect, Object... paras) {
        return super.paginateByCache(cacheName, key, pageNumber, pageSize, select, sqlExceptSelect, paras);
    }

    @Override
    public Page<M> paginateByCache(String cacheName, Object key, int pageNumber, int pageSize, boolean isGroupBySql,
                                   String select, String sqlExceptSelect, Object... paras) {
        return super.paginateByCache(cacheName, key, pageNumber, pageSize, isGroupBySql, select,
                sqlExceptSelect, paras);
    }

    @Override
    public Page<M> paginateByCache(String cacheName, Object key, int pageNumber, int pageSize, String select,
                                   String sqlExceptSelect) {
        return super.paginateByCache(cacheName, key, pageNumber, pageSize, select, sqlExceptSelect);
    }

    private void debugPrintParas(Object... objects) {
        if (JFinal.me().getConstants().getDevMode()) {
            System.out.println("\r\n---------------Paras: " + Arrays.toString(objects) + "----------------");
        }
    }

    public Map<String, Object> _getAttrsAsMap() {
        return _getAttrs();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof LambkitModel)) {
            return false;
        }

        //Object id = ((LambkitModel) o).get(getPrimaryKey());
        Object id = getPrimaryKeyValue();
        if (id == null) {
            return false;
        }
        return id.equals(getPrimaryKeyValue());
        //return id.equals(get(getPrimaryKey()));
    }
}
