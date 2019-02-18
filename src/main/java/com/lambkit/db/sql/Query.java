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
package com.lambkit.db.sql;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class Query {

	public static Page<Record> paginate(QueryParas q) {
		return paginate(null, q);
	}
	
	public static Page<Record> paginate(String configName, QueryParas q) {
		if(q==null) return null;
		Object[] paras = q.getParas();
		DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(paras==null) {
			return dp.paginate(q.getPageNumber(), q.getPageSize(), q.getSelect(), q.getSqlExceptSelect());
		}
		/*
		System.out.print("[");
		for (Object object : paras) {
			System.out.print(object);
			System.out.print(",");
		}
		System.out.println("]");
		*/
		return dp.paginate(q.getPageNumber(), q.getPageSize(), q.getSelect(), q.getSqlExceptSelect(), paras);
	}
	
	public static List<Record> list(QueryParas q) {
		return list(null, q);
	}
	
	public static List<Record> list(String configName, QueryParas q) {
		if(q==null) return null;
		DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(q.getParas()==null) {
			return dp.find(q.getSql());
		}
		return dp.find(q.getSql(), q.getParas());
	}
	public static Record alone(QueryParas q) {
		return alone(null, q);
	}
	public static Record alone(String configName, QueryParas q) {
		if(q==null) return null;
		DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(q.getParas()==null) {
			return dp.findFirst(q.getSql());
		}
		return dp.findFirst(q.getSql(), q.getParas());
	}
	
	public static Object query(QueryParas q) {
		return query(null, q);
	}
	public static Object query(String configName, QueryParas q) {
		if(q.getPageNumber()==0) {
			return alone(q);
		} else {
			if(q.getPageSize()==0) {
				return list(q);
			} else {
				return paginate(q);
			}
		}
	}
	
	public static Long count(QueryParas paras) {
		return count(null, paras);
	}
	public static Long count(String configName, QueryParas paras) {
		if(paras==null) return null;
		DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(paras.getParas()==null) {
			return dp.queryLong(paras.getSql());
		}
		return dp.queryLong(paras.getSql(), paras.getParas());
	}

	public static int delete(QueryParas paras) {
		return delete(null, paras);
	}
	public static int delete(String configName, QueryParas paras) {
    	if(paras==null) return -1;
    	String sql = "delete " + paras.getSqlExceptSelect();
    	DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(paras.getParas()==null) {
			return dp.update(sql);
		}
    	return dp.update(sql, paras.getParas());
    }
	
	public static int update(String configName, QueryParas paras) {
    	if(paras==null) return -1;
    	DbPro dp;
		if(StrKit.isBlank(configName)) {
			dp = Db.use();
		} else {
			dp = Db.use(configName);
		}
		if(paras.getParas()==null) {
			return dp.update(paras.getSql());
		}
    	return dp.update(paras.getSql(), paras.getParas());
    }
}
