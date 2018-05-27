package com.mygdx.game.controller.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.MapModel;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;

public class MapBody extends EntityBody {
    private final World world;
    //private MapModel mapModel;
    //private Boolean isDynamic;


    public MapBody(World world, MapModel map, Boolean isDynamic) {
        super(world, map, isDynamic, true);
        this.world = world;
        MapModel mapModel = map;
        //this.isDynamic = isDynamic;
    }

    public void createBody(TiledMap map) {

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        Body body;
        FixtureDef fixtureDef = new FixtureDef();

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / PIXEL_TO_METER, (rect.getY() + rect.getHeight() / 2) / PIXEL_TO_METER);


            body = world.createBody(bodyDef);
            shape.setAsBox((rect.getWidth() / 2) / PIXEL_TO_METER, (rect.getHeight() / 2) / PIXEL_TO_METER);
            fixtureDef.shape = shape;
            //fixtureDef.density = .5f;      // how heavy is the ground
            fixtureDef.friction = 0f;    // how slippery is the ground
            fixtureDef.restitution = .5f; // how bouncy is the ground
            body.createFixture(fixtureDef);
        }


    }


}
