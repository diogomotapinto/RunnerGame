package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameServices;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;

public class GameOverScreen implements Screen {
    OrthographicCamera overCamera;
    RunnerGame game;
    GameServices gameServices;

    public GameOverScreen(RunnerGame game) {
        this.game = game;
        overCamera = new OrthographicCamera();
        overCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);
        //gameServices = GameServices();
        loadMenuAssets();


    }
    public void loadMenuAssets(){

        this.game.getAssetManager().load("gameOver.jpg", Texture.class);

        this.game.getAssetManager().finishLoading();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        overCamera.update();
        game.getBatch().setProjectionMatrix(overCamera.combined);

        Gdx.gl.glClearColor(0, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        drawButtons();

        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            //game.setScreen(new GameView(new RunnerGame(gameServices)));
            dispose();
        }

    }

    public void drawButtons(){
        Texture startButton = game.getAssetManager().get("gameOver.jpg", Texture.class);

        game.getBatch().draw(startButton, GameController.V_WIDTH/2-startButton.getWidth()/2,GameController.V_HEIGHT/4);


    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
