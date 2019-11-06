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
package com.lambkit.db.datasource;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.kit.StrKit;
import com.lambkit.common.util.SecurityUtils;


public class DataSourceConfig {
    public static final String NAME_DEFAULT = "main";

    public static final String TYPE_MYSQL = "mysql";
    public static final String TYPE_ORACLE = "oracle";
    public static final String TYPE_SQLSERVER = "sqlserver";
    public static final String TYPE_SQLITE = "sqlite";
    public static final String TYPE_ANSISQL = "ansisql";
    public static final String TYPE_POSTGRESQL = "postgresql";

    private String name;
    private String type = TYPE_MYSQL;
    private String url;
    private String user;
    private String password;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String connectionInitSql;
    private String poolName;
    private boolean cachePrepStmts = true;
    private int prepStmtCacheSize = 500;
    private int prepStmtCacheSqlLimit = 2048;
    private int maximumPoolSize = 100;
    
    private String dbname;
    private String schema;
    
    private boolean druidMergeSql = true;
	private boolean druidLogSlowSql = true;
	private int druidSlowSqlMillis = 3000;

    private String sqlTemplatePath;
    private String sqlTemplate;
    private String table;
    private String excludeTable;
    private String factory;// = DruidDataSourceFactory.class.getName();

    private boolean shardingEnable = false;
    private String shardingDatabase;


    private List<DataSourceConfig> childDatasourceConfigs;

    /**
     * 是否需要添加到映射
     * 在一个表有多个数据源的情况下，应该只需要添加一个映射就可以了，
     * 添加映射：默认为该model的数据源，
     * 不添加映射：通过 model.use("xxx").save()这种方式去调用该数据源
     */
    private boolean needAddMapping = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
    	return SecurityUtils.decodePassword(password, name);
        //return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionInitSql() {
        return connectionInitSql;
    }

    public void setConnectionInitSql(String connectionInitSql) {
        this.connectionInitSql = connectionInitSql;
    }

    public boolean isCachePrepStmts() {
        return cachePrepStmts;
    }

    public void setCachePrepStmts(boolean cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
    }

    public int getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public void setPrepStmtCacheSize(int prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
    }

    public int getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit;
    }

    public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public boolean isConfigOk() {
        return (StrKit.notBlank(url) && StrKit.notBlank(user))
                || shardingEnable == true;
    }


    public boolean isMysqlType() {
        return TYPE_MYSQL.equals(getType());
    }

    public boolean isOracleType() {
        return TYPE_ORACLE.equals(getType());
    }

    public boolean isSqlServerType() {
        return TYPE_SQLSERVER.equals(getType());
    }

    public boolean isSqliteType() {
        return TYPE_SQLITE.equals(getType());
    }

    public boolean isAnsiSqlType() {
        return TYPE_ANSISQL.equals(getType());
    }

    public String getSqlTemplatePath() {
        return sqlTemplatePath;
    }

    public void setSqlTemplatePath(String sqlTemplatePath) {
        this.sqlTemplatePath = sqlTemplatePath;
    }

    public String getSqlTemplate() {
        return sqlTemplate;
    }

    public void setSqlTemplate(String sqlTemplate) {
        this.sqlTemplate = sqlTemplate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getExcludeTable() {
        return excludeTable;
    }

    public void setExcludeTable(String excludeTable) {
        this.excludeTable = excludeTable;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public boolean isNeedAddMapping() {
        return needAddMapping;
    }

    public void setNeedAddMapping(boolean needAddMapping) {
        this.needAddMapping = needAddMapping;
    }

    public boolean isShardingEnable() {
        return shardingEnable;
    }

    public void setShardingEnable(boolean shardingEnable) {
        this.shardingEnable = shardingEnable;
    }

    public String getShardingDatabase() {
        return shardingDatabase;
    }

    public void setShardingDatabase(String shardingDatabase) {
        this.shardingDatabase = shardingDatabase;
    }

    public List<DataSourceConfig> getChildDatasourceConfigs() {
        return childDatasourceConfigs;
    }

    public void setChildDatasourceConfigs(List<DataSourceConfig> childDatasourceConfigs) {
        this.childDatasourceConfigs = childDatasourceConfigs;
    }

    public void addChildDatasourceConfig(DataSourceConfig config) {
        if (this.childDatasourceConfigs == null) {
            this.childDatasourceConfigs = new ArrayList<>();
        }

        this.childDatasourceConfigs.add(config);
    }

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getSchema() {
		if(StrKit.isBlank(schema)) {
			if(type.equals(TYPE_POSTGRESQL)) {
				schema = "public";
			}
		}
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public int getDruidSlowSqlMillis() {
		return druidSlowSqlMillis;
	}

	public void setDruidSlowSqlMillis(int druidSlowSqlMillis) {
		this.druidSlowSqlMillis = druidSlowSqlMillis;
	}

	public boolean isDruidLogSlowSql() {
		return druidLogSlowSql;
	}

	public void setDruidLogSlowSql(boolean druidLogSlowSql) {
		this.druidLogSlowSql = druidLogSlowSql;
	}

	public boolean isDruidMergeSql() {
		return druidMergeSql;
	}

	public void setDruidMergeSql(boolean druidMergeSql) {
		this.druidMergeSql = druidMergeSql;
	}
}
