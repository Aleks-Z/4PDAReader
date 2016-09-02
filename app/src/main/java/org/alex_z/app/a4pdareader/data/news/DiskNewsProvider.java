package org.alex_z.app.a4pdareader.data.news;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.util.Log;


import org.alex_z.app.a4pdareader.additional.greendao.GreenDaoProvider;
import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.greenrobot.greendao.rx.RxDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rx.Observable;

public class DiskNewsProvider implements INewsProvider, INewsStorage {

    private RxDao<NewsDataEntity, Long> rx =
            GreenDaoProvider
                    .getInstance()
                    .getDaoSession()
                    .getNewsDataEntityDao()
                    .rx();

    @NonNull
    @Override
    public Observable<List<NewsDataEntity>> getNews(URL linkFeed, @Size(min = 0) int size) {
        return rx.loadAll().limit(size);
    }

    @NonNull
    @Override
    public Observable<List<NewsDataEntity>> getNews(URL linkFeed) {
        return getNews(linkFeed, Integer.MAX_VALUE);
    }

    @NonNull
    @Override
    public Observable<NewsDataEntity> getStreamNews(URL linkFeed, @Size(min = 0) int size) {
        return null;
    }

    @NonNull
    @Override
    public Observable<NewsDataEntity> getStreamNews(URL linkFeed) {
        return getStreamNews(linkFeed, Integer.MAX_VALUE);
    }

    @Override
    public Observable<Void> saveNews(NewsDataEntity entity) {
        return Observable.create(subscriber -> {
            try {
                URL url = new URL(entity.getUrlNews());
                entity.setSourceHTML(getSource(url));
                rx.getDao().save(entity);
                //long count = rx.getDao().count();
                subscriber.onNext(null);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }

    private String getSource(URL url) throws IOException {
        StringBuilder htmlCode = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        "windows-1251")
        );
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            htmlCode.append(inputLine);
        }
        in.close();
        return htmlCode.toString();
    }
}
