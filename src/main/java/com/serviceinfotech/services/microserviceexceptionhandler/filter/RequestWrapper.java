package com.serviceinfotech.services.microserviceexceptionhandler.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

import static java.util.stream.Collectors.joining;

public class RequestWrapper extends RequestBodyWrapper {

    private String requestBody;

    private ServletInputStream servletInputStream;


    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = readBodyFromRequest(request);
    }

    private String readBodyFromRequest(HttpServletRequest request) throws IOException {

        BufferedReader bufferedReader = null;

        try {

            InputStream inputStream = request.getInputStream();
            servletInputStream = (ServletInputStream) inputStream;

            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, request.getCharacterEncoding()));
                requestBody = bufferedReader.lines().collect(joining(System.lineSeparator()));
            } else {
                requestBody = null;
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return requestBody;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());

        try (ServletInputStream servletInputStream = new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return RequestWrapper.this.servletInputStream.isFinished();
            }

            @Override
            public boolean isReady() {
                return RequestWrapper.this.servletInputStream.isReady();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new RuntimeException("Not implemented");
            }


            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        }) {

            return servletInputStream;
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getRequestBody() {
        return requestBody;
    }

}
