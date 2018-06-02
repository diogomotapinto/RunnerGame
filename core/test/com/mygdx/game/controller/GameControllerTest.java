package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.model.GameModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest extends GameTest {


    GameModel gameModel;
    GameController gameController;

    /**
     * Tests if the score doesn't change randomly
     */
    @Test
    public void getScore() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);
        assertEquals(0, gameController.getScore());

        this.gameController.run();


        assertEquals(0, gameController.getScore());


    }

    /**
     * Tests if the hero changes his linearvelocity in the y-axis
     */
    @Test
    public void jump() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);

        this.gameController.jump();
        assertEquals(true,gameController.getHeroBody().getBody().getLinearVelocity().y>0);

    }

    /**
     * Tests if the hero changes his linearvelocity in the x-axis
     */
    @Test
    public void run() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);

        this.gameController.run();
        assertEquals(true,gameController.getHeroBody().getBody().getLinearVelocity().x>0);

    }


    /**
     * Tests if a bullet is created
     */
    @Test
    public void shoot() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        gameModel = new GameModel();
        gameController = new GameController(map, gameModel);

        this.gameController.shoot();
        assertEquals(true,gameModel.getBullets() != null);

    }


}