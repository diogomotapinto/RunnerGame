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

/**
 * A concrete representation of a MapBody representing the map
 */
public class MapBody extends EntityBody {

    /**
     * Friction of the map
     */
    private static final float FRICTION = 0f;

    /**
     * Restitution of the map
     */
    private static final float RESTITUTION = 0.5f;

    /**
     * Layer with the floor
     */
    private static final int MAP_BODY_LAYER = 2;

    /**
     * Physics world for the map to be on
     */
    private final World world;

    /**
     * Class constructor
     *
     * @param world     the physical world this bullet belongs to
     * @param map       the model representing map
     * @param isDynamic boolean to know if the body is dynamic or static
     */
    public MapBody(World world, MapModel map, Boolean isDynamic) {
        super(world, map, isDynamic, true);
        this.world = world;
        MapModel mapModel = map;
    }

    /**
     * Creates the body of the map
     *
     * @param map tilemap to be parsed into a body
     */
    public void createBody(TiledMap map) {

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        Body body;
        FixtureDef fixtureDef = new FixtureDef();

        for (MapObject object : map.getLayers().get(MAP_BODY_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / PIXEL_TO_METER, (rect.getY() + rect.getHeight() / 2) / PIXEL_TO_METER);


            body = world.createBody(bodyDef);
            shape.setAsBox((rect.getWidth() / 2) / PIXEL_TO_METER, (rect.getHeight() / 2) / PIXEL_TO_METER);
            fixtureDef.shape = shape;
            fixtureDef.friction = FRICTION;
            fixtureDef.restitution = RESTITUTION;
            body.createFixture(fixtureDef);
        }
    }

}
