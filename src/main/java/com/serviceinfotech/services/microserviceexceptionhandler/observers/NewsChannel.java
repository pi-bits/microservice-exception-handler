package com.serviceinfotech.services.microserviceexceptionhandler.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewsChannel implements PropertyChangeListener {
    private String news;

    public String getNews() {
        return news;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.news = (String) evt.getNewValue();
    }
}
