package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameServices;
import com.mygdx.game.RunnerGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.GameModel;

import javax.swing.text.View;

public class GameOverScreen extends Stage implements Screen {
    OrthographicCamera overCamera;
    RunnerGame game;
    GameServices gameServices;
    private Table table;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle textButtonStyle;
//    private  TextButton button;
    private Stage stage;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    public GameOverScreen(RunnerGame game, Viewport viewport) {
        this.game = game;
        this.table = new Table();
        //this.stage = new Stage(viewport, this.game.getBatch());



        addRestartBtn();
        overCamera = new OrthographicCamera();
        overCamera.setToOrtho(false, GameController.V_WIDTH, GameController.V_HEIGHT);
        //gameServices = GameServices();
        //addRestartBtn();
        //loadMenuAssets();

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

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            //game.setScreen(new GameView(new RunnerGame(gameServices)));
            dispose();
        }

    }

    public void drawButtons(){
        //Texture startButton = game.getAssetManager().get("gameOver.jpg", Texture.class);
        //game.getBatch().draw(startButton, GameController.V_WIDTH/2-startButton.getWidth()/2,GameController.V_HEIGHT/4);
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




    protected void addRestartBtn() {

        myTexture = new Texture(Gdx.files.internal("gameOver.jpg"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //GameController.getInstance().getHeroBody().setTransform(200,25,0);
                GameModel model = GameModel.getInstance().newGameModel();
                GameModel.getInstance().newInstance(model);
                GameController controller = GameController.getInstance().newGameContoller();
                GameController.getInstance().newInstance(controller);
                MainMenuView view = new MainMenuView(game);
                game.setScreen(view);
                dispose();
            }
        });

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

    }

    public Skin getSkin() {
        return skin;
    }
}
