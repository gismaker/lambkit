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
package com.lambkit.module.meta.service.impl;

import java.util.List;

import com.lambkit.common.service.BaseServiceMock;
import com.lambkit.db.meta.ColumnMeta;
import com.lambkit.db.mgr.IField;
import com.lambkit.db.mgr.ITable;
import com.lambkit.module.meta.model.MetaField;
import com.lambkit.module.meta.service.MetaFieldService;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-11-24
 * @version 1.0
 * @since 1.0
 */
public class MetaFieldServiceMock extends BaseServiceMock<MetaField> implements MetaFieldService {

	@Override
	public List<? extends IField> findByTbid(Object tbid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IField> findByTbid(Object tbid, int type, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryKey(Object tbid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField findById(Object fldid, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByTbid(Object fldid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IField findFirstByTbName(String tbname, String fld, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField findFirstByTbName(String tbname, int type, String fld, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField findFirstByTbid(Object tbid, String fld, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField findFirstByTbid(Object tbid, int type, String fld, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField findGeomField(Object tbid, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField getAddOrEditModel(Object id, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IField columnToField(ColumnMeta column, ITable tbc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaField joinSubModel(MetaField metaField, int type, Long tmid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaField getPrimaryKeyField(Long tbid, String keyname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaField> getByTableId(Long tbid) {
		// TODO Auto-generated method stub
		return null;
	}
}