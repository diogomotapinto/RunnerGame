package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


public class GameScreen implements Screen {
    private RunnerGame game;
    private OrthographicCamera gameCamera;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameHUD gameHud;

    public GameScreen(RunnerGame game){
        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(RunnerGame.V_WIDTH,RunnerGame.V_HEIGHT,gameCamera);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        gameHud = new GameHUD(game.getBatch());

    }

    public void handleInput(float delta){



        if( (Gdx.input.getAccelerometerY() > 1) ) {
            gameCamera.position.x += 100 * Gdx.input.getAccelerometerY()/100;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gameCamera.position.x += 100 * delta;
        }

        gameHud.update(delta,  gameCamera.position.x);

        if(Gdx.input.getAccelerometerY() < -1){
            gameCamera.position.x -= 100 * delta;
        }

        if(Gdx.input.isTouched()){

        }


        if(gameCamera.position.x > 3642){
            gameCamera.position.x = 200;
        }

    }

    public void update(float delta){
        handleInput(delta);
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);
        gameHud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
