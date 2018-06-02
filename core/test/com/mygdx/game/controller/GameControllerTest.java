package com.mygdx.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.HeroState;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.utils.Utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameControllerTest extends  GameTest{


    GameModel gameModel;
    GameController gameController;

    @Test
    public void getScore() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);
        assertEquals(gameController.getScore(), 0);

        this.gameController.run();


        assertEquals(0, gameController.getScore());


    }

    @Test
    public void jump() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);

        this.gameController.jump();
        assertEquals(true,gameController.getHeroBody().getBody().getLinearVelocity().y>0);

    }

    @Test
    public void run() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel model = new GameModel();
        gameController = new GameController(map, model);

        this.gameController.run();
        assertEquals(true,gameController.getHeroBody().getBody().getLinearVelocity().x>0);

    }

    @Test
    public void shoot() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        gameModel = new GameModel();
        gameController = new GameController(map, gameModel);

        this.gameController.shoot();
        assertEquals(true,gameModel.getBullets() != null);

    }

    @Test
    public void removeBody() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        gameModel = new GameModel();
        gameController = new GameController(map, gameModel);
        int originalGoldNumber = gameModel.getGolds().size();

        gameModel.getGolds().add(new GoldModel(gameModel.getHero().getPosition().x, gameModel.getHero().getPosition().y));

        gameController.jump();
        gameController.update();

        System.out.println("originalGoldNumber= "+originalGoldNumber);
        System.out.println("gameModel.getGolds().size()= "+gameModel.getGolds().size());


        assertEquals(true,gameModel.getGolds().size() < originalGoldNumber);

    }
}