package test.method;

public class TestMethodObj {

	public void setName(String name) {
		System.out.println("name: " + name);
	}
	
	public void setInfo(String key, String info) {
		System.out.println("info: " + key + ", " + info);
	}
	
	public void setState(String name, String msg, int state) {
		System.out.println("state: " + name + ", " + msg + ", " + state);
	}
	
	public void setStatus() {
		System.out.println("status: 0");
	}
}
