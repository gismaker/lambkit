package com.lambkit.core.api.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiBody {
	Class<? extends ApiRender> value() default ApiRenderJson.class;
	String view() default "default";
}
