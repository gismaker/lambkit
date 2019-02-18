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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.jfinal.template.source.ISource;

/**
 * 扫描jar中的sql文件
 */
public class ScanJarStringSource implements ISource {
    private String fileName;
    private String encoding;

    public ScanJarStringSource(String fileName) {
        this.fileName = fileName;
        this.encoding = "UTF-8";
    }

    public ScanJarStringSource(String fileName, String encoding) {
        this.fileName = fileName;
        this.encoding = encoding;
    }

    public boolean isModified() {
        return true;
    }

    public String getKey() {
        return this.fileName;
    }

    public StringBuilder getContent() {
        return loadFile(fileName, encoding);
    }

    public String getEncoding() {
        return this.encoding;
    }

    private String buildFinalFileName(String fileName) {
        char firstChar = fileName.charAt(0);
        String finalFileName;
        if (firstChar != 47 && firstChar != 92) {
            finalFileName = File.separator + fileName;
        } else {
            finalFileName = fileName;
        }

        return finalFileName;
    }

    private static StringBuilder loadFile(String fileName, String encoding) {
        StringBuilder out = new StringBuilder();
        InputStream inputStream = com.jfinal.template.source.FileSource.class.getClassLoader().getResourceAsStream(fileName);
        byte[] b = new byte[4096];
        try {
            for (int n; (n = inputStream.read(b)) != -1; ) {
                out.append(new String(b, 0, n, encoding));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading sql file.", e);
        } finally {
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {
                com.jfinal.kit.LogKit.error(e.getMessage(), e);
            }
        }
        return out;
    }
}
