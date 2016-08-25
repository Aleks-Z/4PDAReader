package org.alex_z.app.a4pdareader.data.news;

import android.support.annotation.NonNull;
import android.support.annotation.Size;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.RSSFeed;
import com.einmalfel.earl.RSSItem;
import com.google.common.io.Closer;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.alex_z.app.a4pdareader.data.map.RSSItemToNewsDataEntityMapper;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import rx.Observable;

public class NetNewsProvider implements INewsProvider {

    @NonNull
    @Override
    public Observable<List<NewsDataEntity>> getNews(URL linkFeed, @Size(min = 0) int size) {
        return Observable.create(subscriber -> {
            InputStream in = null;
            try {
                in = linkFeed.openStream();
                Feed feed = EarlParser.parseOrThrow(in, size);
                if (!RSSFeed.class.isInstance(feed))
                    throw new DataFormatException("Feed isn't RSS type");
                RSSFeed rssFeed = (RSSFeed) feed;
                subscriber.onNext(new ArrayList<>(new RSSItemToNewsDataEntityMapper().map(rssFeed.items)));
                subscriber.onCompleted();
            } catch (DataFormatException | XmlPullParserException | IOException e) {
                subscriber.onError(e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {

                    }
                }
            }

        });
    }

    @NonNull
    @Override
    public Observable<List<NewsDataEntity>> getNews(URL linkFeed) {
        return getNews(linkFeed, Integer.MAX_VALUE);
    }

    @NonNull
    @Override
    public Observable<NewsDataEntity> getStreamNews(URL linkFeed, @Size(min = 0) int size) {
        //TODO
        throw new UnsupportedOperationException("don't work. IDDQD x2");
    }

    @NonNull
    @Override
    public Observable<NewsDataEntity> getStreamNews(URL linkFeed) {
        return getStreamNews(linkFeed, Integer.MAX_VALUE);
    }
}
