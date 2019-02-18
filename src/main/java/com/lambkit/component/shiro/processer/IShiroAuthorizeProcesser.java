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
package com.lambkit.component.shiro.processer;

/**
 * Shiro 的认证处理器
 * 用于对每个controller 的 每个方法进行认证
 * 
 * #anon:所有的人都可以访问
 * #authc:[ShiroRequiresAuthenticationProcesser]需要认证 
 * #user:[ShiroRequiresUserProcesser]表示用户不一定已通过认证,
 * 			只要曾被shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe。
 * #role:[ShiroRequiresRolesProcesser]用户角色验证
 * #perm:[ShiroRequiresPermissionsProcesser]用户权限验证
 * 
 * 使用当前方法，ini中urls就不要配置了
 */
public interface IShiroAuthorizeProcesser {


    public AuthorizeResult authorize();

}
