package org.alex_z.app.a4pdareader.presenter.app;

import org.alex_z.app.a4pdareader.additional.dagger.RxSchedulerModule;
import org.alex_z.app.a4pdareader.data.DataModule;
import org.alex_z.app.a4pdareader.domain.DomainModule;
import org.alex_z.app.a4pdareader.presenter.PresenterModule;
import org.alex_z.app.a4pdareader.presenter.app.main.news.NewsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import viper.ViewCallbacks;


@Singleton
@Component(modules = {
        RxSchedulerModule.class,
        DataModule.class,
        DomainModule.class,
        PresenterModule.class
})
public interface AppComponent {
    void inject(NewsFragment view);
}
