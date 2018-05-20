package com.mygdx.game.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;

import javax.swing.text.View;

public class GamePausedScreen extends Stage implements Screen {
    private RunnerGame game;
    private Viewport viewport;

    public GamePausedScreen(RunnerGame game, Viewport viewport) {
        this.game = game;
        this.viewport = viewport;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
