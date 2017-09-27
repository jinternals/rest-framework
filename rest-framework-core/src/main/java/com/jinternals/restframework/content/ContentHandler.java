package com.jinternals.restframework.content;

public interface ContentHandler {
    public String handle(Object obj, String contentType) throws Exception ;
    public String handleContentType(String contentType);

}
