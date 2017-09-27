package com.jinternals.restframework.request;

import java.lang.reflect.Method;

public class RequestDefinitionBuilder {
    private String path;
    private RequestMethod requestMethod;
    private Method method;
    private Class controllerClass;
    private String produceContentType;

    public RequestDefinitionBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public RequestDefinitionBuilder setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public RequestDefinitionBuilder setMethod(Method method) {
        this.method = method;
        return this;
    }

    public RequestDefinitionBuilder setControllerClass(Class controllerClass) {
        this.controllerClass = controllerClass;
        return this;
    }
    public RequestDefinitionBuilder setProduceContentType(String produceContentType) {
        this.produceContentType = produceContentType;
        return this;
    }



    public RequestDefinition build() {
        return new RequestDefinition(path, requestMethod, method, controllerClass, produceContentType);
    }
}