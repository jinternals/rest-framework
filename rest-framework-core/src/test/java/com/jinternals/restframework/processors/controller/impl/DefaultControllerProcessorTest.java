package com.jinternals.restframework.processors.controller.impl;


import com.jinternals.restframework.DemoController;
import com.jinternals.restframework.TestController;
import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.request.ContentType;
import com.jinternals.restframework.request.RequestDefinition;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static com.jinternals.restframework.request.ContentType.JSON;
import static com.jinternals.restframework.request.RequestMethod.GET;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultControllerProcessorTest {

    private DefaultControllerProcessor processor;

    private String CLASS_NAME1 = "com.jinternals.restframework.DemoController";
    private String CLASS_NAME2 = "com.jinternals.restframework.TestController";

    @Before
    public void setUp() throws Exception {
        processor = new DefaultControllerProcessor();
    }

    @Test
    public void shouldParseRequestMethodWithRequestMappingAnnotation() throws Exception {
        Config config = new Config();
        config.getControllers().add(CLASS_NAME1);
        config.getControllers().add(CLASS_NAME2);
        Method method1 = DemoController.class.getMethod("toString");
        Method method2 = TestController.class.getMethod("toString");
        List<RequestDefinition> requestDefinitions = processor.processController(config);


        assertThat(requestDefinitions.get(0).getProduceContentType()).isEqualTo(JSON);
        assertThat(requestDefinitions.get(1).getProduceContentType()).isEqualTo(JSON);

        assertThat(requestDefinitions.get(0).getControllerClass()).isEqualTo(DemoController.class);
        assertThat(requestDefinitions.get(1).getControllerClass()).isEqualTo(TestController.class);

        assertThat(requestDefinitions.get(0).getPath()).isEqualTo("/api/demo/path");
        assertThat(requestDefinitions.get(1).getPath()).isEqualTo("/api/test/path");

        assertThat(requestDefinitions.get(0).getMethod()).isEqualTo(method1);
        assertThat(requestDefinitions.get(1).getMethod()).isEqualTo(method2);

        assertThat(requestDefinitions.get(0).getHttpMethod()).isEqualTo(GET);
        assertThat(requestDefinitions.get(1).getHttpMethod()).isEqualTo(GET);

    }


}
