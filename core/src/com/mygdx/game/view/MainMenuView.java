package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;

import static com.mygdx.game.view.GameView.PIXEL_TO_METER;


public class MainMenuView implements Screen {
    private RunnerGame game;
    private OrthographicCamera menuCamera;
    private Viewport menuPort;

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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        drawButtons();

        game.getBatch().end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameView(this.game));
            dispose();
        }

    }

    public void loadMenuAssets(){

        this.game.getAssetManager().load("start_game.png", Texture.class);

        this.game.getAssetManager().finishLoading();

    }

    public void drawButtons(){
        Texture startButton = game.getAssetManager().get("start_game.png", Texture.class);
        game.getBatch().draw(startButton,25,50);


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
