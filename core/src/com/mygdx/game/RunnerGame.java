package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.view.MainMenuView;


public class RunnerGame extends Game {
    private final GameServices gameServices;
    private SpriteBatch batch;
    private AssetManager assetManager;


    public RunnerGame(GameServices gameServices) {
        this.gameServices = gameServices;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("mapa.tmx");
        GameModel gameModel = new GameModel();
        setScreen(new MainMenuView(this, new GameController(map, gameModel, this.gameServices), gameModel));
        //setScreen(new GameView(this));
    }

    @Override
    public void render() {
        super.render();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public GameServices getGameServices() {
        return gameServices;
    }

}
