package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;



public class GameView implements Screen {
    private Box2DDebugRenderer boxDebug;
    private RunnerGame game;
    private OrthographicCamera gameCamera;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private GameHUD gameHud;
    private float seconds = 0f;


    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 100;

    public GameView(RunnerGame game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(GameController.V_WIDTH / PIXEL_TO_METER, GameController.V_HEIGHT / PIXEL_TO_METER, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PIXEL_TO_METER);

        GameController.getInstance().setCameraPosition(gamePort.getWorldWidth() / 2);

        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        gameHud = new GameHUD(game.getBatch());
        boxDebug = new Box2DDebugRenderer();


    }

    public void handleInput(float delta) {

        if ((Gdx.input.getAccelerometerY() > 1)) {
            GameController.getInstance().run(delta);
            gameHud.update(delta,  GameController.getInstance().getCameraPosition(), seconds);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            GameController.getInstance().run(delta);

        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.justTouched()){
            GameController.getInstance().jump(delta);
        }
    }

    public void update(float delta) {
        handleInput(delta);
        GameController.getInstance().update(delta);
        gameCamera.position.x =  GameController.getInstance().getHeroBody().getX();
        seconds +=Gdx.graphics.getRawDeltaTime();
        gameHud.update(delta,  GameController.getInstance().getHeroBody().getX(), seconds);
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        GameController.getInstance().removeBody();
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);
        gameHud.stage.draw();
        boxDebug.render( GameController.getInstance().getWorld(), gameCamera.combined);
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
