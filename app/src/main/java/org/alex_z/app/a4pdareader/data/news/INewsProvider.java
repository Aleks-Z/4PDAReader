package org.alex_z.app.a4pdareader.data.news;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;

import java.net.URL;
import java.util.List;

import rx.Observable;

interface INewsProvider {
    @NonNull
    Observable<List<NewsDataEntity>> getNews(URL linkFeed, @Size(min = 0) int size);

    @NonNull
    Observable<List<NewsDataEntity>> getNews(URL linkFeed);

    @NonNull
    Observable<NewsDataEntity> getStreamNews(URL linkFeed, @Size(min = 0) int size);

    @NonNull
    Observable<NewsDataEntity> getStreamNews(URL linkFeed);
}
