package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

public class EnemyBody extends EntityBody {

    public EnemyBody(World world, EntityModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, 1.27f, 6);
    }

}
