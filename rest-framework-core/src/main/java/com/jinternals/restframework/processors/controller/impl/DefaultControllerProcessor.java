package com.jinternals.restframework.processors.controller.impl;

import com.jinternals.restframework.annotations.RequestMapping;
import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.processors.controller.ControllerProcessor;
import com.jinternals.restframework.request.RequestDefinition;
import com.jinternals.restframework.request.RequestDefinitionBuilder;
import com.jinternals.restframework.request.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Class.forName;

public class DefaultControllerProcessor implements ControllerProcessor {

    public List<RequestDefinition> processController(Config cls) {
       return cls.getControllers().stream().map( controller -> this.processController(controller) ) .collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
    }
    public List<RequestDefinition> processController(String cls) {
        List<RequestDefinition> requestDefinitions = new ArrayList<RequestDefinition>();
        try {
            Class cl = forName(cls);
            RequestDefinition classRequestDefinition = classLabelRequestDefinition(cl);
            Method[] methods = cl.getDeclaredMethods();

            for (Method method : methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String path = requestMapping.path();
                RequestMethod requestMethod = requestMapping.method()[0];

                RequestDefinition requestDefinition = new RequestDefinitionBuilder()
                        .setPath(classRequestDefinition.getPath() + path)
                        .setRequestMethod(requestMethod)
                        .setProduceContentType(requestMapping.produces())
                        .setMethod(method)
                        .setControllerClass(cl)
                        .build();

                requestDefinitions.add(requestDefinition);
            }
            return requestDefinitions;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private RequestDefinition classLabelRequestDefinition(Class cls) {
        RequestMapping requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            String path = requestMapping.path();

            RequestDefinition requestDefinition = new RequestDefinitionBuilder()
                    .setPath(path)
                    .setControllerClass(cls)
                    .build();
            return requestDefinition;
        }
        return new RequestDefinitionBuilder()
                .setPath("")
                .build();
    }
}
