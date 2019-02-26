package com.serviceinfotech.services.microserviceexceptionhandler.filter;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRequestBodyWrapperTest {

    RequestWrapper requestWrapper;

    @Mock
    HttpServletRequest servletRequest;


    @Before
    public void setUp() throws Exception {
        Mockito.when(servletRequest.getCharacterEncoding()).thenReturn("UTF-8");
        String data = "3456789";
        Mockito.when(servletRequest.getInputStream()).thenReturn(new DelegatingServletInputStream(IOUtils.toInputStream(data)));
        requestWrapper = new RequestWrapper(servletRequest);
    }

    @Test
    public void shouldWrapHttpServletRequest() throws Exception {
        assertThat(IOUtils.toString(requestWrapper.getInputStream(),"UTF-8"), Is.is("3456789"));
        assertThat(requestWrapper.getRequestBody(), Is.is("3456789"));
    }
}