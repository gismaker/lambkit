package com.lambkit.core.hearbeat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lambkit.core.hearbeat.HeartBeatFrequency;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface HeartBeatTask {
	HeartBeatFrequency frequency() default HeartBeatFrequency.FIVE;
}
