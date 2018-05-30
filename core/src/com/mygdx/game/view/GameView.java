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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.view.entities.EntityView;
import com.mygdx.game.view.entities.ViewFactory;

import java.util.ArrayList;


public class GameView implements Screen {
    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 100f;
    //private Box2DDebugRenderer boxDebug;
    private final RunnerGame game;
    private final GameController gameController;
    private final GameModel gameModel;

    private final OrthographicCamera gameCamera;
    private final Viewport gamePort;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final GameHUD gameHud;
    private float seconds = 0f;
    private boolean pause;
    private int mapPixelWidth;
    private int mapPixelHeight;


    /**
     * Class constructor
     * @param game The game this screen belongs to
     */
    public GameView(RunnerGame game) {
        this.game = game;
        gameController= GameController.getInstance();
        gameModel = GameModel.getInstance();
        gameCamera = new OrthographicCamera();
        gamePort = new StretchViewport(GameController.V_WIDTH / PIXEL_TO_METER, GameController.V_HEIGHT / PIXEL_TO_METER, gameCamera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");

        renderer = new OrthogonalTiledMapRenderer(map, 1 / PIXEL_TO_METER);

        gameController.setCameraPosition(gamePort.getWorldWidth() / 2);


        gameCamera.position.set(gameController.getHeroBody().getX(), gamePort.getWorldHeight() / 2, 0);


        gameHud = new GameHUD(game.getBatch());
        pause = gameHud.isPause();
   //     Box2DDebugRenderer boxDebug = new Box2DDebugRenderer();
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
     * @param delta
     */
    private void handleInput(float delta) {

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
     * @param delta the delta time in seconds between screens
     */
    private void update(float delta) {
        handleInput(delta);
        gameController.update();

        if (gameController.getHeroBody().getX() * PIXEL_TO_METER >= 200 && gameController.getHeroBody().getX() * PIXEL_TO_METER < 3640) {
            gameCamera.position.set(gameController.getHeroBody().getX(), gamePort.getWorldHeight() / 2, 0);
        }

        seconds += Gdx.graphics.getRawDeltaTime();
        gameHud.update(gameController.getScore(), seconds);
        gameCamera.update();
        renderer.setView(gameCamera);
        //game.getGameServices().submitScore(gameController.getScore());


        if (gameController.getHeroBody().getBody().getPosition().y < 0) {
            game.setScreen(new GameOverScreen(this.game, this.gamePort));
            dispose();
        }

        if (gameController.getHeroBody().getBody().getPosition().x * PIXEL_TO_METER > mapPixelWidth) {
            System.out.println("Game won");
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


        if (!(pause = this.gameHud.isPause())) {
            resume();
            update(delta);
        } else {
            pause();
        }

        //game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);


        game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);

        gameHud.stage.draw();

        // boxDebug.render( GameController.getInstance().getWorld(), gameCamera.combined);


    }

    /**
     * loads the images to be used in this screen
     */
    private void loadAssets() {
        this.game.getAssetManager().load("sonic.png", Texture.class);
        this.game.getAssetManager().load("newcoin.png", Texture.class);
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


    /**
     * @return the state of the game
     */
    public boolean isPause() {
        return pause;
    }


    /**
     * Sets the state of the game
     * @param pause the state of the game if he is paused or not
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override

    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        game.setScreen(new GamePausedScreen(this.game, this.gamePort));
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
