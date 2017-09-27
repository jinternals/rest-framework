package com.jinternals.restframework.config;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private List<String> controllers = new ArrayList<String>();
    public List<String> getControllers() {
        return this.controllers;
    }

    public void setControllers(List<String> controllers) {
        this.controllers = controllers;
    }
}
