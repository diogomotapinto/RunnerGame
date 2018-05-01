package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.HeroModel;

public class HeroBody extends EntityBody {



    public HeroBody(World world, HeroModel model, boolean isDynamic) {
        super(world, model, isDynamic,false);

        createFixtures(body,1.27f,0f,0.6f);

    }

    public Body getBody() {
        return body;
    }

}
