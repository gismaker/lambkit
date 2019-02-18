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

/**
 * [description]
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/7/6
 */
public class SwaggerResponse {
    private String description;

    private Schema schema;

    public SwaggerResponse(String description) {
        this.description = description;
    }

    public SwaggerResponse(Schema schema) {
        this.schema = schema;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public static class Schema {
        private String type;
        private Items items;

        public Schema(String type, Items items) {
            this.type = type;
            this.items = items;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    public static class Items {
        private String $ref;

        public Items(String ref) {
            this.$ref = ref;
        }

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }
    }
}
