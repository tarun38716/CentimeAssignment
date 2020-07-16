package com.centime.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface LogMethodParam {
	
	public enum Logger {
	    INFO,
	    DEBUG;
	}
	
	Logger logLevel() default Logger.INFO;
	
	

}
