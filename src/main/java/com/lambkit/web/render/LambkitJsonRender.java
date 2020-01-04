package com.lambkit.web.render;

import com.jfinal.render.JsonRender;
import com.lambkit.common.LambkitConsts;

/**
 * @author Henry Yang 杨勇 (gismail@foxmail.com)
 * @version 1.0
 * @Package com.lambkit.web.render
 */
public class LambkitJsonRender extends JsonRender {

    static {
        excludedAttrs.add(LambkitConsts.ATTR_REQUEST);
        excludedAttrs.add(LambkitConsts.ATTR_CONTEXT_PATH);
        excludedAttrs.add(LambkitConsts.ATTR_TEMPLATE_PATH);
    }

    public LambkitJsonRender() {
    }

    public LambkitJsonRender(String key, Object value) {
        super(key, value);
    }

    public LambkitJsonRender(String[] attrs) {
        super(attrs);
    }

    public LambkitJsonRender(String jsonText) {
        super(jsonText);
    }

    public LambkitJsonRender(Object object) {
        super(object);
    }

}
