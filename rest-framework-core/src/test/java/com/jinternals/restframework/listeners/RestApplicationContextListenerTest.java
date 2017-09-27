package com.jinternals.restframework.listeners;

import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.config.ConfigProcessor;
import com.jinternals.restframework.processors.controller.impl.DefaultControllerProcessor;
import com.jinternals.restframework.request.RequestDefinition;
import org.assertj.core.internal.Maps;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import java.io.File;
import java.util.HashMap;

import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestApplicationContextListenerTest {


    private RestApplicationContextListener listener;

    @Mock
    private ServletContextEvent contextEvent;

    @Mock
    private DefaultControllerProcessor controllerProcessor;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ConfigProcessor configProcessor;

    @Mock
    private Config config;

    @Before
    public void setUp() throws Exception {

        listener = new RestApplicationContextListener(configProcessor,controllerProcessor);
    }

    @Test
    public void shouldInitializeRestApplicationContextListener() throws Exception {
        when(config.getControllers()).thenReturn(newArrayList("com.jinternals.restframework.DemoController"));
        when(contextEvent.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("config")).thenReturn("classpath:config.yml");

        when(configProcessor.process(any(String.class))).thenReturn(config);

        when(controllerProcessor.processController(config)).thenReturn(newArrayList());


        listener.contextInitialized(contextEvent);

        verify(servletContext,times(1)).setAttribute("config",config);
        verify(servletContext,times(1)).setAttribute("requestDefinitions",new HashMap<String,RequestDefinition>());


        verify(servletContext,times(1)).getInitParameter("config");

    }
}
