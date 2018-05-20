package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.entities.BulletBody;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.entities.BulletModel;
import com.mygdx.game.model.entities.EntityModel;
import com.mygdx.game.model.entities.GoldModel;
import com.mygdx.game.model.entities.HeroModel;
import com.mygdx.game.view.entities.EntityView;
import com.mygdx.game.view.entities.HeroView;
import com.mygdx.game.view.entities.ViewFactory;
import java.util.ArrayList;




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
    public final static float PIXEL_TO_METER = 100f;

    public GameView(RunnerGame game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        gamePort = new StretchViewport(GameController.V_WIDTH / PIXEL_TO_METER, GameController.V_HEIGHT / PIXEL_TO_METER, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mapa.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PIXEL_TO_METER);

        GameController.getInstance().setCameraPosition(gamePort.getWorldWidth() / 2);

        gameCamera.position.set(GameController.getInstance().getHeroBody().getX(), gamePort.getWorldHeight() / 2, 0);

        gameHud = new GameHUD(game.getBatch());
        boxDebug = new Box2DDebugRenderer();
        loadAssets();

    }

    public void handleInput(float delta) {

        if ((Gdx.input.getAccelerometerY() > 1)) {
            GameController.getInstance().run(delta);
            gameHud.update(delta,  GameController.getInstance().getCameraPosition(), seconds);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            GameController.getInstance().run(delta);

        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) || ( Gdx.input.justTouched() &&  Gdx.input.getX()< Gdx.graphics.getWidth()/2 )){
            System.out.println("Jump " +Gdx.input.getX());
            GameController.getInstance().jump(delta);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.S) || (Gdx.input.justTouched() && Gdx.input.getX()> Gdx.graphics.getWidth()/2)){
            System.out.println("Shoot " +Gdx.input.getX());
            GameController.getInstance().shoot();
        }

    }

    public void update(float delta) {
        handleInput(delta);
        GameController.getInstance().update(delta);

        //gameCamera.position.x =  GameController.getInstance().getHeroBody().getX();
        gameCamera.position.set( GameController.getInstance().getHeroBody().getX() , gamePort.getWorldHeight() / 2, 0);
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
        game.getBatch().setProjectionMatrix(gameCamera.combined);
        //game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);
        game.getBatch().begin();
        drawEntities();
        game.getBatch().end();


        game.getBatch().setProjectionMatrix(gameHud.stage.getCamera().combined);

        gameHud.stage.draw();

        boxDebug.render( GameController.getInstance().getWorld(), gameCamera.combined);


        if(GameController.getInstance().getHeroBody().getBody().getPosition().y < 0){
            game.setScreen(new GameOverScreen(this.game, this.gamePort));
        }
    }

    private void loadAssets(){
        this.game.getAssetManager().load("sonic.png", Texture.class);
        this.game.getAssetManager().load("newcoin.png", Texture.class);
        this.game.getAssetManager().load("gold.png", Texture.class);
        this.game.getAssetManager().load("hello.png", Texture.class);
        this.game.getAssetManager().load("enemy.png", Texture.class);
        this.game.getAssetManager().load("bullet.png", Texture.class);
        this.game.getAssetManager().finishLoading();
    }

    private void drawEntities(){
        ArrayList<GoldModel> goldList = GameModel.getInstance().getGolds();
        for (GoldModel gold : goldList){

            EntityView view = ViewFactory.makeView(game,gold);
            view.update(gold);
            view.draw(game.getBatch());
        }

        ArrayList<BulletModel> bulletList = GameModel.getInstance().getBullets();
        for (BulletModel bullet : bulletList){

            EntityView view = ViewFactory.makeView(game,bullet);
            view.update(bullet);
            view.draw(game.getBatch());
        }




        HeroModel heroModel = GameModel.getInstance().getHero();
        EntityView view = ViewFactory.makeView(game, heroModel);

        view.update(heroModel);
        view.draw(game.getBatch());


        EntityModel enemyModel = GameModel.getInstance().getEnemy();
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
    }
}
