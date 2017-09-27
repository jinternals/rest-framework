package com.jinternals.restframework.annotations;

import com.jinternals.restframework.request.ContentType;
import com.jinternals.restframework.request.RequestMethod;

import java.lang.annotation.*;

import static com.jinternals.restframework.request.ContentType.JSON;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String path() default "";

    RequestMethod[] method() default {};

    String produces() default JSON;

}
