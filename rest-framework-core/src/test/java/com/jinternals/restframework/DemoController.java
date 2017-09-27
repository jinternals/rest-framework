package com.jinternals.restframework;

import com.jinternals.restframework.annotations.RequestMapping;
import com.jinternals.restframework.request.ContentType;

import static com.jinternals.restframework.request.ContentType.JSON;
import static com.jinternals.restframework.request.RequestMethod.*;

@RequestMapping(path = "/api/demo")
public class DemoController {


    @RequestMapping(path = "/path",method = GET, produces = JSON)
    public String toString() {
        return super.toString();
    }
}
