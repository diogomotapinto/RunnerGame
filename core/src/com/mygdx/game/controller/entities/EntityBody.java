package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

/**
 * Wrapper class represebting an abstract physical body
 */
public abstract class EntityBody {

    /**
     * Box2d body
     */
    protected final Body body;

    /**
     * Physics world used in the game
     */
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
            bodyDef.position.set(model.getPosition().x / PIXEL_TO_METER, model.getPosition().y / PIXEL_TO_METER);
        }

        body = world.createBody(bodyDef);
        body.setUserData(model);

    }


    /**
     * Creates fixtures of the bodies
     *
     * @param body    the body to get a fixture applied to it
     * @param density of the body
     * @param size    of the body
     */
    void createFixtures(Body body, float density, float size) {
        CircleShape circle = new CircleShape();

        circle.setRadius(size / PIXEL_TO_METER);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;

        fixtureDef.density = density;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;


        body.createFixture(fixtureDef);
        circle.dispose();

    }


    /**
     * @return x position of the body
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * @return y position of the body
     */
    public float getY() {
        return body.getPosition().y;
    }


    /**
     * Used to move the body
     *
     * @param x     position in the x-axis
     * @param y     position in the y-axis
     * @param angle of the body to be changed
     */
    public void setTransform(float x, float y, float angle) {
        body.setTransform(x / PIXEL_TO_METER, y / PIXEL_TO_METER, angle);

    }

    /**
     * @return user data of the model
     */
    public Object getUserData() {
        return body.getUserData();
    }

    /**
     * @return body
     */
    public Body getBody() {
        return body;
    }
}
