package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.HeroModel;

public class HeroBody extends EntityBody {


    public HeroBody(World world, HeroModel model, boolean isDynamic) {
        super(world, model, isDynamic, false);
        createFixtures(body, 1.27f, 6);
    }

}
