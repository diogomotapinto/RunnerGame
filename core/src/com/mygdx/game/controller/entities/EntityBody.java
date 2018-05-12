package com.mygdx.game.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;

import java.awt.geom.RectangularShape;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public abstract class EntityBody {
    protected World world;

    /**
     * Box2d body
     */
    final Body body;

    public EntityBody(World world, EntityModel model, boolean isDynamic, boolean isFloor){
        BodyDef bodyDef = new BodyDef();

        if(isDynamic){
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }else{
            bodyDef.type = BodyDef.BodyType.KinematicBody;
        }
        this.world = new World(new Vector2(0, -10), true);

        if(!isFloor) {
            bodyDef.position.set(model.getX()/ PIXEL_TO_METER,model.getY()/ PIXEL_TO_METER);
        }

        body = world.createBody(bodyDef);
        body.setUserData(model);

    }

    void createFixtures(Body body, float density, float friction, float restitution, boolean isCircle){
        CircleShape circle = new CircleShape();
        PolygonShape rect = new PolygonShape();

        if(isCircle) {

            circle.setRadius(6f / PIXEL_TO_METER);
        }else
        {
            rect.setAsBox(16/PIXEL_TO_METER,4/PIXEL_TO_METER);
        }


        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        if(isCircle){
            fixtureDef.shape = circle;
        }else {
            fixtureDef.shape = rect;
        }

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;

        // Create our fixture and attach it to the body
        body.createFixture(fixtureDef);
        circle.dispose();

    }


    public float getX(){
        return body.getPosition().x;
    }

    public float getY(){
        return body.getPosition().y;
    }

    public void setTransform(float x, float y, float angle){
        body.setTransform(x,y,angle);

    }

    public Object getUserData(){
        return body.getUserData();
    }

    public Body getBody() {
        return body;
    }
}
