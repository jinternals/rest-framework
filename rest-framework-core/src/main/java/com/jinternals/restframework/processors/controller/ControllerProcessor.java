package com.jinternals.restframework.processors.controller;

import com.jinternals.restframework.config.Config;
import com.jinternals.restframework.request.RequestDefinition;

import java.util.List;

public interface ControllerProcessor {


    List<RequestDefinition> processController(Config cls);


}
