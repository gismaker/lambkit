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
package com.lambkit.db.sql.criterion.projection;

import java.util.ArrayList;
import java.util.List;

/**
 * 7. 投影(Projections)、聚合（aggregation）和分组（grouping）
 * org.hibernate.criterion.Projections是 Projection 的实例工厂。我们通过调用setProjection()应用投影到一个查询。
 * 
 * List results = session.createCriteria(Cat.class)  
    .setProjection( Projections.rowCount() )  
    .add( Restrictions.eq("color", Color.BLACK) )  
    .list();  
   
List results = session.createCriteria(Cat.class)  
    .setProjection( Projections.projectionList()  
        .add( Projections.rowCount() )  
        .add( Projections.avg("weight") )  
        .add( Projections.max("weight") )  
        .add( Projections.groupProperty("color") )  
    )  
    .list(); 
 * @author 孤竹行
 *
 */
public class Projections {
	
	public static List<Projection> projectionList() {
		return new ArrayList<Projection>();
	}

	public static Projection rowCount() {
		return new Projection();
	}
	
	public static Projection groupProperty(String property) {
		return new Projection(property);
	}
	
	public static Projection avg(String property) {
		return new Projection(property, ProjectionMode.AVG);
	}
	
	public static Projection max(String property) {
		return new Projection(property, ProjectionMode.MAX);
	}
	
	public static Projection min(String property) {
		return new Projection(property, ProjectionMode.MIN);
	}
	
	public static Projection sum(String property) {
		return new Projection(property, ProjectionMode.SUM);
	}
	
	public static Projection count(String property) {
		return new Projection(property, ProjectionMode.COUNT);
	}
}
