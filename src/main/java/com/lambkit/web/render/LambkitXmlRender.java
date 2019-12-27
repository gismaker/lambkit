package com.lambkit.web.render;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.web.render
 */
public class LambkitXmlRender extends LambkitTemplateRender {

    private static final String contentType = "text/xml; charset=" + getEncoding();

    public LambkitXmlRender(String view) {
        super(view);
    }

    public String getContentType() {
        return contentType;
    }
}
