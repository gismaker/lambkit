package com.lambkit.web.render;

import javax.servlet.ServletContext;

import com.jfinal.config.Constants;
import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;
import com.jfinal.template.Engine;

public class LambkitRenderFactory extends RenderFactory {

	protected MainRenderFactory mainRenderFactory;
	
	@Override
	public void init(Engine engine, Constants constants, ServletContext servletContext) {
		this.engine = engine;
		this.constants = constants;
		this.servletContext = servletContext;
		
		// create mainRenderFactory
		switch (constants.getViewType()) {
		case JFINAL_TEMPLATE:
			mainRenderFactory = new MainRenderFactory();
			break ;
		case FREE_MARKER:
			mainRenderFactory = new FreeMarkerRenderFactory();
			break ;
		case JSP:
			mainRenderFactory = new JspRenderFactory();
			break ;
		case VELOCITY:
			mainRenderFactory = new VelocityRenderFactory();
			break ;
		}
	}
	
	@Override
	public Render getRender(String view) {
		return mainRenderFactory.getRender(view);
	}
	
	public Render getXmlRender(String view) {
		return new LambkitXmlRender(view);
	}
    
    // --------
 	private static class MainRenderFactory {
 		public Render getRender(String view) {
 			return new LambkitTemplateRender(view);
 		}
 	}
 	
 	private static class FreeMarkerRenderFactory extends MainRenderFactory {
 		public Render getRender(String view) {
 			return new LambkitFreeMarkerRender(view);
 		}
 	}
 	
 	private static class JspRenderFactory extends MainRenderFactory {
 		public Render getRender(String view) {
 			return new LambkitJspRender(view);
 		}
 	}
 	
 	private static class VelocityRenderFactory extends MainRenderFactory {
 		public Render getRender(String view) {
 			return new LambkitVelocityRender(view);
 		}
 	}

}
