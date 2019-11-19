package test.controller;

import com.lambkit.web.controller.LambkitController;

public class IndexController extends LambkitController {

	public void index() {
		renderText("hello world!");
	}
}
