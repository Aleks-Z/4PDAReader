package org.alex_z.app.a4pdareader.domain.entity;

import android.support.annotation.NonNull;

import java.net.URL;

public class NewsDomainEntity {
    @NonNull
    private String title;
    @NonNull
    private URL urlNews;

    private URL commentUrlNews;

    private String sourceHTML;

    public NewsDomainEntity(@NonNull String title, @NonNull URL urlNews, URL commentUrlNews, String sourceHTML) {
        this.title = title;
        this.urlNews = urlNews;
        this.commentUrlNews = commentUrlNews;
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

    public URL getCommentUrlNews() {
        return commentUrlNews;
    }

    public void setCommentUrlNews(URL commentUrlNews) {
        this.commentUrlNews = commentUrlNews;
    }

    public String getSourceHTML() {
        return sourceHTML;
    }

    public void setSourceHTML(String sourceHTML) {
        this.sourceHTML = sourceHTML;
    }
}
