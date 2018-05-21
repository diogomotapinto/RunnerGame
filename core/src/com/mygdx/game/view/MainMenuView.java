package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;


public class MainMenuView implements Screen {
    private RunnerGame game;
    private OrthographicCamera menuCamera;


    public MainMenuView(RunnerGame game) {
        this.game = game;
        menuCamera = new OrthographicCamera();
        menuCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);

        loadMenuAssets();


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        menuCamera.update();
        game.getBatch().setProjectionMatrix(menuCamera.combined);

        Gdx.gl.glClearColor(0, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        drawButtons();

        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameView(this.game));
            dispose();
        }

    }

    public void loadMenuAssets(){

        this.game.getAssetManager().load("playButton.jpg", Texture.class);

        this.game.getAssetManager().finishLoading();

    }

    public void drawButtons(){
        Texture startButton = game.getAssetManager().get("playButton.jpg", Texture.class);

        game.getBatch().draw(startButton, GameController.V_WIDTH/2-startButton.getWidth()/2,GameController.V_HEIGHT/2);


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
