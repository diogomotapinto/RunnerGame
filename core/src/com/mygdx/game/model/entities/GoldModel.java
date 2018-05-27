package com.mygdx.game.model.entities;

/**
 * A model representing a single gold
 */
public class GoldModel extends EntityModel {

    /**
     * Constructs a gold model belonging to a game model.
     *
     * @param x The x-coordinate of this gold
     * @param y The y-coordinate of this gold
     */
    public GoldModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.GOLD;
    }


}
