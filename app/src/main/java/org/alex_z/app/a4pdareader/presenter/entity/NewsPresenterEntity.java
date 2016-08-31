package org.alex_z.app.a4pdareader.presenter.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.net.URL;

public class NewsPresenterEntity implements Serializable {
    @NonNull
    private String title;
    @NonNull
    private URL urlNews;

    private String sourceHTML;

    public NewsPresenterEntity(@NonNull String title, @NonNull URL urlNews, String sourceHTML) {
        this.title = title;
        this.urlNews = urlNews;
        this.sourceHTML = sourceHTML;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public URL getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(@NonNull URL urlNews) {
        this.urlNews = urlNews;
    }

    public String getSourceHTML() {
        return sourceHTML;
    }

    public void setSourceHTML(String sourceHTML) {
        this.sourceHTML = sourceHTML;
    }
}
