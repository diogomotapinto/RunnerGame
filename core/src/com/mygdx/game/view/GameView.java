package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.HeroState;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.view.entities.EntityView;
import com.mygdx.game.view.entities.ViewFactory;

import java.util.ArrayList;

/**
 * Screen for the game to be played in
 */
public class GameView implements Screen {

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 100f;

    /**
     * Minimum position for the camera
     */
    private final static int MIN_MAP = 200;

    /**
     * Max position for the camera
     */
    private final static int MAX_MAP = 3640;

    /**
     * The game this screen belongs to
     */
    private final RunnerGame game;

    /*
     * Game controller instance
     */
    private final GameController gameController;

    /**
     * Game model instance
     */
    private final GameModel gameModel;


    /**
     * Orthographic Camera of the game
     */
    private final OrthographicCamera gameCamera;

    /**
     * Viewport used for the game
     */
    private final Viewport gamePort;

    /**
     * TiledMap used for the floor and the background of the game
     */
    private final TiledMap map;

    /**
     * TileMap renderer
     */
    private final OrthogonalTiledMapRenderer renderer;

    /**
     * Instance of the game HUD that gives information like time passed and Score
     */
    private final GameHUD gameHud;

    /**
     *
     */
    private float seconds = 0f;


    /**
     * Map width in pixels
     */
    private int mapPixelWidth;

    /**
     * Map height in pixels
     */
    private int mapPixelHeight;


    /**
     * Class constructor
     *
     * @param game The game this screen belongs to
     */
    public GameView(RunnerGame game, GameController gameController, GameModel gameModel) {
        this.game = game;


        gameCamera = new OrthographicCamera();
        gamePort = new StretchViewport(GameController.V_WIDTH / PIXEL_TO_METER, GameController.V_HEIGHT / PIXEL_TO_METER, gameCamera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");

        this.gameController = new GameController(map, gameModel);
        this.gameModel = gameModel;

        renderer = new OrthogonalTiledMapRenderer(map, 1 / PIXEL_TO_METER);

        gameController.setCameraPosition(gamePort.getWorldWidth() / 2);
        gameCamera.position.set(gameController.getHeroBody().getX(), gamePort.getWorldHeight() / 2, 0);


        gameHud = new GameHUD(game.getBatch());
        loadAssets();
        mapProperties();

    }


    /**
     * Loads the map properties
     */
    private void mapProperties() {
        MapProperties properties = map.getProperties();
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);
        int tilePixelWidth = properties.get("tilewidth", Integer.class);
        int tilePixelHeight = properties.get("tileheight", Integer.class);

        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;
    }


    /**
     * Handles the inputs from the devices
     */
    private void handleInput() {

        if ((Gdx.input.getAccelerometerY() > 1)) {
            gameController.run();
            gameHud.update(gameController.getCameraPosition(), seconds);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameController.run();
        }


        if ((Gdx.input.isKeyJustPressed(Input.Keys.UP) || (Gdx.input.justTouched() && Gdx.input.getX() < Gdx.graphics.getWidth() / 2) && this.mapPixelHeight > gameController.getHeroBody().getX())) {
            gameController.jump();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) || (Gdx.input.justTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() / 2)) {
            gameController.shoot();
        }

    }


    /**
     * Updates what is in the screen
     */
    private void update() {
        handleInput();
        gameController.update();

        if (gameController.getHeroBody().getX() * PIXEL_TO_METER >= MIN_MAP && gameController.getHeroBody().getX() * PIXEL_TO_METER < MAX_MAP) {
            gameCamera.position.set(gameController.getHeroBody().getX(), gamePort.getWorldHeight() / 2, 0);
        }

        seconds += Gdx.graphics.getRawDeltaTime();
        gameHud.update(gameController.getScore(), seconds);
        gameCamera.update();
        renderer.setView(gameCamera);

        if ((gameModel.getHeroState().getState() == HeroState.State.DEAD)
                || gameController.getHeroBody().getBody().getPosition().x * PIXEL_TO_METER > mapPixelWidth
                ) {
            game.setScreen(new GameOverScreen(this.game, this.gamePort, gameModel, gameController));
            game.getGameServices().submitScore(gameController.getScore());
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameController.removeBody();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        game.getBatch().setProjectionMatrix(gameCamera.combined);
        game.getBatch().begin();
        drawEntities();
        game.getBatch().end();


        if (this.gameHud.isPause()) {
            pause();
        } else {
            resume();
            update();
        }

        game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);
        gameHud.stage.draw();
    }

    /**
     * loads the images to be used in this screen
     */
    private void loadAssets() {
        this.game.getAssetManager().load("gold.png", Texture.class);
        this.game.getAssetManager().load("hello.png", Texture.class);
        this.game.getAssetManager().load("enemy.png", Texture.class);
        this.game.getAssetManager().load("bullet.png", Texture.class);
        this.game.getAssetManager().load("pauseButton.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }


    /**
     * Draws the entities
     */
    private void drawEntities() {
        ArrayList<GoldModel> goldList = gameModel.getGolds();
        for (GoldModel gold : goldList) {

            EntityView view = ViewFactory.makeView(game, gold);
            view.update(gold);
            view.draw(game.getBatch());
        }

        ArrayList<BulletModel> bulletList = gameModel.getBullets();
        for (BulletModel bullet : bulletList) {

            EntityView view = ViewFactory.makeView(game, bullet);
            view.update(bullet);
            view.draw(game.getBatch());
        }


        HeroModel heroModel = gameModel.getHero();
        EntityView view = ViewFactory.makeView(game, heroModel);

        view.update(heroModel);
        view.draw(game.getBatch());


        EntityModel enemyModel = gameModel.getEnemy();
        EntityView enemyView = ViewFactory.makeView(game, enemyModel);

        enemyView.update(enemyModel);
        enemyView.draw(game.getBatch());

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
        gameHud.getStage().dispose();
    }
}
