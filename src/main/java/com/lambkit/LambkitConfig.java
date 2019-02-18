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
package com.lambkit;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit")
public class LambkitConfig {

	private String name = "";
    private String version = "1.2.0";
    private String mode = LambkitMode.DEV.getValue();
    private boolean bannerEnable = true;
    private String bannerFile = "banner.txt";
    private String jfinalConfig = DefaultJFinalConfig.class.getName();
    
    private String ctrlPackages = "com.lambkit";
    private String tagPackages = "com.lambkit";
    private String eventPackages = "com.lambkit";
    private String modulePackages = "com.lambkit";
    private String servicePackages = "com.lambkit";
    
    private String encryptKey = "e607857331855d81593b568a03c070d0";

    public String getVersion() {
        return version;
    }

    public boolean isBannerEnable() {
        return bannerEnable;
    }

    public void setBannerEnable(boolean bannerEnable) {
        this.bannerEnable = bannerEnable;
    }

    public String getBannerFile() {
        return bannerFile;
    }

    public void setBannerFile(String bannerFile) {
        this.bannerFile = bannerFile;
    }

    public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

    public String getJfinalConfig() {
        return jfinalConfig;
    }

    public void setJfinalConfig(String jfinalConfig) {
        this.jfinalConfig = jfinalConfig;
    }


    @Override
    public String toString() {
        return "LambkitConfig {" +
                "version='" + version + '\'' +
                ", mode='" + mode + '\'' +
                ", bannerEnable=" + bannerEnable +
                ", bannerFile='" + bannerFile + '\'' +
                ", jfinalConfig='" + jfinalConfig + '\'' +
                '}';
    }

	public String getCtrlPackages() {
		return ctrlPackages;
	}

	public void setCtrlPackages(String ctrlPackages) {
		this.ctrlPackages = ctrlPackages;
	}

	public String getEventPackages() {
		return eventPackages;
	}

	public void setEventPackages(String eventPackages) {
		this.eventPackages = eventPackages;
	}

	public String getTagPackages() {
		return tagPackages;
	}

	public void setTagPackages(String tagPackages) {
		this.tagPackages = tagPackages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public String getModulePackages() {
		return modulePackages;
	}

	public void setModulePackages(String modulePackages) {
		this.modulePackages = modulePackages;
	}

	public String getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(String servicePackages) {
		this.servicePackages = servicePackages;
	}
}
