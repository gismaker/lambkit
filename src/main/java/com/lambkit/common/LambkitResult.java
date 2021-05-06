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
package com.lambkit.common;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 统一返回结果类
 */
public class LambkitResult {

    // 状态码：1成功，其他为失败
    public int code;

    // 成功为success，其他为失败原因
    public String message;

    // 数据结果集
    public Object data;
    
    // 接口注释
    public List<ParamInfo> info;
    
    public List<CodeInfo> codetable;

    public LambkitResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public int getCode() {
        return code;
    }

    public LambkitResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public LambkitResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public LambkitResult setData(Object data) {
        this.data = data;
        return this;
    }

	public List<ParamInfo> getInfo() {
		return info;
	}

	public LambkitResult setInfo(List<ParamInfo> info) {
		this.info = info;
		return this;
	}

	public LambkitResult infoList() {
		if(this.info==null) this.info = Lists.newArrayList();
		return this;
	}
	
	public LambkitResult info(ParamInfo para) {
		if(this.info==null) this.info = Lists.newArrayList();
		this.info.add(para);
		return this;
	}
	
	public LambkitResult info(String name, String title, String remark) {
		if(this.info==null) this.info = Lists.newArrayList();
		ParamInfo para = new ParamInfo(name, title, remark);
		this.info.add(para);
		return this;
	}
	
	public LambkitResult info(String name, String title, String type, String remark) {
		if(this.info==null) this.info = Lists.newArrayList();
		ParamInfo para = new ParamInfo(name, title, type, remark);
		this.info.add(para);
		return this;
	}
	
	public LambkitResult info(String name, String title, String type, String required, String remark) {
		if(this.info==null) this.info = Lists.newArrayList();
		ParamInfo para = new ParamInfo(name, title, type, required, remark);
		this.info.add(para);
		return this;
	}

	public List<CodeInfo> getCodetable() {
		return codetable;
	}

	public LambkitResult setCodetable(List<CodeInfo> codetable) {
		this.codetable = codetable;
		return this;
	}
	
	public LambkitResult code() {
		if(this.codetable==null) this.codetable = Lists.newArrayList();
		this.codetable.add(new CodeInfo("1", "成功", ""));
		this.codetable.add(new CodeInfo("0", "失败", ""));
		return this;
	}
	
	public LambkitResult code(CodeInfo codeInfo) {
		if(this.codetable==null) this.codetable = Lists.newArrayList();
		this.codetable.add(codeInfo);
		return this;
	}
	
	public LambkitResult code(String code, String title, String remark) {
		if(this.codetable==null) this.codetable = Lists.newArrayList();
		CodeInfo codeInfo = new CodeInfo(code, title, remark);
		this.codetable.add(codeInfo);
		return this;
	}
	

}
