package com.jinternals.restframework;


import com.jinternals.restframework.content.ContentHandler;
import com.jinternals.restframework.content.impl.DefaultContentHandler;
import com.jinternals.restframework.exception.RestFrameworkException;
import com.jinternals.restframework.request.RequestDefinition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RestFrameworkDispatcher extends HttpServlet {

    ContentHandler contentHandler = new DefaultContentHandler();


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter=resp.getWriter();

        String requestPath =  req.getRequestURI().substring(req.getContextPath().length());
        Map<String,RequestDefinition> requestDefinitionMap = (Map<String, RequestDefinition>) req.getServletContext().getAttribute(Constants.REQUEST_DEFINITIONS);
        RequestDefinition requestDefinition = requestDefinitionMap.get(requestPath);

        if(requestDefinition != null)
        {
            resp.setContentType(contentHandler.handleContentType(requestDefinition.getProduceContentType()));

            try {
                Object controllerObject = requestDefinition.getControllerClass().newInstance();
                Object object =  requestDefinition.getMethod().invoke(controllerObject);
                String content = contentHandler.handle(object,requestDefinition.getProduceContentType());
                printWriter.println(content);

            }catch (Exception e){
                throw  new RestFrameworkException(e.getMessage(),e);
            }finally {
                printWriter.close();
            }
        }
        else {
            resp.sendError(400);
        }

    }
}
