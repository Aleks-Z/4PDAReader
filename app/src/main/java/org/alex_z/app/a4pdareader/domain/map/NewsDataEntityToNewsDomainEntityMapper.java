package org.alex_z.app.a4pdareader.domain.map;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;

import java.net.MalformedURLException;
import java.net.URL;

import viper.Mapper;

public class NewsDataEntityToNewsDomainEntityMapper extends Mapper<NewsDataEntity, NewsDomainEntity> {
    @Override
    public NewsDomainEntity map(NewsDataEntity entity) {
        URL url = null;
        try {
            url = new URL(entity.getUrlNews());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new NewsDomainEntity(entity.getTitle(), entity.getDescription(), url);
    }
}
