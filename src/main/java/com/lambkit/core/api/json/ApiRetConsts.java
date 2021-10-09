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
package com.lambkit.core.api.json;

/**
 * API接口结果常量枚举类
 */
public enum ApiRetConsts {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    INVALID_LENGTH(10001, "Invalid length"),
    INVALID_PARAM(10002, "Invalid param"),

    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account"),
	EMPTY_CAPTCHA(10106, "Captcha cannot be empty"),
	INVALID_CAPTCHA(10107, "Captcha error"),
	EMPTY_REPSWD(10108, "RePassword cannot be empty"),
	
	EMPTY_TOKEN(10201, "Token canot be empty"),
	INVALID_TOKEN(10202, "Token Invalid"),
	
	APP_NONE(10301, "没有对应机构的认证信息"),
	APP_NONE_ACCESS(10302, "您现在没有权限生成对应的AccessToken"),
	
    INVALID_DATA(10401, "Invalid data");

    public int code;

    public String message;

    ApiRetConsts(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
