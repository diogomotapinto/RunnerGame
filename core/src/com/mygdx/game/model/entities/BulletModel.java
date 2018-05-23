package com.mygdx.game.model.entities;

public class BulletModel extends EntityModel {

    public BulletModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.BULLET;
    }
}
