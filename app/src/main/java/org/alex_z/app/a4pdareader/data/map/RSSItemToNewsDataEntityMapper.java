package org.alex_z.app.a4pdareader.data.map;

import com.einmalfel.earl.RSSItem;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;

import viper.Mapper;

public class RSSItemToNewsDataEntityMapper extends Mapper<RSSItem, NewsDataEntity> {
    @Override
    public NewsDataEntity map(RSSItem entity) {
        assert entity.comments != null;
        return new NewsDataEntity(entity.getTitle(), entity.getLink(), entity.comments.toString(), null);
    }
}
