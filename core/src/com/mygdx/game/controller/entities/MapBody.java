package com.mygdx.game.controller.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.MapModel;

public class MapBody extends EntityBody{
    private World world;
    private MapModel mapModel;
    private Boolean isDynamic;


    public MapBody(World world, MapModel map, Boolean isDynamic){
        super(world, map, isDynamic);
        this.world = world;
        this.mapModel = map;
        this.isDynamic = isDynamic;
    }

    public void createBody(TiledMap map){


        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        Body body;
        FixtureDef fixtureDef = new FixtureDef();

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight() /2);


            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }


    }


}
