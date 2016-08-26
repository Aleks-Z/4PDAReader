package org.alex_z.app.a4pdareader.presenter.entity;

import android.support.annotation.NonNull;

import java.net.URL;

public class NewsPresenterEntity {
    @NonNull
    private String title;
    @NonNull
    private String description;

    private URL urlNews;

    public NewsPresenterEntity(@NonNull String title, @NonNull String description, URL urlNews) {
        this.title = title;
        this.description = description;
        this.urlNews = urlNews;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public URL getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(URL urlNews) {
        this.urlNews = urlNews;
    }

}
