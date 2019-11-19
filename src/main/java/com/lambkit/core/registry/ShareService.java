package com.lambkit.core.registry;

import com.jfinal.kit.StrKit;

/**
 * 该类用于封装提供服务类消息
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.core.registry
 */
public class ShareService {

	//服务接口/type/target/method
	private String type;
	private String target;
	private String method;
    //服务提供者地址
    private String IP;
    //服务提供者的端口号；
    private int port;
    
    public String getPath() {
    	return this.type + "/" + this.target + "/" + this.method;
    }
    
    public void setPath(String path) {
    	if(StrKit.isBlank(path)) return;
    	String[] paths = path.split("/");
    	this.type = paths.length > 0 ? paths[0] : null;
    	this.target = paths.length > 1 ? paths[1] : null;
    	this.method = paths.length > 2 ? paths[2] : null;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return this.type + "/" + this.target + "/" + this.method + " IP: " + this.IP + " port: " + this.port;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
