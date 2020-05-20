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
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;

import com.jfinal.log.Log;

/**
 * hbase原生java操作工具类
 * 
 * @author yangyong
 */
public class Hbase {

	private static final Log logger = Log.getLog(Thread.currentThread().getClass());
	public static Connection connection;

	private static Admin getAdmin(Connection connection) {
		try {
			return connection.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void createTable(String table, String[] familyNames) {
		Admin admin = getAdmin(connection);
		try {
			if (!admin.tableExists(TableName.valueOf(table))){
		        TableName tableName = TableName.valueOf(table);
		        //表描述器构造器
		        TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(tableName);
		        //列族描述器构造器
		        ColumnFamilyDescriptorBuilder cdb = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("user"));
		        //获得列描述器
		        ColumnFamilyDescriptor cfd = cdb.build();
		        //添加列族
		        tdb.setColumnFamily(cfd);
		        //获得表描述器
		        TableDescriptor td = tdb.build();
		        //创建表
		        admin.createTable(td);
		    }else {
		        System.out.println("表已存在");
		    }
		    //关闭连接
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
			TableDescriptorBuilder tdb = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
			tdb.setCoprocessor(className);
			TableDescriptor td = tdb.build();
			admin.modifyTable(td);
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

	public static TableDescriptor tableDescriptor(String tableName) {
		Admin admin = getAdmin(connection);
		TableDescriptor desc = null;
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf(tableName));
			desc = table.getDescriptor();
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
		List<Put> puts = new ArrayList<Put>();
		puts.add(put);
		put(tableName, puts);
	}

	public static void put(String tableName, List<Put> puts) {
		Table table = null;
		try {
			System.out.println("Table: " + tableName + ", " + table);
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
	
	/**
	 * 根据rowKey删除一行数据、或者删除某一行的某个列簇，或者某一行某个列簇某列
	 * @param tableName
	 * @param rowKey
	 * @throws Exception
	 */
	public static void deleteData(TableName tableName, String rowKey, String columnFamily, String columnName) throws Exception{
	    Table table = connection.getTable(tableName);
	    Delete delete = new Delete(Bytes.toBytes(rowKey));
	    //①根据rowKey删除一行数据
	    table.delete(delete);
	    
	    //②删除某一行的某一个列簇内容
	    delete.addFamily(Bytes.toBytes(columnFamily));
	    
	    //③删除某一行某个列簇某列的值
	    delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnName));
	    table.close();
	}
	
	/**
	 * 根据RowKey , 列簇， 列名修改值
	 * @param tableName
	 * @param rowKey
	 * @param columnFamily
	 * @param columnName
	 * @param columnValue
	 * @throws Exception
	 */
	public static void updateData(TableName tableName, String rowKey, String columnFamily, String columnName, String columnValue) throws Exception{
	    Table table = connection.getTable(tableName);
	    Put put1 = new Put(Bytes.toBytes(rowKey));
	    put1.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnName), Bytes.toBytes(columnValue));
	    table.put(put1);
	    table.close();
	}

	/**
	 * 根据rowKey查询数据
	 * @param tableName
	 * @param rowKey
	 * @throws Exception
	 */
	public static void getResult(TableName tableName, String rowKey) throws Exception{
	    Table table = connection.getTable(tableName);
	    //获得一行
	    Get get = new Get(Bytes.toBytes(rowKey));
	    Result set = table.get(get);
	    Cell[] cells = set.rawCells();
	    for (Cell cell: cells){
	        System.out.println(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + "::" +
	        Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
	    }
	    table.close();
	}

	//过滤器 LESS <  LESS_OR_EQUAL <=   EQUAL =   NOT_EQUAL <>   GREATER_OR_EQUAL >=   GREATER >   NO_OP 排除所有

	/**
	 * @param tableName
	 * @throws Exception
	
	public static void scanTable(TableName tableName) throws Exception{
	    Table table = connection.getTable(tableName);
	    
	    //①全表扫描
	    Scan scan1 = new Scan();
	    ResultScanner rscan1 = table.getScanner(scan1);
	    
	    //②rowKey过滤器
	    Scan scan2 = new Scan();
	    //str$ 末尾匹配，相当于sql中的 %str  ^str开头匹配，相当于sql中的str%
	    RowFilter filter = new RowFilter(CompareOperator.EQUAL, new RegexStringComparator("Key1$"));
	    scan2.setFilter(filter);
	    ResultScanner rscan2 = table.getScanner(scan2);
	    
	    //③列值过滤器
	    Scan scan3 = new Scan();
	    //下列参数分别为列族，列名，比较符号，值
	    SingleColumnValueFilter filter3 = new SingleColumnValueFilter(Bytes.toBytes("author"), Bytes.toBytes("name"),
	               CompareOperator.EQUAL, Bytes.toBytes("spark"));
	    scan3.setFilter(filter3);
	    ResultScanner rscan3 = table.getScanner(scan3);
	    
	    //列名前缀过滤器
	    Scan scan4 = new Scan();
	    ColumnPrefixFilter filter4 = new ColumnPrefixFilter(Bytes.toBytes("name"));
	    scan4.setFilter(filter4);
	    ResultScanner rscan4 = table.getScanner(scan4);
	    
	    //过滤器集合
	    Scan scan5 = new Scan();
	    FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);
	    SingleColumnValueFilter filter51 = new SingleColumnValueFilter(Bytes.toBytes("author"), Bytes.toBytes("name"),
	              CompareOperator.EQUAL, Bytes.toBytes("spark"));
	    ColumnPrefixFilter filter52 = new ColumnPrefixFilter(Bytes.toBytes("name"));
	    list.addFilter(filter51);
	    list.addFilter(filter52);
	    scan5.setFilter(list);
	    ResultScanner rscan5 = table.getScanner(scan5);
	    
	    for (Result rs : rscan5){
	        String rowKey = Bytes.toString(rs.getRow());
	        System.out.println("row key :" + rowKey);
	        Cell[] cells = rs.rawCells();
	        for (Cell cell: cells){
	            System.out.println(Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) + "::"
	                    + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + "::"
	                    + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
	        }
	        System.out.println("-------------------------------------------");
	    }
	} */
}
