package org.alex_z.app.a4pdareader.presenter.entity;

import android.support.annotation.NonNull;

public class NewsPresenterEntity {
    @NonNull
    private String title;
    @NonNull
    private String description;

    public NewsPresenterEntity(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
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
}
