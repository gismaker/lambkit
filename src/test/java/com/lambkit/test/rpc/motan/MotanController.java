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
package com.lambkit.test.rpc.motan;

import com.jfinal.core.Controller;
import com.lambkit.core.rpc.RpcKit;
import com.lambkit.distributed.node.ManagerNodeService;

import test.service.CategoryService;
import test.service.User;
import test.service.UserService;


//@RequestMapping("/rpc")
public class MotanController extends Controller {

    public void index() {
    	renderText("rpc controller.");
    }
    
    public void node() {
    	ManagerNodeService service = RpcKit.obtain(ManagerNodeService.class, "lambkit", "1.0", "127.0.0.1:8002");
		if(service!=null) {
			System.out.println(service.getToken());
		} else {
			System.out.println("fail");
		}
    }
    
    public void helloUser() {
    	//Rpc rpc = RpcManager.me().getRpc();

        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");
        System.out.println("obtain:" + (System.currentTimeMillis() - time) + "---" + service);


        for (int i = 0; i < 10; i++) {
            // 使用服务
            System.out.println(service.hello("lambkit" + i));
        }


        renderText("ok");
    }
    
    public void hello() {
        CategoryService service = RpcKit.obtain(CategoryService.class, "lambkit", "1.0");
        System.out.println(service.hello("lambkit"));
    }


    public void exception() {
    	//Rpc rpc = RpcManager.me().getRpc();
    	
        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");

//        try {
        String string = service.exception("1");
//        } catch (LambkitException e) {
//            System.out.println("exception : " + e.getMessage());
//        }

        renderText("exception:" + string);

    }
    
    public void find() {
    	//Rpc rpc = RpcManager.me().getRpc();
    	
        long time = System.currentTimeMillis();
        UserService service = RpcKit.obtain(UserService.class, "lambkit", "1.0");
        System.out.println("obtain:" + (System.currentTimeMillis() - time) + "---" + service);

        User user = service.findUserById("1");

        renderText("get user name :" + user.getName() + ", id: "+user.getId());
    }



}
