package org.alex_z.app.a4pdareader.data.map;

import com.einmalfel.earl.RSSItem;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;

import viper.Mapper;

public class RSSItemToNewsDataEntityMapper extends Mapper<RSSItem, NewsDataEntity> {
    @Override
    public NewsDataEntity map(RSSItem entity) {
        //TODO
        System.out.println(entity.toString());
        return new NewsDataEntity(entity.getTitle(), entity.getDescription());
    }
}
