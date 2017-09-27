package com.jinternals.restframework.config.impl;

import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.config.ConfigProcessor;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class DefaultConfigProcessor implements ConfigProcessor{

    public Config process(String configPath) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource(configPath);
        Yaml yaml = new Yaml();
        Config config = yaml.loadAs(  classPathResource.getInputStream(), Config.class );
        return config;
    }

}
