package com.jinternals.restframework.request;

import java.lang.reflect.Method;

public class RequestDefinition {

    private String path;
    private RequestMethod requestMethod;
    private Method method;
    private Class controllerClass;
    private String produceContentType;


    public RequestDefinition() {
    }

    public RequestDefinition(String path, RequestMethod requestMethod, Method method, Class controllerClass, String produceContentType) {
        this.path = path;
        this.requestMethod = requestMethod;
        this.method = method;
        this.controllerClass = controllerClass;
        this.produceContentType = produceContentType;
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getHttpMethod() {
        return requestMethod;
    }

    public Method getMethod() {
        return method;
    }

    public Class getControllerClass() {
        return controllerClass;
    }

    public String getProduceContentType() {
        return produceContentType;
    }
}
