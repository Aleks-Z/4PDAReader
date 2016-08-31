package org.alex_z.app.a4pdareader.data.news;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;

import rx.Observable;

public interface INewsStorage {
    Observable<Void> saveNews(NewsDataEntity entity);
}
