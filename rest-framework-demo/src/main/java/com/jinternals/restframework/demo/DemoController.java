package com.jinternals.restframework.demo;

import com.jinternals.restframework.annotations.RequestMapping;
import com.jinternals.restframework.request.ContentType;

import static com.jinternals.restframework.request.ContentType.JSON;
import static com.jinternals.restframework.request.RequestMethod.GET;

@RequestMapping(path = "/api/demo")
public class DemoController {

    @RequestMapping(path = "/path",method = GET,produces = JSON)
    public Demo demo() {
        return new Demo();
    }

   static class Demo{
        String message = "Hello World";

       public String getMessage() {
           return message;
       }

       @Override
       public String toString() {
           return "Demo{" +
                   "message='" + message + '\'' +
                   '}';
       }
    }
}
