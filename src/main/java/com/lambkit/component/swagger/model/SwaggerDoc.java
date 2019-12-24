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
package com.lambkit.component.swagger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [description]
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/7/6
 */
public class SwaggerDoc {
    private String swagger;

    private InfoBean info;

    private String host;

    private String basePath;

    private List<TagBean> tags = new ArrayList<>();

    private List<String> schemes = new ArrayList<>();

    private Map<String, Map<String, SwaggerPath.ApiMethod>> paths = new HashMap<>();

    public String getSwagger() {
        return swagger;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<TagBean> getTags() {
        return tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
    }

    public List<String> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<String> schemes) {
        this.schemes = schemes;
    }

    public Map<String, Map<String, SwaggerPath.ApiMethod>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<String, SwaggerPath.ApiMethod>> paths) {
        this.paths = paths;
    }

    public SwaggerDoc addTags(TagBean tagBean) {
        tags.add(tagBean);
        return this;
    }

    public SwaggerDoc addScheme(String scheme) {
        schemes.add(scheme);
        return this;
    }

    public static class InfoBean {
        private String description;
        private String version;
        private String title;
        private String termsOfService;

        public InfoBean(String description, String version, String title, String termsOfService) {
            this.description = description;
            this.version = version;
            this.title = title;
            this.termsOfService = termsOfService;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTermsOfService() {
            return termsOfService;
        }

        public void setTermsOfService(String termsOfService) {
            this.termsOfService = termsOfService;
        }
    }

    public static class TagBean {
        private String name;
        private String description;

        public TagBean(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
