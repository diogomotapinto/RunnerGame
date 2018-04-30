package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.HeroBody;
import com.mygdx.game.controller.entities.MapBody;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.model.entities.MapModel;
import com.mygdx.game.view.GameView;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class GameController {
    private static GameController instance;
    private float xPosition;
    private final World world;
    private HeroBody heroBody;
    private MapBody mapBody;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;



    private GameController() {

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");


        xPosition = 0;
        MapModel mapModel = new MapModel();
        HeroModel heroModel = new HeroModel(200,25);
        world = new World(new Vector2(0, -10), true);

        heroBody = new HeroBody(world,heroModel, true );
        mapBody = new MapBody(world,mapModel,false);
        mapBody.createBody(map);

    }

    public HeroBody getHeroBody() {
        return heroBody;
    }

    public void update(float delta){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);


        world.step(1/60f,6,2);
        for (Body b : bodies){
            //verificar colisões


        }
    }

    public World getWorld() {
        return world;
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }


    public void jump(float delta){
        heroBody.getBody().applyLinearImpulse(new Vector2(0,3f / PIXEL_TO_METER), heroBody.getBody().getWorldCenter(), true);
    }

    public void run(float delta){
        if(heroBody.getBody().getLinearVelocity().x <= 2 || (heroBody.getBody().getPosition().y > 0)){
            heroBody.getBody().applyLinearImpulse(new Vector2(0.6f / PIXEL_TO_METER,0), heroBody.getBody().getWorldCenter(), true);
        }
    }

    public float getCameraPosition() {
        return xPosition;
    }






    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;
    }
}

