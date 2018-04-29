package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.HeroBody;
import com.mygdx.game.controller.entities.MapBody;
import com.mygdx.game.model.entities.MapModel;
import com.mygdx.game.view.GameView;


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
        world = new World(new Vector2(0, -10), true);

        heroBody = new HeroBody(world);

        mapBody = new MapBody(world,mapModel,false);
        mapBody.createBody(map);

    }

    public void update(float delta){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);



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

    public float getCameraPosition() {
        return xPosition;
    }






    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;
    }
}

