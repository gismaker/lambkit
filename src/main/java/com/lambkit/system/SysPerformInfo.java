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
package com.lambkit.system;

import java.io.Serializable;

public class SysPerformInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8497141208192790474L;

	private float cpuRate;
	private float memoryRate;
	private int threadCount;
	private float upSpeed;
	private float downSpeed;
	
	public float getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(float cpuRate) {
		this.cpuRate = cpuRate;
	}
	public float getMemoryRate() {
		return memoryRate;
	}
	public void setMemoryRate(float memoryRate) {
		this.memoryRate = memoryRate;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	public float getUpSpeed() {
		return upSpeed;
	}
	public void setUpSpeed(float upSpeed) {
		this.upSpeed = upSpeed;
	}
	public float getDownSpeed() {
		return downSpeed;
	}
	public void setDownSpeed(float downSpeed) {
		this.downSpeed = downSpeed;
	}

}
