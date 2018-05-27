package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public abstract class EntityBody {

    /**
     * Box2d body
     */
    final Body body;
    private final World world;

    EntityBody(World world, EntityModel model, boolean isDynamic, boolean isFloor) {
        BodyDef bodyDef = new BodyDef();

        if (isDynamic) {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        } else {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        this.world = world;

        if (!isFloor) {
            bodyDef.position.set(model.getX() / PIXEL_TO_METER, model.getY() / PIXEL_TO_METER);
        }

        body = world.createBody(bodyDef);
        body.setUserData(model);

    }

    void createFixtures(Body body, float density, float size) {
        CircleShape circle = new CircleShape();

        circle.setRadius(size / PIXEL_TO_METER);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;

        fixtureDef.density = density;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        // Create our fixture and attach it to the body
        body.createFixture(fixtureDef);
        circle.dispose();

    }


    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setTransform(float x, float y, float angle) {
        body.setTransform(x / PIXEL_TO_METER, y / PIXEL_TO_METER, angle);

    }

    public Object getUserData() {
        return body.getUserData();
    }

    public Body getBody() {
        return body;
    }
}
