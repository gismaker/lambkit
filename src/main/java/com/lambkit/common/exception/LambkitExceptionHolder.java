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
package com.lambkit.common.exception;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LambkitExceptionHolder {

    static ThreadLocal<List<Throwable>> throwables = ThreadLocal.withInitial(() -> new LinkedList<>());
    static ThreadLocal<List<String>> messages = ThreadLocal.withInitial(() -> new LinkedList<>());

    public static void release() {
        if (!throwables.get().isEmpty()){
            throwables.get().clear();
        }
        if (!messages.get().isEmpty()){
            messages.get().clear();
        }
    }

    public static void hold(Throwable ex) {
        throwables.get().add(ex);
    }
    public static void hold(String message,Throwable ex) {
        messages.get().add(message);
        throwables.get().add(ex);
    }

    public static List<Throwable> getThrowables() {
        return throwables.get();
    }

    public static List<String> getMessages() {
        return messages.get();
    }

}
