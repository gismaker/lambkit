package com.lambkit.plugin.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * FOR : 普通JavaBean或者是Model变为可以使用插件的JwtUser
 */
public interface IJwtAble extends Serializable {
	
	String getUserName();
    
	/**
     * 获取角色集合
     *
     * @return
     */
    Set<String> getRoles();

    /**
     * 获取权限集合
     *
     * @return
     */
    Set<String> getForces();

    /**
     * 上次修改密码时间
     *
     * @return
     */
    Date getLastModifyPasswordTime();

}