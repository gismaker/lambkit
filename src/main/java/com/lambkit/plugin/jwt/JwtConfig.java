package com.lambkit.plugin.jwt;

import com.lambkit.core.config.annotation.PropertieConfig;

@PropertieConfig(prefix = "lambkit.jwt")
public class JwtConfig {
	
	// 默认请求头标识符
	private String header = "Authorization";  	
	// 默认token前缀
    private String tokenPrefix = "Lambkit@"; 
    // 默认私钥
    private String secret = "n1gEgB3/NiGrOxdT9IxWxA=="; 
    // 默认失效时间(秒)
    private Long expirationSecond = 604800L;       
    private String cacheName = "jwtcache";
    
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public Long getExpirationSecond() {
		return expirationSecond;
	}
	public void setExpirationSecond(Long expirationSecond) {
		this.expirationSecond = expirationSecond;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
}
