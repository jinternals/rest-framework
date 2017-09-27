package com.jinternals.restframework;

import com.jinternals.restframework.annotations.RequestMapping;

import static com.jinternals.restframework.request.ContentType.JSON;
import static com.jinternals.restframework.request.RequestMethod.GET;

@RequestMapping(path = "/api/test")
public class TestController  {


    @RequestMapping(path = "/path",method = GET,produces = JSON)
    public String toString() {
        return super.toString();
    }
}
