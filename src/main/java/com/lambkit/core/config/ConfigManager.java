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
package com.lambkit.core.config;

import com.jfinal.kit.*;
import com.jfinal.log.Log;
import com.lambkit.Lambkit;
import com.lambkit.common.util.ArrayUtils;
import com.lambkit.common.util.StringUtils;
import com.lambkit.core.aop.AopKit;
import com.lambkit.core.config.annotation.PropertieConfig;
import com.lambkit.exception.LambkitException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置管理类
 * <p>
 * 用于读取配置信息，包括本地配置信息和分布式远程配置信息
 */
public class ConfigManager {

    private static ConfigManager me = new ConfigManager();

    public static ConfigManager me() {
        return me;
    }

    private Prop prop;

    private PropInfoMap propInfoMap = new PropInfoMap();

    private ConcurrentHashMap<String, Object> configs = new ConcurrentHashMap<>();
    private static final Log log = Log.getLog(ConfigManager.class);

    public ConfigManager() {
        prop = PropKit.use("lambkit.properties");
        initModeProp(prop);
    }
    
    public String getValue(String key) {
    	String value = Lambkit.getArg(key);
    	if (StringUtils.isBlank(value)) {
    		value = prop.get(key);
    	}
        return value;
    }
    
    public boolean containsKey(String key) {
    	if(StrKit.notBlank(Lambkit.getArg(key))) {
    		return true;
    	}
    	if(prop.containsKey(key)) {
    		return true;
    	}
    	return false;
    }

    public <T> T get(Class<T> clazz) {
        PropertieConfig propertieConfig = clazz.getAnnotation(PropertieConfig.class);
        if (propertieConfig == null) {
            return get(clazz, null);
        }
        return get(clazz, propertieConfig.prefix());
    }


    /**
     * 获取配置信息，并创建和赋值clazz实例
     *
     * @param clazz  指定的类
     * @param prefix 配置文件前缀
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> clazz, String prefix) {

        T obj = (T) configs.get(clazz.getName() + prefix);
        if (obj != null) {
            return obj;
        }

        obj = AopKit.newInstance(clazz);

        List<Method> setMethods = new ArrayList<>();

        Method[] methods = obj.getClass().getMethods();
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method m : methods) {
                if (m.getName().startsWith("set") 
                    && m.getName().length() > 3 
                    && m.getParameterTypes().length == 1) {//java7
                    //&& m.getParameterCount() == 1) {//java8
                    setMethods.add(m);
                }
            }
        }

        for (Method method : setMethods) {

            String key = getKeyByMethod(prefix, method);
            String value = getValueByKey(key);

            try {
                if (StrKit.notBlank(value)) {
                    Object val = convert(method.getParameterTypes()[0], value);
                    method.invoke(obj, val);
                }
            } catch (Throwable ex) {
                log.error(ex.toString(), ex);
            }
        }

        configs.put(clazz.getName() + prefix, obj);

        return obj;
    }

    private String getKeyByMethod(String prefix, Method method) {

        String key = StrKit.firstCharToLowerCase(method.getName().substring(3));

        if (StrKit.notBlank(prefix)) {
            key = prefix.trim() + "." + key;
        }

        return key;
    }

    /**
     * 根据 key 获取value的值
     * <p>
     * 优先获取系统启动设置参数
     * 第二 获取远程配置
     * 第三 获取本地配置
     *
     * @param key
     * @return
     */
    private String getValueByKey(String key) {
    	String value = Lambkit.getArg(key);
    	if (StringUtils.isBlank(value)) {
    		value = prop.get(key);
    	}
        return value;
    }


    /**
     * 或者默认的配置信息
     *
     * @return
     */
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(prop.getProperties());
        return properties;
    }


    /**
     * 初始化不同model下的properties文件
     *
     * @param prop
     */
    private void initModeProp(Prop prop) {
        String mode = PropKit.use("lambkit.properties").get("lambkit.mode");
        if (StrKit.isBlank(mode)) {
            return;
        }

        Prop modeProp = null;
        try {
            String p = String.format("lambkit-%s.properties", mode);
            modeProp = PropKit.use(p);
        } catch (Throwable ex) {
        }

        if (modeProp == null) {
            return;
        }

        prop.getProperties().putAll(modeProp.getProperties());
    }


    /**
     * 数据转化
     *
     * @param type
     * @param s
     * @return
     */
    private static final Object convert(Class<?> type, String s) {

        if (type == String.class) {
            return s;
        }


        // mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt(s);
        }

        // mysql type: bigint
        if (type == Long.class || type == long.class) {
            return Long.parseLong(s);
        }


        // mysql type: real, double
        if (type == Double.class || type == double.class) {
            return Double.parseDouble(s);
        }

        // mysql type: float
        if (type == Float.class || type == float.class) {
            return Float.parseFloat(s);
        }

        // mysql type: bit, tinyint(1)
        if (type == Boolean.class || type == boolean.class) {
            String value = s.toLowerCase();
            if ("1".equals(value) || "true".equals(value)) {
                return Boolean.TRUE;
            } else if ("0".equals(value) || "false".equals(value)) {
                return Boolean.FALSE;
            } else {
                throw new RuntimeException("Can not parse to boolean type of value: " + s);
            }
        }

        // mysql type: decimal, numeric
        if (type == java.math.BigDecimal.class) {
            return new java.math.BigDecimal(s);
        }

        // mysql type: unsigned bigint
        if (type == java.math.BigInteger.class) {
            return new java.math.BigInteger(s);
        }

        // mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob. I have not finished the test.
        if (type == byte[].class) {
            return s.getBytes();
        }

        throw new LambkitException(type.getName() + " can not be converted, please use other type in your config class!");
    }

    public PropInfoMap getPropInfoMap() {
        return propInfoMap;
    }

}
