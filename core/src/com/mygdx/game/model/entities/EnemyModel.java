package com.mygdx.game.model.entities;

/**
 * A model representing a single enemy
 */
public class EnemyModel extends EntityModel {

    /**
     * Constructs a enemy model belonging to a game model.
     *
     * @param x The x-coordinate of this enemy
     * @param y The y-coordinate of this enemy
     */
    public EnemyModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.ENEMY;
    }
}
