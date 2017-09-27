package com.jinternals.restframework.listeners;

import com.jinternals.restframework.Constants;
import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.config.ConfigProcessor;
import com.jinternals.restframework.config.impl.DefaultConfigProcessor;
import com.jinternals.restframework.exception.RestFrameworkException;
import com.jinternals.restframework.processors.controller.ControllerProcessor;
import com.jinternals.restframework.processors.controller.impl.DefaultControllerProcessor;
import com.jinternals.restframework.request.RequestDefinition;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jinternals.restframework.Constants.CONFIG;
import static com.jinternals.restframework.Constants.REQUEST_DEFINITIONS;

public class RestApplicationContextListener implements ServletContextListener {

    private ConfigProcessor configProcessor;
    private ControllerProcessor controllerProcessor;

    public RestApplicationContextListener() {
        this.configProcessor = new DefaultConfigProcessor();
        this.controllerProcessor = new DefaultControllerProcessor();
    }

    public RestApplicationContextListener(ConfigProcessor configProcessor, DefaultControllerProcessor controllerProcessor) {
        this.configProcessor = configProcessor;
        this.controllerProcessor = controllerProcessor;
    }


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        String configFileName = ctx.getInitParameter("config");
        Config config = null;
        try {
            config = configProcessor.process(configFileName);
        } catch (Exception e) {
            throw new RestFrameworkException("Config loading failed", e);
        }
        ctx.setAttribute(CONFIG, config);

        List<RequestDefinition> requestDefinitions = controllerProcessor.processController(config);
        Map<String,RequestDefinition> requestDefinitionMap = new HashMap<>();
        requestDefinitions.forEach(requestDefinition -> requestDefinitionMap.put(requestDefinition.getPath(),requestDefinition));
        ctx.setAttribute(REQUEST_DEFINITIONS, requestDefinitionMap);




    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
