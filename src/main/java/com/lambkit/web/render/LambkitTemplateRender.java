package com.lambkit.web.render;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.render.RenderManager;
import com.jfinal.template.Engine;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.web.render
 */
public class LambkitTemplateRender extends LambkitRender {

	private static Engine engine;
	private static final String contentType = "text/html; charset=" + getEncoding();

    private Engine getEngine() {
        if (engine == null) {
            engine = RenderManager.me().getEngine();
        }
        return engine;
    }
    
    public LambkitTemplateRender(String view) {
        super(view);
    }
    
    /**
	 * 继承类可通过覆盖此方法改变 contentType，从而重用 velocity 模板功能
	 * 例如利用 velocity 实现  VelocityXmlRender
	 */
    public String getContentType() {
        return contentType;
    }
    
    @Override
    public void render() {
        response.setContentType(getContentType());

        Map<Object, Object> data = new HashMap<Object, Object>();
        for (Enumeration<String> attrs = request.getAttributeNames(); attrs.hasMoreElements(); ) {
            String attrName = attrs.nextElement();
            data.put(attrName, request.getAttribute(attrName));
        }

        String html = getEngine().getTemplate(view).renderToString(data);

        renderHtml(response, html, getContentType());
    }
}
