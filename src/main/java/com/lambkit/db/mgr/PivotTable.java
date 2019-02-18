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
package com.lambkit.db.mgr;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;

public class PivotTable {
	/**
	 * 行维度, name, label, category
	 */
	private List<IField> rows;
	/**
	 * 列维度, name, label, category
	 */
	private List<IField> columns;
	/**
	 * 度量, name, label, data
	 */
	private List<IField> measures;
	
	private List<Record> rowHead;//
	private List<Record> colHead;//
	private int rowlen;//
	private List<Record> data;
	private List<Record> rowCategory;
	private List<Record> colCategory;

	public PivotTable() {
		rows = new ArrayList<IField>();
		columns = new ArrayList<IField>();
		measures = new ArrayList<IField>();
		rowHead = new ArrayList<>();
		colHead = new ArrayList<>();
	}
	
	/**
	 * 添加行名称
	 * @param measure_name
	 */
	public void addRow(IField fld) {
		int collen = columns.size();
		Record record = new Record();
		record.set("property", fld.getName());
		record.set("label", fld.getTitle());
		record.set("rowspan", collen + 1);
		rowHead.add(record);
		rows.add(fld);
	}
	/**
	 * 添加列名称
	 * @param measure_name
	 */
	public void addColumn(IField fld) {
		columns.add(fld);
	}
	/**
	 * 添加名称
	 * @param measure_name
	 */
	public void addMeasure(IField fld) {
		measures.add(fld);
	}
	
	public List<Record> getData() {
		return data;
	}

	public List<IField> getMeasures() {
		return measures;
	}
	public void setMeasures(List<IField> measures) {
		this.measures = measures;
	}

	public List<IField> getRows() {
		return rows;
	}

	public void setRows(List<IField> rows) {
		this.rows = rows;
	}

	public List<IField> getColumns() {
		return columns;
	}

	public void setColumns(List<IField> columns) {
		this.columns = columns;
	}
	
	public void setData(List<Record> data) {
		this.data = data;
	}

	public int getRowlen() {
		return rowlen;
	}

	public void setRowlen(int rowlen) {
		this.rowlen = rowlen;
	}

	public List<Record> getColHead() {
		return colHead;
	}

	public void setColHead(List<Record> colHead) {
		this.colHead = colHead;
	}

	public List<Record> getRowHead() {
		return rowHead;
	}

	public void setRowHead(List<Record> rowHead) {
		this.rowHead = rowHead;
	}

	public List<Record> getRowCategory() {
		return rowCategory;
	}

	public void setRowCategory(List<Record> rowCategory) {
		this.rowCategory = rowCategory;
	}

	public List<Record> getColCategory() {
		return colCategory;
	}

	public void setColCategory(List<Record> colCategory) {
		this.colCategory = colCategory;
	} 
}
