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
package com.lambkit.common.util;

import com.jfinal.log.Log;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类实例创建者创建者
 */
public class ClassNewer {

    public static Log log = Log.getLog(ClassNewer.class);
    private static final Map<Class, Object> singletons = new ConcurrentHashMap<>();

    /**
     * 获取单例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T singleton(Class<T> clazz) {
        Object object = singletons.get(clazz);
        if (object == null) {
            synchronized (clazz) {
                object = singletons.get(clazz);
                if (object == null) {
                    object = newInstance(clazz);
                    if (object != null) {
                        singletons.put(clazz, object);
                    } else {
                        Log.getLog(clazz).error("cannot new newInstance!!!!");
                    }

                }
            }
        }

        return (T) object;
    }

    /**
     * 创建新的实例
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        return newInstance(clazz, false);
    }


    public static <T> T newInstance(Class<T> clazz, boolean createdByGuice) {
        if (createdByGuice) {
            return null;
        } else {
            try {
                Constructor constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                return (T) constructor.newInstance();
            } catch (Exception e) {
                log.error("can not newInstance class:" + clazz + "\n" + e.toString(), e);
            }

            return null;
        }
    }

    /**
     * 创建新的实例
     *
     * @param <T>
     * @param clazzName
     * @return
     */
    public static <T> T newInstance(String clazzName) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(clazzName, false, Thread.currentThread().getContextClassLoader());
            return newInstance(clazz);
        } catch (Exception e) {
            log.error("can not newInstance class:" + clazzName + "\n" + e.toString(), e);
        }

        return null;
    }


}
