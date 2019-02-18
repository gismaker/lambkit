package com.lambkit.db.sql.test;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.SqlPara;
import com.lambkit.db.dialect.IModelDialect;
import com.lambkit.db.dialect.LambkitPostgreSqlDialect;
import com.lambkit.db.sql.column.Example;
import com.lambkit.module.upms.rpc.sql.UpmsRolePermissionCriteria;

public class ExampleTest {
	public static void main(String[] args) {
		 List<Long> deleteIds = new ArrayList<Long>();
		 deleteIds.add(2L);
		Example upmsRolePermissionExample = UpmsRolePermissionCriteria.create().andPermissionIdIn(deleteIds)
				.andRoleIdEqualTo(1L).example();
		IModelDialect dialect = new LambkitPostgreSqlDialect();
		SqlPara forDeleteByExample = dialect.forDeleteByExample(upmsRolePermissionExample);
		System.out.println(forDeleteByExample.getSql());
		for(int i=0; i<forDeleteByExample.getPara().length; i++) {
			System.out.println(forDeleteByExample.getPara()[i]);
		}
	}
}
