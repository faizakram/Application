package com.app.util.custom.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.app.util.CommonConstants;

/**
 * Define allowed roles that can access the class or method
 * 
 *
 */
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AllowedRoles {

    /**
     * contains roles allowed
     * 
     * @return Array of String values 
     */
    public String[] values() default CommonConstants.DOUBLE_QUOTES;
}
