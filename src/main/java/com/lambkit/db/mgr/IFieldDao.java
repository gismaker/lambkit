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

import java.util.List;

import com.lambkit.db.meta.ColumnMeta;

public interface IFieldDao {

	List<? extends IField> findByTbid(Object tbid);
	List<? extends IField> findByTbid(Object tbid, int type);
	
	List<? extends IField> findByTbid(Object tbid, String orderby);
	List<? extends IField> findByTbid(Object tbid, int type, String orderby);
	
	String getPkey(Object tbid);
	
	IField findById(Object fldid, int type);
	
	int deleteByTbid(Object tbid);
	
	IField findFirstByTbName(String tbname, String fld, Object value);
	IField findFirstByTbName(String tbname, int type, String fld, Object value);
	
	IField findFirstByTbid(Object tbid, String fld, Object value);
	IField findFirstByTbid(Object tbid, int type, String fld, Object value);
	
	IField findGeomField(Object tbid, int type);
	
	IField getAddOrEditModel(Object id, int type);
	
	IField columnToField(ColumnMeta column, ITable tbc);
}
