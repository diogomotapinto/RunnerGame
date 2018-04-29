package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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
    private World world;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    public GameView(RunnerGame game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new FitViewport(GameController.V_WIDTH, GameController.V_HEIGHT, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        GameController.getInstance().setCameraPosition(gamePort.getWorldWidth() / 2);
        gameCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        gameHud = new GameHUD(game.getBatch());

        boxDebug = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -10), true);


    }

    public void handleInput(float delta) {

        if ((Gdx.input.getAccelerometerY() > 1)) {
            GameController.getInstance().setCameraPosition(100 * Gdx.input.getAccelerometerY() / 100);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            float f = GameController.getInstance().getCameraPosition();
            GameController.getInstance().setCameraPosition(f += 100 * delta);
            gameHud.update(delta, GameController.getInstance().getCameraPosition());

        }

        if (Gdx.input.getAccelerometerY() < -1) {
            float f = GameController.getInstance().getCameraPosition();
            GameController.getInstance().setCameraPosition(f -= 100 * delta);
        }

    }

    public void update(float delta) {
        handleInput(delta);
        gameCamera.position.x = GameController.getInstance().getCameraPosition();
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        boxDebug.render(GameController.getInstance().getWorld(), gameCamera.combined);
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

    public TiledMap getMap() {
        return map;
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
