package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.MainMenuView;


public class RunnerGame extends Game {
    private SpriteBatch batch;
    private AssetManager assetManager;
    private final GameServices gameServices;


    public RunnerGame(GameServices gameServices) {
        this.gameServices = gameServices;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        setScreen(new MainMenuView(this));
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
