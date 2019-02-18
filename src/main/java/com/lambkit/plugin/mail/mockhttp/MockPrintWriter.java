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
package com.lambkit.plugin.mail.mockhttp;

import java.io.PrintWriter;
import java.io.Writer;

public class MockPrintWriter extends PrintWriter {
	
	private String content = null;
	
	public MockPrintWriter(Writer out) {
		super(out);
	}
	
	@Override
	public void close() {
		content = out.toString();
		super.close();
	}
	
	@Override
	public String toString(){
		try {
			close();
		} catch (Exception e) {
			//修复Beetl没关闭流导致失败的BUG
		}
		return content;
	}
}
