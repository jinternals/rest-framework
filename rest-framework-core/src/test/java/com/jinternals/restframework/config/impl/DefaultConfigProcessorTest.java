package com.jinternals.restframework.config.impl;

import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.config.ConfigProcessor;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class DefaultConfigProcessorTest {

    private ConfigProcessor configProcessor;
    
    @Before
    public void setUp() throws Exception {
        configProcessor = new DefaultConfigProcessor();
    }

    @Test
    public void shouldParseConfigFileToConfiObject() throws Exception {
       List<String> expectedControllers = newArrayList(
               "com.jinternals.restframework.DemoController",
                         "com.jinternals.restframework.TestController");
        Yaml yaml = new Yaml();
        Config config =  configProcessor.process("config.yml");
        assertThat(config.getControllers()).isEqualTo(expectedControllers);
    }
}
