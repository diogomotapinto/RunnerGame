package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.GoldModel;

/**
 * A concrete representation of an MapBody representing the enemy
 */
public class GoldBody extends EntityBody {


    public GoldBody(World world, GoldModel model, boolean isDynamic) {
        super(world,  model, isDynamic, false);
        createFixtures(body, 0f, 6);
    }

    public Body getBody() {
        return body;
    }

}
