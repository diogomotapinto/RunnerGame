package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

public class TileBody extends EntityBody {
    public TileBody(World world, EntityModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);


        createFixtures(body, 0f,0f,0 , false, 16);

    }
}
