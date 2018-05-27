package com.mygdx.game.model.entities;

/**
 * A model representing the hero
 */
public class HeroModel extends EntityModel {

    /**
     * Constructs a hero model belonging to a game model.
     *
     * @param x The x-coordinate of this hero
     * @param y The y-coordinate of this hero
     */
    public HeroModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.HERO;
    }
}
