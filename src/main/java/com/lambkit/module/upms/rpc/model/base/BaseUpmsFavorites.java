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
package com.lambkit.module.upms.rpc.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.lambkit.common.model.LambkitModel;

/**
 * @author yangyong 
 * @website: www.lambkit.com
 * @email: gismail@foxmail.com
 * @date 2018-12-26
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class BaseUpmsFavorites<M extends BaseUpmsFavorites<M>> extends LambkitModel<M> implements IBean {

	public String tableName() {
		return "upms_favorites";
	}
    
	public java.lang.Long getId() {
		return this.get("id");
	}

	public void setId(java.lang.Long id) {
		this.set("id", id);
	}
	public java.lang.Long getUserId() {
		return this.get("user_id");
	}

	public void setUserId(java.lang.Long userId) {
		this.set("user_id", userId);
	}
	public java.lang.String getTitle() {
		return this.get("title");
	}

	public void setTitle(java.lang.String title) {
		this.set("title", title);
	}
	public java.lang.String getUrl() {
		return this.get("url");
	}

	public void setUrl(java.lang.String url) {
		this.set("url", url);
	}
	public java.lang.String getDescription() {
		return this.get("description");
	}

	public void setDescription(java.lang.String description) {
		this.set("description", description);
	}
	public java.lang.String getTags() {
		return this.get("tags");
	}

	public void setTags(java.lang.String tags) {
		this.set("tags", tags);
	}
	public java.math.BigInteger getCtime() {
		return this.get("ctime");
	}

	public void setCtime(java.math.BigInteger ctime) {
		this.set("ctime", ctime);
	}
}
