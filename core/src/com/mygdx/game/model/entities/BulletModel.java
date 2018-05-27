package com.mygdx.game.model.entities;

/**
 * A model representing a single bullet
 */
public class BulletModel extends EntityModel {

    /**
     * Constructs a bullet model belonging to a game model.
     *
     * @param x The x-coordinate of this bullet
     * @param y The y-coordinate of this bullet
     */
    public BulletModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.BULLET;
    }
}
