package com.lambkit.test.db.sql.criteria;

import com.lambkit.db.sql.criterion.Criteria;
import com.lambkit.db.sql.criterion.FetchMode;
import com.lambkit.db.sql.criterion.Restrictions;
import com.lambkit.db.sql.criterion.SqlParas;
import com.lambkit.db.sql.criterion.order.Order;

public class SqlCriteria {

	public static void main(String[] args) {
		Criteria crt = new Criteria("user");

		// sql: name like ? and (age = ? or age is null)
		crt.add(Restrictions.like("name", "Fritz%"))
				.add(Restrictions.or(Restrictions.eq("age", new Integer(0)), Restrictions.isNull("age")));

		String sql = crt.getSqlParas().getSql();
		System.out.println("sql: " + sql);

		// sql: name in (?,?,?) and (age is null or age = ? or age = ? or age =
		// ?)
		crt.clear();
		crt.add(Restrictions.in("name", new String[] { "Fritz", "Izi", "Pk" }))
				.add(Restrictions.disjunction().add(Restrictions.isNull("age"))
						.add(Restrictions.eq("age", new Integer(0))).add(Restrictions.eq("age", new Integer(1)))
						.add(Restrictions.eq("age", new Integer(2))));
		SqlParas sqlParas = crt.getSqlParas();
		sql = sqlParas.getSql();
		System.out.print("sql: " + sql);
		if(sqlParas.getParas()!=null) {
			for(Object obj : sqlParas.getParas()) {
				if(obj!=null) System.out.print("," + obj.toString());
			}
		}
		System.out.println();

		// sql: lower(name) like lower(?) and age is null
		crt.clear();
		crt.add(Restrictions.sql("lower({alias}.name) like lower(?)", "Fritz%")).add(Restrictions.isNull("age"));
		sql = crt.getSqlParas().getSql();
		System.out.println("sql: " + sql);

		// sql: from user as user inner join role as role on user.id = role.userid and role.name like ad% 
		// where user.name like F% order by user.name asc,user.age desc,role.name asc
		crt.clear();
		crt.setAlias("user").setUseParas(false)
				.join(Restrictions.createCriteria("role", FetchMode.INNTER_JOIN)
						.add(Restrictions.eqProperty("user.id", "role.userid"))
						.add(Restrictions.like("name", "ad%")))
				.add(Restrictions.like("name", "F%"))
				.addOrder(Order.asc("name"))
				.addOrder(Order.desc("age"))
				.addOrder(Order.asc("role", "name"));

		sql = crt.getSqlParas().getSql();
		System.out.println("sql: " + sql);
	}
}
