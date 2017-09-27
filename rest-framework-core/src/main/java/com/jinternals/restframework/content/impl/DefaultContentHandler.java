package com.jinternals.restframework.content.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinternals.restframework.content.ContentHandler;

import static com.jinternals.restframework.request.ContentType.JSON;

public class DefaultContentHandler implements ContentHandler {
    private static ObjectMapper mapper = new ObjectMapper();

    public String handle(Object obj, String contentType) throws Exception {
        if (contentType.equals(JSON))
            return mapper.writeValueAsString(obj);

        return null;
    }

    @Override
    public String handleContentType(String contentType) {
        if(contentType==null)
        {
            return JSON;
        }

        return contentType;
    }
}
