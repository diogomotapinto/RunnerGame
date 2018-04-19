package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.HeroBody;


public class GameController {
    private static GameController instance;
    private float xPosition;
    private final World world;
    private HeroBody heroBody;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;



    private GameController() {
        xPosition = 0;



        world = new World(new Vector2(0, 0), true);

        heroBody = new HeroBody(world);


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

