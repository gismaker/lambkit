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
package com.lambkit.server;

import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.server.Scanner;
import com.lambkit.Lambkit;

import java.io.File;

/**
 * 自动加载管理
 * 
 * @author 孤竹行
 *
 */
public class AutoDeployManager {

    private static AutoDeployManager manager = new AutoDeployManager();

    private AutoDeployManager() {
    }

    public static AutoDeployManager me() {
        return manager;
    }


    public void run() {

        File file = new File(PathKit.getRootClassPath()).getParentFile().getParentFile();
        Scanner scanner = new Scanner(file.getAbsolutePath(), 3) {
            public void onChange() {
                try {
                    System.err.println("file changes ......");
                    Lambkit.me().getServer().restart();
                    LambkitServerFactory.me().buildServer().start();
                    System.err.println("Loading complete.");
                } catch (Exception e) {
                    System.err.println("Error reconfiguring/restarting webapp after change in watched files");
                    LogKit.error(e.getMessage(), e);
                }
            }
        };

        scanner.start();
    }

}
