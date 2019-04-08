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
 */package com.lambkit.plugin.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FOR : 通过Auth注解来判断权限/角色
 */
@Retention(RetentionPolicy.RUNTIME)
/**运行时注解*/
@Target({ElementType.TYPE, ElementType.METHOD})
/**方法或者类的注解*/
public @interface Auth {

    String[] hasForces() default {};               // 需要的权限 满足一个就可以访问--优先级第三

    String[] hasRoles() default {};                // 满足的角色 满足一个就可以访问--优先级第四

    String[] withForces() default {};              // 需要的权限 满足全部才可以访问--优先级第一

    String[] withRoles()  default {};              // 满足的橘色 满足全部才可以访问--优先级第二

}