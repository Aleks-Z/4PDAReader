package org.alex_z.app.a4pdareader.domain.news;

import org.alex_z.app.a4pdareader.additional.dagger.Job;
import org.alex_z.app.a4pdareader.additional.dagger.UI;
import org.alex_z.app.a4pdareader.data.news.NetNewsProvider;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.domain.map.NewsDataEntityToNewsDomainEntityMapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import viper.Interactor;

public class NewsInteractor extends Interactor<URL, List<NewsDomainEntity>> {

    private NetNewsProvider netNewsProvider;

    /**
     * @param subscribeOn the Scheduler that modifies source Observable returned from {@link #createObservable} to perform its emissions
     *                    on.
     * @param observeOn   the Scheduler that modifies source Observable returned from {@link #createObservable} to notify its Observers
     *                    on.
     * @see #createObservable(Object)
     * @since 0.1.0
     */
    @Inject
    NewsInteractor(@Job Scheduler subscribeOn,
                   @UI Scheduler observeOn,
                   NetNewsProvider netNewsProvider) {
        super(subscribeOn, observeOn);
        this.netNewsProvider = netNewsProvider;
    }

    @Override
    protected Observable<List<NewsDomainEntity>> createObservable(URL linkFeed) {
        return netNewsProvider
                .getNews(linkFeed)
                .map(
                        newsDataEntities ->
                                new ArrayList<>(
                                        new NewsDataEntityToNewsDomainEntityMapper()
                                                .map(newsDataEntities)
                                )
                );
    }
}
