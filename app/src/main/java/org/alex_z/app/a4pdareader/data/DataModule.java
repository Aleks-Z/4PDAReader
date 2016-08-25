package org.alex_z.app.a4pdareader.data;

import org.alex_z.app.a4pdareader.data.news.DiskNewsProvider;
import org.alex_z.app.a4pdareader.data.news.NetNewsProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataModule {
    @Provides
    @Singleton
    NetNewsProvider providerNetNewsProvider() {
        return new NetNewsProvider();
    }

    @Provides
    @Singleton
    DiskNewsProvider providerDiskNewsProvider() {
        return new DiskNewsProvider();
    }
}
