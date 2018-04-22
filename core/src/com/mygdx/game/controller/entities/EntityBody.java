package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

public abstract class EntityBody {

    /**
     * Box2d body
     */
    private final Body body;

    public EntityBody(World world, EntityModel model, boolean isDynamic) {
        BodyDef bodyDef = new BodyDef();
        if (isDynamic) {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        } else {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }


        bodyDef.position.set(model.getPosition());

        body = world.createBody(bodyDef);
        body.setUserData(model);


        /*BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());
      //  bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);*/

    }


    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);

    }

    public Object getUserData() {
        return body.getUserData();
    }

}
