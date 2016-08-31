package org.alex_z.app.a4pdareader.domain.news;

import org.alex_z.app.a4pdareader.additional.dagger.Job;
import org.alex_z.app.a4pdareader.additional.dagger.UI;
import org.alex_z.app.a4pdareader.data.news.DiskNewsProvider;
import org.alex_z.app.a4pdareader.data.news.NetNewsProvider;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.domain.map.NewsDomainEntityToNewsDataEntityMapper;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import viper.Interactor;

public class SaveNewsInteractor extends Interactor<NewsDomainEntity, Void> {
    private DiskNewsProvider diskNewsProvider;

    /**
     * @param subscribeOn the Scheduler that modifies source Observable returned from {@link #createObservable} to perform its emissions
     *                    on.
     * @param observeOn   the Scheduler that modifies source Observable returned from {@link #createObservable} to notify its Observers
     *                    on.
     * @see #createObservable(Object)
     * @since 0.1.0
     */
    @Inject
    SaveNewsInteractor(@Job Scheduler subscribeOn,
                       @UI Scheduler observeOn,
                       DiskNewsProvider diskNewsProvider) {
        super(subscribeOn, observeOn);
        this.diskNewsProvider = diskNewsProvider;
    }

    @Override
    protected Observable<Void> createObservable(NewsDomainEntity newsDomainEntity) {
        return diskNewsProvider.saveNews(new NewsDomainEntityToNewsDataEntityMapper().map(newsDomainEntity));
    }
}
