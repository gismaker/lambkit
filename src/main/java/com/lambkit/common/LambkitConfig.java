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

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit")
public class LambkitConfig {

	private String name = "";
    private String version = "0.2-alpha1";
    private String mode = LambkitMode.DEV.getValue();
    private boolean lmsActived = false;
    private String encryptKey = "n1gEgB3/NiGrOxdT9IxWxA==";
    
    private String autoRegisterControllerPackages;
    private String autoRegisterTagPackages;
    private String autoRegisterEventPackages;
    private String autoRegisterModulePackages;
    private String autoRegisterServicePackages;
    
	public boolean isDevMode() {
        return LambkitMode.DEV.getValue().equals(mode);
    }

    @Override
    public String toString() {
        return "LambkitConfig {" +
                "version='" + version + '\'' +
                ", mode='" + mode + '\'' +
                ", lmsActived=" + lmsActived +
                '}';
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
		this.version = version;
	}

    public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutoRegisterControllerPackages() {
		return autoRegisterControllerPackages;
	}

	public void setAutoRegisterControllerPackages(String autoRegisterControllerPackages) {
		this.autoRegisterControllerPackages = autoRegisterControllerPackages;
	}

	public String getAutoRegisterTagPackages() {
		return autoRegisterTagPackages;
	}

	public void setAutoRegisterTagPackages(String autoRegisterTagPackages) {
		this.autoRegisterTagPackages = autoRegisterTagPackages;
	}

	public String getAutoRegisterEventPackages() {
		return autoRegisterEventPackages;
	}

	public void setAutoRegisterEventPackages(String autoRegisterEventPackages) {
		this.autoRegisterEventPackages = autoRegisterEventPackages;
	}

	public String getAutoRegisterModulePackages() {
		return autoRegisterModulePackages;
	}

	public void setAutoRegisterModulePackages(String autoRegisterModulePackages) {
		this.autoRegisterModulePackages = autoRegisterModulePackages;
	}

	public String getAutoRegisterServicePackages() {
		return autoRegisterServicePackages;
	}

	public void setAutoRegisterServicePackages(String autoRegisterServicePackages) {
		this.autoRegisterServicePackages = autoRegisterServicePackages;
	}


	public boolean isLmsActived() {
		return lmsActived;
	}


	public void setLmsActived(boolean lmsActived) {
		this.lmsActived = lmsActived;
	}


	public String getEncryptKey() {
		return encryptKey;
	}


	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}
}
