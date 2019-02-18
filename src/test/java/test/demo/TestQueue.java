package test.demo;

import com.lambkit.common.util.LimitQueue;

public class TestQueue {
	public static void main(String[] args) {  
        LimitQueue<String> lqueue = new LimitQueue<String>(3);  
          
        lqueue.offer("1");  
        lqueue.offer("2");  
        lqueue.offer("3");  
        lqueue.offer("4");  
          
        //1因超出队列大小限制已自动出队,输出结果为2,3,4  
        for (String string : lqueue.getQueue()) {  
            System.out.println(string);  
        }  
    }  
}
