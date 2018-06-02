package com.mygdx.game.view.entities;

import com.mygdx.game.RunnerGame;
import com.mygdx.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for EntityView objects with cache
 */
public class ViewFactory {

    /**
     * Map that has model types as a key and the entity views correspondents
     */
    private static final Map<EntityModel.ModelType, EntityView> cache = new HashMap<EntityModel.ModelType, EntityView>();


    /**
     *
     * @param game the game this entity belongs
     * @param model of the entities
     * @return the entity view corresponding to the model passed as parameter
     */
    public static EntityView makeView(RunnerGame game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == EntityModel.ModelType.HERO) {
                cache.put(model.getType(), new HeroView(game));
            }
            if (model.getType() == EntityModel.ModelType.ENEMY) {
                cache.put(model.getType(), new EnemyView(game));
            }
            if (model.getType() == EntityModel.ModelType.BULLET) {
                cache.put(model.getType(), new BulletView(game));
            }
            if (model.getType() == EntityModel.ModelType.GOLD) {
                cache.put(model.getType(), new GoldView(game));
            }
        }
        return cache.get(model.getType());
    }
}
