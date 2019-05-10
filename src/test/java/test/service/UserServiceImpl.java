/**
 * Copyright (c) 2015-2017, York Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.service;

import com.lambkit.common.exception.LambkitException;
import com.lambkit.core.aop.AopKit;

public class UserServiceImpl implements UserService {
    @Override
    public String hello(String name) {

        System.out.println("UserServiceImpl hello invoked!!!");

        return AopKit.newInstance(CategoryServiceImpl.class).hello(name);
    }


    @Override
    public User findUserById(String userId) {
        //return "get user:" + userId;
    	return new User(Integer.parseInt(userId), "default");
    }

    @Override
    public boolean saveUser(User user) {
        System.out.println("save user :" + user);

        return true;
    }

    @Override
    public String exception(String id) {
        throw new LambkitException(id);
    }


}
