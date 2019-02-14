package com.serviceinfotech.services.microserviceexceptionhandler.observers;

import com.serviceinfotech.services.microserviceexceptionhandler.observbales.NewsAgency;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewsObserverTest {

    private NewsChannel newsChannel;
    private NewsAgency newsAgency;

    @Before
    public void setUp() throws Exception {
        newsChannel = new NewsChannel();
        newsAgency = new NewsAgency();

    }

    @Test
    public void shouldReceiveChangesByObserver() throws Exception {
        newsAgency.addObserver(newsChannel);
        newsAgency.setNews("some news");
        Assert.assertThat("some news", Is.is(newsChannel.getNews()));
    }

    @Test
    public void shouldNotReceiveChangesByObserver() throws Exception {
        newsAgency.removeObserver(newsChannel);
        newsAgency.setNews("some new news");
        Assert.assertNull(newsChannel.getNews());
    }

}