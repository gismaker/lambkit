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
package com.lambkit.component.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;

import com.jfinal.log.Log;

/**
 * hbase原生java操作工具类
 * 
 * @author yangyong
 */
public class Hbase {

	private static final Log logger = Log.getLog(Thread.currentThread().getClass());
	private static final String aggregateImplementationCoprocessor = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";
	public static Connection connection;
	public static AggregationClient aggregationClient;
	public static LongColumnInterpreter longColumnInterpreter = new LongColumnInterpreter();

	private static Admin getAdmin(Connection connection) {
		try {
			return connection.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void createTable(String tableName, String[] familyNames) {
		Admin admin = getAdmin(connection);
		try {
			HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
			hTableDescriptor.addCoprocessor(aggregateImplementationCoprocessor);
			for (String familyName : familyNames) {
				HColumnDescriptor family = new HColumnDescriptor(familyName);
				hTableDescriptor.addFamily(family);
			}
			admin.createTable(hTableDescriptor);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addCoprocessor(String tableName, String className) {
		Admin admin = getAdmin(connection);
		try {
			admin.disableTable(TableName.valueOf(tableName));
			HTableDescriptor hTableDescriptor = admin.getTableDescriptor(TableName.valueOf(tableName));
			hTableDescriptor.addCoprocessor(className);
			admin.modifyTable(TableName.valueOf(tableName), hTableDescriptor);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				admin.enableTable(TableName.valueOf(tableName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteTable(String tableName) {
		Admin admin = getAdmin(connection);
		try {
			admin.disableTable(TableName.valueOf(tableName));
			admin.deleteTable(TableName.valueOf(tableName));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static HTableDescriptor tableDescriptor(String tableName) {
		Admin admin = getAdmin(connection);
		HTableDescriptor desc = null;
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			desc = table.getTableDescriptor();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return desc;
	}

	public static void put(String tableName, Put put) {
		put(tableName, Arrays.asList(put));
	}

	public static void put(String tableName, List<Put> puts) {
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			table.put(puts);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Result> scan(String tableName) {
		return scan(tableName, new Scan());
	}

	public static List<Result> scan(String tableName, Integer pageSize) {
		return scan(tableName, new Scan(), pageSize);
	}

	public static List<Result> scan(String tableName, Scan scan) {
		return scan(tableName, scan, null);
	}

	public static List<Result> scan(String tableName, Scan scan, Integer pageSize) {
		List<Result> results = new ArrayList<>();
		ResultScanner resultScanner = null;
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			resultScanner = table.getScanner(scan);
			if (pageSize != null) {
				Result[] rs = resultScanner.next(pageSize);
				for (Result result : rs) {
					results.add(result);
				}
			} else {
				for (Result result : resultScanner) {
					results.add(result);
				}
			}
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			if (resultScanner != null) {
				resultScanner.close();
			}
			try {
				table.close();
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return results;
	}

	public static void delete(String tableName, Delete del) {
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			table.delete(del);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Result get(String tableName, Get g) {
		Result[] result = get(tableName, Arrays.asList(g));
		if (result != null && result.length > 0) {
			return result[0];
		}
		return null;
	}

	public static Result[] get(String tableName, List<Get> gets) {
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			return table.get(gets);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static long rowCount(String tableName, Scan scan) {
		try {
			return aggregationClient.rowCount(TableName.valueOf(tableName), longColumnInterpreter, scan);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 0;
	}
}
