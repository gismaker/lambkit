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

import com.lambkit.core.config.ConfigManager;
import com.lambkit.server.app.AppServer;
import com.lambkit.server.jetty.JettyServer;

public class LambkitServerFactory {

    private static LambkitServerFactory me = new LambkitServerFactory();

    public static LambkitServerFactory me() {
        return me;
    }

    public LambkitServer buildServer() {
        LambkitServerConfig serverConfig = ConfigManager.me().get(LambkitServerConfig.class);
        switch (serverConfig.getType()) {
            case LambkitServerConfig.TYPE_JETTY:
                return new JettyServer();
            case LambkitServerConfig.TYPE_APP:
                return new AppServer();
            default:
                return new JettyServer();
        }
    }

}
