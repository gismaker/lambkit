##create bean,this is a class
#parse("/template/java_copyright.include")
package $!{basepackage};

import com.lambkit.core.config.annotation.PropertieConfig;

#parse("/template/java_author.include")
@PropertieConfig(prefix="lambkit.msch")
public class MschConfig {

	private String serverType = "server";
	private String version = "1.0";
	private String dbconfig;

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDbconfig() {
		return dbconfig;
	}

	public void setDbconfig(String dbconfig) {
		this.dbconfig = dbconfig;
	}
	
}
