package com.serviceinfotech.services.microserviceexceptionhandler.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public abstract class RequestBodyWrapper extends HttpServletRequestWrapper {

    public RequestBodyWrapper(HttpServletRequest request) {
        super(request);
    }

    public abstract String getRequestBody() throws IOException;
}