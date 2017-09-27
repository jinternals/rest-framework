package com.jinternals.restframework.config;

import java.io.File;

public interface ConfigProcessor {

    Config process(String configPath)throws Exception;

}
