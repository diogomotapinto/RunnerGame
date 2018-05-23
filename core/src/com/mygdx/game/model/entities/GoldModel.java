package com.mygdx.game.model.entities;

public class GoldModel extends EntityModel {
    public GoldModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.GOLD;
    }


}
