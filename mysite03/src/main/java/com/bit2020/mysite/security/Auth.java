package com.bit2020.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	public enum Role{USER, ADMIN};
	Role value() default Role.USER;
	
	//String value() default "";	
	//boolean test() default false;
}
