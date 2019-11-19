

import com.jfinal.config.Routes;
import com.lambkit.LambkitApplicationContext;
import com.lambkit.LambkitApplication;
import com.lambkit.module.LambkitModule;

import test.controller.IndexController;

public class TestApplicationStart extends LambkitApplicationContext {
	@Override
	public void configModule(LambkitModule module) {
		LambkitModule config = new LambkitModule() {
    		@Override
    		public void configRoute(Routes me) {
    			// TODO Auto-generated method stub
    			me.add("/", IndexController.class, "");
    		}
		};
		module.addModule(config);
	}
	
	public static void main(String[] args) {
		LambkitApplication.run(TestApplicationStart.class, null);
	}
}
