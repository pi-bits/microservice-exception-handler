package com.serviceinfotech.services.microserviceexceptionhandler.observbales;

import com.serviceinfotech.services.microserviceexceptionhandler.observers.NewsChannel;

import java.beans.PropertyChangeSupport;

public class NewsAgency {
    private PropertyChangeSupport propertyChangeSupport;
    private String news;


    public NewsAgency() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void setNews(String news) {
        propertyChangeSupport.firePropertyChange("news", this.news, news);
        this.news = news;
    }

    public String getNews() {
        return news;
    }

    public void addObserver(NewsChannel newsChannel) {
        propertyChangeSupport.addPropertyChangeListener(newsChannel);
    }

    public void removeObserver(NewsChannel newsChannel) {
        propertyChangeSupport.removePropertyChangeListener(newsChannel);
    }
}
