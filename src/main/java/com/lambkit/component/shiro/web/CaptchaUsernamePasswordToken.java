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
package com.lambkit.component.shiro.web;

import org.apache.shiro.authc.UsernamePasswordToken;

/**  
 * @ClassName: CaptchaUsernamePasswordToken  
 * @Description: 在用户名和密码的基础上添加验证码的Token  
 * @author 李飞 (lifei@wellbole.com)   
 * @date 2014年9月11日 下午1:20:33
 * @since V1.0.0  
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 4676958151524148623L;
    
    private String captcha;
    
    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
