package com.mygdx.game.controller.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

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

    /**
     * Helper method to create a polygon fixture represented by a set of vertexes.
     *
     * @param body        The body the fixture is to be attached to.
     * @param vertexes    The vertexes defining the fixture in pixels so it is
     *                    easier to get them from a bitmap image.
     * @param width       The width of the bitmap the vertexes where extracted from.
     * @param height      The height of the bitmap the vertexes where extracted from.
     * @param density     The density of the fixture. How heavy it is in relation to its area.
     * @param friction    The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     * @param category
     * @param mask
     */
    final void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {
        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;              // scale from pixel to meter
        }

        PolygonShape circle = new PolygonShape();
        circle.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

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
        body.setTransform(x, y, angle);

    }

    public Object getUserData() {
        return body.getUserData();
    }

}
