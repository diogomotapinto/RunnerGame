package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.entities.HeroBody;
import com.mygdx.game.model.GameModel;


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
        GameModel g = GameModel.getInstance();
        heroBody = new HeroBody(world, g.getHero(), true);

    }

    public static GameController getInstance() {


        if (instance == null)
            instance = new GameController();

        return instance;


    }

    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body b : bodies) {
            //verificar colisões
        }
    }

    public float getCameraPosition() {
        return xPosition;
    }


    public void setCameraPosition(float xPosition) {
        this.xPosition = xPosition;
    }
}

