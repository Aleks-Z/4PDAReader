package org.alex_z.app.a4pdareader.domain.map;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;

import java.net.MalformedURLException;
import java.net.URL;

import viper.Mapper;

public class NewsDataEntityToNewsDomainEntityMapper extends Mapper<NewsDataEntity, NewsDomainEntity> {
    @Override
    public NewsDomainEntity map(NewsDataEntity entity) {
        URL url, commentUrl;
        try {
            url = new URL(entity.getUrlNews());
            commentUrl = new URL(entity.getCommentUrlNews());
            return new NewsDomainEntity(entity.getTitle(), url, commentUrl, entity.getSourceHTML());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL isn't valid");
        }
    }
}
