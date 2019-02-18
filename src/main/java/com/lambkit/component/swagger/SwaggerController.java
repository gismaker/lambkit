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
package com.lambkit.component.swagger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import com.jfinal.core.Controller;
import com.lambkit.component.swagger.annotation.Api;
import com.lambkit.component.swagger.annotation.ApiOperation;
import com.lambkit.component.swagger.annotation.Param;
import com.lambkit.component.swagger.annotation.Params;
import com.lambkit.component.swagger.model.SwaggerDoc;
import com.lambkit.component.swagger.model.SwaggerPath;

/**
 * swagger
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/7/7
 */
public class SwaggerController extends Controller {

    public void index() {
        renderTemplate("index.html");
    }
    
    public void api() {
        SwaggerDoc doc = new SwaggerDoc();
        Map<String, Map<String, SwaggerPath.ApiMethod>> paths = new HashMap<>();
        Map<String, String> classMap = new HashMap<String, String>();
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : classSet) {
            if (cls.isAnnotationPresent(Api.class)) {
                Api api = cls.getAnnotation(Api.class);
                
                if (!classMap.containsKey(api.tag())) {
                    classMap.put(api.tag(), api.description());
                }
                
                Method[] methods = cls.getMethods();
                
                for (Method method : methods) {
                    Annotation[] annotations = method.getAnnotations();
                    SwaggerPath.ApiMethod apiMethod = new SwaggerPath.ApiMethod();
                    apiMethod.setOperationId("");
                    apiMethod.addProduce("application/json");
                    
                    for (Annotation annotation : annotations) {
                        Class<? extends Annotation> annotationType = annotation.annotationType();
                        if (ApiOperation.class == annotationType) {
                            ApiOperation apiOperation = (ApiOperation) annotation;
                            Map<String, SwaggerPath.ApiMethod> methodMap = new HashMap<>();
                            apiMethod.setSummary(apiOperation.description());
                            apiMethod.setDescription(apiOperation.description());
                            apiMethod.addTag(apiOperation.tag());
                            methodMap.put(apiOperation.httpMethod(), apiMethod);
                            paths.put(apiOperation.url(), methodMap);
                        } else if (Params.class == annotationType) {
                            Params apiOperation = (Params) annotation;
                            Param[] params = apiOperation.value();
                            for (Param apiParam : params) {
                                apiMethod.addParameter(new SwaggerPath.Parameter(apiParam.name(), apiParam.description(), apiParam.required(), apiParam.dataType(), apiParam.format(), apiParam.defaultValue()));
                            }
                        } else if (Param.class == annotationType) {
                            Param apiParam = (Param) annotation;
                            apiMethod.addParameter(new SwaggerPath.Parameter(apiParam.name(), apiParam.description(), apiParam.required(), apiParam.dataType(), apiParam.format(), apiParam.defaultValue()));
                        }
                    }
                }
            }
        }
        
        if (classMap.size() > 0) {
            for (String key : classMap.keySet()) {
                doc.addTags(new SwaggerDoc.TagBean(key, classMap.get(key)));
            }
        }
        doc.setPaths(paths);
        
        // swaggerUI 使用Java的关键字default作为默认值,此处将生成的JSON替换
        renderText(JSON.toJSONString(doc).replaceAll("\"defaultValue\"", "\"default\""));
        
    }
}
