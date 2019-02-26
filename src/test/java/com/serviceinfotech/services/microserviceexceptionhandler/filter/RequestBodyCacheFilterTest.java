package com.serviceinfotech.services.microserviceexceptionhandler.filter;

import org.apache.commons.io.IOUtils;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class RequestBodyCacheFilterTest {
    @InjectMocks
    RequestBodyCacheFilter requestBodyCacheFilter;

    @Mock
    HttpServletRequest servletRequest;

    @Mock
    HttpServletResponse servletResponse;

    @Mock
    FilterChain filterChain;

    @Captor
    private ArgumentCaptor argumentCaptor;

    @Before
    public void setUp() throws Exception {
        Mockito.when(servletRequest.getCharacterEncoding()).thenReturn("UTF-8");
        String data = "hdfhdfhdfhd";
        Mockito.when(servletRequest.getInputStream()).thenReturn(new DelegatingServletInputStream(IOUtils.toInputStream(data)));
    }

    @Test
    public void shouldFilter() throws Exception {
        Mockito.when(servletRequest.getMethod()).thenReturn("POST");
        requestBodyCacheFilter.doFilterInternal(servletRequest, servletResponse, filterChain);

        Mockito.verify(filterChain).doFilter((HttpServletRequest) argumentCaptor.capture(), ArgumentMatchers.any(HttpServletResponse.class));
        RequestWrapper requestWrapper = (RequestWrapper) argumentCaptor.getValue();
        Assert.assertTrue(argumentCaptor.getValue() instanceof RequestWrapper);
        Assert.assertThat(requestWrapper.getRequestBody(), Is.is("hdfhdfhdfhd"));
    }

    @Test
    public void shouldNotFilterThroughWrapper() throws Exception {
        Mockito.when(servletRequest.getMethod()).thenReturn("GET");
        requestBodyCacheFilter.doFilterInternal(servletRequest, servletResponse, filterChain);
        Mockito.verify(filterChain).doFilter((HttpServletRequest) argumentCaptor.capture(), ArgumentMatchers.any(HttpServletResponse.class));
        Assert.assertFalse(argumentCaptor.getValue() instanceof RequestWrapper);
    }

}