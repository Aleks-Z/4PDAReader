package org.alex_z.app.a4pdareader.domain.entity;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.NotNull;

public class NewsDomainEntity {
    @NonNull
    private String title;
    @NonNull
    private String description;

    public NewsDomainEntity(@NonNull String title, @NonNull String description) {
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
