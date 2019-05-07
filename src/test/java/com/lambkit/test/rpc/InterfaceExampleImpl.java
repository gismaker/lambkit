package com.lambkit.test.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.zbus.rpc.Doc;
import io.zbus.rpc.Remote;
import io.zbus.transport.http.Message;

@Remote
public class InterfaceExampleImpl implements InterfaceExample{
 
	@Override 
	public String echo(String string) { 
		return string;
	}
	
	public String getString(String name) { 
		if(name == null){
			System.out.println("got null: "+ name);
		}
		return "Hello World ZBUS " + name;
	}
	
	public String getString(String name, int c) {
		return String.format("%s, %d", name, c);
	}

	@Override
	public String testEncoding() { 
		return "中文";
	}
	
	public String[] stringArray() {
		return new String[]{"hong", "leiming"};
	}
	
	@Override
	public byte[] getBin() {
		return new byte[10];
	}
	
	public Object[] objectArray(String id){  
		return new Object[]{id, getUser("rushmore"), "hong", true, 1, String.class};
	}
	
	
	public int plus(int a, int b) { 
		return a+b;
	}
	
	@Override
	public MyEnum myEnum(MyEnum e) {  
		return MyEnum.Sunday;
	}
	
	
	public User getUser(String name) {
		User user = new User();
		user.setName(name);
		user.setPassword("password"+System.currentTimeMillis());
		user.setAge(new Random().nextInt(100));
		user.setItem("item_1");
		user.setRoles(Arrays.asList("admin", "common"));	
		user.getAttrs().put("extAttr1", "XAttr1");
		user.getAttrs().put("extAttr2", "XAttr2");
		return user;
	}
	
	
	public Order getOrder() {
		Order order = new Order();
		order.setItem(Arrays.asList("item1","item2","item3"));
		order.setData("bin".getBytes());
		return order;
	}
	
	
	public User[] getUsers() {
		return new User[]{getUser("hong"), getUser("leiming")};
	}
	 
	@Doc("测试类") 
	public Map<String, Object> map(
		@Doc("size of map") 
		int value1
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("key1", value1);
		res.put("key2", "value2");
		res.put("key3", 2.5);
		return res;
	}
	
	
	public List<Map<String, Object>> listMap() {
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		res.add(map(1));
		res.add(map(2));
		res.add(map(3));
		return res;
	}
	
	
	public int saveObjectArray(Object[] array) {
		return 0;
	}
	
	@Override
	public int saveUserArray(User[] array) { 
		return 0;
	}
	
	@Override
	public int saveUserList(List<User> array) { 
		return 0;
	}
	
	public void throwException() {
		throw new RuntimeException("runtime exception from server");
	}
	public void throwNullPointerException(){
		throw new NullPointerException("null pointer");
	}
	
	public void throwUnkownException() {  
		throw new PrivateRuntimeException("private runtime exeption");
	}
	
	
	public void noReturn() {
		System.out.println("called noReturn");
	}
	
	
	public Class<?> classTest(Class<?> inClass) { 
		return Double.class;
	}
	
	@Override
	public void testTimeout() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	  
	@Override
	public int getUserScore() { 
		Random r = new Random(System.currentTimeMillis());
		int time = 10 + r.nextInt(100); 
		return time;
	}
	
	@Override
	public String nullParam(String nullStr) { 
		return nullStr;
	}
	
	@Override
	public Message raw(String name) {
		Message message = new Message();
		message.setBody(name);
		return message;
	}
	
	@Override
	public Message raw0(Message req) { 
		req.setBody("raw requested " + System.currentTimeMillis());
		return req;
	}
	
	@Override
	public Message raw1(int i, Message req) {
		Message res = new Message();
		res.setBody(i + ":" + req.getHeader("topic"));
		return res;
	}
	
	@Override
	public Message redirect() {
		Message res = new Message();
		res.setStatus(302);
		res.setHeader("location", "/");
		return res;
	}
}



class PrivateRuntimeException extends RuntimeException{  
	private static final long serialVersionUID = 4587336984841564800L;

	public PrivateRuntimeException() {
		super(); 
	}

	public PrivateRuntimeException(String message, Throwable cause) {
		super(message, cause); 
	}

	public PrivateRuntimeException(String message) {
		super(message); 
	}

	public PrivateRuntimeException(Throwable cause) {
		super(cause); 
	}
	
}