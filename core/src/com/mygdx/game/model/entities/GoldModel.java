package com.mygdx.game.model.entities;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public class GoldModel extends EntityModel {
    public GoldModel(float x, float y) {
        super(x, y);
    }

    @Override
    public ModelType getType() {
        return ModelType.GOLD;
    }



}
