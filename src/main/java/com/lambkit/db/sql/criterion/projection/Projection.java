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

public class Projection {

	private ProjectionMode mode;
	private String property;
	
	public Projection(String property, ProjectionMode mode) {
		this.setMode(mode);
		this.setProperty(property);
	}
	
	public Projection(String property) {
		this.setMode(ProjectionMode.GROUP);
		this.setProperty(property);
	}
	
	public Projection() {
		this.setMode(ProjectionMode.ROWCOUNT);
	}

	public boolean isRowCount() {
		return mode == ProjectionMode.ROWCOUNT ? true : false;
	}
	
	public boolean isGroup() {
		return mode == ProjectionMode.GROUP ? true : false;
	}
	
	public boolean isAgg() {
		return mode == ProjectionMode.ROWCOUNT || mode == ProjectionMode.GROUP ? false : true;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public ProjectionMode getMode() {
		return mode;
	}

	public void setMode(ProjectionMode mode) {
		this.mode = mode;
	}

	
	
}
