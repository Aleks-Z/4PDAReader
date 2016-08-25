package org.alex_z.app.a4pdareader.additional.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class RxSchedulerModule {

    @Provides
    @Job
    @Singleton
    Scheduler provideJobScheduler() {
        return Schedulers.newThread();
    }

    @Provides
    @UI
    @Singleton
    Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
