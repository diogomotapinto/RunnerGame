package com.mygdx.game.controller.entities;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

public class BulletBody extends EntityBody {

    public BulletBody(World world,  EntityModel model, boolean isDynamic) {
        super(world,  model, isDynamic, false);
        createFixtures(body, 7.874f, 2.5f);
    }

}
